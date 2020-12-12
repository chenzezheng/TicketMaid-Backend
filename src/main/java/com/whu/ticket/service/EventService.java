package com.whu.ticket.service;

import com.whu.ticket.pojo.Event;

import java.util.Date;
import java.util.List;

public interface EventService {
    public void addEvent(Event event) throws Exception;
    public void deleteEvent(int id) throws Exception;
    public List<Event> queryEvent(Date time,int pageNo,int pageSize);
    public void modifyEvent(Event event);
}
