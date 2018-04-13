package com.taotao.manage.mapper;

import com.taotao.manage.pojo.OrderTracking;
import com.taotao.manage.pojo.OrderTrackingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderTrackingMapper {
    int countByExample(OrderTrackingExample example);

    int deleteByExample(OrderTrackingExample example);

    int deleteByPrimaryKey(String id);

    int insert(OrderTracking record);

    int insertSelective(OrderTracking record);

    List<OrderTracking> selectByExample(OrderTrackingExample example);

    OrderTracking selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") OrderTracking record, @Param("example") OrderTrackingExample example);

    int updateByExample(@Param("record") OrderTracking record, @Param("example") OrderTrackingExample example);

    int updateByPrimaryKeySelective(OrderTracking record);

    int updateByPrimaryKey(OrderTracking record);
}