package www.diandianxing.com.diandianxing.ShujuBean;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Mr赵 on 2018/4/18.
 */

public class zixun_Bean {


    /**
     * msg : 请求成功
     * code : 200
     * datas : [{"id":3,"smallImage":"/uploadImages/background/2018-04-17/a_jyeulddghi_552.png","bigImage":"/uploadImages/background/2018-04-17/a_aqefdwpfuq_632.png","infoTitle":"111","createTime":1523955608000,"status":0,"isDeleted":0,"updateTime":1523955608000,"infoBody":"<p>11111<\/p>","viewCount":0,"dianZanCount":0,"shareCount":0},{"id":4,"smallImage":"/uploadImages/background/2018-04-17/a_hrpjueedqe_993.png","bigImage":"/uploadImages/background/2018-04-17/a_oehkyoyhwo_685.png","infoTitle":"111","createTime":1523955613000,"status":0,"isDeleted":0,"updateTime":1523955613000,"infoBody":"","viewCount":0,"dianZanCount":0,"shareCount":0},{"id":5,"smallImage":"/uploadImages/background/2018-04-17/a_pdqrgfeepp_363.png","bigImage":"/uploadImages/background/2018-04-17/a_qpwwjiijfy_957.png","infoTitle":"3","createTime":1523950188000,"status":0,"isDeleted":0,"updateTime":1523950188000,"infoBody":"<p>3<\/p>","viewCount":0,"dianZanCount":0,"shareCount":0},{"id":6,"smallImage":"/uploadImages/background/2018-04-17/a_hopofpwwee_733.png","bigImage":"/uploadImages/background/2018-04-17/a_pqfyepreqa_256.png","infoTitle":"4","createTime":1523950208000,"status":0,"isDeleted":0,"updateTime":1523950208000,"infoBody":"","viewCount":0,"dianZanCount":0,"shareCount":0},{"id":7,"smallImage":"/uploadImages/background/2018-04-17/a_kqdlookuoa_398.png","bigImage":"/uploadImages/background/2018-04-17/a_ejiwwrwikd_674.png","infoTitle":"5","createTime":1523950222000,"status":0,"isDeleted":0,"updateTime":1523950222000,"infoBody":"<p>5<\/p>","viewCount":0,"dianZanCount":0,"shareCount":0},{"id":8,"smallImage":"/uploadImages/background/2018-04-17/a_dwrjhorfpe_168.png","bigImage":"/uploadImages/background/2018-04-17/a_pwkqrefaej_546.png","infoTitle":"6","createTime":1523950239000,"status":0,"isDeleted":0,"updateTime":1523950239000,"infoBody":"<p>6<\/p>","viewCount":0,"dianZanCount":0,"shareCount":0},{"id":9,"smallImage":"/uploadImages/background/2018-04-17/a_lhqioeqfwg_147.png","bigImage":"/uploadImages/background/2018-04-17/a_frfhyjieho_183.png","infoTitle":"7","createTime":1523950260000,"status":0,"isDeleted":0,"updateTime":1523950260000,"infoBody":"<p>7<\/p>","viewCount":0,"dianZanCount":0,"shareCount":0},{"id":10,"smallImage":"/uploadImages/background/2018-04-17/a_rjellaeeof_226.png","bigImage":"/uploadImages/background/2018-04-17/a_pfefwipkqu_822.png","infoTitle":"8","createTime":1523950277000,"status":0,"isDeleted":0,"updateTime":1523950277000,"infoBody":"<p>8<\/p>","viewCount":0,"dianZanCount":0,"shareCount":0}]
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

    public static class DatasBean{
        /**
         * id : 3
         * smallImage : /uploadImages/background/2018-04-17/a_jyeulddghi_552.png
         * bigImage : /uploadImages/background/2018-04-17/a_aqefdwpfuq_632.png
         * infoTitle : 111
         * createTime : 1523955608000
         * status : 0
         * isDeleted : 0
         * updateTime : 1523955608000
         * infoBody : <p>11111</p>
         * viewCount : 0
         * dianZanCount : 0
         * shareCount : 0
         */

        private int id;
        private String smallImage;
        private String bigImage;
        private String infoTitle;
        private long createTime;
        private int status;
        private int isDeleted;
        private long updateTime;
        private String infoBody;
        private int viewCount;
        private int dianZanCount;
        private int shareCount;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSmallImage() {
            return smallImage;
        }

        public void setSmallImage(String smallImage) {
            this.smallImage = smallImage;
        }

        public String getBigImage() {
            return bigImage;
        }

        public void setBigImage(String bigImage) {
            this.bigImage = bigImage;
        }

        public String getInfoTitle() {
            return infoTitle;
        }

        public void setInfoTitle(String infoTitle) {
            this.infoTitle = infoTitle;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(int isDeleted) {
            this.isDeleted = isDeleted;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public String getInfoBody() {
            return infoBody;
        }

        public void setInfoBody(String infoBody) {
            this.infoBody = infoBody;
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
    }
}
