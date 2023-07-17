package net.zxclass.online_class_ww.mapper;

import net.zxclass.online_class_ww.model.entity.Episode;
import org.apache.ibatis.annotations.Param;

public interface EpisodeMapper {


    Episode findFirstEpisodeByVideoId(@Param("video_id") int videoId);

}
