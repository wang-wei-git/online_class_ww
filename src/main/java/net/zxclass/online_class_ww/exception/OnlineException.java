package net.zxclass.online_class_ww.exception;

/**
 * 在线课堂
 * 自定义异常类
 */
public class OnlineException extends RuntimeException{

    private Integer code;
    private String msg;

    public OnlineException() {
    }

    public OnlineException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
