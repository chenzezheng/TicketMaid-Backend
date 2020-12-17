package com.whu.ticket.service.impl;

import com.whu.ticket.dao.OrderMapper;
import com.whu.ticket.entity.Order;
import com.whu.ticket.service.OrderService;
import com.whu.ticket.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;

    @Override
    public void addOrder(Order order) {
        orderMapper.insertOrder(order);
    }

    @Override
    public void removeOrder(int id, int userId) {
        orderMapper.deleteByIdAndUserId(id, userId);
    }

    @Override
    public int queryPageNums(int userId, int pageSize) {
        int tot = orderMapper.countByUserId(userId);
        if (tot % pageSize != 0) return tot / pageSize + 1;
        return tot / pageSize;
    }

    @Override
    public List<OrderVO> queryOrder(int userId, int pageNo, int pageSize) {
        if ((pageNo - 1) * pageSize >= orderMapper.countByUserId(userId)) {
            throw new RuntimeException("页码超出范围");
        }
        return orderMapper.selectByUserId(userId, (pageNo - 1) * pageSize, pageSize);
    }

    @Override
    public Order queryOrderInfo(int id, int userId) {
        return orderMapper.selectByIdAndUserId(id, userId);
    }
}
