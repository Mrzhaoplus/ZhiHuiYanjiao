package www.diandianxing.com.diandianxing.my;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.compress.Luban;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import www.diandianxing.com.diandianxing.adapter.Jiaodianadapter;
import www.diandianxing.com.diandianxing.base.BaseActivity;
import www.diandianxing.com.diandianxing.base.Myapplication;
import www.diandianxing.com.diandianxing.bean.GuanzhuJD;
import www.diandianxing.com.diandianxing.bean.Photobean;
import www.diandianxing.com.diandianxing.bean.Shouyebean;
import www.diandianxing.com.diandianxing.bean.UserMsgInfo;
import www.diandianxing.com.diandianxing.util.Api;
import www.diandianxing.com.diandianxing.util.BaseDialog;
import www.diandianxing.com.diandianxing.util.CircleImageView;
import www.diandianxing.com.diandianxing.util.EventMessage;
import www.diandianxing.com.diandianxing.util.GlobalParams;
import www.diandianxing.com.diandianxing.util.JsonBean;
import www.diandianxing.com.diandianxing.util.JsonFileReader;
import www.diandianxing.com.diandianxing.util.MyContants;
import www.diandianxing.com.diandianxing.util.SpUtils;
import www.diandianxing.com.diandianxing.util.ToastUtils;
import www.diandianxing.com.diandianxing.R;


/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class PersonActivity extends BaseActivity implements View.OnClickListener {
    private Shouyebean.DatasBean datas;
     List<String> list=new ArrayList<>();
    private ImageView iv_callback;
    private TextView zhong;
    private TextView you;
    private TextView set_pho;
    private ImageView back;
    private RelativeLayout real_pho;
    private TextView set_name;
    private ImageView back1;
    private RelativeLayout real_name;
    private TextView set_renzheng;
    private ImageView back2;
    private RelativeLayout real_renzheng;
    private CircleImageView per_pho;
    private TextView alter_name;
    private Uri imageUri;
    private static final int CODE_RESULT_REQUEST = 0xa2;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private File fileCropUri = new File(Environment.getExternalStorageDirectory().getPath() + "/crop_photo.jpg");
    private static final int CAMERA_PERMISSIONS_REQUEST_CODE = 0x03;
    private static final int STORAGE_PERMISSIONS_REQUEST_CODE = 0x04;
    private Uri cropImageUri;
    private File fileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/photo.jpg");
    private Handler handler=new Handler();
    private String s;
    private TextView id_card;
    private List<String> cameraList;
    private List<LocalMedia> selectList = new ArrayList<>();
    private String cutPath;
    private File file;
    private BaseDialog dialog;
    private File filess = new File(Environment.getExternalStorageDirectory().getPath() + "/crop_photo.jpg");;
    private RadioGroup rg;
    private RadioButton nan;
    private RadioButton nv;

    private RadioGroup rg_xb;
    private RadioButton rb_nan,rb_nv;
    private TextView tv_phone;
    private EditText et_qm;
    private TextView tv_sf_xz;
    private RelativeLayout rl_dqxz;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyContants.windows(this);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_personmessage);
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void myEvent(EventMessage eventMessage) {
        if (eventMessage.getMsg().equals("myname")) {
            String name = SpUtils.getString(this, "nickname", null);

            Intent intent =new Intent();
            intent.setAction(GlobalParams.TOU_SX);
            intent.putExtra("name",name);
            sendBroadcast(intent);

            Intent gz =new Intent();
            gz.setAction(GlobalParams.GZ);
            sendBroadcast(gz);



            alter_name.setText(name);
        }
         else if(eventMessage.getMsg().equals("idcard")){
            network();
        }
    }
    private void initView() {
        iv_callback = (ImageView) findViewById(R.id.iv_callback);
        zhong = (TextView) findViewById(R.id.zhong);
        you = (TextView) findViewById(R.id.you);
        set_pho = (TextView) findViewById(R.id.set_pho);
        back = (ImageView) findViewById(R.id.back);
        real_pho = (RelativeLayout) findViewById(R.id.real_pho);
        set_name = (TextView) findViewById(R.id.set_name);
        back1 = (ImageView) findViewById(R.id.back1);
        real_name = (RelativeLayout) findViewById(R.id.real_name);
        set_renzheng = (TextView) findViewById(R.id.set_renzheng);
        back2 = (ImageView) findViewById(R.id.back2);
        alter_name = (TextView) findViewById(R.id.alter_name);
        per_pho = (CircleImageView) findViewById(R.id.person_pho);
        rl_dqxz= (RelativeLayout) findViewById(R.id.rl_dqxz);
        tv_sf_xz= (TextView) findViewById(R.id.tv_sf_xz);
        rb_nan= (RadioButton) findViewById(R.id.rb_nan);
        rb_nv= (RadioButton) findViewById(R.id.rb_nv);
        et_qm= (EditText) findViewById(R.id.et_qm);
        tv_phone= (TextView) findViewById(R.id.tv_phone);
        rg_xb= (RadioGroup) findViewById(R.id.rg_xb);
        real_renzheng = (RelativeLayout) findViewById(R.id.real_renzheng);
        id_card = (TextView) findViewById(R.id.idcard_zhuangtai);
        initJsonData();
        iv_callback.setOnClickListener(this);
        real_pho.setOnClickListener(this);
        zhong.setText("个人信息");
        real_name.setOnClickListener(this);
        String paizhao = SpUtils.getString(this, "paiphoto", null);
        if(paizhao!=null&&paizhao.length()>0) {
            RequestOptions options = new RequestOptions()
                    .error(R.drawable.img_motou)
                    .priority(Priority.NORMAL);
            Glide.with(this).load(MyContants.PHOTO+paizhao).apply(options).into(per_pho);
        }
        String nickname = SpUtils.getString(this, "nickname", null);
        alter_name.setText(nickname);
//        String iDcrad = SpUtils.getString(this, "IDcard",null);
//        Log.e("TAG","个人iDcrad==="+iDcrad);
//        if(iDcrad!=null){
//            int idc = Integer.parseInt(iDcrad.trim());
//            if(idc==0){
//                id_card.setText("未认证");
//            }
//            else if(idc==1){
//                id_card.setText("审核中");
//            }
//            else if(idc==2){
//                id_card.setText("审核不通过");
//            }
//            else if(idc==3){
//                id_card.setText("已认证");
//            }
//        }

        rb_nan.setOnClickListener(this);
        rb_nv.setOnClickListener(this);
        rl_dqxz.setOnClickListener(this);
        network();

        et_qm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                signature=editable.toString().trim();

                networkupdata();
            }
        });

    }

    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    private void showPickerView() {
        OptionsPickerView pvOptions=new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String text = options1Items.get(options1).getPickerViewText() +" "+
                        options2Items.get(options1).get(options2) +" "+
                        options3Items.get(options1).get(options2).get(options3);
                tv_sf_xz.setText(text);
                tv_sf_xz.setTextColor(getResources().getColor(R.color.black_san));

                area=text;


                networkupdata();


            }
        }).setTitleText("")
                .setDividerColor(Color.GRAY)
                .setTextColorCenter(Color.GRAY)
                .setContentTextSize(13)
                .setOutSideCancelable(false)
                .build();
          /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }


    private void initJsonData() {   //解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        //  获取json数据
        String JsonData = JsonFileReader.getJson(this, "province_data.json");
        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市

                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {

                    for (int d = 0; d < jsonBean.get(i).getCityList().get(c).getArea().size(); d++) {//该城市对应地区所有数据
                        String AreaName = jsonBean.get(i).getCityList().get(c).getArea().get(d);

                        City_AreaList.add(AreaName);//添加该城市所有地区数据
                    }
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }
    }

    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_callback:
