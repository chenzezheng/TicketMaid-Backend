package com.whu.ticket.service.impl;

import com.whu.ticket.dao.EventMapper;
import com.whu.ticket.pojo.Event;
import com.whu.ticket.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    EventMapper eventMapper;

    @Override
    public void addEvent(Event event) {
        Event res = eventMapper.selectByName(event.getName());
        if(res != null){
            throw new RuntimeException("活动已存在");
        }
        eventMapper.insertEvent(event);
    }

    @Override
    public void deleteEvent(int id) {
        Event res = eventMapper.selectById(id);
        if(res != null) {
            eventMapper.deleteEvent(id);
            return;
        }
        throw new RuntimeException("无对应活动");
    }

    @Override
    public List<Event> queryEvent(Date time, int pageNo, int pageSize) {
        return eventMapper.selectAllEvent(time,(pageNo-1)*pageSize,pageSize);
    }

    @Override
    public void modifyEvent(Event event) {
        Event res = eventMapper.selectById(event.getId());
        if(res != null) {
            eventMapper.updateEventProfile(event);
            return;
        }
        throw new RuntimeException("无对应活动");
    }
}
