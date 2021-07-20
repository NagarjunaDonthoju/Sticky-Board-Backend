package com.board.boardbackend.interceptor;

import com.board.boardbackend.service.AuthService;
import com.google.gson.JsonObject;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;


@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        String authorizationHeader = request.getHeader("Authorization");

        String idToken = null;
        String uid = null;

        System.out.println(authorizationHeader);
//
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            idToken = authorizationHeader.substring(7);
            uid = authService.getAuthorizedUID(idToken);
        }
        else{
            PrintWriter out = response.getWriter();
            response.setStatus(401);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            final JsonObject responseObj = new JsonObject();
            responseObj.addProperty("message", "Unauthorized access");
            out.print(responseObj);
            out.flush();
            return false;
        }

        request.setAttribute("uid", uid);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
