package com.whu.ticket.service;

import com.whu.ticket.entity.Order;
import com.whu.ticket.vo.OrderVO;

import java.util.List;

public interface OrderService {
    public void addOrder(Order order);
    public void removeOrder(int id, int userId);
    public int queryPageNums(int userId, int pageSize);
    public List<OrderVO> queryOrder(int userId, int pageNo, int pageSize);
    public Order queryOrderInfo(int id, int userId);
}
