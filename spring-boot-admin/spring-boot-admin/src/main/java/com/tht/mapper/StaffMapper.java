package com.tht.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tht.entity.Staff;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @description 针对表【staff】的数据库操作Mapper
 * @author tht
 * @createDate 2024-01-21 16:24:32
 * @Entity com.tht.entity.Staff
*/
public interface StaffMapper extends BaseMapper<Staff> {

    IPage<Staff> selectStaffPage(IPage<Staff> page, @Param("staff") Staff staff);

}




