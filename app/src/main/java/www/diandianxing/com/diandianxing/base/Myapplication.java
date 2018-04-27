package www.diandianxing.com.diandianxing.base;

import android.app.ActivityManager;
import android.app.Application;
import android.app.Notification;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.entity.UMessage;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import org.greenrobot.eventbus.EventBus;

import java.util.Iterator;
import java.util.List;

import www.diandianxing.com.diandianxing.util.EventMessage;
import www.diandianxing.com.diandianxing.util.ImageLoder;
import www.diandianxing.com.diandianxing.util.SpUtils;
import www.diandianxing.com.diandianxing.R;

/**
 * Created by ASUS on 2018/3/19.
 */

public class Myapplication extends MultiDexApplication {

    public static Myapplication application;

    public static Myapplication getInstance() {
        return application;
    }

    public static Myapplication getAppContext() {
        return application;
    }
    public static Context getGloableContext()    {
        return  application.getApplicationContext();
    }
    private PushAgent mPushAgent;

    @Override
    public void onCreate() {
        super.onCreate();

        application = this;
        initUMShare();
        Config.DEBUG = true;
//        initUMPush();
//        UMPush();
        ImageLoder.info(this);
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder() //
                .showImageForEmptyUri(R.drawable.ic_launcher) //
                .showImageOnFail(R.drawable.ic_launcher) //
                .cacheInMemory(true) //
                .cacheOnDisk(true) //
                .build();//
        ImageLoaderConfiguration config = new ImageLoaderConfiguration//
                .Builder(getApplicationContext())//
                .defaultDisplayImageOptions(defaultOptions)//
                .discCacheSize(50 * 1024 * 1024)//
                .discCacheFileCount(100)// 缓存一百张图片
                .writeDebugLogs()//
                .build();//
        ImageLoader.getInstance().init(config);
    }

    private void initUMShare() {
        Config.DEBUG = true;
        UMShareAPI.get(this);
        PlatformConfig.setWeixin("wx6d0a0a32729dfd2c", "13fafd760421ce5b34f124d9cae2df21");
        PlatformConfig.setQQZone("1106567631", "QRCByySV1Wqbc93c");
        PlatformConfig.setSinaWeibo("557964441", "b52b29e8a5393bd34e2315e509fb5842", "http://www.baidu.com");//回调地址要跟微博开放平台的一样
    }

    public static Myapplication getApplication() {
        if (application == null) {
            application = getApplication();
        }
        return application;
    }

    private void initUMPush() {
        mPushAgent = PushAgent.getInstance(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                //注册推送服务，每次调用register方法都会回调该接口
                mPushAgent.register(new IUmengRegisterCallback() {

                    @Override
                    public void onSuccess(String deviceToken) {
                        //                Toast.makeText(MyApplication.this, "注册成功" + deviceToken, Toast.LENGTH_SHORT).show();
                        System.out.println("友盟推送注册成功" + deviceToken);
                        //注册成功会返回device token
                        //                mRegistrationId = mPushAgent.getRegistrationId();
                        //                Toast.makeText(MyApplication.this, "注册成功" + mRegistrationId, Toast.LENGTH_SHORT).show();
                        //                SpUtils.putString(instance, "UMPUSHID", mRegistrationId);
                        SpUtils.putString(application, "UMPUSHID", deviceToken);
                    }

                    @Override
                    public void onFailure(String s, String s1) {
                        //                Toast.makeText(MyApplication.this, "注册失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();


    }

    private void UMPush() {
        UmengMessageHandler umengMessageHandler=new UmengMessageHandler(){
            @Override
            public Notification getNotification(Context context, UMessage uMessage) {

                new Handler(getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        EventMessage eventMessage=new EventMessage("tuisong");
                        EventBus.getDefault().postSticky(eventMessage);
                        Log.d("Umne","-------成功执行---");
                    }
                });
                return super.getNotification(context, uMessage);
            }

        };
        mPushAgent.setMessageHandler(umengMessageHandler);
    }

    private String getAppName(int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = this.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pID) {
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                // Log.d("Process", "Error>> :"+ e.toString());
            }
        }
        return processName;
    }
}
