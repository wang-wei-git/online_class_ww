package net.zxclass.online_class_ww.service;

import net.zxclass.online_class_ww.model.entity.VideoOrder;

import java.util.List;

public interface VideoOrderService {

    int save(int userId, int videoId);

    List<VideoOrder> listOrderByUserId(Integer userId);
}
