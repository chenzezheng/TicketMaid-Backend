package com.whu.ticket.service;

import com.whu.ticket.vo.FavoriteVO;
import com.whu.ticket.entity.Favorite;

import java.util.List;

public interface FavoriteService {
    public List<FavoriteVO> queryFavorites(int userId, int pageNo, int pageSize);
    public void addFavorite(Favorite favorite);
    public void removeFavorite(int id, int userId);
}
