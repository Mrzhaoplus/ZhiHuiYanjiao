package www.diandianxing.com.diandianxing;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.LocationSource;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.umeng.socialize.media.Base;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.zackratos.ultimatebar.UltimateBar;

import java.util.ArrayList;
import java.util.List;

import www.diandianxing.com.diandianxing.Login.LoginActivity;
import www.diandianxing.com.diandianxing.base.BaseActivity;
import www.diandianxing.com.diandianxing.bean.FragEventBug;
import www.diandianxing.com.diandianxing.bean.Info;
import www.diandianxing.com.diandianxing.bean.MsgBus;
import www.diandianxing.com.diandianxing.fragment.HomeFragment;
import www.diandianxing.com.diandianxing.fragment.MessageFragment;
import www.diandianxing.com.diandianxing.fragment.MineFragment;
import www.diandianxing.com.diandianxing.fragment.PaikewFragment;
import www.diandianxing.com.diandianxing.util.AddPopwindow;
import www.diandianxing.com.diandianxing.util.ConUtils;
import www.diandianxing.com.diandianxing.util.MyContants;
import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.util.SpUtils;

/*
  zhuye
 */
public class MainActivity extends BaseActivity implements View.OnClickListener ,AMapLocationListener,LocationSource
        ,AMap.OnMapTouchListener{
    private FrameLayout fl;
    private RadioButton rb_home;
    private RadioButton rb_find;
    private RadioButton rb_message;
    private RadioButton rb_mine;
    private RadioGroup rgp;
    private LinearLayout activity_main;
    private Fragment currentf;
    private HomeFragment homeFragment;
    private PaikewFragment findFragment;
    private MessageFragment messageFragment;
    private MineFragment mineFragment;
    private ImageView frag_add;

    private long preTime;

    private int tag;

    //声明mlocationClient对象
    public AMapLocationClient mlocationClient;
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;


    //hahahahahha

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UltimateBar ultimateBar = new UltimateBar(this);
        ultimateBar.setImmersionBar();
        MyContants.windows(this);
        EventBus.getDefault().register(MainActivity.this);
        setContentView(R.layout.activity_main);
        homeFragment = new HomeFragment();
        addFragments(homeFragment);
        mlocationClient = new AMapLocationClient(this);
//初始化定位参数
        mLocationOption = new AMapLocationClientOption();
//设置定位监听
        mlocationClient.setLocationListener(this);
//设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
//设置定位参数
        mlocationClient.setLocationOption(mLocationOption);
// 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
// 注意设置合适的定位时间的间隔（最小间隔支持为1000ms），并且在合适时间调用stopLocation()方法来取消定位请求
// 在定位结束后，在合适的生命周期调用onDestroy()方法
// 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
//启动定位
        mlocationClient.startLocation();
        initView();

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void XXX(MsgBus messageEvent) {

        if("首页".equals(messageEvent.tiaozhuan)){
            rb_find.setChecked(true);
            if (findFragment == null) {
                findFragment = new PaikewFragment("关注");
            }
            addFragments(findFragment);
            tag=1;
            if(findFragment!=null){
                findFragment.Qiehuan();
            }
        }

    }

    private void initView() {
        fl = (FrameLayout) findViewById(R.id.fl);
        rb_home = (RadioButton) findViewById(R.id.rb_home);
        rb_find = (RadioButton) findViewById(R.id.rb_find);
        rb_message = (RadioButton) findViewById(R.id.rb_message);
        rb_mine = (RadioButton) findViewById(R.id.rb_mine);
        rgp = (RadioGroup) findViewById(R.id.rgp);
        activity_main = (LinearLayout) findViewById(R.id.activity_main);
        frag_add = (ImageView) findViewById(R.id.frag_add);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConUtils.TAG_ZT);
        registerReceiver(broadcastReceiver,intentFilter);

        rb_home.setOnClickListener(this);
        rb_find.setOnClickListener(this);
        rb_message.setOnClickListener(this);
        rb_mine.setOnClickListener(this);
        frag_add.setOnClickListener(this);
    }

    @Override
    public void activate(LocationSource.OnLocationChangedListener onLocationChangedListener) {

        if (mlocationClient == null) {
            mlocationClient = new AMapLocationClient(this);
            mLocationOption = new AMapLocationClientOption();
            //设置定位监听
            mlocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();
        }
    }

    @Override
    public void deactivate() {
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }

    private void addFragments(Fragment f) {
        // 第一步：得到fragment管理类
        FragmentManager manager = getSupportFragmentManager();
        // 第二步：开启一个事务
        FragmentTransaction transaction = manager.beginTransaction();

        if (currentf != null) {
            //每次把前一个fragment给隐藏了
            transaction.hide(currentf);
        }
        //isAdded:判断当前的fragment对象是否被加载过
        if (!f.isAdded()) {
            // 第三步：调用添加fragment的方法 第一个参数：容器的id 第二个参数：要放置的fragment的一个实例对象
            transaction.add(R.id.fl, f);
        }
        //显示当前的fragment
        transaction.show(f);
        // 第四步：提交
        transaction.commit();
        currentf = f;
    }
    List<LocalMedia> listAll = new ArrayList<>();
    private List<LocalMedia> selectList = new ArrayList<>();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Log.e("TAG","图片返回");
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    Log.e("TAG","图片选择结果回调");
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    listAll.addAll(selectList);
                    selectList.clear();
                    Info info = new Info();
                    info.list = new ArrayList<>();
                    info.list.addAll(listAll);
                    Intent intent = new Intent(MainActivity.this,ReleaseShootoffActivity.class);
                    intent.putExtra("list",info);
                    startActivity(intent);
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    public void onClick(View v) {
        int guid = SpUtils.getInt(MainActivity.this, "guid", 0);
        switch (v.getId()) {
            case R.id.rb_home:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                }
                addFragments(homeFragment);
                tag=0;
                break;
            case R.id.rb_find:
                if(guid!=2){
                    startActivity(new Intent(MainActivity.this,LoginActivity.class));
                    rb_find.setChecked(false);
                    RadioButton rb= (RadioButton) rgp.getChildAt(0);
                    rb.setChecked(true);
                }else{
                    if (findFragment == null) {
                        findFragment = new PaikewFragment("推荐");
                    }
                    addFragments(findFragment);
                }
//                tag=1;
                break;
            case R.id.rb_message:
                if(guid!=2){
                    startActivity(new Intent(MainActivity.this,LoginActivity.class));
                    rb_message.setChecked(false);
                    RadioButton rb= (RadioButton) rgp.getChildAt(0);
                    rb.setChecked(true);
                }else{
                    if (messageFragment == null) {
                        messageFragment = new MessageFragment();
                    }
                    addFragments(messageFragment);
                }
//                tag=3;
                break;
            case R.id.rb_mine:
                if(guid!=2){
                    startActivity(new Intent(MainActivity.this,LoginActivity.class));
                    rb_mine.setChecked(false);
                    RadioButton rb= (RadioButton) rgp.getChildAt(0);
                    rb.setChecked(true);
                }
                else {
                    if (mineFragment == null) {
                        mineFragment = new MineFragment();
                    }
                    addFragments(mineFragment);
                }
                break;
            case R.id.frag_add:
                AddPopwindow popWindow = new AddPopwindow(MainActivity.this);
                popWindow.showMoreWindow(v);
                //加号
                break;

        }
    }

    /*
       返回键退出程序
     */
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() > preTime + 2000) {
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            preTime = System.currentTimeMillis();
        } else {
            super.onBackPressed();//相当于finish()
            BaseActivity.realBack();//删除所有引用
        }

    }


    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if(ConUtils.TAG_ZT.equals(intent.getAction())){
                tag=0;
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                }
                addFragments(homeFragment);
                RadioButton rb= (RadioButton) rgp.getChildAt(tag);
                rb.setChecked(true);
            }
        }
    };


    @Override
    public void onTouch(MotionEvent motionEvent) {
        mlocationClient.stopLocation();
    }


    @Override
    public void onLocationChanged(AMapLocation amapLocation) {

//        if (mListener != null && aMapLocation != null) {
//            if (aMapLocation != null
//                    && aMapLocation.getErrorCode() == 0) {
//                mListener.onLocationChanged(aMapLocation);// 显示系统小蓝点
//                SpUtils.putString(this,"la",  aMapLocation.getLatitude()+"");
//                SpUtils.putString(this,"lo",aMapLocation.getLongitude()+"");
//                Log.d("ass", aMapLocation.getLatitude()+"dddd"+aMapLocation.getLongitude()+"") ;
//
//
//            } else {
//                String errText = "定位失败," + aMapLocation.getErrorCode()+ ": " + aMapLocation.getErrorInfo();
//                Log.e("AmapErr",errText);
//            }
//        }

        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
//                amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
//                amapLocation.getLatitude();//获取纬度
//                amapLocation.getLongitude();//获取经度
//                amapLocation.getAccuracy();//获取精度信息
                SpUtils.putString(this,"la",  amapLocation.getLatitude()+"");
                SpUtils.putString(this,"lo",amapLocation.getLongitude()+"");
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("TAG","location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
            }
        }

    }


}



