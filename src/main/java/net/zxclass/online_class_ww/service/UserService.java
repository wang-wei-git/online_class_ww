package net.zxclass.online_class_ww.service;

import net.zxclass.online_class_ww.model.entity.User;

import java.util.Map;

public interface UserService {

    /**
     * 新增用户
     * @param userInfo
     * @return
     */
    int save(Map<String, String> userInfo);

    /**
     * 登录
     * @param phone
     * @param pwd
     * @return
     */
    String findByPhoneAndPwd(String phone, String pwd);

    /**
     * 根据用户id查询用户信息
     * @param userId
     * @return
     */
    User findByUserId(Integer userId);
}
