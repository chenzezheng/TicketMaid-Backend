package com.whu.ticket.dao;

import com.whu.ticket.vo.FavoriteVO;
import com.whu.ticket.pojo.Favorite;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteMapper {
    int countByUserId(int userId);
    List<FavoriteVO> selectByUserId(int userId, int offset, int limit);
    Favorite selectByUserIdAndEventId(int userId, int eventId);
    void insertFavorite(Favorite favorite);
    void deleteByIdAndUserId(int id, int userId);
}
