package com.tht.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tht.entity.Staff;

/**
 * @description 针对表【staff】的数据库操作Service
 * @author tht
 * @createDate 2024-01-21 16:24:32
*/
public interface StaffService extends IService<Staff> {

    IPage<Staff> selectStaffPage(IPage<Staff> page, Staff staff);

}
