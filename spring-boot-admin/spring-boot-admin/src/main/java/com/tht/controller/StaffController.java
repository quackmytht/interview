package com.tht.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tht.entity.Staff;
import com.tht.service.StaffService;
import com.tht.utils.Result;
import com.tht.validations.StaffValid;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Objects;

/**
 * @author tht
 * @date 2024-01-21 16:43
 */
@RestController
@RequestMapping("staff")
public class StaffController {

    @Resource
    private StaffService staffService;

    /**
     * 查询
     * @param current 分页参数
     * @param size 分页参数
     * @param staff 条件
     * @return 查询结果
     */
    @GetMapping("page")
    public Result<IPage<Staff>> selectStaffPage(@RequestParam(value="current", required =false, defaultValue ="1") Integer current,
                                                @RequestParam(value="size", required =false, defaultValue ="10") Integer size,
                                                Staff staff) {
        return Result.ok(staffService.selectStaffPage(new Page<>(current, size), staff));
    }

    /**
     * 删除
     * @param staff id
     * @return 成功与否
     */
    @DeleteMapping("delete")
    @Transactional
    public Result<Boolean> deleteStaff(@RequestBody Staff staff) {
        return Result.ok(staffService.removeById(staff.getId()));
    }

    /**
     * 修改
     * @param staff 数据
     * @return 成功与否
     */
    @PostMapping("update")
    @Transactional
    public Result<Boolean> updateStaff(@Validated(StaffValid.update.class) @RequestBody Staff staff) {
        return Result.ok(staffService.updateById(staff));
    }

    /**
     * 新增
     * @param staff 数据
     * @return 成功与否
     */
    @PutMapping("sava")
    @Transactional
    public Result<Boolean> saveStaff(@Validated(StaffValid.insert.class) @RequestBody Staff staff) {
        return Result.ok(staffService.save(staff));
    }

    /**
     * 文件下载（失败了会返回一个有部分数据的Excel）
     */
    @GetMapping("download")
    public void download(HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("员工信息", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

        EasyExcel.write(response.getOutputStream(), Staff.class).sheet("模板").doWrite(staffService.list());
    }

}
