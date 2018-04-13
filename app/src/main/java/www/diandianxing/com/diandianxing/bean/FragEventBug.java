package www.diandianxing.com.diandianxing.bean;

/**
 * Created by Mr赵 on 2018/4/13.
 */

public class FragEventBug {
    private int Msgg;
    private String msg;

    public FragEventBug(int msgg, String msg) {
        Msgg = msgg;
        this.msg = msg;
    }

    public int getMsgg() {
        return Msgg;
    }

    public void setMsgg(int msgg) {
        Msgg = msgg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
