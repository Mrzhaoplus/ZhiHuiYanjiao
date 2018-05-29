package www.diandianxing.com.diandianxing;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import www.diandianxing.com.diandianxing.adapter.GridViewAddImgesAdpter;
import www.diandianxing.com.diandianxing.base.BaseActivity;
import www.diandianxing.com.diandianxing.bean.Info;
import www.diandianxing.com.diandianxing.bean.MsgBus;
import www.diandianxing.com.diandianxing.bean.PaiKeInfo;
import www.diandianxing.com.diandianxing.fragment.mainfragment.JiaoDetailActivity;
import www.diandianxing.com.diandianxing.util.Api;
import www.diandianxing.com.diandianxing.util.BaseDialog;
import www.diandianxing.com.diandianxing.util.GlobalParams;
import www.diandianxing.com.diandianxing.util.MyContants;
import www.diandianxing.com.diandianxing.util.MyGridView;
import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.util.SpUtils;

/**
 * Created by Administrator on 2018/4/8.
 */

public class ReleaseShootoffActivity extends BaseActivity {


    private ImageView include_back;
    private LinearLayout ll_szwz;
    private MyGridView mygridview;
    private GridViewAddImgesAdpter addImgesAdpter;
    ArrayList<String> arrayList = new ArrayList<>();
    List<LocalMedia> listAll = new ArrayList<>();
    private List<LocalMedia> selectList = new ArrayList<>();
    private Info info;
    private TextView textView2;
    private String paths="",address="";
    private EditText et_img_content;
    private TextView tv_fb;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyContants.windows(this);
        setContentView(R.layout.activity_release_shootoff);
        EventBus.getDefault().register(ReleaseShootoffActivity.this);
        include_back= (ImageView) findViewById(R.id.include_back);
        ll_szwz= (LinearLayout) findViewById(R.id.ll_szwz);
        tv_fb= (TextView) findViewById(R.id.tv_fb);
        mygridview= (MyGridView) findViewById(R.id.mygridview);
        textView2= (TextView) findViewById(R.id.textView2);
        et_img_content= (EditText) findViewById(R.id.et_img_content);
        mygridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
        textView2.setText(SpUtils.getString(ReleaseShootoffActivity.this,"PoiName","所在位置"));
        String path=getIntent().getStringExtra("img");
        info= (Info) getIntent().getSerializableExtra("list");
        if(path!=null){
            if(path.length()>0){
                LocalMedia localMedia = new LocalMedia();
                localMedia.setPath(path);
                listAll.add(localMedia);

            }
        }
        if(info!=null){
            listAll.addAll(info.list);
        }
        /**
         * 添加照片adapter
         */
        addImgesAdpter = new GridViewAddImgesAdpter(listAll, this);
        mygridview.setAdapter(addImgesAdpter);
        mygridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                requestPhoto();
            }
        });

        include_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ll_szwz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ReleaseShootoffActivity.this,LocationActivity.class);
                startActivity(intent);

            }
        });
        tv_fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final String trim = et_img_content.getText().toString().trim();

                if(trim!=null&&trim.length()>0){

                    if(listAll.size()>0){

                        shumaDialog(Gravity.CENTER,R.style.Alpah_aniamtion);


                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                networkImg(trim,address);
                            }
                        }).start();

                    }else{
                        Toast.makeText(ReleaseShootoffActivity.this,"请选择图片",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(ReleaseShootoffActivity.this,"请输入内容",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void XXX(MsgBus messageEvent) {
        address=messageEvent.msg;
        textView2.setText(messageEvent.msg);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(ReleaseShootoffActivity.this);
    }


    private void requestPhoto() {
        // 进入相册 以下是例子：不需要的api可以不写
        PictureSelector.create(ReleaseShootoffActivity.this)
                .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .theme(R.style.picture_default_style1)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                .maxSelectNum(9-listAll.size())// 最大图片选择数量
                .minSelectNum(1)// 最小选择数量
                .imageSpanCount(3)// 每行显示个数
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片
                .previewVideo(false)// 是否可预览视频
                .enablePreviewAudio(false) // 是否可播放音频
                .compressGrade(Luban.THIRD_GEAR)// luban压缩档次，默认3档 Luban.FIRST_GEAR、Luban.CUSTOM_GEAR
                .isCamera(true)// 是否显示拍照按钮
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
                .enableCrop(false)// 是否裁剪
                .compress(true)// 是否压缩
                .compressMode(PictureConfig.SYSTEM_COMPRESS_MODE)//系统自带 or 鲁班压缩 PictureConfig.SYSTEM_COMPRESS_MODE or LUBAN_COMPRESS_MODE
                //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .glideOverride(200, 200)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
//                .withAspectRatio(aspect_ratio_x, aspect_ratio_y)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示
                .isGif(false)// 是否显示gif图片
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                .circleDimmedLayer(false)// 是否圆形裁剪
                .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
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
                //.scaleEnabled()// 裁剪是否可放大缩小图片
                //.videoQuality()// 视频录制质量 0 or 1
                //.videoSecond()//显示多少秒以内的视频or音频也可适用
                //.recordVideoSecond()//录制视频秒数 默认60s
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    Log.e("TAG","执行到了~~~~~=============~~");
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    listAll.addAll(selectList);
                    selectList.clear();
                    addImgesAdpter.setList(listAll);
                    addImgesAdpter.notifyDataSetChanged();
                    break;
            }
        }
    }

    private String name="img_scpp";
    private void networkImg(final String title, final String address) {

        List<File> fileList = new ArrayList<>();
        for(int i=0;i<listAll.size();i++){

//            File file = new File(listAll.get(i).getPath());


            Bitmap bitmap = BitmapFactory.decodeFile(listAll.get(i).getPath());


            FileOutputStream fos = null;
            try {

                String substring = listAll.get(i).getPath().substring(listAll.get(i).getPath().lastIndexOf(".")+1, listAll.get(i).getPath().length());
                Log.e("TAG","substring：：："+substring);

                Log.e("TAG","压缩目录：：："+ Environment.getExternalStorageDirectory()+"/"+name+i+".png");
                File file = null;
                if("JPEG".equals(substring)||"jpg".equals(substring)){
                    file=new File(Environment.getExternalStorageDirectory()+"/"+name+i+".jpg");
                    fos = new FileOutputStream(file);

                    bitmap.compress(Bitmap.CompressFormat.JPEG,50,fos);
                }else if("png".equals(substring)){

                    file=new File(Environment.getExternalStorageDirectory()+"/"+name+i+".png");
                    fos = new FileOutputStream(file);

                    bitmap.compress(Bitmap.CompressFormat.PNG,50,fos);
                }else{
                    file=new File(Environment.getExternalStorageDirectory()+"/"+name+i+".png");
                    fos = new FileOutputStream(file);

                    bitmap.compress(Bitmap.CompressFormat.PNG,50,fos);
                }

                if (bitmap != null && !bitmap.isRecycled()){
                    bitmap.recycle();
                    bitmap = null;
                }
                fileList.add(file);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }






        }


        HttpParams params = new HttpParams();
        params.putFileParams("files", fileList);
        params.put("token", SpUtils.getString(this,"token",null));
        Log.d("TAG","数据内容"+params.toString());
        OkGo.<String>post(Api.BASE_URL +"app/paike/uppaikeimages")
                .tag(this)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        Log.d("TAG", "上传图片成功：" + body);
                        JSONObject jsonobj = null;
                        try {
                            jsonobj = new JSONObject(body);
                            int code = jsonobj.getInt("code");

                            if (code == 200) {

                                paths = jsonobj.getString("datas");

                                network(title,address);

                            } else {
                                Toast.makeText(ReleaseShootoffActivity.this,jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("TAG","解析失败了！！！");
                        }
                    }
                });
    }

    private void network(String title,String address) {

        HttpParams params = new HttpParams();
        params.put("content", title);
        params.put("address", address);
        params.put("paths", paths);
        params.put("token", SpUtils.getString(this,"token",null));
        params.put("userId", SpUtils.getString(this,"userid",null));
        Log.d("TAG","数据内容"+params.toString());
        OkGo.<String>post(Api.BASE_URL +"app/paike/insertpaikeimage")
                .tag(this)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dialog.dismiss();
                        String body = response.body();
                        Log.d("TAG", "更新图片上传成功：" + body);
                        JSONObject jsonobj = null;
                        try {
                            jsonobj = new JSONObject(body);
                            int code = jsonobj.getInt("code");


                            if (code == 200) {

                                String datas = jsonobj.getString("datas");

                                Intent tz = new Intent(ReleaseShootoffActivity.this, VideoActivity.class);
                                PaiKeInfo paiKeInfo = new PaiKeInfo();
                                paiKeInfo.pkid=datas;
                                tz.putExtra("pk",paiKeInfo);
                                startActivity(tz);
                                Intent intent = new Intent();
                                intent.setAction(GlobalParams.DONGTAI_SX);
                                sendBroadcast(intent);
                                finish();
                            } else {
                                Toast.makeText(ReleaseShootoffActivity.this,jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("TAG","解析失败了！！！");
                        }
                    }
                });
    }
    BaseDialog dialog;
    /*
* 对话框
* */
    private void shumaDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        dialog = builder.setViewId(R.layout.view_tips_loading)
                //设置dialogpadding
                .setPaddingdp(0, 10, 0, 10)
                //设置显示位置
                .setGravity(grary)
                //设置动画
                .setAnimation(animationStyle)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(false)
                //设置监听事件
                .builder();
        dialog.show();
        TextView tips_loading_msg = dialog.getView(R.id.tips_loading_msg);
        tips_loading_msg.setText("发布中...");
    }

}
