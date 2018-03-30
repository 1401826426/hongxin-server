package com.taotao.manage.mapper;

import com.taotao.manage.pojo.OrderInfo;
import com.taotao.manage.pojo.OrderInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderInfoMapper {
	int countByExample(OrderInfoExample example);

	int deleteByExample(OrderInfoExample example);

	int deleteByPrimaryKey(String id);

	int insert(OrderInfo record);

	int insertSelective(OrderInfo record);

	List<OrderInfo> selectByExample(OrderInfoExample example);

	OrderInfo selectByPrimaryKey(String id);

	int updateByExampleSelective(@Param("record") OrderInfo record, @Param("example") OrderInfoExample example);

	int updateByExample(@Param("record") OrderInfo record, @Param("example") OrderInfoExample example);

	int updateByPrimaryKeySelective(OrderInfo record);

	int updateByPrimaryKey(OrderInfo record);

	List<OrderInfo> selectOrders(@Param("st") int st, @Param("num") int num, @Param("userId") String userId,
			@Param("state") int state);

	List<OrderInfo> selectAllOrders(@Param("st") int st, @Param("num") int num, @Param("userId") String userId);

}