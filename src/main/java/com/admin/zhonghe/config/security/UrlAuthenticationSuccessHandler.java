package com.admin.zhonghe.config.security;

import com.admin.zhonghe.domain.authority.SpUser;
import com.admin.zhonghe.utils.JwtTokenUtil;
import com.admin.zhonghe.utils.R;
import com.alibaba.fastjson.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class UrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        SpUser spUser = (SpUser)authentication.getPrincipal();
        String token= jwtTokenUtil.generateToken(spUser);
        HttpSession session = request.getSession();
        session.setAttribute("userId",spUser);
        JSONObject object=new JSONObject();
        object.put("token", token);
        object.put("username", spUser.getUsername());
        R r=  R.ok(object);
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(r);
        out.write(jsonObject.toJSONString());
        out.close();
    }
}
