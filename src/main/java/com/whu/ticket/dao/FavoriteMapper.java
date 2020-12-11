package com.whu.ticket.dao;

import com.whu.ticket.pojo.Favorite;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteMapper {
    int countByUserId(int userId);
    List<Favorite> selectByUserId(int userId, int offset, int limit);
    Favorite selectByUserIdAndEventId(int userId, int eventId);
    void insertFavorite(Favorite favorite);
    void deleteById(int id);
}
