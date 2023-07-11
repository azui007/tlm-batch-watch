package com.example.demo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.Set;

@Component
public class RequestTrackingInterceptor implements HandlerInterceptor {

    private Set<String> activeRequests = new HashSet<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 在请求开始时，将请求的唯一标识（如请求路径）添加到集合中
        activeRequests.add(request.getRequestURI());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 请求处理完成后，将请求的唯一标识从集合中移除
        activeRequests.remove(request.getRequestURI());
    }

    public Set<String> getActiveRequests() {
        return activeRequests;
    }
}
