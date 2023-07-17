package net.zxclass.online_class_ww.mapper;

import net.zxclass.online_class_ww.model.entity.Video;
import net.zxclass.online_class_ww.model.entity.VideoBanner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoMapper {

    /**
     * 查询视频列表
     * @return
     */
    List<Video> listVideo();

    /**
     * 首页轮播图列表
     * @return
     */
    List<VideoBanner> listVideoBanner();

    /**
     * 查询视频详情
     * @Param("video_id") 一个参数也写一下，养成习惯，这样取值的时候才可以直接取
     * @param videoId
     * @return
     */
    Video findDetailById(@Param("video_id") int videoId);


    /**
     * 简单查询视频信息
     * @param videoId
     * @return
     */
    Video findById(@Param("video_id") int videoId);
}
