package www.diandianxing.com.diandianxing.fragment;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import www.diandianxing.com.diandianxing.MyCollectionActivity;
import www.diandianxing.com.diandianxing.ReleaseShootoffVidoActivity;
import www.diandianxing.com.diandianxing.SignActivity;
import www.diandianxing.com.diandianxing.base.BaseFragment;
import www.diandianxing.com.diandianxing.bean.Event_coll_size;
import www.diandianxing.com.diandianxing.bean.MsgBus;
import www.diandianxing.com.diandianxing.bean.UserInfo;
import www.diandianxing.com.diandianxing.bean.UserMsg1;
import www.diandianxing.com.diandianxing.bean.UserMsg2;
import www.diandianxing.com.diandianxing.fragment.minefragment.MyFansiActivity;
import www.diandianxing.com.diandianxing.fragment.minefragment.MyFllowActivity;
import www.diandianxing.com.diandianxing.fragment.minefragment.MydynamicActivity;
import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.my.JourActivity;
import www.diandianxing.com.diandianxing.my.PersonActivity;
import www.diandianxing.com.diandianxing.my.ShareActivity;
import www.diandianxing.com.diandianxing.set.AboutweActivity;
import www.diandianxing.com.diandianxing.set.Feedback;
import www.diandianxing.com.diandianxing.set.SetActivity;
import www.diandianxing.com.diandianxing.util.Api;
import www.diandianxing.com.diandianxing.util.BaseDialog;
import www.diandianxing.com.diandianxing.util.EventMessage;
import www.diandianxing.com.diandianxing.util.GlobalParams;
import www.diandianxing.com.diandianxing.util.MyContants;
import www.diandianxing.com.diandianxing.util.NetUtil;
import www.diandianxing.com.diandianxing.util.SpUtils;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class MineFragment extends BaseFragment implements View.OnClickListener{
    public View rootView;
    public TextView text_day;
    public ImageView imageView2;
    public TextView guan_num;
    public LinearLayout text_guanzhu;
    public TextView fen_num;
    public LinearLayout text_fensi;
    public TextView dong_num;
    public LinearLayout text_dongtai;
    public TextView collect_num;
    public LinearLayout text_collect;
    public TextView text_qiandao;
    public TextView my_car;
    public RelativeLayout real_car;
    public TextView text_about;
    public RelativeLayout about_we;
    public TextView text_suggest;
    public RelativeLayout real_suggest;
    public TextView text_kefu;
    public RelativeLayout real_kefu;
    public TextView text_yaoqing;
    public RelativeLayout real_yaoqing;
    private ImageView iv_grtx;
    private ImageView iv_sz;
    private TextView tv_zy_name;
    private ImageView iv_sex;
    private TextView tv_zy_dj;
    private ImageView iv_bg_my;
    private boolean flag=true;
    @Override
    protected int setContentView() {

        return R.layout.fragment_mine;
    }

    @Override
    protected void lazyLoad() {
        View contentView = getContentView();
        EventBus.getDefault().register(this);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(GlobalParams.TOU_SX);
        intentFilter.addAction(GlobalParams.LOGING_SX);
        getActivity().registerReceiver(broadcastReceiver,intentFilter);

        this.text_day = (TextView) contentView.findViewById(R.id.text_day);
        this.imageView2 = (ImageView) contentView.findViewById(R.id.imageView2);
        this.guan_num = (TextView) contentView.findViewById(R.id.guan_num);
        this.text_guanzhu = (LinearLayout) contentView.findViewById(R.id.text_guanzhu);
        this.fen_num = (TextView) contentView.findViewById(R.id.fen_num);
        this.text_fensi = (LinearLayout) contentView.findViewById(R.id.text_fensi);
        this.dong_num = (TextView) contentView.findViewById(R.id.dong_num);
        this.text_dongtai = (LinearLayout) contentView.findViewById(R.id.text_dongtai);
        this.collect_num = (TextView) contentView.findViewById(R.id.collect_num);
        this.text_collect = (LinearLayout) contentView.findViewById(R.id.text_collect);
        this.text_qiandao = (TextView) contentView.findViewById(R.id.text_qiandao);
        this.my_car = (TextView) contentView.findViewById(R.id.my_car);
        this.real_car = (RelativeLayout) contentView.findViewById(R.id.real_car);
        this.text_about = (TextView) contentView.findViewById(R.id.text_about);
        this.about_we = (RelativeLayout) contentView.findViewById(R.id.about_we);
        this.text_suggest = (TextView) contentView.findViewById(R.id.text_suggest);
        this.real_suggest = (RelativeLayout) contentView.findViewById(R.id.real_suggest);
        this.text_kefu = (TextView) contentView.findViewById(R.id.text_kefu);
        tv_zy_name=contentView.findViewById(R.id.tv_zy_name);
        iv_sex=contentView.findViewById(R.id.iv_sex);
        tv_zy_dj=contentView.findViewById(R.id.tv_zy_dj);
        iv_bg_my=contentView.findViewById(R.id.iv_bg_my);
        this.real_kefu = (RelativeLayout) contentView.findViewById(R.id.real_kefu);
        this.text_yaoqing = (TextView) contentView.findViewById(R.id.text_yaoqing);
        this.real_yaoqing = (RelativeLayout) contentView.findViewById(R.id.real_yaoqing);
        iv_sz=contentView.findViewById(R.id.iv_sz);
        iv_grtx=contentView.findViewById(R.id.iv_grtx);
        if(NetUtil.checkNet(getActivity())){
            network();
        }else{
            Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
        }
        text_guanzhu.setOnClickListener(this);//设置监听
        text_fensi.setOnClickListener(this);
        text_dongtai.setOnClickListener(this);
        text_collect.setOnClickListener(this);
        text_qiandao.setOnClickListener(this);
        iv_grtx.setOnClickListener(this);
        real_car.setOnClickListener(this);
        about_we.setOnClickListener(this);
        real_suggest.setOnClickListener(this);
        real_kefu.setOnClickListener(this);
        real_yaoqing.setOnClickListener(this);
        iv_sz.setOnClickListener(this);

        //注册eventbus
        /*if(flag){
            //注册
            EventBus.getDefault().register(this);
            flag=false;
        }*/


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void XXX(EventMessage messageEvent) {
//        Drawable fromPath = Drawable.createFromPath(messageEvent.getMsg());
//        iv_bg_my.setImageDrawable(fromPath);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void fff(Event_coll_size msg) {
        int state = msg.getState();
        int size = msg.getSize();
        if(state==3){
            collect_num.setText(size+"");
        }else if(state==2){
            fen_num.setText(size+"");
        }else if(state==1){
            guan_num.setText(size+"");
        }else if(state==4){
            network();
        }


    }


    @Override
    public void onClick(View view) {

         switch (view.getId()){
             case R.id.text_guanzhu://关注
                 Intent intent=new Intent(getActivity(), MyFllowActivity.class);
                 startActivity(intent);
                 break;

             case R.id.text_fensi://粉丝
                 Intent intent1=new Intent(getActivity(), MyFansiActivity.class);
                 intent1.putExtra("uid", SpUtils.getString(getActivity(),"token",null));
                 startActivity(intent1);
                 break;
             case R.id.text_dongtai:
                 if(userInfo!=null){
                     Intent intent2=new Intent(getActivity(), MydynamicActivity.class);
                     intent2.putExtra("title","我的主页");
                     intent2.putExtra("userInfo",userInfo);
                     startActivity(intent2);
                 }
                 break;
             case R.id.text_collect:
                 Intent intent3=new Intent(getActivity(), MyCollectionActivity.class);
                 startActivity(intent3);
                 break;
             case R.id.text_qiandao:
                 Intent intent4=new Intent(getActivity(), SignActivity.class);
                 startActivity(intent4);
                 break;
             case R.id.iv_grtx:
                if(userInfo!=null){
                         Intent intent5=new Intent(getActivity(), MydynamicActivity.class);
                         intent5.putExtra("title","我的主页");
                    intent5.putExtra("userInfo",userInfo);
                         startActivity(intent5);
                }
                 break;
             case R.id.real_car:
                 Intent intent6=new Intent(getActivity(),JourActivity.class);
                 startActivity(intent6);
                 break;
             case R.id.about_we:
                 Intent gy=new Intent(getActivity(),AboutweActivity.class);
                 startActivity(gy);
                 break;
             //意见反馈
             case R.id.real_suggest:
                 Intent yjfk=new Intent(getActivity(),Feedback.class);
                 startActivity(yjfk);
                 break;
             case R.id.real_kefu:

                 networkmoney();


                 break;
             case R.id.real_yaoqing:
                 Intent fx=new Intent(getActivity(),ShareActivity.class);
                 startActivity(fx);
                 break;

             //设置
             case R.id.iv_sz:
                 Intent sz=new Intent(getActivity(), SetActivity.class);
                 startActivity(sz);
                 break;

         }

    }

    private void networkmoney() {
        OkGo.<String>get(MyContants.BASEURL +"s=Login/aboutContact")
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        String body = response.body();
                        Log.d("TAG","关于"+body);
                        try {
                            JSONObject jsonobj = new JSONObject(body);
                            int code = jsonobj.getInt("code");
                            String datas = jsonobj.getString("datas");
                            if (code == 200) {

                                JSONObject jo = new JSONObject(datas);

                                String tel=jo.getString("service_tel");
                                showphotoDialog(Gravity.BOTTOM,R.style.Bottom_Top_aniamtion,tel);


                            } else {
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                });
//        Map<String,String> map=new HashMap<>();
//         map.put("uid",SpUtils.getString(this,"userid",null));
//         map.put("token",SpUtils.getString(this,"token",null));
//        RetrofitManager.post(MyContants.BASEURL+"s=Payment/refundDeposit", map, new BaseObserver1<Tuikuanbean>("") {
//            @Override
//            public void onSuccess(Tuikuanbean result, String tag) {
//                if(result.getCode()==200){
//
//                }
//            }
//
//            @Override
//            public void onFailed(int code, String data) {
//
//            }
//        });
//    }
    }

    private void showphotoDialog(int grary, int animationStyle, final String tel) {
        BaseDialog.Builder builder = new BaseDialog.Builder(getActivity());
        final BaseDialog dialog = builder.setViewId(R.layout.dialog_kefu)
                //设置dialogpadding
                .setPaddingdp(0, 0, 0, 0)
                //设置显示位置
                .setGravity(grary)
                //设置动画
                .setAnimation(animationStyle)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(true)
                //设置监听事件
                .builder();


        TextView text_kefu=dialog.getView(R.id.text_kefu);
        text_kefu.setText(tel);
        //拍照
        dialog.getView(R.id.text_kefu).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                //相机选取

                dialog.dismiss();
                if(getActivity().checkSelfPermission("android.permission.CALL_PHONE")!= PackageManager.PERMISSION_GRANTED){

                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE},1);

                }

                Intent dialIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + tel));
                //跳转到拨号界面，同时传递电话号码
                startActivity(dialIntent);
            }
        });

        dialog.show();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        EventBus.getDefault().removeAllStickyEvents();
        getActivity().unregisterReceiver(broadcastReceiver);
    }


    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();

            if(GlobalParams.TOU_SX.equals(action)){

                String tou = intent.getStringExtra("tou");
                String name = intent.getStringExtra("name");
                String sex = intent.getStringExtra("sex");
                if(sex!=null&&sex.length()>0){
                    if(Integer.parseInt(sex)==0){//男

                        iv_sex.setImageResource(R.drawable.icon_boy);

                    }else{

                        iv_sex.setImageResource(R.drawable.icon_girl);

                    }
                }

                if(name!=null&&name.length()>0){
                    tv_zy_name.setText(name);
                }
                if(tou!=null&&tou.length()>0){
                    Glide.with(getActivity()).load(tou).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv_grtx);
                }

            }else if(GlobalParams.LOGING_SX.equals(action)){
                if(NetUtil.checkNet(getActivity())){
                    network();
                }else{
                    Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                }
            }

        }
    };


    UserInfo userInfo;
    private void network() {

        HttpParams params = new HttpParams();
        params.put("token", SpUtils.getString(getActivity(),"token",null));
        Log.d("TAG","数据内容"+params.toString());
        OkGo.<String>post(Api.BASE_URL +"app/user/myhome")
                .tag(this)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        Log.d("TAG", "我的页面：" + body);
                        JSONObject jsonobj = null;
                        try {
                            jsonobj = new JSONObject(body);
                            int code = jsonobj.getInt("code");
                            JSONObject datas = jsonobj.getJSONObject("datas");
                            if (code == 200) {

                                userInfo = new UserInfo();
                                userInfo.dtNum=datas.getString("dtNum");
                                userInfo.scnum=datas.getString("scnum");
                                userInfo.gznum=datas.getString("gznum");
                                userInfo.days=datas.getString("days");
                                userInfo.fsnum=datas.getString("fsnum");

                                userInfo.userMsg1 = new UserMsg1();
                                JSONObject jo1 = datas.getJSONObject("user");
                                userInfo.userMsg1.id=jo1.getString("id");
                                userInfo.userMsg1.nickname=jo1.getString("nickname");
                                userInfo.userMsg1.password=jo1.getString("password");
                                userInfo.userMsg1.contact=jo1.getString("contact");
                                userInfo.userMsg1.credit=jo1.getString("credit");
                                userInfo.userMsg1.balance=jo1.getString("balance");
                                userInfo.userMsg1.mileage=jo1.getString("mileage");
                                userInfo.userMsg1.token=jo1.getString("token");
                                userInfo.userMsg1.deposit=jo1.getString("deposit");
                                userInfo.userMsg1.depositStatus=jo1.getString("depositStatus");
                                userInfo.userMsg1.doubles=jo1.getString("doubles");
                                userInfo.userMsg1.idIdent=jo1.getString("idIdent");
                                userInfo.userMsg1.type=jo1.getString("type");
                                userInfo.userMsg1.addTime=jo1.getString("addTime");
                                userInfo.userMsg1.encrypt=jo1.getString("encrypt");
                                userInfo.userMsg1.lastTime=jo1.getString("lastTime");
                                userInfo.userMsg1.ridingState=jo1.getString("ridingState");

                                JSONObject jo2 = datas.getJSONObject("userinfo");
                                userInfo.userMsg2 = new UserMsg2();
                                userInfo.userMsg2.id=jo2.getString("id");
                                userInfo.userMsg2.uid=jo2.getString("uid");
                                userInfo.userMsg2.pic=jo2.getString("pic");
                                userInfo.userMsg2.wechat=jo2.getString("wechat");
                                userInfo.userMsg2.tencentQq=jo2.getString("tencentQq");
                                userInfo.userMsg2.microblog=jo2.getString("microblog");
                                userInfo.userMsg2.wechatName=jo2.getString("wechatName");
                                userInfo.userMsg2.qqName=jo2.getString("qqName");
                                userInfo.userMsg2.sinaName=jo2.getString("sinaName");
                                userInfo.userMsg2.uname=jo2.getString("uname");
                                userInfo.userMsg2.identPic=jo2.getString("identPic");
                                userInfo.userMsg2.identNum=jo2.getString("identNum");
                                userInfo.userMsg2.nickName=jo2.getString("nickName");
                                userInfo.userMsg2.sort=jo2.getString("sort");
                                userInfo.userMsg2.zan=jo2.getString("zan");
                                userInfo.userMsg2.userLevel=jo2.getString("userLevel");



                                guan_num.setText(userInfo.gznum);
                                fen_num.setText(userInfo.fsnum);
                                dong_num.setText(userInfo.dtNum);
                                collect_num.setText(userInfo.scnum);
                                text_day.setText("加入我们"+userInfo.days+"天");

                                if(userInfo.userMsg2.pic.length()>0){
                                    Glide.with(getActivity()).load(userInfo.userMsg2.pic).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv_grtx);
                                }



                                tv_zy_name.setText(userInfo.userMsg1.nickname);
                                tv_zy_dj.setText(userInfo.userMsg2.userLevel);

                                if(Integer.parseInt(jo2.getString("userSex"))==0){//男

                                    iv_sex.setImageResource(R.drawable.icon_boy);

                                }else{

                                    iv_sex.setImageResource(R.drawable.icon_girl);

                                }

//                                Glide.with(getActivity()).load(datas.getString("imageurl")).into(iv_bg_my);

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

}
