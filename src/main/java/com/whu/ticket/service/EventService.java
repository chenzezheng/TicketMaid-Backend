package com.whu.ticket.service;

import com.whu.ticket.pojo.Event;

import java.util.Date;
import java.util.List;

public interface EventService {
    public void addEvent(Event event) throws Exception;
    public void deleteEvent(int id,int host_id) throws Exception;
    public List<Event> queryEvent(Date time,int pageNo,int pageSize);
    public void modifyEvent(Event event);
    public List<Event> queryAdminEvent(int host_id,int pageNo,int pageSize);
}
