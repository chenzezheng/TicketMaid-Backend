package com.whu.ticket.dao;

import com.whu.ticket.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EventRedisDao {
    @Autowired
    RedisUtil redisUtil;

    @Autowired
    EventMapper eventMapper;

    public Integer getQuota(int eventid) {
        String eventstr = "event_" + String.valueOf(eventid);
        if (redisUtil.hasKey(eventstr)) {
            return (Integer) redisUtil.get(eventstr);
        } else {
            int quota = eventMapper.selectQuotaById(eventid);
            setQuota(eventid, quota);
            return quota;
        }
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

    public int consumeQuota(int eventid, int quota) {
        int rest = getQuota(eventid);
        if (quota > rest) return rest - quota;
        incQuota(eventid, -quota);
        return rest - quota;
    }
}
