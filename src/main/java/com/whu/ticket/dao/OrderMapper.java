package com.whu.ticket.dao;

import com.whu.ticket.entity.Order;
import com.whu.ticket.vo.OrderVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {
    void insertOrder(Order order);
    void deleteByIdAndUserId(int id, int userId);
    int countByUserId(int userId);
    List<OrderVO> selectByUserId(int userId, int offset, int limit);
    Order selectByIdAndUserId(int id, int userId);
    Order selectById(int id);
}
