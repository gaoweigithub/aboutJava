package com.gw.mapper;

import com.gw.model.po.User1;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public interface User1Mapper {
    int insert(User1 record);
    User1 selectByPrimaryKey(@Param("id") Integer id);
    //其他方法省略...
}
