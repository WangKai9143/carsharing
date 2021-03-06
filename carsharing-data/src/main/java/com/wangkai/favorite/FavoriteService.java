package com.wangkai.favorite;

import com.github.pagehelper.PageHelper;
import com.wangkai.favorite.bean.FavoriteBean;
import com.wangkai.favorite.bean.FavoriteBeanVo;
import com.wangkai.favorite.dao.FavoriteDao;
import com.wangkai.information.bean.InfoBean;
import com.wangkai.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class FavoriteService {
    @Autowired
    private FavoriteDao favoriteDao;

    @Autowired
    private UserService userService;

    private static int pageSize = 5;


    public void addFav(FavoriteBean favoriteBean) {
        int uId = userService.getUId(favoriteBean.getSk());
        favoriteBean.setUid(uId);
        favoriteBean.setTime(System.currentTimeMillis()/1000);
        favoriteDao.addFav(favoriteBean);
    }

    public void delFav(FavoriteBean favoriteBean) {
        int uId = userService.getUId(favoriteBean.getSk());
        favoriteBean.setUid(uId);
        favoriteDao.delFav(favoriteBean);
    }

    public boolean isFav(FavoriteBean favoriteBean) {
        int uId = userService.getUId(favoriteBean.getSk());
        favoriteBean.setUid(uId);
        return favoriteDao.isfav(favoriteBean)>0;
    }

    public List<Map<String,Object>> myFav(Map<String, Object> paramsMap) {
        int uId = userService.getUId(String.valueOf(paramsMap.get("sk")));
        int currPage = 1;
        if (paramsMap != null && !paramsMap.isEmpty()) {
            currPage = Integer.parseInt(paramsMap.get("page").toString());
        }
        PageHelper.startPage(currPage, pageSize);
        List<Map<String,Object>> favoriteList = favoriteDao.myFav(uId);
        return favoriteList;
    }
}
