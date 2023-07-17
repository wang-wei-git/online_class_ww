package net.zxclass.online_class_ww.service;

import net.zxclass.online_class_ww.model.entity.Video;
import net.zxclass.online_class_ww.model.entity.VideoBanner;

import java.util.List;

public interface VideoService {

    /**
     * 查询视频列表
     * @return
     */
    List<Video> listVideo();

    /**
     * 首页轮播图列表
     * @return
     */
    List<VideoBanner> listBanner();

    /**
     * 查询视频详情
     * @param videoId
     * @return
     */
    Video findDetailById(int videoId);
}
