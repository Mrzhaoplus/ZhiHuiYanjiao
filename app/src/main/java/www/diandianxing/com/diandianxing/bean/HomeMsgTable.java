package www.diandianxing.com.diandianxing.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2018/5/23.
 */

public class HomeMsgTable extends DataSupport {

    public int hsid;
    public int userId;
    public int sponsorId;
    public String title;
    public int objId;
    public String content;
    public long createTime;
    public int objType;
    public int isDeleted;
    public int operationType;
    public String nickName;
    public String pic;
    public String userLevel;

}
