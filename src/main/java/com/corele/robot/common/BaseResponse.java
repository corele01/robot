package com.corele.robot.common;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author jun
 */
@Data
@Builder
public class BaseResponse<T> implements Serializable {
    private Integer code;
    private T data;
    private String msg;
    
    public static BaseResponse success(){
        return BaseResponse.builder().code(BaseConstants.CODE_SUCCESS).msg("success").build();
    }

    public static BaseResponse success(Object object){
        return BaseResponse.builder().code(BaseConstants.CODE_SUCCESS).msg("success").data(object).build();
    }
    
    public static BaseResponse fail(Integer code){
        return BaseResponse.builder().code(code).msg("fail").build();
    }

    public static BaseResponse fail(){
       return fail(BaseConstants.CODE_FAIL);
    }
    
    public static <T> BaseResponse baseResponse(Integer code, T data,String msg){
        return BaseResponse.builder().code(code).data(data).msg(msg).build();
    }
}
