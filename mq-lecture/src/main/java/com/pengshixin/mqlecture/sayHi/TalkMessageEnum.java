package com.pengshixin.mqlecture.sayHi;

public enum TalkMessageEnum {

    Q1(new TalkMessage(false,"张大爷","吃了没，您呢？")),
    A1(new TalkMessage(true,"李大爷","刚吃")),

    Q2(new TalkMessage(false,"李大爷","您这，嘛去？")),
    A2(new TalkMessage(true,"张大爷","嗨，没事溜溜弯儿。")),

    Q3(new TalkMessage(false,"李大爷","有空家里坐坐啊。")),
    A3(new TalkMessage(true,"张大爷","回头去给老太太请安")),

    ;
    private TalkMessage message;

    TalkMessageEnum(TalkMessage message) {
        this.message = message;
    }

    public TalkMessage getMessage() {
        return message;
    }

    public void setMessage(TalkMessage message) {
        this.message = message;
    }
}
