package com.infinitusint.sdk.oa.sso;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * sso 用户会话类
 * @author zhuzhoucheng
 */
public class SessionService {

    public static final String TOKEN = "Token";
    private static final Logger logger = LoggerFactory.getLogger(SessionService.class);
    private SessionService(){}


    public static String getToken(HttpServletRequest request){
        return request.getParameter(TOKEN);
    }

    /**
     * 获取用户信息
     * @param request
     * @return
     * @throws IOException
     */
    public static AccountInfo  getAccountInfo(HttpServletRequest request) throws IOException {
        final  String token = getToken(request);
        logger.info("token:"+token);
        if(token != null && token.trim().length() > 0){
            final String getInfoUrl = createInfoUrl(token);
            final String body = HttpUtil.get(getInfoUrl);
            logger.info("body:"+body);
            if(body!=null){
                final String decrypt = SecurityDESUtil.decrypt(token, body);
                logger.info("decrypt:"+decrypt);
                return JSON.parseObject(decrypt,AccountInfo.class);
            }
        }
        return null;
    }

    /**
     * 到登录页面
     * @param backUrl
     * @param response
     * @throws IOException
     */
    public static void toLogin(String backUrl, HttpServletResponse response) throws IOException {
        final String loginUrl = createLoginUrl(backUrl);
        response.sendRedirect(loginUrl);
    }

    /**
     * 到登录页面
     * @param request
     * @param response
     * @throws IOException
     */
    public static void toLogin(HttpServletRequest request,HttpServletResponse response) throws IOException {
       final String backUrl = getCurrentUrl(request);
        toLogin(backUrl,response);
    }

    public static String getCurrentUrl(HttpServletRequest request) {
        final  StringBuffer backUrl = request.getRequestURL();
        final String queryString = request.getQueryString();
        if (queryString!=null&&queryString.length()>0){
            backUrl.append("?").append(queryString);
        }
        return backUrl.toString();
    }

    //创建登录url
    private static String createLoginUrl(String backUrl) {
        return ConfigUtil.getSSOUrl()+"?a="+ConfigUtil.getAppID()+"&r="+backUrl;
    }

    //创建用户信息Url
    private static String createInfoUrl(String token) {
        return ConfigUtil.getTokenUrl()+"?AppID="+ConfigUtil.getAppID()+"&Token="+token;
    }

    public static void logout(HttpServletResponse response) throws IOException {
        response.sendRedirect(ConfigUtil.getLogoutUrl());
    }

}
