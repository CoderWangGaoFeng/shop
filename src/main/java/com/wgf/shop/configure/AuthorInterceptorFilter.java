package com.wgf.shop.configure;

import com.wgf.shop.modules.AccountModule;
import com.wgf.shop.modules.vo.LoginRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * token校验过滤器，判读是否登录状态
 * @Author wanggaofeng
 * @Date 2018/8/13 16:54
 */
public class AuthorInterceptorFilter implements HandlerInterceptor{

    /**
     * 请求调用controller前调用
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        /**
         * 如果不是映射到方法直接通过
         */
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

//        AccountModule account = method.getAnnotation(LoginRequest.class);
        return false;
    }

    /**
     * 请求处理之后进行调用，在调用视图层之前调用（controller调用之后）
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 整个请求结束后调用，包括逻辑处理及视图渲染。（主要用于资源清理工作）
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
