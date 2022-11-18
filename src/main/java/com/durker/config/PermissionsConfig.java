package com.durker.config;

import com.durker.bean.SysUser;
import com.durker.common.NoLoginException;
import com.durker.common.Permissions;
import com.durker.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class PermissionsConfig implements HandlerInterceptor {

    @Autowired
    private ISysUserService sysUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;

            Method method = handlerMethod.getMethod();
            Permissions permissions = method.getAnnotation(Permissions.class);

            if (permissions == null) { // 访问的方法头上没有加Permissions注解
                return true;
            } else {
                String token = request.getHeader("token");
                SysUser sysUser = sysUserService.getUserInfo(token);
                if (sysUser != null) {
                    return true;
                } else {
                    throw new NoLoginException();
                }
            }
        }
        return true;
    }
}
