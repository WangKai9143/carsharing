package com.wangkai.favorite.dao;

import com.wangkai.favorite.bean.FavoriteBean;
import com.wangkai.favorite.bean.FavoriteBeanVo;

import java.util.List;
import java.util.Map;

public interface FavoriteDao {
     void addFav(FavoriteBean favoriteBean);

     void delFav(FavoriteBean favoriteBean);

     int isfav(FavoriteBean favoriteBean);

     List<Map<String,Object>> myFav(int uid);
}
