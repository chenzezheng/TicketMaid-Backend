package com.whu.ticket.controller;

import com.whu.ticket.annotation.AdminLogin;
import com.whu.ticket.pojo.Event;
import com.whu.ticket.pojo.Result;
import com.whu.ticket.service.EventService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
@RequestMapping("/event")
public class EventController {
    private  static final Logger log = LoggerFactory.getLogger(EventController.class);

    @Autowired
    EventService eventService;

    @AdminLogin
    @PostMapping("/add")
    public Result addEvent(HttpServletRequest request) throws ParseException {
        String name = request.getParameter("name");
        int host_id = Integer.parseInt(request.getParameter("host_id"));
        int quota = Integer.parseInt(request.getParameter("quota"));
        String res = request.getParameter("time");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time;
        if(StringUtils.isBlank(res)){
            time = null;
        }
        else{
            time = sdf.parse(res);
        }
        String location = request.getParameter("location");
        String info = request.getParameter("info");
        String cover = request.getParameter("cover");
        double price = Double.parseDouble(request.getParameter("price"));
        Event event = new Event();
        event.setName(name);
        event.setHost_id(host_id);
        event.setQuota(quota);
        event.setTime(time);
        event.setLocation(location);
        event.setInfo(info);
        event.setCover(cover);
        event.setPrice(price);
        try{
            eventService.addEvent(event);
            return new Result(0,null,"添加活动成功");
        }catch(Exception e){
            log.info("addEvent fail");
            return new Result(-1,null,e.getMessage());
        }
    }

    @AdminLogin
    @DeleteMapping("delete")
    public Result deleteEvent(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            eventService.deleteEvent(id);
            return new Result(0, null, "删除活动成功");
        } catch (Exception e) {
            log.info("deleteEvent fail");
            return new Result(0, null, e.getMessage());
        }
    }


    @GetMapping("All")
    public Result queryEvent(HttpServletRequest request){
        int pageNo = Integer.parseInt(request.getParameter("pageNo"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        Date time = new Date();
        eventService.queryEvent(time,pageNo,pageSize);
        return new Result(0,eventService.queryEvent(time,pageNo,pageSize),"查询成功");
    }

    @AdminLogin
    @PutMapping("/modify")
    public Result modifyEventProfile(HttpServletRequest request) throws ParseException {
        int newQuota = Integer.parseInt(request.getParameter("quota"));
        int id = Integer.parseInt(request.getParameter("id"));
        String res = request.getParameter("time");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date newTime;
        if(StringUtils.isBlank(res)){
            newTime = null;
        }
        else{
            newTime = sdf.parse(res);
        }
        String newLocation = request.getParameter("location");
        String newInfo = request.getParameter("info");
        String newCover = request.getParameter("cover");
        Event newEvent = new Event();
        newEvent.setId(id);
        newEvent.setQuota(newQuota);
        newEvent.setCover(newCover);
        newEvent.setInfo(newInfo);
        newEvent.setTime(newTime);
        newEvent.setLocation(newLocation);
        eventService.modifyEventProfile(newEvent);
        return new Result(0,null,"修改活动信息成功");
    }
}
