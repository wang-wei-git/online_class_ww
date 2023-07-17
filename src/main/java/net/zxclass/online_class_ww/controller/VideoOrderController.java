package net.zxclass.online_class_ww.controller;

import net.zxclass.online_class_ww.model.entity.VideoOrder;
import net.zxclass.online_class_ww.model.request.VideoOrderRequest;
import net.zxclass.online_class_ww.service.VideoOrderService;
import net.zxclass.online_class_ww.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pri/order")
public class VideoOrderController {


    @Autowired
    private VideoOrderService videoOrderService;


    /**
     * 下单接口
     * @return
     */
    @RequestMapping("/save")
    //需要传入对应下单的视频id，然后前端肯定有对应的json数据，所以这里需要建一个VideoOrderRequest
    public JsonData saveOrder(@RequestBody(required=false) VideoOrderRequest videoOrderRequest, HttpServletRequest request){

        Integer userId = (Integer) request.getAttribute("user_id");
        int rows = videoOrderService.save(userId, videoOrderRequest.getVideoId());



        return rows == 0 ? JsonData.buildError("下单失败"):JsonData.buildSuccess();
    }




    /**
     * 订单列表
     * @param request
     * @return
     */
    @GetMapping("/list")
    public JsonData listOrder(HttpServletRequest request){
        Integer userId = (Integer) request.getAttribute("user_id");

        List<VideoOrder> videoOrderList = videoOrderService.listOrderByUserId(userId);

        return JsonData.buildSuccess(videoOrderList);

    }




}
