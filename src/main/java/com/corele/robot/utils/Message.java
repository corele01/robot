package com.corele.robot.utils;

import lombok.Builder;

/**
 * @author liujun
 */
public class Message {

    private StringBuffer messageBuffer;

    public void addString(String string){
        if (messageBuffer == null){
            messageBuffer = new StringBuffer();
        }
        messageBuffer.append(string);
    }

    public static MessageBuilder builder(){
        return new MessageBuilder();
    }

    public String toMessage(){
        return messageBuffer.toString();
    }

    public static class MessageBuilder{

        private static Message message;

        public MessageBuilder addString(String string){
            if (message == null){
                message = new Message();
            }
            message.addString(string);
            return this;
        }

        public MessageBuilder addString(Object string){
            addString(String.valueOf(string));
            return this;
        }

        public MessageBuilder addEnter(){
            addString("\n");
            return this;
        }

        public MessageBuilder addFace(int faceId){
            addString(Face.face(faceId));
            return this;
        }

        public MessageBuilder addSpace(){
            addString(" ");
            return this;
        }

        public String toMsg(){
            String msg = MessageBuilder.message.toMessage();
            message = new Message();
            return msg;
        }
    }
}
