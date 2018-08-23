package com.wgf.shop.configure;

import com.wgf.shop.configure.interceptor.LoginInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置拦截器
 * @Author wanggaofeng
 * @Date 2018/8/15 16:50
 */
@Configuration
@AllArgsConstructor
public class WebConfituration implements WebMvcConfigurer {

    private LoginInterceptor loginInterceptor;//自定义拦截器

    /**
     * 将拦截器具体逻辑添加到连接器中
     * @Author wanggaofeng
     * @Date 2018/8/15 17:11
     **/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**")
                // 排除swarger
                .excludePathPatterns("/swagger**", "/v2/api-docs",
                "/swagger-resources/**", "/webjars/**");
                // 排除登录login和注册register
//                .excludePathPatterns("/login", "/regist")
//                .excludePathPatterns("/**");
    }
}
