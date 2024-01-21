package com.tht.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tht.entity.Staff;
import com.tht.mapper.StaffMapper;
import com.tht.service.StaffService;
import org.springframework.stereotype.Service;

/**
 * @description 针对表【staff】的数据库操作Service实现
 * @author tht
 * @createDate 2024-01-21 16:24:32
*/
@Service
public class StaffServiceImpl extends ServiceImpl<StaffMapper, Staff> implements StaffService {

    @Override
    public IPage<Staff> selectStaffPage(IPage<Staff> page, Staff staff) {
        return this.baseMapper.selectStaffPage(page, staff);
    }
}




