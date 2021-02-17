package com.wangkai.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Administrator on 2020/2/22.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BaseBean {
    private String sk;
    private String appid;
    private String appsecret;
}
