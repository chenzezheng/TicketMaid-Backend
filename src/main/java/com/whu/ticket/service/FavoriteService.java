package com.whu.ticket.service;

import com.whu.ticket.pojo.Favorite;

import java.util.List;

public interface FavoriteService {
    public List<Favorite> queryFavorites(int userId, int pageNo, int pageSize);
    public void addFavorite(Favorite favorite);
    public void removeFavorite(int id);
}
