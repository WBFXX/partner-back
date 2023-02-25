package com.partner.boot.common;

import cn.hutool.http.HttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 并发限流算法
 */
@Component
@Slf4j
public class MyFilter implements Filter {

    //时间窗口
    //1秒之内只允许通过2个请求,
    // volatile:1、保证变量的可见性，禁止指令重排序 2、更轻量型的同步操作 3、适合多线程公用一个变量的场景
    //普通线程操作变量时，先将变量拷贝到cpu的cache缓存中，每个线程再对变量进行不同的处理，volatile使线程直接对内存操作，保证变量原子性
    public static volatile long startTime = System.currentTimeMillis() ;

    public static final long windowTime = 1000L;
    //桶
    public static final AtomicInteger bear = new AtomicInteger(0);

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
                if ( count > 2) {//如果大于2，关门放狗
                    return;
                }
            }else {
                //重新进入下一个窗口
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
