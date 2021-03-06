package com.wangkai.favorite.bean;

import com.wangkai.information.bean.InfoBean;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteBeanVo extends FavoriteBean{
    private InfoBean infoBean;
}
