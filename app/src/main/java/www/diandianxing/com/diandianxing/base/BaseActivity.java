package www.diandianxing.com.diandianxing.base;

import android.Manifest;
import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.danikula.videocache.HttpProxyCacheServer;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.entity.UMessage;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.bean.Sharebean;
import www.diandianxing.com.diandianxing.network.BaseObserver1;
import www.diandianxing.com.diandianxing.network.RetrofitManager;
import www.diandianxing.com.diandianxing.util.MyContants;
import www.diandianxing.com.diandianxing.util.MyUtils;
import www.diandianxing.com.diandianxing.util.SpUtils;


/**
 * Created by lxk on 2017/6/10.
 */

public class BaseActivity extends AppCompatActivity {
    private static List<Activity> activityList = new ArrayList<>();
    private static int MY_PERMISSION_REQUEST_CODE=1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在这里判断是否token是否存在、是否过期之类的
        if (activityList != null)
            activityList.add(this);
        boolean isPermission = checkSelfPermissionAll(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA,Manifest.permission.RECORD_AUDIO,Manifest.permission.ACCESS_FINE_LOCATION});
        if (isPermission) {
//            Toast.makeText(MainActivity.this, "正在查看!", Toast.LENGTH_SHORT).show();
            return;
        }
//        请求权限
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA,Manifest.permission.RECORD_AUDIO,Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_REQUEST_CODE);

        initPush();
    }



//    /** 判断触摸时间派发间隔 */
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
//            if (MyUtils.isFastDoubleClick()) {
//                return true;
//            }
//        }
//        return super.dispatchTouchEvent(ev);
//    }


            private void initPush() {
                PushAgent mPushAgent = PushAgent.getInstance(this);
                UmengMessageHandler umengMessageHandler = new UmengMessageHandler() {
                    @Override
                    public Notification getNotification(Context context, UMessage uMessage) {
                        Toast.makeText(context, "收到", Toast.LENGTH_SHORT).show();
                Log.d("MyApplication", uMessage.text + "------------------------------------------");
//                if (uMessage.text.equals("该用户已被停用")) {
//                    EventMessageCheck eventMessageCheck=new EventMessageCheck();
//                    eventMessageCheck.setCheckNum(101);
//                    EventBus.getDefault().postSticky(eventMessageCheck);
//                }

                return super.getNotification(context, uMessage);
            }
        };
        mPushAgent.setMessageHandler(umengMessageHandler);
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSION_REQUEST_CODE) {
            boolean isPermission = true;
            for (int grant : grantResults) {
                // 判断是否所有的权限都已经授予了
                if (grant != PackageManager.PERMISSION_GRANTED) {
                    isPermission = false;
                    break;
                }
            }
            if (isPermission) {
//                Toast.makeText(BaseActivity.this, "我看看", Toast.LENGTH_SHORT).show();
            } else {
                // 弹出对话框告诉用户需要权限的原因, 并引导用户去应用权限管理中手动打开权限按钮
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("备份通讯录需要访问")
                        .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent=new Intent();
                                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                intent.addCategory(Intent.CATEGORY_DEFAULT);
                                intent.setData(Uri.parse("package:" + getPackageName()));
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("退出",null);
                builder.show();
            }
        }
    }

    //    检查是否拥有指定的所有权限
    private boolean checkSelfPermissionAll(String[] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        //        overridePendingTransition(R.anim.start_activity_in, R.anim.start_activity_out);
    }

    @Override
    public void finish() {
        super.finish();
        //        overridePendingTransition(R.anim.finish_activity_in, R.anim.finish_activity_out);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (activityList != null)
            activityList.remove(this);
    }

    /*
    * 必须调用 MobclickAgent.onResume() 和 MobclickAgent.onPause()方法，
    * 才能够保证获取正确的新增用户、活跃用户、启动次数、使用时长等基本数据。
    * 这两个方法是用来统计应用时长的(也就是Session时长,当然还包括一些其他功能)
    * MobclickAgent.onPageStart() 和 MobclickAgent.onPageEnd() 方法是用来统计页面跳转的
    * 在仅有Activity的应用中，SDK 自动帮助开发者调用了上面的方法，并把Activity 类名作为页面名称统计。
    * 但是在包含fragment的程序中我们希望统计更详细的页面，所以需要自己调用方法做更详细的统计。
    * */
    @Override
    protected void onResume() {
        super.onResume();
       // MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
      //  MobclickAgent.onPause(this);
    }

    public static List<Activity> getAllActivitys() {
        return activityList;
    }

    public static void removeAllActivitys() {
        if (activityList != null && activityList.size() > 0) {
            for (int i = 0; i < activityList.size(); i++) {
                if (activityList.get(i) != null) {
                    activityList.get(i).finish();
                }
            }
            activityList.clear();
            //            activityList = null;
        }
    }

    public static void realBack() {
        if (activityList != null && activityList.size() > 0) {
            for (int i = 0; i < activityList.size(); i++) {
                if (activityList.get(i) != null) {
                    activityList.get(i).finish();
                }
            }
            activityList.clear();
            activityList = null;
        }
    }
//    private void UMPush() {
//        UmengMessageHandler umengMessageHandler=new UmengMessageHandler(){
//            @Override
//            public Notification getNotification(Context context, UMessage uMessage) {
//                EventMessage eventMessage=new EventMessage("tuisong");
//                EventBus.getDefault().postSticky(eventMessage);
//                return super.getNotification(context, uMessage);
//
//            }
//        };
//
//
//
//    }

    public void setShowPop(PopupWindow popupWindow, View view){
        if(popupWindow!=null&&popupWindow.isShowing()){
            popupWindow.dismiss();
        }else{
            setWindowTranslucence(0.3);
            popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        }
    }
    //设置Window窗口的透明度
    public void setWindowTranslucence(double d){

        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.alpha=(float) d;
        window.setAttributes(attributes);

    }


}
