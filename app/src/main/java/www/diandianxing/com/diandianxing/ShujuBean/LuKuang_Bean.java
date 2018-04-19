package www.diandianxing.com.diandianxing.ShujuBean;

import java.util.List;

/**
 * Created by Mr赵 on 2018/4/19.
 */

public class LuKuang_Bean {

    /**
     * msg : 请求成功
     * code : 200
     * datas : [{"id":6,"imgUrl":"http://47.93.45.38:8080/uploadImages/background/2018-04-17/a_ddjuaowfde_157.png","trafficTitle":"美丽燕郊南1","cameraUrl":"http://square.ys7.com/play/index?cameraId=686742","cameraClassification":"0","createTime":1523950293000,"updateTime":1523950293000,"isDeleted":0,"status":0,"viewCount":0,"dianZanCount":0,"shareCount":0,"is_zan":0},{"id":7,"imgUrl":"http://47.93.45.38:8080/uploadImages/background/2018-04-17/a_ddjwfewfrw_593.png","trafficTitle":"美丽燕郊北","cameraUrl":"http://square.ys7.com/play/index?cameraId=686730","cameraClassification":"0","createTime":1523950302000,"updateTime":1523950302000,"isDeleted":0,"status":0,"viewCount":0,"dianZanCount":0,"shareCount":0,"is_zan":0},{"id":8,"imgUrl":"http://47.93.45.38:8080/uploadImages/background/2018-04-17/a_edikhpiure_907.png","trafficTitle":"通燕进出京全景路况","cameraUrl":"http://square.ys7.com/play/index?cameraId=862776","cameraClassification":"0","createTime":1523950311000,"updateTime":1523950311000,"isDeleted":0,"status":0,"viewCount":0,"dianZanCount":0,"shareCount":0,"is_zan":0},{"id":9,"imgUrl":"http://47.93.45.38:8080/uploadImages/background/2018-04-17/a_ufifudjroh_32.png","trafficTitle":"4","cameraUrl":"4","cameraClassification":"0","createTime":1523950327000,"updateTime":1523950327000,"isDeleted":0,"status":0,"viewCount":0,"dianZanCount":0,"shareCount":0,"is_zan":0},{"id":10,"imgUrl":"http://47.93.45.38:8080/uploadImages/background/2018-04-17/a_dwhfhqwrdk_27.png","trafficTitle":"5","cameraUrl":"5","cameraClassification":"0","createTime":1523950337000,"updateTime":1523950337000,"isDeleted":0,"status":0,"viewCount":0,"dianZanCount":0,"shareCount":0,"is_zan":0},{"id":11,"imgUrl":"http://47.93.45.38:8080/uploadImages/background/2018-04-17/a_okeywjflry_159.png","trafficTitle":"6","cameraUrl":"6","cameraClassification":"0","createTime":1523950350000,"updateTime":1523950350000,"isDeleted":0,"status":0,"viewCount":0,"dianZanCount":0,"shareCount":0,"is_zan":0},{"id":12,"imgUrl":"http://47.93.45.38:8080/uploadImages/background/2018-04-17/a_qurdojdflh_855.png","trafficTitle":"7","cameraUrl":"7","cameraClassification":"0","createTime":1523950359000,"updateTime":1523950359000,"isDeleted":0,"status":0,"viewCount":0,"dianZanCount":0,"shareCount":0,"is_zan":0},{"id":13,"imgUrl":"http://47.93.45.38:8080/uploadImages/background/2018-04-17/a_equkweeqlw_771.png","trafficTitle":"9","cameraUrl":"9","cameraClassification":"0","createTime":1523950368000,"updateTime":1523950368000,"isDeleted":0,"status":0,"viewCount":0,"dianZanCount":0,"shareCount":0,"is_zan":0}]
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
         * id : 6
         * imgUrl : http://47.93.45.38:8080/uploadImages/background/2018-04-17/a_ddjuaowfde_157.png
         * trafficTitle : 美丽燕郊南1
         * cameraUrl : http://square.ys7.com/play/index?cameraId=686742
         * cameraClassification : 0
         * createTime : 1523950293000
         * updateTime : 1523950293000
         * isDeleted : 0
         * status : 0
         * viewCount : 0
         * dianZanCount : 0
         * shareCount : 0
         * is_zan : 0
         */

        private int id;
        private String imgUrl;
        private String trafficTitle;
        private String cameraUrl;
        private String cameraClassification;
        private long createTime;
        private long updateTime;
        private int isDeleted;
        private int status;
        private int viewCount;
        private int dianZanCount;
        private int shareCount;
        private int is_zan;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getTrafficTitle() {
            return trafficTitle;
        }

        public void setTrafficTitle(String trafficTitle) {
            this.trafficTitle = trafficTitle;
        }

        public String getCameraUrl() {
            return cameraUrl;
        }

        public void setCameraUrl(String cameraUrl) {
            this.cameraUrl = cameraUrl;
        }

        public String getCameraClassification() {
            return cameraClassification;
        }

        public void setCameraClassification(String cameraClassification) {
            this.cameraClassification = cameraClassification;
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

        public int getIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(int isDeleted) {
            this.isDeleted = isDeleted;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getViewCount() {
            return viewCount;
        }

        public void setViewCount(int viewCount) {
            this.viewCount = viewCount;
        }

        public int getDianZanCount() {
            return dianZanCount;
        }

        public void setDianZanCount(int dianZanCount) {
            this.dianZanCount = dianZanCount;
        }

        public int getShareCount() {
            return shareCount;
        }

        public void setShareCount(int shareCount) {
            this.shareCount = shareCount;
        }

        public int getIs_zan() {
            return is_zan;
        }

        public void setIs_zan(int is_zan) {
            this.is_zan = is_zan;
        }
    }
}
