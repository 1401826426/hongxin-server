package com.taotao.manage.mapper;

import com.taotao.manage.pojo.OrderDetailName;
import com.taotao.manage.pojo.OrderDetailNameExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderDetailNameMapper {
    int countByExample(OrderDetailNameExample example);

    int deleteByExample(OrderDetailNameExample example);

    int deleteByPrimaryKey(String name);

    int insert(OrderDetailName record);

    int insertSelective(OrderDetailName record);

    List<OrderDetailName> selectByExample(OrderDetailNameExample example);

    int updateByExampleSelective(@Param("record") OrderDetailName record, @Param("example") OrderDetailNameExample example);

    int updateByExample(@Param("record") OrderDetailName record, @Param("example") OrderDetailNameExample example);
}