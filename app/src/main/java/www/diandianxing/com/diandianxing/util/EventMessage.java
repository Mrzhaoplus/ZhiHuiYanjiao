package www.diandianxing.com.diandianxing.util;

/**
 * Created by Administrator on 2017/8/30.
 */

public class EventMessage {
    private String msg;
    private String msg2;

    public String getMsg2() {
        return msg2;
    }

    public void setMsg2(String msg2) {
        this.msg2 = msg2;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public EventMessage(String msg) {
        this.msg = msg;
    }

    public EventMessage(String msg, String msg2) {
        this.msg = msg;
        this.msg2 = msg2;
    }
}
