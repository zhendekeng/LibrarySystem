package cn.zzy.library_web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import cn.zzy.library_web.annotation.PassToken;
import cn.zzy.library_web.annotation.UserLoginToken;
import cn.zzy.library_web.entity.User;
import cn.zzy.library_web.error.ResponseData;
import cn.zzy.library_web.jwt.JWTHS256;

import cn.zzy.library_web.service.UserService;
import com.alibaba.fastjson.JSON;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Method;


public class InterceptorDemo implements HandlerInterceptor {
    private static final Logger LOG = Logger.getLogger(InterceptorDemo.class);

    @Autowired
    UserService userService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        ResponseData responseData = null;
        // 获取请求头中的token验证字符串
        String headerToken = request.getHeader("token");
//        try{
//            HandlerMethod handlerMethod = (HandlerMethod) handler;
//            LOG.info("当前拦截的方法为：{}" + handlerMethod.getMethod());
//            LOG.info("当前拦截的方法参数长度为：{}" + handlerMethod.getMethod().getParameters().length);
//            LOG.info("当前拦截的方法为：{}" + handlerMethod.getBean().getClass().getName());
//            System.out.println("开始拦截---------");
//            String uri = request.getRequestURI();
//            System.out.println("拦截的uri："+uri);
//        }catch (Exception e){
//
//        }

        // 如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        JWTHS256 jwths256 = new JWTHS256();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                jwths256.updateToken(headerToken);
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (headerToken == null) {
                    responseData = ResponseData.unauthorized();
                }
                else {
                    try {
                        responseData = jwths256.verifyToken(headerToken);
                        if (responseData == null){
                            jwths256.updateToken(headerToken);
                        }
                    }catch (Exception e){
                        System.out.println(e);
                        responseData = ResponseData.serverInternalError();
                    }
                }

            }
        }

        // 如果有错误信息
        if (responseData != null) {
            response.getWriter().write(JSON.toJSONString(responseData));
            return false;
        } else {
            // 将token加入返回的header中
            response.setHeader("token", headerToken);
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub

    }

}