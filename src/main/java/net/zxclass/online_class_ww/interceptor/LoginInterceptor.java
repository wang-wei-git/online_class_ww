package net.zxclass.online_class_ww.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import net.zxclass.online_class_ww.utils.JWTUtils;
import net.zxclass.online_class_ww.utils.JsonData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /**
         * 登录成功
         */
        try {


//            // 如果是OPTIONS则结束请求
//            if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
//                response.setStatus(HttpStatus.NO_CONTENT.value());
//                response.setHeader("Access-Control-Allow-Origin", "*");
//                response.setHeader("Access-Control-Allow-Credentials", "true");
//                response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS");
//                response.setHeader("Access-Control-Max-Age", "86400");
//                response.setHeader("Access-Control-Allow-Headers", "*");
//                return false;
//            }

            //通过getHeader获取token
            String accessToken = request.getHeader("token");
            //如果为空
            if (accessToken == null) {
                //通过getParameter获取token
                accessToken = request.getParameter("token");
            }

            //如果accessToken不为空
            if (StringUtils.isNotBlank(accessToken)) {
                //通过JWT进行解密，可以获取Claims，通过Claims我们就可以获取用户的基本信息
                Claims claims = JWTUtils.checkJWT(accessToken);

                if (claims == null) {
                    //告诉登录（失败）过期，重新登录
                    sendJsonMessage(response, JsonData.buildError("登录过期，重新登录"));
                    return false;
                }
                //如果部位null，我们就获取对应参数
                Integer id = (Integer) claims.get("id");
                String name = (String) claims.get("name");

                request.setAttribute("user_id", id);
                request.setAttribute("name", name);

                return true;

            }

        }catch (Exception e){}


        /**
         *    登录失败
         */
        sendJsonMessage(response, JsonData.buildError("登录过期，重新登录"));

        return false;
    }



    /**
     * 响应json数据给前端
     * @param response
     * @param obj
     */

    //怎么把信息给到前端呢，我们可以把这个对象序列化成json字符串，然后通过writer流的方式写出去
    public static void sendJsonMessage(HttpServletResponse response, Object obj){

        try{

            //ObjectMapper 这个可以序列化字符串
            ObjectMapper objectMapper = new ObjectMapper();
            //响应类型
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();
//          writeValueAsString()  当成一个字符串写出去
            writer.print(objectMapper.writeValueAsString(obj));
            writer.close();
            //刷新
            response.flushBuffer();
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
