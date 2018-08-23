package com.wgf.shop.configure.interceptor;

import com.wgf.shop.configure.annotation.CheckLogin;
import com.wgf.shop.modules.ResponseObject;
import com.wgf.shop.modules.TokenModule;
import com.wgf.shop.repository.TokenRepository;
import lombok.AllArgsConstructor;
import net.sf.json.JSONObject;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Optional;

/**
 * 登录拦截器
 * @Author wanggaofeng
 * @Date 2018/8/15 16:48
 */
@Component
@AllArgsConstructor
public class LoginInterceptor implements HandlerInterceptor{

    private TokenRepository tokenRepository;

    /**
     * 登录拦截器具体实现
     * 该方法会在请求到达controller前执行
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
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        CheckLogin checkLogin = handlerMethod.getMethodAnnotation(CheckLogin.class);//获取访问的controller是否需要登录
        if(checkLogin == null){//不需要登录
            return true;
        }else{
            /**
             * 需要登录
             * 从cookie中获取token，判断token的有效性
             * 从数据库根据token查询数据
             * 判断account是否能匹配
             * cookie在跨域使用ajax的时候获取不到值，暂时使用token在参数中使用
             */
//            Cookie[] cookies = request.getCookies();
            String token = request.getParameter("token");
//            String accountId = "";
            if(!"".equals(token) && token != null && !"".equals(token.trim())){//token不为空判断token是否有效
                TokenModule myToken = this.tokenRepository.findByToken(token);
                if(myToken == null){
//                    response.new ResponseObject().auth("请登录",null);
                    return false;
                }else{
                    /**
                     * 判断token是否在有效期中
                     */
                    if(myToken.getOverdueTime().before(new Timestamp(System.currentTimeMillis()))){
                        return false;
                    }else{
                        return true;
                    }
//                    if(myToken.getAccountId() != null && myToken.getAccountId().equals(accountId)){
//                        return true;
//                    }else{
//                        return false;
//                    }
                }
            }
//            if(cookies != null && cookies.length > 0){
//                for(Cookie cookie : cookies){
//                    if("token".equals(cookie.getName())){
//                        token = cookie.getValue();
//                    }
//                    if("accountId".equals(cookie.getName())){
//                        accountId = cookie.getValue();
//                    }
//                }
//            }
            return false;
        }
    }

}
