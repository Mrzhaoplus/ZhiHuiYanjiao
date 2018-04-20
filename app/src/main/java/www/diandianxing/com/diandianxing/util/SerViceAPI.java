package www.diandianxing.com.diandianxing.util;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import www.diandianxing.com.diandianxing.ShujuBean.DianzanAndFenxiang_Bean;
import www.diandianxing.com.diandianxing.ShujuBean.Live_gunzhu_Bean;
import www.diandianxing.com.diandianxing.ShujuBean.zixun_Bean;
import www.diandianxing.com.diandianxing.ShujuBean.Live_Bean;
import www.diandianxing.com.diandianxing.ShujuBean.LuKuang_Bean;
import www.diandianxing.com.diandianxing.ShujuBean.Lunbo_Bean;
import www.diandianxing.com.diandianxing.model.SouLabel_bean;

/**
 * Created by Mr赵 on 2018/4/18.
 */

public interface SerViceAPI {
      //轮播图
      @FormUrlEncoded
      @POST("app/home/carouselFigure")
      Observable<Lunbo_Bean> lun(@Field("token") String token);
      //资讯
      @FormUrlEncoded
      @POST("app/home/getInformation")
      Observable<zixun_Bean> zixun(@Field("type") int type,@Field("token") String token,@Field("typeNo") int typeNo);
      //生活服务
      @FormUrlEncoded
      @POST("app/home/getClassificationList")
      Observable<Live_Bean> live(@Field("token") String token);
      //路况
      @FormUrlEncoded
      @POST("app/home/getShowTrafficList")
      Observable<LuKuang_Bean> lukuang(@Field("pageNo") int type,@Field("token") String token);
      //首页搜索标签
      @FormUrlEncoded
      @POST("app/home/searchLabel")
      Observable<SouLabel_bean> soulabel(@Field("token") String token);

      //生活服务下关注
      @FormUrlEncoded
      @POST("app/home/focusAttention")
      Observable<Live_gunzhu_Bean> live_guanzhu(@Field("token") String token,@Field("pageNo") int type);
      //点赞或者关注
      @FormUrlEncoded
      @POST("app/home/focusAttention")
      Observable<DianzanAndFenxiang_Bean> zanandguan(@Field("token") String token, @Field("objId") int objId,@Field("obj_type")int obj_type,@Field("operation_type")int operation_type);




}
