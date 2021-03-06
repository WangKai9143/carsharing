package com.wangkai.favorite;

import com.wangkai.favorite.bean.FavoriteBean;
import com.wangkai.favorite.bean.FavoriteBeanVo;
import com.wangkai.utils.ResultDataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/fav")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @PostMapping("/addfav")
    public String addFav(FavoriteBean favoriteBean) {
        favoriteService.addFav(favoriteBean);
        return ResultDataUtils.build(200, "操作成功");
    }

    @DeleteMapping("/delfav")
    public String delFav(FavoriteBean favoriteBean) {
        favoriteService.delFav(favoriteBean);
        return ResultDataUtils.build(200, "操作成功");
    }

    @GetMapping("/isfav")
    public String isFav(FavoriteBean favoriteBean){
        boolean result = favoriteService.isFav(favoriteBean);
        return ResultDataUtils.build(200, "操作成功",result);
    }

    @GetMapping("/myFav")
    public String myFav(@RequestParam Map<String,Object> paramsMap){
        List<Map<String,Object>> favoriteBeanList = favoriteService.myFav(paramsMap);
        return ResultDataUtils.build(200, "操作成功",favoriteBeanList);
    }

}
