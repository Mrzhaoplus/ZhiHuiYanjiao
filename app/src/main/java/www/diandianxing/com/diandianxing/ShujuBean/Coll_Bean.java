package www.diandianxing.com.diandianxing.ShujuBean;

import java.util.List;

/**
 * Created by Mr赵 on 2018/4/26.
 */

public class Coll_Bean {

    /**
     * msg : 请求成功
     * code : 200
     * datas : [{"id":0,"nickname":"","password":"","contact":"","credit":0,"balance":0,"mileage":0,"token":"","deposit":0,"depositStatus":0,"doubles":0,"idIdent":0,"type":0,"addTime":1525934153997,"encrypt":"","lastTime":1525934153997,"ridingState":0,"userId":4571,"pic":"http://47.93.45.38/test/uploads/head/20180502/5ae922f5a7469.jpg","nickName":"赵","postImage":"/uploadImages/front/2018-05-08/a_dheeqeporp_358.png","postTitle":"","postId":105,"isGz":0,"postImagePaths":["http://47.93.45.38:8080/uploadImages/front/2018-05-08/a_dheeqeporp_358.png"]}]
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
         * nickname :
         * password :
         * contact :
         * credit : 0
         * balance : 0
         * mileage : 0
         * token :
         * deposit : 0
         * depositStatus : 0
         * doubles : 0
         * idIdent : 0
         * type : 0
         * addTime : 1525934153997
         * encrypt :
         * lastTime : 1525934153997
         * ridingState : 0
         * userId : 4571
         * pic : http://47.93.45.38/test/uploads/head/20180502/5ae922f5a7469.jpg
         * nickName : 赵
         * postImage : /uploadImages/front/2018-05-08/a_dheeqeporp_358.png
         * postTitle :
         * postId : 105
         * isGz : 0
         * postImagePaths : ["http://47.93.45.38:8080/uploadImages/front/2018-05-08/a_dheeqeporp_358.png"]
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
        private int depositStatus;
        private int doubles;
        private int idIdent;
        private int type;
        private long addTime;
        private String encrypt;
        private long lastTime;
        private int ridingState;
        private int userId;
        private String pic;
        private String nickName;
        private String postImage;
        private String postTitle;
        private int postId;
        private int isGz;
        private List<String> postImagePaths;

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

        public int getDepositStatus() {
            return depositStatus;
        }

        public void setDepositStatus(int depositStatus) {
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

        public int getRidingState() {
            return ridingState;
        }

        public void setRidingState(int ridingState) {
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

        public List<String> getPostImagePaths() {
            return postImagePaths;
        }

        public void setPostImagePaths(List<String> postImagePaths) {
            this.postImagePaths = postImagePaths;
        }
    }
}
