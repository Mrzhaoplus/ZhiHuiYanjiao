package www.diandianxing.com.diandianxing.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import www.diandianxing.com.diandianxing.base.BaseActivity;
import www.diandianxing.com.diandianxing.util.BaseDialog;
import www.diandianxing.com.diandianxing.util.EventMessage;
import www.diandianxing.com.diandianxing.util.MyContants;
import www.diandianxing.com.diandianxing.util.SoftKeyboardTool;
import www.diandianxing.com.diandianxing.util.SpUtils;
import www.diandianxing.com.diandianxing.util.ToastUtils;
import www.diandianxing.com.diandianxing.R;


/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class KefuActivity extends BaseActivity implements View.OnClickListener {
    private ImageView iv_callback;
    private TextView zhong;
    private TextView you;
    private ImageView img_sao;
    private ImageView img_increate;
    private LinearLayout liner;
    private EditText fu_edtext;
    private LinearLayout liner1;
    private TextView fu_tijiao;
    private Uri imageUri;
    private static final int CODE_RESULT_REQUEST = 0xa2;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private RelativeLayout real_renzheng;
    private File fileCropUri = new File(Environment.getExternalStorageDirectory().getPath() + "/crop_photo.jpg");
    private static final int CAMERA_PERMISSIONS_REQUEST_CODE = 0x03;
    private static final int STORAGE_PERMISSIONS_REQUEST_CODE = 0x04;
    private Uri cropImageUri;
    private File fileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/photo.jpg");
    private TextView zishu;
    private TextView tv_bianhao;
    private Handler handler=new Handler();
    private EditText ed;
    private List<String> cameraList;
    private List<LocalMedia> selectList = new ArrayList<>();
    private String cutPath;
    private File file;
    private ImageView img_delete;
    private RelativeLayout relan_sao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        MyContants.windows(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kefu);
        EventBus.getDefault().register(this);
        initView();
    }

    private void initView() {
        iv_callback = (ImageView) findViewById(R.id.iv_callback);
        zhong = (TextView) findViewById(R.id.zhong);
        you = (TextView) findViewById(R.id.you);
        img_sao = (ImageView) findViewById(R.id.img_sao);
        img_increate = (ImageView) findViewById(R.id.img_increate);
        liner = (LinearLayout) findViewById(R.id.liner);
        fu_edtext = (EditText) findViewById(R.id.fu_edtext);
        liner1 = (LinearLayout) findViewById(R.id.liner1);
        fu_tijiao = (TextView) findViewById(R.id.fu_tijiao);
        zishu = (TextView) findViewById(R.id.zishu);
        tv_bianhao = (TextView) findViewById(R.id.tv_bianhao);
        relan_sao = (RelativeLayout) findViewById(R.id.relan_sao);
        zhong.setText("违停举报");
        img_sao.setOnClickListener(this);
        img_increate.setOnClickListener(this);
        iv_callback.setOnClickListener(this);
        fu_tijiao.setOnClickListener(this);
        relan_sao.setOnClickListener(this);
        fu_edtext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String trim = editable.toString().trim();
                int length = trim.length();
                int i = 140 - length;
                zishu.setText(i+"/140");
            }
        });
    }

    private void submit() {
        boolean numeric = isNumeric(tv_bianhao.getText().toString());
        if(!(numeric)){
            Toast.makeText(this, "请扫车辆编码", Toast.LENGTH_SHORT).show();
            return;
        }
        if(file==null){
            Toast.makeText(this, "请上传照片", Toast.LENGTH_SHORT).show();
            return;
        }
        // validate
        String edtext = fu_edtext.getText().toString().trim();
        if (TextUtils.isEmpty(edtext)) {
            Toast.makeText(this, "请输入反馈内容", Toast.LENGTH_SHORT).show();
            return;
        }

        networks();

        // TODO validate success, do something


    }
    public final static boolean isNumeric(String s) {
        if (s != null && !"".equals(s.trim()))
            return s.matches("^[0-9]*$");
        else
            return false;
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_callback:
                EventMessage eventMessage=new EventMessage("yincang");
                EventBus.getDefault().postSticky(eventMessage);
                finish();
                break;
            //添加照片
            case R.id.img_increate:
                requestCamera();
            //    showphotoDialog(Gravity.BOTTOM,R.style.Bottom_Top_aniamtion);
                break;
            //扫一扫
            case R.id. relan_sao:
                /*
                  跳扫码
                 */
                Intent intent=new Intent(this,ZiXingActivity.class);
                 intent.putExtra("abool",true);
                startActivity(intent);



                break;
            //提交
            case R.id.fu_tijiao:
                submit();


                break;
        }
    }
    private void networks(){
        HttpParams httpParams=new HttpParams();
        httpParams.put("uid", SpUtils.getString(KefuActivity.this,"userid",null));
        httpParams.put("token", SpUtils.getString(KefuActivity.this,"token",null));
        httpParams.put("identnum",tv_bianhao.getText().toString());
        httpParams.put("type",1+"");
        Map<String,File> maps=new HashMap<>();
        if(file!=null) {
            maps.put("file", file);
        }
        OkGo.<String>post(MyContants.BASEURL+"s=Bike/feedback")
                .params(httpParams)
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        try {
                            JSONObject jsobobj=new JSONObject(body);
                            int code = jsobobj.getInt("code");
                            if(code==200){
                                ToastUtils.show(KefuActivity.this,"提交成功",1);
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void requestCamera() {
        PictureSelector.create(this)
                .openCamera(PictureMimeType.ofImage())// 单独拍照，也可录像或也可音频 看你传入的类型是图片or视频
                .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles
                .enableCrop(false)// 是否裁剪
                .compress(false)// 是否压缩
                .compressMode(PictureConfig.SYSTEM_COMPRESS_MODE)//系统自带 or 鲁班压缩 PictureConfig.SYSTEM_COMPRESS_MODE or LUBAN_COMPRESS_MODE
                .freeStyleCropEnabled(false)// 裁剪框是否可拖拽
                .circleDimmedLayer(false)// 是否圆形裁剪
                .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                .scaleEnabled(false)// 裁剪是否可放大缩小图片
//                .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
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
                    cutPath = selectList.get(0).getPath();
                    file = new File(cutPath);
                    Glide.with(this).load(cutPath).into(img_increate);
                    Log.d("files",file+"");
                    break;
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void myEvent(EventMessage eventMessage) {
        //扫码成功后刷新主页面
        if (eventMessage.getMsg().equals("weiting")) {
            String bikenum = SpUtils.getString(this, "bianhao", null);
            tv_bianhao.setText(bikenum);

        }
        else if(eventMessage.getMsg().equals("shoush")){
            showCarDialog(R.style.Alpah_aniamtion);

        }
    }

    private void showCarDialog( int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        final BaseDialog dialog = builder.setViewId(R.layout.dialog_shuru)
                //设置dialogpadding
                .setPaddingdp(0, 0, 0, 0)
                //设置显示位置
                .setGravity(580)
                //设置动画
                .setAnimation(animationStyle)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(false)

                //设置监听事件
                .builder();

        ed = (EditText) dialog.getView(R.id.ed_shuru).findViewById(R.id.ed_shuru);
        img_delete = dialog.getView(R.id.img_delete).findViewById(R.id.img_delete);
        SoftKeyboardTool.showSoftKeyboard(ed);
        dialog.getView(R.id.bike_sso).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoftKeyboardTool.closeKeyboard(ed);
                dialog.dismiss();
                tv_bianhao.setText(ed.getText().toString().trim());
            }
        });
        dialog.getView(R.id.img_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SoftKeyboardTool.closeKeyboard(ed);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        /*
          返回键调用eventbus刷新主页面使其输车牌好影藏

         */
          EventMessage eventMessage=new EventMessage("yincang");
          EventBus.getDefault().postSticky(eventMessage);
    }
}
