package www.diandianxing.com.diandianxing.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.DisplayMetrics;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyUtils {
    private static int mScreenWidth, mScreenHeight;

    /** 判断是否是快速点击 */
    private static long lastClickTime;

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 1000) {

            return true;
        }
        lastClickTime = time;
        return false;

    }

    public static String stampToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    public static String stampsToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    public static String stampYRToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    public static String stampNYToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
    public static String stampNYrToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }


    public static String dateDiff(String startTime, String endTime, String format,String data) {
        // 按照传入的格式生成一个simpledateformate对象
        SimpleDateFormat sd = new SimpleDateFormat(format);
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long ns = 1000;// 一秒钟的毫秒数
        long diff;
        long day = 0;
        try {
            // 获得两个时间的毫秒时间差异
            diff = sd.parse(endTime).getTime()
                    - sd.parse(startTime).getTime();
            day = diff / nd;// 计算差多少天
            long hour = diff % nd / nh;// 计算差多少小时
            long min = diff % nd % nh / nm;// 计算差多少分钟
            long sec = diff % nd % nh % nm / ns;// 计算差多少秒
            // 输出结果
            System.out.println("时间相差：" + day + "天" + hour + "小时" + min
                    + "分钟" + sec + "秒。");
            if (day>=1) {
//                return day;

                return stampYRToDate(data);

            }else {
                if (day==0) {

                    if(hour>=1&&hour<24){

                        return hour+"小时前";

                    }else{

                        if(min>=1&&min<60){

                            return min+"分钟前";

                        }else{
                            return 1+"分钟前";
                        }

                    }
                }else {
                    return "今天";
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "今天";

    }



    // base64图片转字符串
    public static String Bitmap2StrByBase64(Bitmap bit) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bit.compress(Bitmap.CompressFormat.JPEG, 40, bos);//参数100表示不压缩
        byte[] bytes = bos.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

//    public static boolean islogin(Context context) {
//     //   String userid = SpUtils.getString(context, "user_id", "");
//        if (TextUtils.isEmpty(userid)) {
////            Toast.makeText(context, "请先登录", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        return true;
//    }

//    public static void showloginDialog(final Context context, int grary, int animationStyle) {
//        BaseDialog.Builder builder = new BaseDialog.Builder(context);
//        final BaseDialog dialog = builder.setViewId(R.layout.dialog_phone)
//                //设置dialogpadding
//                .setPaddingdp(10, 0, 10, 0)
//                //设置显示位置
//                .setGravity(grary)
//                //设置动画
//                .setAnimation(animationStyle)
//                //设置dialog的宽高
//                .setWidthHeightpx(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
//                //设置触摸dialog外围是否关闭
//                .isOnTouchCanceled(true)
//                //设置监听事件
//                .builder();
//        dialog.show();
//        TextView tv_content = dialog.getView(R.id.tv_content);
//        tv_content.setText("你还未登录，请登录");
//        TextView tv_canel = dialog.getView(R.id.tv_canel);
//        tv_canel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //关闭dialog
//                dialog.close();
//            }
//        });
//        TextView tv_yes = dialog.getView(R.id.tv_yes);
//        tv_yes.setText("去登陆");
//        tv_yes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //登录
//                Intent intent = new Intent(context, LoginActivity.class);
//                intent.putExtra("gologin", "gologin");
//                context.startActivity(intent);
//                dialog.dismiss();
//            }
//        });
//    }

    /**
     * * 清除本应用SharedPreference(/data/data/com.xxx.xxx/shared_prefs)
     * context
     */
    public static void cleanSharedPreference(Context context) {
        deleteFilesByDirectory(new File("/data/data/"
                + context.getPackageName() + "/shared_prefs"));
    }

    /**
     * 删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理
     */
    private static void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                item.delete();
            }
        }
    }

    public static String getMD5(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    public static String getToken() {
        return getMD5(getCurrentDate() + "xfkj");
    }


    /*将日期转为时间戳*/
    public static long getStringToDate(String time) {
        //        String timestamp = String.format("%010d", stringToDate);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = new Date();
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date.getTime();
    }

    //  时间戳转为日期  /年/月/日
    public static String getDateToString(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long lcc_time = Long.valueOf(time);
        String format = sdf.format(new Date(lcc_time * 1000L));
        return format;
    }

    //  时间戳转为日期  /年/月/日
    public static String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String date = sdf.format(curDate);
        return date;
    }

    public static String getDateToStringTime(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        long lcc_time = Long.valueOf(time);
        String format = sdf.format(new Date(lcc_time * 1000L));
        return format;
    }

    //手机号判断逻辑
    public static boolean isMobileNO(String mobiles) {
//        Pattern p = Pattern.compile("^(13[0-9]|14[57]|15[0-35-9]|17[6-8]|18[0-9])[0-9]{8}$");
//        Matcher m = p.matcher(mobiles);
//        return m.matches();
        //上面的验证会有些问题，手机号码格式不是固定的，所以就弄简单点
        if (mobiles.length()==11){
            return true;
        }
        return false;
    }

    //正则6-16位数字或字母
    public static boolean isPassMobileNO(String mobiles) {
        Pattern p = Pattern
                .compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$");
        Matcher m = p.matcher(mobiles);
        System.out.println(m.matches() + "---");
        return m.matches();
    }

    //写这个方法主要是想当发布release版本的时候不会输出日志
    public static void syso(String str) {
        System.out.print(str);
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        mScreenWidth = metrics.widthPixels;
        return mScreenWidth;
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        mScreenHeight = metrics.heightPixels;
        return mScreenHeight;
    }

    //启动应用的设置，跳转到应用的设置界面
    public static void startAppSettingsResult(Activity activity) {
        Intent intent = new Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + activity.getPackageName()));
        activity.startActivityForResult(intent, 0);
    }

    //得到此设备的设备信息，这里用到的是方便进行友盟的集成测试
    public static String getDeviceInfo(Context context) {
        JSONObject jsonObject = new JSONObject();
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = manager.getDeviceId();
        return deviceId;
    }

    /**
     * 获取当前应用程序的包名
     *
     * @param context 上下文对象
     * @return 返回包名
     */
    public static String getAppPackageName(Context context) {
        //当前应用pid
        int pid = android.os.Process.myPid();
        //任务管理类
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        //遍历所有应用
        List<ActivityManager.RunningAppProcessInfo> infos = manager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo info : infos) {
            if (info.pid == pid)//得到当前应用
                return info.processName;//返回包名
        }
        return "";
    }

    /**
     * 获取程序的版本号
     *
     * @param context
     * @return
     */
    public static String getAppVersion(Context context) {
        //包管理操作管理类
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo packinfo = pm.getPackageInfo(getAppPackageName(context), 0);
            return packinfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static String dateToString(Date date, String type) {
        String str = null;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (type.equals("SHORT")) {
            // 07-1-18
            format = DateFormat.getDateInstance(DateFormat.SHORT);
            str = format.format(date);
        } else if (type.equals("MEDIUM")) {
            // 2007-1-18
            format = DateFormat.getDateInstance(DateFormat.MEDIUM);
            str = format.format(date);
        } else if (type.equals("FULL")) {
            // 2007年1月18日 星期四
            format = DateFormat.getDateInstance(DateFormat.FULL);
            str = format.format(date);
        }
        return str;
    }
}
