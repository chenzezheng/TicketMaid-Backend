package com.whu.ticket.service;

import com.whu.ticket.entity.Order;

public interface DBAccessService {
    public void consumeMessage();
    public void deleteOrder(Order order);
}
