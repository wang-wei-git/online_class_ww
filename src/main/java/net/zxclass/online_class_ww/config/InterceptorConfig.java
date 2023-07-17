package net.zxclass.online_class_ww.config;

import net.zxclass.online_class_ww.interceptor.CorsInterceptor;
import net.zxclass.online_class_ww.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置
 *
 * 不用权限可以访问url    /api/v1/pub/
 * 要登录可以访问url    /api/v1/pri/
 */

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {


    @Bean
    LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }

    @Bean
    CorsInterceptor corsInterceptor(){
        return new CorsInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        /**
         * 拦截全部路径，这个跨域需要放在最上面
         */
        //暂时不写这行
        registry.addInterceptor(corsInterceptor()).addPathPatterns("/**");

        //配置拦截器拦截的路径
        //拦截全部
        registry.addInterceptor(loginInterceptor()).addPathPatterns("/api/v1/pri/*/*/**")
                //不拦截哪些路径   斜杠一定要加
                //注释这行和不注释，分别测试，未放行和放行拦截器的作用
                .excludePathPatterns("/api/v1/pri/user/login","/api/v1/pri/user/register");


        WebMvcConfigurer.super.addInterceptors(registry);

    }
}
