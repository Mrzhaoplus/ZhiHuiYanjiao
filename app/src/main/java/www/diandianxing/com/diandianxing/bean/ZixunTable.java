package www.diandianxing.com.diandianxing.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2018/5/23.
 */

public class ZixunTable extends DataSupport {

    public int zxid;
    public String smallImage;
    public String bigImage;
    public String infoTitle;
    public long createTime;
    public int status;
    public int isDeleted;
    public long updateTime;
    public String infoBody;
    public int viewCount;
    public int dianZanCount;
    public int shareCount;

}
