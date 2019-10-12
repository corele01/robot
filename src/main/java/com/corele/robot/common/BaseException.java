package com.corele.robot.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author liujun
 */
@Data
public class BaseException extends RuntimeException {

    private int errorCode;

    private String message;

    public BaseException(String message){
        super(message);
        this.message = message;
    }

    public BaseException(int errorCode,String message){
        super(message);
        this.message = message;
        this.errorCode = errorCode;
    }

    public static BaseException exception(String message){
        return new BaseException(0,message);
    }
    public static BaseException exception(){
        return new BaseException(0,"系统异常，请联系管理员");
    }
}
