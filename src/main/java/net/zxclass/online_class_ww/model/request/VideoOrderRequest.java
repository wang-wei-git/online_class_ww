package net.zxclass.online_class_ww.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VideoOrderRequest {


    @JsonProperty("video_id")
    private int videoId;

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }
}
