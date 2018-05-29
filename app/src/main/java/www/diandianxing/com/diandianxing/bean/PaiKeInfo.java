package www.diandianxing.com.diandianxing.bean;

import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/4/20.
 */

public class PaiKeInfo extends DataSupport implements Serializable{

    public String pkid;
    public String imageUrl;
    public String mvUrl;
    public String userId;
    public String address;
    public String userName;
    public String userLevel;
    public String isRecommend;
    public String pkOperation;
    public String createTime;
    public String isDeleted;
    public String pkTitle;
    public String imagesUrl;
    public String pkContent;
    public String commentCount;
    public String dianZanCount;
    public String collectCount;
    public String relayCount;
    public String nickName;
    public String pic;
    public List<String> paths;
    public String isZan;
    public String iszan;
    public String zannum;
    public String plnum;
    public String isgz;

    public String getId() {
        return pkid;
    }

    public void setId(String id) {
        this.pkid = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMvUrl() {
        return mvUrl;
    }

    public void setMvUrl(String mvUrl) {
        this.mvUrl = mvUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public String getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(String isRecommend) {
        this.isRecommend = isRecommend;
    }

    public String getPkOperation() {
        return pkOperation;
    }

    public void setPkOperation(String pkOperation) {
        this.pkOperation = pkOperation;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getPkTitle() {
        return pkTitle;
    }

    public void setPkTitle(String pkTitle) {
        this.pkTitle = pkTitle;
    }

    public String getImagesUrl() {
        return imagesUrl;
    }

    public void setImagesUrl(String imagesUrl) {
        this.imagesUrl = imagesUrl;
    }

    public String getPkContent() {
        return pkContent;
    }

    public void setPkContent(String pkContent) {
        this.pkContent = pkContent;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public String getDianZanCount() {
        return dianZanCount;
    }

    public void setDianZanCount(String dianZanCount) {
        this.dianZanCount = dianZanCount;
    }

    public String getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(String collectCount) {
        this.collectCount = collectCount;
    }

    public String getRelayCount() {
        return relayCount;
    }

    public void setRelayCount(String relayCount) {
        this.relayCount = relayCount;
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

    public List<String> getPaths() {
        return paths;
    }

    public void setPaths(List<String> paths) {
        this.paths = paths;
    }

    public String getIsZan() {
        return isZan;
    }

    public void setIsZan(String isZan) {
        this.isZan = isZan;
    }

    public String getIszan() {
        return iszan;
    }

    public void setIszan(String iszan) {
        this.iszan = iszan;
    }

    public String getZannum() {
        return zannum;
    }

    public void setZannum(String zannum) {
        this.zannum = zannum;
    }

    public String getPlnum() {
        return plnum;
    }

    public void setPlnum(String plnum) {
        this.plnum = plnum;
    }

    public String getIsgz() {
        return isgz;
    }

    public void setIsgz(String isgz) {
        this.isgz = isgz;
    }
}
