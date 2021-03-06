package com.wangkai.favorite.bean;

import com.wangkai.common.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FavoriteBean extends BaseBean {
    private int id;
    private int uid;
    private int iid;
    private Long time;
}
