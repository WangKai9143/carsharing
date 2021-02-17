package com.wangkai.user.controller;

/**
 * Created by Administrator on 2019/11/27.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wangkai.common.GlobalCache;
import com.wangkai.utils.ObjectUtils;
import com.wangkai.user.bean.UserBean;
import com.wangkai.user.service.UserService;
import com.wangkai.utils.HttpClientUtil;
import com.wangkai.utils.ResultDataUtils;
import com.wangkai.utils.WechatGetUserInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RequestMapping("/user")
@RestController
public class UserController {
    @Value("${wx.appid}")
    private String appid;

    @Value("${wx.appsecret}")
    private String appSecret;

    @Autowired
    private UserService userService;

    /**
     * 登录接口，前端通过wx.login接口获取code传递到后台，后台通过请求api换回session_key和openid，
     * 返回的openid是每个用户唯一的，通过这个 可以匹配 微信（腾讯）的用户 跟 我们的用户，就是我们后台通过openid来判断这个人是谁
     *
     * @param encryptedData
     * @param iv
     * @param code
     * @return
     */
    @GetMapping("/login")
    public String login(String encryptedData, String iv, String code) throws IllegalAccessException {
        if (StringUtils.isEmpty(code)) {
            return ResultDataUtils.build(202, "未获取到用户凭证code");
        }
        String apiUrl = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid + "&secret=" + appSecret + "&js_code=" + code + "&grant_type=authorization_code";
        String responseBody = HttpClientUtil.doGet(apiUrl);
        JSONObject jsonObject = JSON.parseObject(responseBody);
        String openId = jsonObject.getString("openid");
        String session_key = jsonObject.getString("session_key");

        UserBean userBean = userService.getUserInfoByOpenid(openId);
        String result = null;
        if (userBean != null) {
            Map<String, Object> userResult = new HashMap<>();
            userResult.put("userInfo", userBean);
            userResult.put("sk", session_key);
            GlobalCache.put(session_key, ObjectUtils.objectToMap(userBean));
            result = ResultDataUtils.build(200, "登陆成功", userResult);
        } else if (!StringUtils.isEmpty(openId) && !StringUtils.isEmpty(session_key)) {
            // 解密获取用户信息
            JSONObject userInfoJSON = WechatGetUserInfoUtil.getUserInfo(encryptedData, session_key, iv);
            if (userInfoJSON != null) {
                //这步应该set进实体类,还有phone，vehicle，name
                Map<String, Object> userInfo = new HashMap();
                userInfo.put("openId", userInfoJSON.get("openId"));
                userInfo.put("nickName", userInfoJSON.get("nickName"));
                userInfo.put("gender", userInfoJSON.get("gender"));
                userInfo.put("city", userInfoJSON.get("city"));
                userInfo.put("province", userInfoJSON.get("province"));
                userInfo.put("country", userInfoJSON.get("country"));
                userInfo.put("avatarUrl", userInfoJSON.get("avatarUrl"));
                userInfo.put("language", userInfoJSON.get("language"));
                userInfo.put("name", "游客");
                userService.saveUser(userInfo);
                Map<String, Object> userResult = new HashMap<>();
                userResult.put("userInfo", userInfo);
                userResult.put("sk", session_key);
                GlobalCache.put(session_key, userInfo);
                result = ResultDataUtils.build(200, "登陆成功", userResult);
            } else {
                result = ResultDataUtils.build(202, "请求微信端服务器失败或者解析用户信息失败");
            }
        } else {
            result = ResultDataUtils.build(202, "未获取到用户openid 或 session");
        }
        return result;
    }

}
