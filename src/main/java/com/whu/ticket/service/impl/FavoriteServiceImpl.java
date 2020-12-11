package com.whu.ticket.service.impl;

import com.whu.ticket.vo.FavoriteVO;
import com.whu.ticket.dao.FavoriteMapper;
import com.whu.ticket.pojo.Favorite;
import com.whu.ticket.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    FavoriteMapper favoriteMapper;

    @Override
    public List<FavoriteVO> queryFavorites(int userId, int pageNo, int pageSize) {
        if ((pageNo - 1) * pageSize >= favoriteMapper.countByUserId(userId)) {
            throw new RuntimeException("页码超出范围");
        }
        return favoriteMapper.selectByUserId(userId, (pageNo - 1) * pageSize, pageSize);
    }

    @Override
    public void addFavorite(Favorite favorite) {
        Favorite res = favoriteMapper.selectByUserIdAndEventId(favorite.getUser_id(), favorite.getEvent_id());
        if (res != null) {
            throw new RuntimeException("请勿重复收藏");
        }
        favoriteMapper.insertFavorite(favorite);
    }

    @Override
    public void removeFavorite(int id, int userId) {
        favoriteMapper.deleteByIdAndUserId(id, userId);
    }
}
