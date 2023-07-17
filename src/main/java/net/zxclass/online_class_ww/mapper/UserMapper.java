package net.zxclass.online_class_ww.mapper;

import net.zxclass.online_class_ww.model.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    /**
     * 注册
     * @param user
     * @return
     */
    int save(User user);


    /**
     * 查询手机号是否存在，存在那肯定不能注册了
     * @param phone
     * @return
     */
    User findByPhone(@Param("phone") String phone);


    /**
     * 登录
     * @param phone
     * @param pwd
     * @return
     */
    User findByPhoneAndPwd(@Param("phone") String phone, @Param("pwd") String pwd);


    /**
     * 根据用户id查询用户信息
     * @param userId
     * @return
     */
    User findByUserId(@Param("user_id") Integer userId);
}
