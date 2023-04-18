package com.partner.boot.common;

//import cn.dev33.satoken.interceptor.SaInterceptor;
//import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * web配置
 */
@Configuration
public class MyWebMvcConfig extends WebMvcConfigurationSupport {
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器，校验规则为 StpUtil.checkLogin() 登录校验。
        registry.addInterceptor(new SaInterceptor(handle -> StpUtil.checkLogin()))
                //这里配置要拦截的路径
                .addPathPatterns("/**")
                //这里配置不要拦截的路径
                .excludePathPatterns("/", "/login", "/register", "/email", "/password/reset", "/file/download/**","/**/export","/**/import")
                //这里配置不拦截上方的url
                .excludePathPatterns("/swagger**/**", "/webjars/**", "/v3/**", "/doc.html", "/favicon.ico");  // 排除 swagger拦截
    }

    @Override
    /*
    如果访问路径是addResourceHandler中的filepath 这个路径
    那么就映射到访问本地的addResourceLocations 的参数的这个路径上，
    这样就可以让别人访问服务器的本地文件了，比如本地图片或者本地音乐视频什么的。
     */
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        ////访问路径
        registry.
                addResourceHandler("/swagger-ui/**")
                //映射真实路径
                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
                .resourceChain(false);
    }

    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/swagger-ui/", "/swagger-ui/index.html");
    }
}

