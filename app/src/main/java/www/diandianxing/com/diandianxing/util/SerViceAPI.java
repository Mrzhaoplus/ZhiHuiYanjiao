package www.diandianxing.com.diandianxing.util;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import www.diandianxing.com.diandianxing.ShujuBean.Home_zixun_Bean;
import www.diandianxing.com.diandianxing.ShujuBean.Live_Bean;
import www.diandianxing.com.diandianxing.ShujuBean.LuKuang_Bean;
import www.diandianxing.com.diandianxing.ShujuBean.Lunbo_Bean;

/**
 * Created by Mr赵 on 2018/4/18.
 */

public interface SerViceAPI {
      //轮播图
      @POST("app/home/carouselFigure")
      Observable<Lunbo_Bean> lun();
      //首页资讯
      @FormUrlEncoded
      @POST("app/home/getInformation")
      Observable<Home_zixun_Bean> zixun(@Field("type") int type);
      //生活服务
      @POST("app/home/getClassificationList")
      Observable<Live_Bean> live();

      //路况
      @FormUrlEncoded
      @POST("app/home/getShowTrafficList")
      Observable<LuKuang_Bean> lukuang(@Field("pageNo") int type);


}
