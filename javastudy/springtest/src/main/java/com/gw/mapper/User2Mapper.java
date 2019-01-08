package com.gw.mapper;

import com.gw.pojo.User2;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public interface User2Mapper {
    int insert(User2 record);
    User2 selectByPrimaryKey(@Param("id")Integer id);
    //其他方法省略...
}
