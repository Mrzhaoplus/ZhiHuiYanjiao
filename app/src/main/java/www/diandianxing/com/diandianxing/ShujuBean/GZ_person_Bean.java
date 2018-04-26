package www.diandianxing.com.diandianxing.ShujuBean;

import java.util.List;

/**
 * Created by Mr赵 on 2018/4/25.
 */

public class GZ_person_Bean {


    /**
     * msg : 请求成功
     * code : 200
     * datas : [{"id":0,"uid":1,"pic":"http://47.93.45.38/server/uploads/head/20180328/5abb7c8abee96.jpg","wechat":"","tencentQq":"","microblog":"","wechatName":"","qqName":"","sinaName":"","uname":"","identPic":"","identNum":"","ddxUser":{"id":0,"nickname":"","password":"","contact":"","credit":0,"balance":0,"mileage":0,"token":"","deposit":0,"depositStatus":false,"doubles":0,"idIdent":0,"type":0,"addTime":1524712985582,"encrypt":"","lastTime":1524712985582,"ridingState":false,"userId":0,"pic":"","nickName":"","postImage":"","postTitle":"","postId":0,"isGz":0,"postImagePaths":[]},"nickName":"啊咯","sort":0,"zan":0,"userLevel":"","userSex":0,"isGz":0},{"id":0,"uid":1047,"pic":"http://47.93.45.38/server/uploads/head/timg.jpg","wechat":"","tencentQq":"","microblog":"","wechatName":"","qqName":"","sinaName":"","uname":"","identPic":"","identNum":"","ddxUser":{"id":0,"nickname":"","password":"","contact":"","credit":0,"balance":0,"mileage":0,"token":"","deposit":0,"depositStatus":false,"doubles":0,"idIdent":0,"type":0,"addTime":1524712985582,"encrypt":"","lastTime":1524712985582,"ridingState":false,"userId":0,"pic":"","nickName":"","postImage":"","postTitle":"","postId":0,"isGz":0,"postImagePaths":[]},"nickName":"test","sort":0,"zan":0,"userLevel":"","userSex":0,"isGz":0}]
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
         * id : 0
         * uid : 1
         * pic : http://47.93.45.38/server/uploads/head/20180328/5abb7c8abee96.jpg
         * wechat :
         * tencentQq :
         * microblog :
         * wechatName :
         * qqName :
         * sinaName :
         * uname :
         * identPic :
         * identNum :
         * ddxUser : {"id":0,"nickname":"","password":"","contact":"","credit":0,"balance":0,"mileage":0,"token":"","deposit":0,"depositStatus":false,"doubles":0,"idIdent":0,"type":0,"addTime":1524712985582,"encrypt":"","lastTime":1524712985582,"ridingState":false,"userId":0,"pic":"","nickName":"","postImage":"","postTitle":"","postId":0,"isGz":0,"postImagePaths":[]}
         * nickName : 啊咯
         * sort : 0
         * zan : 0
         * userLevel :
         * userSex : 0
         * isGz : 0
         */

        private int id;
        private int uid;
        private String pic;
        private String wechat;
        private String tencentQq;
        private String microblog;
        private String wechatName;
        private String qqName;
        private String sinaName;
        private String uname;
        private String identPic;
        private String identNum;
        private DdxUserBean ddxUser;
        private String nickName;
        private int sort;
        private int zan;
        private String userLevel;
        private int userSex;
        private int isGz;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getWechat() {
            return wechat;
        }

        public void setWechat(String wechat) {
            this.wechat = wechat;
        }

        public String getTencentQq() {
            return tencentQq;
        }

        public void setTencentQq(String tencentQq) {
            this.tencentQq = tencentQq;
        }

        public String getMicroblog() {
            return microblog;
        }

        public void setMicroblog(String microblog) {
            this.microblog = microblog;
        }

        public String getWechatName() {
            return wechatName;
        }

        public void setWechatName(String wechatName) {
            this.wechatName = wechatName;
        }

        public String getQqName() {
            return qqName;
        }

        public void setQqName(String qqName) {
            this.qqName = qqName;
        }

        public String getSinaName() {
            return sinaName;
        }

        public void setSinaName(String sinaName) {
            this.sinaName = sinaName;
        }

        public String getUname() {
            return uname;
        }

        public void setUname(String uname) {
            this.uname = uname;
        }

