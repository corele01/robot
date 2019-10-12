package com.corele.robot.controller;

import com.corele.robot.common.BaseResponse;
import com.corele.robot.dto.MessageInfo;
import com.corele.robot.model.RobotUser;
import com.corele.robot.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author liujun
 */
@RestController
public class MessageController extends BaseController{
    @Autowired
    private MessageService messageService;

    @PostMapping("message")
    public BaseResponse message(@RequestBody MessageInfo messageInfo) throws UnsupportedEncodingException {
        RobotUser user = getUser(messageInfo.getGroupNo(), messageInfo.getSendNo());
        String decode = URLDecoder.decode(messageInfo.getMessage(), "UTF-8");
        messageInfo.setMessage(decode);
        String result = this.messageService.handleMsg(messageInfo,user);
        return BaseResponse.success(result);
    }
}
