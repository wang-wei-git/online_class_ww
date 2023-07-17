package net.zxclass.online_class_ww.controller;

import net.zxclass.online_class_ww.model.entity.User;
import net.zxclass.online_class_ww.model.request.LoginRequest;
import net.zxclass.online_class_ww.service.UserService;
import net.zxclass.online_class_ww.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("api/v1/pri/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册接口
     * @param userInfo
     * @return
     */
    @PostMapping("register")
    public JsonData register(@RequestBody Map<String,String> userInfo ){

        int rows = userService.save(userInfo);

        return rows == 1 ? JsonData.buildSuccess(): JsonData.buildError("注册失败，请重试");

    }


    /**
     * 登录接口
     * @return
     */
    //这里的参数我们用这个loginRequest，上面的注册也一样，也可以一样用Request包装，没有规定说
    //一定要用 Map<String,String> userInfo，我们有多种形式，就看你们公司需要用哪种
    @PostMapping("/login")
    public JsonData login(@RequestBody LoginRequest loginRequest){
        String token = userService.findByPhoneAndPwd(loginRequest.getPhone(), loginRequest.getPwd());
        return token == null ?JsonData.buildError("登录失败，账号密码错误"): JsonData.buildSuccess(token);
    }


    /**
     * 根据用户id查询用户信息
     * @param request
     * @return
     */
    @GetMapping("find_by_token")
    public JsonData findUserInfoByToken(HttpServletRequest request){
        //这里的user_id是从 LoginInterceptor登录成功之后的 request.setAttribute("user_id", id);获取到
        Integer userId = (Integer) request.getAttribute("user_id");

        if(userId == null){
            return JsonData.buildError("查询失败");
        }
        User user =  userService.findByUserId(userId);
        return JsonData.buildSuccess(user);

    }







}
