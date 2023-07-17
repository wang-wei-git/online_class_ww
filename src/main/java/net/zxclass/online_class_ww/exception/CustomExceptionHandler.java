package net.zxclass.online_class_ww.exception;

import net.zxclass.online_class_ww.utils.JsonData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常处理类
 */
@ControllerAdvice
public class CustomExceptionHandler {


        //3.如果后续需要记录日志，我们可以在这里写一下
        private final static Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

        //1
        @ExceptionHandler(value = Exception.class)
        @ResponseBody   //如果出现异常需要响应给前端，所以需要ResponseBody
        public JsonData handle(Exception e) {


            //4.可以自定义日志内容模板，我们写一下
            logger.error("[ 系统异常 ]{}",e);

            //2.可能会出现很多异常，所以我们需要判断它是不是我们自定义异常的实例
            //是的话我们就拿对应的值，你可以写很多if else,我们这里就写单独一个就行了
            if(e instanceof OnlineException ){
                OnlineException onlineException = (OnlineException) e;
                return JsonData.buildError(onlineException.getCode(),onlineException.getMsg());
            }else{
                return JsonData.buildError("全局异常，未知错误");
            }
        }
    }
