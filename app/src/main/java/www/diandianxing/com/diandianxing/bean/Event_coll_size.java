package www.diandianxing.com.diandianxing.bean;

/**
 * Created by Mr赵 on 2018/4/27.
 */

public class Event_coll_size {
    private int state;
    private int size;

    public Event_coll_size(int state, int size) {
        this.state = state;
        this.size = size;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
