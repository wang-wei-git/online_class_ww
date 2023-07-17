package net.zxclass.online_class_ww.config;


/**
 * 缓存key管理类
 */
public class CacheKeyManager {

    /**
     * 首页轮播图缓存key
     */
    //    index:banner:list ，这个呢，也是我们redis里边的一个规范，缓存的话
            //模块与模块之间使用冒号（:）划分，那这样咱们使用可视化工具查看的时候
            //它就有一个文件夹 --- 文件夹的形式
    public static final String INDEX_BANNER_KEY = "index:banner:list";


    /**
     * 首页视频列表缓存key
     */
    public static final String INDEX_VIDEL_LIST = "index:video:list";


    /**
     * 视频详情缓存key, %s是视频id
     */
    public static final String VIDEO_DETAIL = "video:detail:%s";


}
