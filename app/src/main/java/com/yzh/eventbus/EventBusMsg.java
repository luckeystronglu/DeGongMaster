package com.yzh.eventbus;


public class EventBusMsg {

    public static final int MSG_Receive_Device_data = 1001;
    public static final int MSG_Device_disconnect = 1002;
    public static final int MSG_Device_connected = 1003;
    public static final int MSG_device_location_data = 1004;


    private int MsgType;

    public int getMsgType() {
        return MsgType;
    }

    public void setMsgType(int msgType) {
        MsgType = msgType;
    }




    private EventMsgType eventMsgType;
    public EventMsgType getEventMsgType() {
        return eventMsgType;
    }

    public void setEventMsgType(EventMsgType eventMsgType) {
        this.eventMsgType = eventMsgType;
    }




    private boolean judgeLogin;
    public boolean isJudgeLogin() {
        return judgeLogin;
    }

    public void setJudgeLogin(boolean judgeLogin) {
        this.judgeLogin = judgeLogin;
    }





    public enum EventMsgType {
        JUDGE_IS_LOGIN_ORNOT,
        MSG_REFRESH
    }
}
