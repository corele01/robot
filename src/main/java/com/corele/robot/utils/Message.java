package com.corele.robot.utils;

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
        private MessageBuilder(){}

        private static Message message;

        public MessageBuilder string(String string){
            if (message == null){
                message = new Message();
            }
            message.addString(string);
            return this;
        }

        public MessageBuilder string(Object string){
            string(String.valueOf(string));
            return this;
        }

        public MessageBuilder enter(){
            string("\n");
            return this;
        }

        public MessageBuilder face(int faceId){
            string(Face.face(faceId));
            return this;
        }

        public MessageBuilder space(){
            string(" ");
            return this;
        }
        public MessageBuilder space(int cols){
            for (int i = 0; i < cols; i++) {
                string(" ");
            }
            return this;
        }

        public String toMsg(){
            String msg = MessageBuilder.message.toMessage();
            message = new Message();
            return msg;
        }

        public MessageBuilder at(String userNo){
            string("[CQ:at,qq="+userNo+"]");
            return this;
        }
    }
}
