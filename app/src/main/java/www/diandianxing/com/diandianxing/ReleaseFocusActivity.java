package www.diandianxing.com.diandianxing;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
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
import java.util.ArrayList;
import java.util.List;

import www.diandianxing.com.diandianxing.adapter.GridViewAddImgesAdpter;
import www.diandianxing.com.diandianxing.adapter.TagAdapter;
import www.diandianxing.com.diandianxing.base.BaseActivity;
import www.diandianxing.com.diandianxing.bean.FaBuTypeInfo;
import www.diandianxing.com.diandianxing.bean.MsgBus;
import www.diandianxing.com.diandianxing.fragment.mainfragment.JiaodianActivity;
import www.diandianxing.com.diandianxing.util.Api;
import www.diandianxing.com.diandianxing.util.BaseDialog;
import www.diandianxing.com.diandianxing.util.MyContants;
import www.diandianxing.com.diandianxing.util.MyGridView;
import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.util.SpUtils;

/**
 * Created by Administrator on 2018/4/4.
 */

public class ReleaseFocusActivity extends BaseActivity {

    private RecyclerView rv_fbfl;

    private ImageView include_back;

    private LinearLayout ll_szwz;
    private MyGridView mygridview;
    private TextView textView2;
    private GridViewAddImgesAdpter addImgesAdpter;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayList<FaBuTypeInfo> mList = new ArrayList<>();
    List<LocalMedia> list = new ArrayList<>();
    List<LocalMedia> listAll = new ArrayList<>();
    private List<LocalMedia> selectList = new ArrayList<>();
    TagAdapter tagAdapter;
    private TextView tv_fb;

    private String postContent="",address="",postTitle="",postType="",images="";

    private EditText et_title_jd,et_content_jd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyContants.windows(this);
        setContentView(R.layout.activity_release_focus);

        EventBus.getDefault().register(ReleaseFocusActivity.this);

        include_back= (ImageView) findViewById(R.id.include_back);
        rv_fbfl= (RecyclerView) findViewById(R.id.rv_fbfl);
        ll_szwz= (LinearLayout) findViewById(R.id.ll_szwz);
        textView2= (TextView) findViewById(R.id.textView2);
        et_title_jd= (EditText) findViewById(R.id.et_title_jd);
        et_content_jd= (EditText) findViewById(R.id.et_content_jd);
        tv_fb= (TextView) findViewById(R.id.tv_fb);
        mygridview= (MyGridView) findViewById(R.id.mygridview);
        networkType();

        rv_fbfl.setLayoutManager(new GridLayoutManager(ReleaseFocusActivity.this,4));
        rv_fbfl.setNestedScrollingEnabled(false);

        /**
         * 添加照片adapter
         */
        addImgesAdpter = new GridViewAddImgesAdpter(list, this);
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

                Intent intent = new Intent(ReleaseFocusActivity.this,LocationActivity.class);
                startActivity(intent);

            }
        });

        tv_fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                postTitle=et_title_jd.getText().toString().trim();
                postContent=et_content_jd.getText().toString().trim();


                if(postTitle!=null&&postTitle.length()>0){

                    if(postContent!=null&&postContent.length()>0){


                        if(postType!=null&&postType.length()>0){

                            if(listAll.size()>0){

                                shumaDialog(Gravity.CENTER,R.style.Alpah_aniamtion);
                                networkImg();

                            }else{
                                Toast.makeText(ReleaseFocusActivity.this,"请选择图片",Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(ReleaseFocusActivity.this,"请选择分类",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(ReleaseFocusActivity.this,"请输入内容",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(ReleaseFocusActivity.this,"请输入标题",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void XXX(MsgBus messageEvent) {

        textView2.setText(messageEvent.msg);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(ReleaseFocusActivity.this);
    }

    private void requestPhoto() {
        // 进入相册 以下是例子：不需要的api可以不写
        PictureSelector.create(ReleaseFocusActivity.this)
                .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .theme(R.style.picture_default_style1)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                .maxSelectNum(9)// 最大图片选择数量
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


    private void networkImg() {

        List<File> fileList = new ArrayList<>();
        for(int i=0;i<listAll.size();i++){

            File file = new File(listAll.get(i).getPath());

            fileList.add(file);

        }


        HttpParams params = new HttpParams();
        params.putFileParams("file", fileList);
//        params.put("token", Api.token);
        Log.d("TAG","数据内容"+params.toString());
        OkGo.<String>post(Api.BASE_URL +"app/home/uploadFrontImage")
                .tag(this)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        Log.d("TAG", "焦点上传图片成功：" + body);
                        JSONObject jsonobj = null;
                        try {
                            jsonobj = new JSONObject(body);
                            int code = jsonobj.getInt("statue");

                            if (code == 1) {

                                images = jsonobj.getString("response");

                                network();

                            } else {
                                Toast.makeText(ReleaseFocusActivity.this,jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();

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
        params.put("postContent", postContent);
        params.put("address", address);
        params.put("images", images);
        params.put("token", SpUtils.getString(this,"token",null));
        params.put("postTitle", postTitle);
        params.put("postType", postType);
        Log.d("TAG","数据内容"+params.toString());
        OkGo.<String>post(Api.BASE_URL +"app/home/insertPostInfo")
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

                                Intent intent = new Intent(ReleaseFocusActivity.this,JiaodianActivity.class);

                                intent.putExtra("tianzhuan",true);

                                startActivity(intent);
                                finish();

                            } else {
                                Toast.makeText(ReleaseFocusActivity.this,jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("TAG","解析失败了！！！");
                        }
                    }
                });
    }


    private void networkType() {
        HttpParams params = new HttpParams();
        params.put("token", SpUtils.getString(this,"token",null));
        Log.d("TAG","数据内容"+params.toString());
        OkGo.<String>post(Api.BASE_URL +"app/home/getClassificationList")
                .tag(this)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        Log.d("TAG", "分类数据：" + body);
                        JSONObject jsonobj = null;
                        try {
                            jsonobj = new JSONObject(body);
                            int code = jsonobj.getInt("code");
                            JSONArray datas = jsonobj.getJSONArray("datas");
                            List<FaBuTypeInfo> list = new ArrayList<FaBuTypeInfo>();
                            if (code == 200) {

                                    for(int i=0;i<datas.length();i++){

                                        JSONObject jo=datas.getJSONObject(i);
                                        FaBuTypeInfo faBuTypeInfo = new FaBuTypeInfo();
                                        faBuTypeInfo.id=jo.getString("id");
                                        faBuTypeInfo.title=jo.getString("title");
                                        faBuTypeInfo.textType=jo.getString("textType");
                                        faBuTypeInfo.isDeleted=jo.getString("isDeleted");
                                        faBuTypeInfo.content=jo.getString("content");
                                        list.add(faBuTypeInfo);
                                    }

                                mList.addAll(list);
                                if(tagAdapter==null){

                                    tagAdapter  = new TagAdapter(R.layout.type_item_view, mList,ReleaseFocusActivity.this);
                                    rv_fbfl.setAdapter(tagAdapter);

                                }else{
                                    tagAdapter.notifyDataSetChanged();
                                }
                                tagAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        Log.e("TAG","点击到：："+position);

                                        postType=mList.get(position).id;
                                        tagAdapter.setXZ(position);
                                        tagAdapter.notifyDataSetChanged();

                                    }
                                });
                            } else {
                                Toast.makeText(ReleaseFocusActivity.this,jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();

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
