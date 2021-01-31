package com.wangkai.user.controller;

/**
 * Created by Administrator on 2019/11/27.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wangkai.utils.HttpClientUtil;
import com.wangkai.utils.ResultDataUtils;
import com.wangkai.utils.WechatGetUserInfoUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RequestMapping("/user")
@RestController
public class UserController {
    //    @Value("${wx.appid}")
    private String appid;

    //    @Value("${wx.appSecret}")
    private String appSecret;

    /**
     * 登录接口
     *
     * @param encryptedData
     * @param iv
     * @param code
     * @return
     */
    @GetMapping("/login")
    public String login(String encryptedData, String iv, String code) {
        if (StringUtils.isEmpty(code)) {
            return ResultDataUtils.build(202, "未获取到用户凭证code");
        }
        if (StringUtils.isEmpty(appid)) {
            appid = "wx7315731f7da2477e";
        }
        if (StringUtils.isEmpty(appSecret)) {
            appSecret = "ef517b41546112236b858748d935ad75";
        }
        String apiUrl = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid + "&secret=" + appSecret + "&js_code=" + code + "&grant_type=authorization_code";
        System.out.println(apiUrl);
        String responseBody = HttpClientUtil.doGet(apiUrl);
        System.out.println(responseBody);
        JSONObject jsonObject = JSON.parseObject(responseBody);
        if (!StringUtils.isEmpty(jsonObject.getString("openid")) && !
                StringUtils.isEmpty(jsonObject.getString("session_key"))) {
            //解密获取用户信息
            JSONObject userInfoJSON = WechatGetUserInfoUtil.getUserInfo(encryptedData, jsonObject.getString("session_key"), iv);
            if (userInfoJSON != null) {
                //这步应该set进实体类
                Map userInfo = new HashMap();
                userInfo.put("openId", userInfoJSON.get("openId"));
                userInfo.put("nickName", userInfoJSON.get("nickName"));
                userInfo.put("gender", userInfoJSON.get("gender"));
                userInfo.put("city", userInfoJSON.get("city"));
                userInfo.put("province", userInfoJSON.get("province"));
                userInfo.put("country", userInfoJSON.get("country"));
                userInfo.put("avatarUrl", userInfoJSON.get("avatarUrl"));
                // 解密unionId & openId;
                if (userInfoJSON.get("unionId") != null) {
                    userInfo.put("unionId", userInfoJSON.get("unionId"));
                }
                //然后根据openid去数据库判断有没有该用户信息，若没有则存入数据库，有则返回用户数据
                Map<String, Object> dataMap = new HashMap<String, Object>();
                dataMap.put("userInfo", userInfo);
                String uuid = UUID.randomUUID().toString();
                dataMap.put("WXTOKEN", uuid);
                String result = ResultDataUtils.build(200, "登陆成功", dataMap);
                return result;
            } else {
                return ResultDataUtils.build(202, "解密失败");
            }
        } else {
            return ResultDataUtils.build(202, "未获取到用户openid 或 session");
        }
    }


}
