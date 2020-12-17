package com.whu.ticket.dao;

import com.whu.ticket.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EventRedisDao {
    @Autowired
    RedisUtil redisUtil;

    public Integer getQuota(int eventid) {
        String eventstr = "event_" + String.valueOf(eventid);
        if (redisUtil.hasKey(eventstr)) {
            return (Integer) redisUtil.get(eventstr);
        }
        return null;
    }

    public boolean setQuota(int eventid, Integer quota) {
        return redisUtil.set("event_" + String.valueOf(eventid), quota);
    }

    public void incQuota(int eventid, int delta) {
        if (delta > 0) {
            redisUtil.incr("event_" + String.valueOf(eventid), delta);
        } else {
            redisUtil.decr("event_" + String.valueOf(eventid), -delta);
        }
    }
}
