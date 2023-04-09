package com.partner.boot.common;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 并发限流算法
 */
@Component
@Slf4j
public class MyFilter implements Filter {

    //1秒之内只允许通过2个请求,
    // volatile:1、保证变量的可见性，禁止指令重排序 2、更轻量型的同步操作 3、适合多线程公用一个变量的场景
    //volatile使线程直接对内存操作，保证变量原子性
    //AtomicInteger原子操作类
    public static volatile long startTime = System.currentTimeMillis() ;
    public static final long windowTime = 1000L;
    //桶
    public static final AtomicInteger bear = new AtomicInteger(0);
    public static final int door = 200;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        int count = bear.incrementAndGet();//只要来了一个人就自加1且赋值给count
        if (count == 1) {
            startTime = System.currentTimeMillis();
        }
        //发生了请求
        long now = System.currentTimeMillis();
            if (now - startTime <= windowTime) {
                if ( count > door) {//如果大于2，关门放狗
                    HttpServletResponse response = (HttpServletResponse)servletResponse;
                    response.setStatus(HttpStatus.OK.value());
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
                    response.getWriter().print(JSONUtil.toJsonStr(Result.error("402","接口请求太频繁")));
                    return;
                }
            }else {
                //重新进入下一个时间窗口
                startTime = System.currentTimeMillis();
                bear.set(1);
            }
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        filterChain.doFilter(servletRequest,servletResponse);
        log.info("接口请求路径为，{}",request.getServletPath());

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
