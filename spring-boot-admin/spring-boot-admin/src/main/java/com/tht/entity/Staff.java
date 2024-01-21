package com.tht.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import com.tht.utils.ListVar;
import com.tht.validations.StaffValid;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @author tht
 * @TableName staff
 */
@TableName(value ="staff")
@Data
public class Staff implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    @NotNull(message = "id不能为空", groups = {StaffValid.update.class, StaffValid.delete.class})
    @Null(message = "ID必须为空", groups = {StaffValid.insert.class})
    @ExcelProperty("序号")
    private Integer id;

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空", groups = {StaffValid.insert.class})
    @ExcelProperty("姓名")
    private String empName;

    /**
     * 性别
     */
    @NotBlank(message = "性别不能为空", groups = {StaffValid.insert.class})
    @ListVar(message = "性别不正确", values = {"男", "女"}, groups = {StaffValid.insert.class, StaffValid.update.class})
    @ExcelProperty("性别")
    private String sex;

    /**
     * 年龄
     */
    @NotNull(message = "年龄不能为空", groups = {StaffValid.insert.class})
    @ExcelProperty("年龄")
    private Integer age;

    /**
     * 部门
     */
    @NotBlank(message = "部门不能为空", groups = {StaffValid.insert.class})
    @ListVar(message = "部门不正确", values = {"业务部", "后勤部", "人事部"}, groups = {StaffValid.insert.class, StaffValid.update.class})
    @ExcelProperty("部门")
    private String deptName;

    /**
     * 学历
     */
    @NotBlank(message = "学历不能为空", groups = {StaffValid.insert.class})
    @ListVar(message = "学历不正确", values = {"大专", "本科", "研究生"}, groups = {StaffValid.insert.class, StaffValid.update.class})
    @ExcelProperty("学历")
    private String empDegreeName;

}