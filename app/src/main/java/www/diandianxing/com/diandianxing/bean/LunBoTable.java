package www.diandianxing.com.diandianxing.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2018/5/23.
 */

public class LunBoTable extends DataSupport {

    public int loid;
    public String imageName;
    public String imageLink;
    public String imageDesc;
    public int imageType;
    public int isDeleted;
    public long createTime;
    public long updateTime;
    public long activeTime;
    public int isActive;
    public String imageUrl;
    public int userId;
    public String objIdType;
    public String linkType;
    public int objId;
}
