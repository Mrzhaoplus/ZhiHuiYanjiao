package www.diandianxing.com.diandianxing.fragment.mainfragment;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import www.diandianxing.com.diandianxing.adapter.Tuijiantieadapter;
import www.diandianxing.com.diandianxing.base.BaseFragment;
import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.base.Myapplication;
import www.diandianxing.com.diandianxing.bean.GuanzhuJD;
import www.diandianxing.com.diandianxing.bean.Sharebean;
import www.diandianxing.com.diandianxing.network.BaseObserver1;
import www.diandianxing.com.diandianxing.network.RetrofitManager;
import www.diandianxing.com.diandianxing.util.Api;
import www.diandianxing.com.diandianxing.util.MyContants;
import www.diandianxing.com.diandianxing.util.ShareListener;
import www.diandianxing.com.diandianxing.util.SpUtils;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class OldnewFragment extends BaseFragment {
    private ListView jiao_list;
    private SpringView jiao_spring;
    private List<GuanzhuJD> lists =new ArrayList<>();
    private int pageNo=1;
    Tuijiantieadapter jiaodianadapter;
    @Override
    protected int setContentView() {
        return R.layout.fragment_oldnew;
    }

    @Override
    protected void lazyLoad() {
        View contentView = getContentView();
        jiao_list = contentView.findViewById(R.id.jiaodan_list);
        jiao_spring = contentView.findViewById(R.id.jiao_springview);


        networklist();

        jiao_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Intent intent=new Intent(getActivity(),JiaoDetailActivity.class);
                startActivity(intent);
            }
        });
        jiao_spring.setType(SpringView.Type.FOLLOW);
        jiao_spring.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        lists.clear();
                        pageNo=1;
                        networklist();
                    }
                }, 0);
                jiao_spring.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pageNo++;
                        networklist();

                    }
                }, 0);
                jiao_spring.onFinishFreshAndLoad();
            }
        });
        jiao_spring.setFooter(new DefaultFooter(getActivity()));
        jiao_spring.setHeader(new DefaultHeader(getActivity()));

    }

    private void networklist() {

        HttpParams params = new HttpParams();
        params.put("pageNo", pageNo);
        params.put("token", Api.token);
        params.put("type",2);
        Log.d("TAG","数据内容"+params.toString());
        OkGo.<String>post(Api.BASE_URL +"app/home/postHotOrNew")
                .tag(this)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        String body = response.body();
                        Log.d("TAG", "内容：" + body);
                        JSONObject jsonobj = null;
                        try {
                            jsonobj = new JSONObject(body);
                            int code = jsonobj.getInt("code");
                            JSONArray datas = jsonobj.getJSONArray("datas");
                            List<GuanzhuJD> guanzhuJDs = new ArrayList<GuanzhuJD>();
                            if (code == 200) {
                                for(int i=0;i<datas.length();i++){

                                    JSONObject jo = datas.getJSONObject(i);

                                    GuanzhuJD guanzhu = new GuanzhuJD();
                                    guanzhu.id=jo.getString("id");
                                    guanzhu.userId=jo.getString("userId");
                                    guanzhu.postType=jo.getString("postType");
                                    guanzhu.postTitle=jo.getString("postTitle");
                                    guanzhu.postContent=jo.getString("postContent");
                                    guanzhu.createTime=jo.getString("createTime");
                                    guanzhu.updateTime=jo.getString("updateTime");
                                    guanzhu.address=jo.getString("address");
                                    guanzhu.postFlage=jo.getString("postFlage");
                                    guanzhu.postStatus=jo.getString("postStatus");
                                    guanzhu.isDeleted=jo.getString("isDeleted");
                                    guanzhu.userName=jo.getString("userName");
                                    guanzhu.userLevel=jo.getString("userLevel");
                                    guanzhu.postImge=jo.getString("postImge");
                                    guanzhu.commentCount=jo.getString("commentCount");
                                    guanzhu.dianZanCount=jo.getString("dianZanCount");
                                    guanzhu.collectCount=jo.getString("collectCount");
                                    guanzhu.relayCount=jo.getString("relayCount");
                                    guanzhu.pic=jo.getString("pic");
                                    guanzhu.is_collect=jo.getString("is_collect");
                                    guanzhu.is_zan=jo.getString("is_zan");
                                    guanzhu.is_fx=jo.getString("is_fx");
                                    guanzhu.is_focus=jo.getString("is_focus");
                                    JSONArray ja=jo.getJSONArray("imagesList");

                                    guanzhu.imagesList = new ArrayList<String>();
                                    for(int j=0;j<ja.length();j++){
                                        guanzhu.imagesList.add(ja.getString(j));
                                    }

                                    guanzhuJDs.add(guanzhu);


                                }
                                if(pageNo>1){

                                    if(guanzhuJDs.size()<=0){

                                        Toast.makeText(getActivity(),Api.TOAST,Toast.LENGTH_SHORT).show();

                                    }else{
                                        lists.addAll(guanzhuJDs);
                                    }

                                }else{

                                    lists.addAll(guanzhuJDs);

                                }

                                if(jiaodianadapter==null){
                                    jiaodianadapter=new Tuijiantieadapter(getActivity(),shareListener,lists);
                                    jiao_list.setAdapter(jiaodianadapter);
                                }else{

                                    jiaodianadapter.notifyDataSetChanged();
                                }


                            } else {
                                Toast.makeText(getActivity(),jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("TAG","解析失败了！！！");
                        }
                    }
                });
    }

    private ShareListener shareListener = new ShareListener() {
        @Override
        public void OnShareListener(int poss) {
            switch (poss){
                case 0:
                    SharebyWeixin(getActivity());
                    break;
                case 1:
                    SharebyWeixincenter(getActivity());
                    break;
                case 2:
                    SharebyQQ(getActivity());
                    break;
                case 3:
                    SharebyQzon(getActivity());
                    break;
                case 4:
                    break;
            }
        }
    };

    protected  void SharebyQQ(Activity context) {
        UMWeb web = new UMWeb("http://android.myapp.com/myapp/detail.htm?apkName=www.diandianxing.com.diandianxing&ADTAG=mobile");
        web.setTitle("智慧燕郊-点点行");//标题
        web.setThumb(new UMImage(context, R.drawable.img_diandianlogo));  //缩略图
        web.setDescription("点击下载“点点行”，开启燕郊骑行之旅~");//描述

        new ShareAction(context)
                .withMedia(web)
                .setPlatform(SHARE_MEDIA.QQ)//传入平台
                .withText("点击下载“点点行”，开启燕郊骑行之旅~")//分享内容
                .setCallback(umShareListener)//回调监听器
                .share();
    }

    protected  void SharebyQzon(Activity context) {
        UMWeb web = new UMWeb("http://android.myapp.com/myapp/detail.htm?apkName=www.diandianxing.com.diandianxing&ADTAG=mobile");
        web.setTitle("智慧燕郊-点点行");//标题
        web.setThumb(new UMImage(context,R.drawable.img_diandianlogo));  //缩略图
        web.setDescription("点击下载“点点行”，开启燕郊骑行之旅~");//描述
        new ShareAction(context)
                .setPlatform(SHARE_MEDIA.QZONE)//传入平台
                .withMedia(web)
                .withText("点击下载“点点行”，开启燕郊骑行之旅~")//分享内容
                .setCallback(umShareListener)//回调监听器
                .share();
    }

    protected  void SharebyWeixin(Activity context) {
        UMWeb web = new UMWeb("http://android.myapp.com/myapp/detail.htm?apkName=www.diandianxing.com.diandianxing&ADTAG=mobile");
        web.setTitle("智慧燕郊-点点行");//标题
        web.setThumb(new UMImage(context,R.drawable.img_diandianlogo));  //缩略图
        web.setDescription("点击下载“点点行”，开启燕郊骑行之旅~");//描述
        new ShareAction(context)
                .setPlatform(SHARE_MEDIA.WEIXIN)//传入平台
                .withMedia(web)
                .withText("点击下载“点点行”，开启燕郊骑行之旅~")//分享内容
                .setCallback(umShareListener)//回调监听器
                .share();
    }
    protected  void SharebyWeixincenter(Activity context) {
        UMWeb web = new UMWeb("http://android.myapp.com/myapp/detail.htm?apkName=www.diandianxing.com.diandianxing&ADTAG=mobile");
        web.setTitle("智慧燕郊-点点行");//标题
        web.setThumb(new UMImage(context,R.drawable.img_diandianlogo));  //缩略图
        web.setDescription("点击下载“点点行”，开启燕郊骑行之旅~");//描述
        new ShareAction(context)
                .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)//传入平台
                .withText("点击下载“点点行”，开启燕郊骑行之旅~")//分享内容
                .setCallback(umShareListener)//回调监听器
                .share();
    }
    public UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //分享开始的回调
        }

        @Override
        public void onResult( final  SHARE_MEDIA platform) {
            //            Log.d("plat", "platform" + platform);
             /*
               成功调用接口
              */
            String type = "";
            if (platform.equals(SHARE_MEDIA.WEIXIN)) {
                type = "1";

            } else if(platform.equals(SHARE_MEDIA.WEIXIN_CIRCLE)){
                type="2";

            }
            else if (platform.equals(SHARE_MEDIA.QQ)) {
                type = "3";
            } else if (platform.equals(SHARE_MEDIA.QZONE)) {
                type = "4";
            }
            //网络请求
            Map<String,String> map=new HashMap<>();
            map.put("uid", SpUtils.getString(getActivity(),"userid",null));
            map.put("type",type);
            map.put("token", SpUtils.getString(getActivity(),"token",null));
            map.put("platform",2+"");
            RetrofitManager.post(MyContants.BASEURL + "s=User/share", map, new BaseObserver1<Sharebean>("") {
                @Override
                public void onSuccess(Sharebean result, String tag) {
                    if(result.getCode()==200){
                        Toast.makeText(Myapplication.getGloableContext(), platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailed(int code,String data) {

                }
            });

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            // Toast.makeText(Myapplication.getGloableContext(), platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if (t != null) {
                //                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            //    Toast.makeText(Myapplication.getGloableContext(), platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };
}