        public String getIdentPic() {
            return identPic;
        }

        public void setIdentPic(String identPic) {
            this.identPic = identPic;
        }

        public String getIdentNum() {
            return identNum;
        }

        public void setIdentNum(String identNum) {
            this.identNum = identNum;
        }

        public DdxUserBean getDdxUser() {
            return ddxUser;
        }

        public void setDdxUser(DdxUserBean ddxUser) {
            this.ddxUser = ddxUser;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getZan() {
            return zan;
        }

        public void setZan(int zan) {
            this.zan = zan;
        }

        public String getUserLevel() {
            return userLevel;
        }

        public void setUserLevel(String userLevel) {
            this.userLevel = userLevel;
        }

        public int getUserSex() {
            return userSex;
        }

        public void setUserSex(int userSex) {
            this.userSex = userSex;
        }

        public int getIsGz() {
            return isGz;
        }

        public void setIsGz(int isGz) {
            this.isGz = isGz;
        }

        public static class DdxUserBean {
            /**
             * id : 0
             * nickname :
             * password :
             * contact :
             * credit : 0
             * balance : 0
             * mileage : 0
             * token :
             * deposit : 0
             * depositStatus : false
             * doubles : 0
             * idIdent : 0
             * type : 0
             * addTime : 1524712985582
             * encrypt :
             * lastTime : 1524712985582
             * ridingState : false
             * userId : 0
             * pic :
             * nickName :
             * postImage :
             * postTitle :
             * postId : 0
             * isGz : 0
             * postImagePaths : []
             */

            private int id;
            private String nickname;
            private String password;
            private String contact;
            private int credit;
            private int balance;
            private int mileage;
            private String token;
            private int deposit;
            private boolean depositStatus;
            private int doubles;
            private int idIdent;
            private int type;
            private long addTime;
            private String encrypt;
            private long lastTime;
            private boolean ridingState;
            private int userId;
            private String pic;
            private String nickName;
            private String postImage;
            private String postTitle;
            private int postId;
            private int isGz;
            private List<?> postImagePaths;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getContact() {
                return contact;
            }

            public void setContact(String contact) {
                this.contact = contact;
            }

            public int getCredit() {
                return credit;
            }

            public void setCredit(int credit) {
                this.credit = credit;
            }

            public int getBalance() {
                return balance;
            }

            public void setBalance(int balance) {
                this.balance = balance;
            }

            public int getMileage() {
                return mileage;
            }

            public void setMileage(int mileage) {
                this.mileage = mileage;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public int getDeposit() {
                return deposit;
            }

            public void setDeposit(int deposit) {
                this.deposit = deposit;
            }

            public boolean isDepositStatus() {
                return depositStatus;
            }

            public void setDepositStatus(boolean depositStatus) {
                this.depositStatus = depositStatus;
            }

            public int getDoubles() {
                return doubles;
            }

            public void setDoubles(int doubles) {
                this.doubles = doubles;
            }

            public int getIdIdent() {
                return idIdent;
            }

            public void setIdIdent(int idIdent) {
                this.idIdent = idIdent;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public long getAddTime() {
                return addTime;
            }

            public void setAddTime(long addTime) {
                this.addTime = addTime;
            }

            public String getEncrypt() {
                return encrypt;
            }

            public void setEncrypt(String encrypt) {
                this.encrypt = encrypt;
            }

            public long getLastTime() {
                return lastTime;
            }

            public void setLastTime(long lastTime) {
                this.lastTime = lastTime;
            }

            public boolean isRidingState() {
                return ridingState;
            }

            public void setRidingState(boolean ridingState) {
                this.ridingState = ridingState;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getPostImage() {
                return postImage;
            }

            public void setPostImage(String postImage) {
                this.postImage = postImage;
            }

            public String getPostTitle() {
                return postTitle;
            }

            public void setPostTitle(String postTitle) {
                this.postTitle = postTitle;
            }

            public int getPostId() {
                return postId;
            }

            public void setPostId(int postId) {
                this.postId = postId;
            }

            public int getIsGz() {
                return isGz;
            }

            public void setIsGz(int isGz) {
                this.isGz = isGz;
            }

            public List<?> getPostImagePaths() {
                return postImagePaths;
            }

            public void setPostImagePaths(List<?> postImagePaths) {
                this.postImagePaths = postImagePaths;
            }
        }
    }
}
