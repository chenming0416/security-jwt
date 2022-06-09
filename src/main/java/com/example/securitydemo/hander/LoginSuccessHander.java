package com.example.securitydemo.hander;

import cn.hutool.json.JSONUtil;
import com.example.securitydemo.entity.Result;
import com.example.securitydemo.tools.JwtTokenTools;
import com.sun.net.httpserver.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class LoginSuccessHander implements AuthenticationSuccessHandler {
    @Autowired
    private Result result;
    @Autowired
    private JwtTokenTools jwtTokenTools;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();

        String username = httpServletRequest.getParameter("username");

        String token = jwtTokenTools.generateJwtToken(username);
        Result getresult = result.success(token);
        servletOutputStream.write(JSONUtil.toJsonStr(getresult).getBytes(StandardCharsets.UTF_8));
        servletOutputStream.flush();
        servletOutputStream.close();
    }
}
