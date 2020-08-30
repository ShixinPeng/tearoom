package com.pengshixin.mqlecture.sayHi;

import java.io.Serializable;

/**
 * @author shixinpeng
 * @description 对话封装对象
 * @ClassName: TalkMessage
 * @date 2020/8/30
 *
 */
public class TalkMessage implements Serializable {


    /**
     * 问还是答
     */
    private Boolean ack;

    /**
     * 说话者
     */
    private String persion;

    /**
     * 说话内容
     */
    private String content;

    public String getPersion() {
        return persion;
    }

    public void setPersion(String persion) {
        this.persion = persion;
    }

    public Boolean getAck() {
        return ack;
    }

    public void setAck(Boolean ack) {
        this.ack = ack;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public TalkMessage(Boolean ack, String persion, String content) {
        this.ack = ack;
        this.persion = persion;
        this.content = content;
        System.out.println("初始化message："+ toString());
    }

    @Override
    public String toString() {
        return "TalkMessage{" +
                "ack=" + ack +
                ", persion='" + persion + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
