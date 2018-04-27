package www.diandianxing.com.diandianxing.ShujuBean;

import java.util.List;

/**
 * Created by Mr赵 on 2018/4/18.
 */

public class Lunbo_Bean {

    /**
     * msg : 请求成功
     * code : 200
     * datas : [{"id":46,"imageName":"1","imageLink":"1","imageDesc":"1","imageStatus":null,"imageType":0,"isDeleted":0,"createTime":1521517378000,"updateTime":1521517378000,"activeTime":1521996019000,"sort":null,"isActive":0,"imageUrl":"http://47.93.45.38:8080/uploadImages/background/2018-04-08/a_pwfqddifof_390.png","userId":0},{"id":48,"imageName":"2","imageLink":"2","imageDesc":"2","imageStatus":null,"imageType":0,"isDeleted":0,"createTime":1521987751000,"updateTime":1521987751000,"activeTime":1521995783000,"sort":null,"isActive":0,"imageUrl":"http://47.93.45.38:8080/uploadImages/background/2018-04-08/a_iehgyeiodi_369.png","userId":0},{"id":49,"imageName":"3","imageLink":"3","imageDesc":"3","imageStatus":null,"imageType":0,"isDeleted":0,"createTime":1521987761000,"updateTime":1521987761000,"activeTime":1521987761000,"sort":null,"isActive":0,"imageUrl":"http://47.93.45.38:8080/uploadImages/background/2018-04-08/a_edwepqqdok_710.png","userId":0},{"id":51,"imageName":"5","imageLink":"5","imageDesc":"5","imageStatus":null,"imageType":0,"isDeleted":0,"createTime":1521987777000,"updateTime":1521987777000,"activeTime":1521987777000,"sort":null,"isActive":0,"imageUrl":"http://47.93.45.38:8080/uploadImages/background/2018-04-08/a_wldidwhryg_323.png","userId":0},{"id":52,"imageName":"6","imageLink":"6","imageDesc":"6","imageStatus":null,"imageType":0,"isDeleted":0,"createTime":1521987785000,"updateTime":1521987785000,"activeTime":1521987785000,"sort":null,"isActive":0,"imageUrl":"http://47.93.45.38:8080/uploadImages/background/2018-04-08/a_ehelipfrde_477.png","userId":0}]
     */

    private String msg;
    private String code;
    private List<DatasBean> datas;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * id : 46
         * imageName : 1
         * imageLink : 1
         * imageDesc : 1
         * imageStatus : null
         * imageType : 0
         * isDeleted : 0
         * createTime : 1521517378000
         * updateTime : 1521517378000
         * activeTime : 1521996019000
         * sort : null
         * isActive : 0
         * imageUrl : http://47.93.45.38:8080/uploadImages/background/2018-04-08/a_pwfqddifof_390.png
         * userId : 0
         */

        private int id;
        private String imageName;
        private String imageLink;
        private String imageDesc;
        private Object imageStatus;
        private int imageType;
        private int isDeleted;
        private long createTime;
        private long updateTime;
        private long activeTime;
        private Object sort;
        private int isActive;
        private String imageUrl;
        private int userId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImageName() {
            return imageName;
        }

        public void setImageName(String imageName) {
            this.imageName = imageName;
        }

        public String getImageLink() {
            return imageLink;
        }

        public void setImageLink(String imageLink) {
            this.imageLink = imageLink;
        }

        public String getImageDesc() {
            return imageDesc;
        }

        public void setImageDesc(String imageDesc) {
            this.imageDesc = imageDesc;
        }

        public Object getImageStatus() {
            return imageStatus;
        }

        public void setImageStatus(Object imageStatus) {
            this.imageStatus = imageStatus;
        }

        public int getImageType() {
            return imageType;
        }

        public void setImageType(int imageType) {
            this.imageType = imageType;
        }

        public int getIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(int isDeleted) {
            this.isDeleted = isDeleted;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public long getActiveTime() {
            return activeTime;
        }

        public void setActiveTime(long activeTime) {
            this.activeTime = activeTime;
        }

        public Object getSort() {
            return sort;
        }

        public void setSort(Object sort) {
            this.sort = sort;
        }

        public int getIsActive() {
            return isActive;
        }

        public void setIsActive(int isActive) {
            this.isActive = isActive;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
