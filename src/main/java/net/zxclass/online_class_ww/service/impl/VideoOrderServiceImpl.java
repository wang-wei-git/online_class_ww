package net.zxclass.online_class_ww.service.impl;

import net.zxclass.online_class_ww.exception.OnlineException;
import net.zxclass.online_class_ww.mapper.EpisodeMapper;
import net.zxclass.online_class_ww.mapper.PlayRecordMapper;
import net.zxclass.online_class_ww.mapper.VideoMapper;
import net.zxclass.online_class_ww.mapper.VideoOrderMapper;
import net.zxclass.online_class_ww.model.entity.Episode;
import net.zxclass.online_class_ww.model.entity.PlayRecord;
import net.zxclass.online_class_ww.model.entity.Video;
import net.zxclass.online_class_ww.model.entity.VideoOrder;
import net.zxclass.online_class_ww.service.VideoOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class VideoOrderServiceImpl implements VideoOrderService {


    @Autowired
    private VideoOrderMapper videoOrderMapper;
    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private EpisodeMapper episodeMapper;


    @Autowired
    private PlayRecordMapper playRecordMapper;

    /**
     * 下单操作
     * 未来版本：优惠券抵扣，风控用户检查，生成订单基础信息，生成支付信息
     * @param userId
     * @param videoId
     * @return
     */
    @Override
    @Transactional
    public int save(int userId, int videoId) {

        //判断是否已经购买
        VideoOrder videoOrder = videoOrderMapper.findByUserIdAndVideoIdAndState(userId,videoId,1);


        if(videoOrder!=null){
            //表示已经支付过了
            return  0;
        }

        Video video = videoMapper.findById(videoId);

        VideoOrder newVideoOrder = new VideoOrder();
        newVideoOrder.setCreateTime(new Date());
        //随机生成一串订单号
        newVideoOrder.setOutTradeNo(UUID.randomUUID().toString());
        newVideoOrder.setState(1);
        newVideoOrder.setTotalFee(video.getPrice());
        newVideoOrder.setUserId(userId);

        newVideoOrder.setVideoId(videoId);
        newVideoOrder.setVideoImg(video.getCoverImg());
        newVideoOrder.setVideoTitle(video.getTitle());

        int rows = videoOrderMapper.saveOrder(newVideoOrder);

//        int i = 1/0;

        //生成播放记录
        if(rows == 1){  //rows==1 表示下单成功
            //下来我们需要获取播放的集数，我们就让它从第一集开始
            Episode episode = episodeMapper.findFirstEpisodeByVideoId(videoId);
            if(episode == null){
                throw  new OnlineException(-1,"视频没有集信息，请运营人员检查");
            }
            PlayRecord playRecord = new PlayRecord();
            playRecord.setCreateTime(new Date());
            playRecord.setEpisodeId(episode.getId());
            playRecord.setCurrentNum(episode.getNum());
            playRecord.setUserId(userId);
            playRecord.setVideoId(videoId);
            //保存播放记录信息
            playRecordMapper.saveRecord(playRecord);
        }

        return rows;
    }

    @Override
    public List<VideoOrder> listOrderByUserId(Integer userId) {
        return videoOrderMapper.listOrderByUserId(userId);
    }













}