//                EventMessage eventMessage = new EventMessage("personphoto");
//                EventBus.getDefault().postSticky(eventMessage);
                finish();
                break;
            //修改头像
            case R.id.real_pho:
                showphotoDialog(Gravity.BOTTOM, R.style.Bottom_Top_aniamtion);
                break;
            //修改名字
            case R.id.real_name:
                Intent it=new Intent(this,AlternameActivity.class);
                startActivity(it);
                break;
            //实名认证
            case R.id.real_renzheng:
                Intent its=new Intent(PersonActivity.this,RenzhenActivity.class);
                startActivity(its);
                break;
            case R.id.rb_nan:

                sex="0";
                networkupdata();
                break;
            case R.id.rb_nv:

                sex="1";
                networkupdata();

                break;
            case R.id.rl_dqxz:

                showPickerView();

                break;

        }
    }
    private void showphotoDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        //设置dialogpadding
//设置显示位置
//设置动画
//设置dialog的宽高
//设置触摸dialog外围是否关闭
//设置监听事件
        dialog = builder.setViewId(R.layout.dialog_photo)
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
        //拍照
        dialog.getView(R.id.tv_paizhao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //相机选取
                requestCamera();
                dialog.dismiss();

            }
        });
        //相册
        dialog.getView(R.id.tv_local).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //相册选取
                requestPhoto();
                dialog.dismiss();

            }
        });
        //取消
        dialog.getView(R.id.tv_pause).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

   /*
     调用相册
    */
    private void requestPhoto() {
        // 进入相册 以下是例子：不需要的api可以不写
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .theme(R.style.picture_default_style1)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                .maxSelectNum(1)// 最大图片选择数量
                .minSelectNum(1)// 最小选择数量
                .imageSpanCount(4)// 每行显示个数
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片
                .previewVideo(false)// 是否可预览视频
                .enablePreviewAudio(false) // 是否可播放音频
                .compressGrade(Luban.THIRD_GEAR)// luban压缩档次，默认3档 Luban.FIRST_GEAR、Luban.CUSTOM_GEAR
                .isCamera(true)// 是否显示拍照按钮
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
                .enableCrop(true)// 是否裁剪
                .compress(true)// 是否压缩
                .compressMode(PictureConfig.SYSTEM_COMPRESS_MODE)//系统自带 or 鲁班压缩 PictureConfig.SYSTEM_COMPRESS_MODE or LUBAN_COMPRESS_MODE
                //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .glideOverride(200, 200)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
//                .withAspectRatio(aspect_ratio_x, aspect_ratio_y)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示
                .isGif(false)// 是否显示gif图片
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                .circleDimmedLayer(true)// 是否圆形裁剪
                .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                .openClickSound(false)// 是否开启点击声音
//                .selectionMedia(list)// 是否传入已选图片
//                        .videoMaxSecond(15)
//                        .videoMinSecond(10)
                //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                //.cropCompressQuality(90)// 裁剪压缩质量 默认100
                //.compressMaxKB()//压缩最大值kb compressGrade()为Luban.CUSTOM_GEAR有效
                //.compressWH() // 压缩宽高比 compressGrade()为Luban.CUSTOM_GEAR有效
                //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                //.rotateEnabled() // 裁剪是否可旋转图片
                .scaleEnabled(false)// 裁剪是否可放大缩小图片
                //.videoQuality()// 视频录制质量 0 or 1
                //.videoSecond()//显示多少秒以内的视频or音频也可适用
                //.recordVideoSecond()//录制视频秒数 默认60s
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }
   /*
     调用相机
    */
    private void requestCamera() {
        PictureSelector.create(this)
                .openCamera(PictureMimeType.ofImage())// 单独拍照，也可录像或也可音频 看你传入的类型是图片or视频
                .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles
                .enableCrop(true)// 是否裁剪
                .compress(true)// 是否压缩
                .compressMode(PictureConfig.SYSTEM_COMPRESS_MODE)//系统自带 or 鲁班压缩 PictureConfig.SYSTEM_COMPRESS_MODE or LUBAN_COMPRESS_MODE
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                .circleDimmedLayer(true)// 是否圆形裁剪
                .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                .scaleEnabled(false)// 裁剪是否可放大缩小图片
               .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
//                .selectionMedia(list)// 是否传入已选图片
//                .previewEggs(true)//预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    cutPath = selectList.get(0).getCutPath();
                    file = new File(cutPath);
                    Log.d("TAg",cutPath);
                    Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    int byteCount = bitmap.getByteCount();
                    Log.d("TAG",byteCount+"-----size----"+file.length());
                    /*
                       质量压缩
                     */
                    FileOutputStream baos = null;
                    try {

                        baos = new FileOutputStream(filess);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, baos);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
//                    int quality =2;

                    list.clear();
                    list.add(cutPath);
                    Log.d("Tag",list.toString());
                    photoworks();
                    break;
            }
        }
    }

    private String sex="0",area="",signature="";

    private void networkupdata() {

        HttpParams params = new HttpParams();
        params.put("sex", Integer.parseInt(sex));
        params.put("area",area);
        params.put("signature",signature);

        params.put("token", SpUtils.getString(this,"token",null));
        Log.d("TAG","数据内容"+params.toString());
        OkGo.<String>post(Api.BASE_URL +"app/home/updateUserInfo")
                .tag(this)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        String body = response.body();
                        Log.d("TAG", "修改" + body);
                        JSONObject jsonobj = null;
                        try {
                            jsonobj = new JSONObject(body);
                            int code = jsonobj.getInt("code");
                            if (code == 200) {

//                                Toast.makeText(PersonActivity.this,"修改成功",Toast.LENGTH_SHORT).show();


                                Intent intent =new Intent();
                                intent.setAction(GlobalParams.TOU_SX);
                                intent.putExtra("sex",sex);

                                if(signature.length()>0){
                                    intent.putExtra("signature",signature);
                                }

                                sendBroadcast(intent);


                            } else {
                                Toast.makeText(PersonActivity.this,jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("TAG","解析失败了！！！");
                        }
                    }
                });
    }


    private void network() {
        HttpParams params = new HttpParams();
        params.put("token", SpUtils.getString(this,"token",null));
        Log.d("TAG","数据内容"+params.toString());
        OkGo.<String>post(Api.BASE_URL +"app/home/getUserInfo")
                .tag(this)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        String body = response.body();
                        Log.d("TAG", "个人信息：" + body);
                        JSONObject jsonobj = null;
                        try {
                            jsonobj = new JSONObject(body);
                            int code = jsonobj.getInt("code");
                            JSONObject datas = jsonobj.getJSONObject("datas");
                            if (code == 200) {

                                Glide.with(PersonActivity.this).load(datas.getString("pic")).into(per_pho);

                                tv_phone.setText(datas.getString("contact"));
                                sex=datas.getString("userSex");
                                if(Integer.parseInt(datas.getString("userSex"))==0){
                                    rb_nan.setChecked(true);
                                }else{
                                    rb_nv.setChecked(true);
                                }
                                signature=datas.getString("signature");
                                if(datas.getString("signature").length()>0){
                                    et_qm.setText(datas.getString("signature"));
                                }
                                area=datas.getString("area");
                                if(datas.getString("area").length()>0){
                                    tv_sf_xz.setText(datas.getString("area"));
                                    tv_sf_xz.setTextColor(getResources().getColor(R.color.black_san));
                                }


                                SpUtils.putString(PersonActivity.this,"IDcard",datas.getString("id_ident"));
                                Log.e("TAG","认证！！！！！！==="+datas.getString("id_ident"));
                                if(Integer.parseInt(datas.getString("id_ident"))==0){
                                    id_card.setText("未认证");
                                    real_renzheng.setOnClickListener(PersonActivity.this);
                                }
                                else if(Integer.parseInt(datas.getString("id_ident"))==1){
                                    id_card.setText("认证中");
                                    real_renzheng.setOnClickListener(PersonActivity.this);
                                }
                                else if(Integer.parseInt(datas.getString("id_ident"))==2){
                                    id_card.setText("认证失败");
                                    real_renzheng.setOnClickListener(PersonActivity.this);
                                }
                                else if(Integer.parseInt(datas.getString("id_ident"))==3){
                                    id_card.setText("已认证");
                                }




                                alter_name.setText(datas.getString("nickname"));



                            } else {
                                Toast.makeText(PersonActivity.this,jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("TAG","解析失败了！！！");
                        }
                    }
                });
    }



    public void photoworks(){
        HttpParams httpParams=new HttpParams();
        httpParams.put("file", filess);
        Log.d("ffff",file+"");
        httpParams.put("uid", SpUtils.getString(Myapplication.getApplication(),"userid",null));
        httpParams.put("token", SpUtils.getString(PersonActivity.this,"token",null));

            OkGo.<String>post(MyContants.BASEURL+"s=User/updateHeadImg")
                    .params(httpParams)
                    .tag(this)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            String body = response.body();
                            try {
                                JSONObject jsonobj=new JSONObject(body);
                                int code = jsonobj.getInt("code");
                                String datas = jsonobj.getString("datas");
                                if(code==200){
                                    ToastUtils.showShort(PersonActivity.this,"上传成功");
                                    Gson gson = new Gson();
                                    Photobean photobean = gson.fromJson(body, Photobean.class);
                                    String headImageUrl = photobean.getDatas().getHeadImageUrl();
                                    Glide.with(PersonActivity.this).load(MyContants.PHOTO+headImageUrl).into(per_pho);
                                    //sp存头像
                                    SpUtils.putString(PersonActivity.this, "paiphoto",headImageUrl);
                                    Log.d("TAg",headImageUrl);


                                    Intent intent =new Intent();
                                    intent.setAction(GlobalParams.TOU_SX);
                                    intent.putExtra("tou",MyContants.PHOTO+headImageUrl);
                                    sendBroadcast(intent);



                                }
                                else {
                                    ToastUtils.showShort(PersonActivity.this,datas.toString());
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });

    }




    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        EventMessage eventMessage = new EventMessage("personphoto");
//        EventBus.getDefault().postSticky(eventMessage);
    }

//    @Override
//    public void onCheckedChanged(RadioGroup radioGroup, int i) {
//        switch (radioGroup.getCheckedRadioButtonId()){
//            case R.id.nan:
//                nan.setChecked(true);
//                nv.setChecked(false);
//                break;
//                 case R.id.nv:
//                     nv.setChecked(true);
//                     nan.setChecked(false);
//            break;
//        }
//    }
}
