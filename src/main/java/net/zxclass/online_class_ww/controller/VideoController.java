package net.zxclass.online_class_ww.controller;

import net.zxclass.online_class_ww.model.entity.Video;
import net.zxclass.online_class_ww.model.entity.VideoBanner;
import net.zxclass.online_class_ww.service.VideoService;
import net.zxclass.online_class_ww.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@RequestMapping("/video")
@RequestMapping("/api/v1/pub/video")    //v1发布版本号，pub，不用登录可以访问的
public class VideoController {

    @Autowired
    private VideoService videoService;


//    //1.
//    @GetMapping("/list")
//    public Object listVideo(){
//        return videoService.listVideo();
//    }


    /**
     * 视频列表
     * @return
     */
    @GetMapping("/list")
    public JsonData listVideo(){
        List<Video> videos = videoService.listVideo();
        //02 异常检验
//         int i = 1/0;
//        System.out.println(4444);
        return JsonData.buildSuccess(videos);
    }


    /**
     * 轮播图列表
     * @return
     */
    @GetMapping("/list_banner")
    public JsonData indexBanner(){
        List<VideoBanner> bannerList  = videoService.listBanner();
        return JsonData.buildSuccess(bannerList);

    }


    /**
     * 查询视频详情，包含章，集信息（包含 视频、章、集表）
     * required = true 表示 参数必传的
     * @param videoId
     * @return
     */
    @GetMapping("/find_detail_by_id")
    public JsonData findDetailById(@RequestParam(value="video_id",required = true) int videoId){
        Video video = videoService.findDetailById(videoId);
        return JsonData.buildSuccess(video);
    }
}
