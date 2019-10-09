package com.example.user.util;

import javax.servlet.http.HttpServletRequest;

public class GetIPUtil {

    public static String getRemoteIPAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
//        if ("0:0:0:0:0:0:0:1".equals(ip)) {
//            ip = "本地";
//        }
        return ip;
    }

}
