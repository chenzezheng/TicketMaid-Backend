package com.whu.ticket.dao;

import com.whu.ticket.entity.Event;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EventMapper {
    void insertEvent(Event event);
    void deleteEvent(int id,int host_id);
    Event selectByName(String name);
    List<Event> selectAllEvent(Date time,int offset,int limit);
    Event selectById(int id);
    void updateEventProfile(Event event);
    List<Event> selectByHostID(int host_id,int offset,int limit);
    int selectQuotaById(int id);
    void updateQuotaById(int id, int quota);
    int countEvent();
}
