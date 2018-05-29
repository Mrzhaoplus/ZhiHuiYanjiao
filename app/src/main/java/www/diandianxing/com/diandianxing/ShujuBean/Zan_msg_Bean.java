package www.diandianxing.com.diandianxing.ShujuBean;

import java.util.List;

/**
 * Created by Mr赵 on 2018/4/23.
 */

public class Zan_msg_Bean {

    /**
     * msg : 请求成功
     * code : 200
     * datas : [{"id":372,"userId":1047,"sponsorId":1047,"title":"测试","objId":13,"content":"","createTime":1524447945000,"objType":0,"isDeleted":0,"operationType":1,"nickName":"test","pic":"http://47.93.45.38/server/uploads/head/timg.jpg"},{"id":371,"userId":1,"sponsorId":1047,"title":"帖子标题","objId":2,"content":"","createTime":1524447941000,"objType":0,"isDeleted":0,"operationType":1,"nickName":"test","pic":"http://47.93.45.38/server/uploads/head/timg.jpg"},{"id":370,"userId":1,"sponsorId":1047,"title":"帖子标题","objId":2,"content":"","createTime":1524447939000,"objType":0,"isDeleted":0,"operationType":1,"nickName":"test","pic":"http://47.93.45.38/server/uploads/head/timg.jpg"},{"id":369,"userId":1,"sponsorId":1,"title":"二手市场","objId":11,"content":"","createTime":1524447935000,"objType":0,"isDeleted":0,"operationType":1,"nickName":"啊咯","pic":"http://47.93.45.38/server/uploads/head/20180328/5abb7c8abee96.jpg"},{"id":368,"userId":1047,"sponsorId":1,"title":"测试","objId":13,"content":"","createTime":1524294067000,"objType":0,"isDeleted":0,"operationType":1,"nickName":"啊咯","pic":"http://47.93.45.38/server/uploads/head/20180328/5abb7c8abee96.jpg"},{"id":367,"userId":1,"sponsorId":1,"title":"二手市场","objId":11,"content":"","createTime":1524294064000,"objType":0,"isDeleted":0,"operationType":1,"nickName":"啊咯","pic":"http://47.93.45.38/server/uploads/head/20180328/5abb7c8abee96.jpg"},{"id":366,"userId":1,"sponsorId":1,"title":"二手市场","objId":11,"content":"","createTime":1524276512000,"objType":0,"isDeleted":0,"operationType":1,"nickName":"啊咯","pic":"http://47.93.45.38/server/uploads/head/20180328/5abb7c8abee96.jpg"},{"id":365,"userId":1047,"sponsorId":1,"title":"测试","objId":13,"content":"","createTime":1524276448000,"objType":0,"isDeleted":0,"operationType":1,"nickName":"啊咯","pic":"http://47.93.45.38/server/uploads/head/20180328/5abb7c8abee96.jpg"},{"id":364,"userId":1047,"sponsorId":1,"title":"测试","objId":13,"content":"","createTime":1524276447000,"objType":0,"isDeleted":0,"operationType":1,"nickName":"啊咯","pic":"http://47.93.45.38/server/uploads/head/20180328/5abb7c8abee96.jpg"},{"id":363,"userId":1,"sponsorId":1,"title":"帖子标题","objId":2,"content":"","createTime":1524276005000,"objType":0,"isDeleted":0,"operationType":1,"nickName":"啊咯","pic":"http://47.93.45.38/server/uploads/head/20180328/5abb7c8abee96.jpg"}]
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
         * id : 372
         * userId : 1047
         * sponsorId : 1047
         * title : 测试
         * objId : 13
         * content :
         * createTime : 1524447945000
         * objType : 0
         * isDeleted : 0
         * operationType : 1
         * nickName : test
         * pic : http://47.93.45.38/server/uploads/head/timg.jpg
         */

        private int id;
        private int userId;
        private int sponsorId;
        private String title;
        private int objId;
        private String content;
        private long createTime;
        public int objType;
        private int isDeleted;
        private int operationType;
        private String nickName;
        private String pic;
        private String userLevel;

        public String getUserLevel() {
            return userLevel;
        }

        public void setUserLevel(String userLevel) {
            this.userLevel = userLevel;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getSponsorId() {
            return sponsorId;
        }

        public void setSponsorId(int sponsorId) {
            this.sponsorId = sponsorId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getObjId() {
            return objId;
        }

        public void setObjId(int objId) {
            this.objId = objId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getObjType() {
            return objType;
        }

        public void setObjType(int objType) {
            this.objType = objType;
        }

        public int getIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(int isDeleted) {
            this.isDeleted = isDeleted;
        }

        public int getOperationType() {
            return operationType;
        }

        public void setOperationType(int operationType) {
            this.operationType = operationType;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }
    }
}
