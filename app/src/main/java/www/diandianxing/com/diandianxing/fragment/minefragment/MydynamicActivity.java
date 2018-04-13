package www.diandianxing.com.diandianxing.fragment.minefragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.widget.SpringView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.compress.Luban;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.decoration.RecycleViewDivider;
import com.luck.picture.lib.entity.LocalMedia;
import com.umeng.socialize.UMShareAPI;

import org.zackratos.ultimatebar.UltimateBar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import www.diandianxing.com.diandianxing.Login.UmshareActivity;
import www.diandianxing.com.diandianxing.MyCollectionActivity;
import www.diandianxing.com.diandianxing.TopRuleActivity;
import www.diandianxing.com.diandianxing.VideoActivity;
import www.diandianxing.com.diandianxing.adapter.DongTaiAdapter;
import www.diandianxing.com.diandianxing.adapter.TieziAdapter;
import www.diandianxing.com.diandianxing.base.BaseActivity;
import www.diandianxing.com.diandianxing.fragment.mainfragment.JiaoDetailActivity;
import www.diandianxing.com.diandianxing.my.PersonActivity;
import www.diandianxing.com.diandianxing.util.BaseDialog;
import www.diandianxing.com.diandianxing.util.SharingPop;
import www.diandianxing.com.diandianxing.util.ZDPop;
import www.diandianxing.com.diandianxing.R;
/*
 * Created by ASUS on 2018/3/21.
 * 他人主页
 */

public class MydynamicActivity extends UmshareActivity implements View.OnClickListener {
    ImageView iv_gz,iv_zyfh;
    private ImageView img_tou;
    private TextView text_deng;
    private ImageView imageView2;
    private TextView text_name;
    private ImageView text_sex;
    private TextView guan_num;
    private LinearLayout text_guanzhu;
    private TextView fen_num;
    private LinearLayout text_fensi;
    private TextView collect_num;
    private LinearLayout text_collect;
    private TextView text_pin;
    private View v1;
    private LinearLayout liner_tiezi;
    private TextView text_zan;
    private View v2;
    private LinearLayout liner_dongtai;
    private SpringView sv_tz;
    private RecyclerView recy_card;
    private RelativeLayout rl_bg;
    ArrayList<String> mList=new ArrayList<>();

    private ZDPop zdPop;
    private SharingPop sharingPop;
    private TextView tv_jfzd,tv_sc,tv_close;
    Dialog DDdialog;
    RecycleViewDivider recycleViewDivider;
    private TextView tv_dt_title;
    private BaseDialog dialog;
    private List<LocalMedia> selectList = new ArrayList<>();
    private String cutPath;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UltimateBar ultimateBar = new UltimateBar(this);
        ultimateBar.setImmersionBar();
        setContentView(R.layout.activity_minedynamic);
        initView();
    }

    private void initView() {
        img_tou = (ImageView) findViewById(R.id.img_tou);
        iv_gz= (ImageView) findViewById(R.id.iv_gz);
        text_deng = (TextView) findViewById(R.id.text_deng);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        text_name = (TextView) findViewById(R.id.text_name);
        text_sex = (ImageView) findViewById(R.id.text_sex);
        tv_dt_title= (TextView) findViewById(R.id.tv_dt_title);
        guan_num = (TextView) findViewById(R.id.guan_num);
        text_guanzhu = (LinearLayout) findViewById(R.id.text_guanzhu);
        fen_num = (TextView) findViewById(R.id.fen_num);
        iv_zyfh= (ImageView) findViewById(R.id.iv_zyfh);
        text_fensi = (LinearLayout) findViewById(R.id.text_fensi);
        collect_num = (TextView) findViewById(R.id.collect_num);
        text_collect = (LinearLayout) findViewById(R.id.text_collect);
        text_pin = (TextView) findViewById(R.id.text_pin);
        recy_card= (RecyclerView) findViewById(R.id.recy_card);
        sv_tz= (SpringView) findViewById(R.id.sv_tz);
        rl_bg= (RelativeLayout) findViewById(R.id.rl_bg);
        v1 = (View) findViewById(R.id.v1);
        liner_tiezi = (LinearLayout) findViewById(R.id.liner_tiezi);
        text_zan = (TextView) findViewById(R.id.text_zan);
        v2 = (View) findViewById(R.id.v2);
        liner_dongtai = (LinearLayout) findViewById(R.id.liner_dongtai);

        String title = getIntent().getStringExtra("title");
        if(title!=null){
            tv_dt_title.setText(title);
        }

        if("我的主页".equals(title)){
            iv_gz.setVisibility(View.INVISIBLE);
            img_tou.setOnClickListener(this);
            rl_bg.setOnClickListener(this);
        }

        zdPop = new ZDPop(MydynamicActivity.this,R.layout.zd_pop_item_view);
        sharingPop = new SharingPop(MydynamicActivity.this,R.layout.sharing_pop_item_view);
        recycleViewDivider = new RecycleViewDivider(MydynamicActivity.this, GridLayoutManager.HORIZONTAL, 5, getResources().getColor(R.color.transparent));
          /*
           设置监听
         */
        if (mList.size()<=0){
            mList.add("");
            mList.add("");
            mList.add("");
            mList.add("");
            mList.add("");
        }
        recy_card.setLayoutManager(new LinearLayoutManager(MydynamicActivity.this));
        recy_card.setNestedScrollingEnabled(false);
        TieziAdapter tieziAdapter= new TieziAdapter(R.layout.tz_item_view, mList);
        recy_card.setAdapter(tieziAdapter);
        tieziAdapter.setOnDianClickListener(dianClickListener);
        tieziAdapter.SetOnItemClickListener(onItemClickListener);
        initRefresh();
        liner_dongtai.setOnClickListener(this);
        liner_tiezi.setOnClickListener(this);
        iv_gz.setOnClickListener(this);
        iv_zyfh.setOnClickListener(this);
        text_guanzhu.setOnClickListener(this);
        text_fensi.setOnClickListener(this);
        text_collect.setOnClickListener(this);

        zdPop.setOnDismissListener(onDismissListener);
        zdPop.setZDClickListener(zdClickListener);
        sharingPop.setOnDismissListener(onDismissListener);
    }

    ;private TieziAdapter.OnItemClickListener onItemClickListener = new TieziAdapter.OnItemClickListener() {
        @Override
        public void ItemClick(View view, int position) {

            Intent intent=new Intent(MydynamicActivity.this,JiaoDetailActivity.class);
            startActivity(intent);

        }

        @Override
        public void FxClickListener(int pos) {
//            setShowPop(sharingPop,liner_tiezi);
            showFXDialog(Gravity.BOTTOM, R.style.Bottom_Top_aniamtion);

        }

        @Override
        public void ScClickListener(int pos) {

        }

        @Override
        public void DzClickListener(int pos) {

        }
    };
    private LinearLayout ll_wx,ll_pyq,ll_qq,ll_kj,ll_wb;
    private TextView quxiao;
    Dialog dialog_fx;
    private void showFXDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        //设置触摸dialog外围是否关闭
        //设置监听事件
        dialog_fx = builder.setViewId(R.layout.sharing_pop_item_view)
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

        dialog_fx.show();
        ll_wx=dialog_fx.findViewById(R.id.ll_wx);
        ll_pyq=dialog_fx.findViewById(R.id.ll_pyq);
        ll_qq=dialog_fx.findViewById(R.id.ll_qq);
        ll_kj=dialog_fx.findViewById(R.id.ll_kj);
        ll_wb=dialog_fx.findViewById(R.id.ll_wb);
        quxiao=dialog_fx.findViewById(R.id.quxiao);
        quxiao.setOnClickListener(this);
        ll_wx.setOnClickListener(this);
        ll_pyq.setOnClickListener(this);
        ll_qq.setOnClickListener(this);
        ll_kj.setOnClickListener(this);
        ll_wb.setOnClickListener(this);
    }

    private void showGZDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        //设置触摸dialog外围是否关闭
        //设置监听事件
        final Dialog dialog = builder.setViewId(R.layout.gz_item_view)
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
        dialog.show();

        TextView tv_qxgz = dialog.findViewById(R.id.tv_qxgz);
        TextView tv_gz_close = dialog.findViewById(R.id.tv_gz_close);
        tv_qxgz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();

            }
        });
        tv_gz_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    private void showDDDDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        //设置触摸dialog外围是否关闭
        //设置监听事件
        DDdialog = builder.setViewId(R.layout.zd_pop_item_view)
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
        DDdialog.show();
        tv_jfzd=DDdialog.findViewById(R.id.tv_jfzd);
        tv_sc=DDdialog.findViewById(R.id.tv_sc);
        tv_close=DDdialog.findViewById(R.id.tv_close);
        tv_jfzd.setOnClickListener(this);
        tv_sc.setOnClickListener(this);
        tv_close.setOnClickListener(this);

    }

    private ZDPop.ZDClickListener zdClickListener = new ZDPop.ZDClickListener() {
        @Override
        public void OnZDClickListener() {

            shumaDialog(Gravity.CENTER,R.style.Alpah_aniamtion);

            zdPop.dismiss();
        }

        @Override
        public void OnSCClickListener() {

            zdPop.dismiss();
        }

        @Override
        public void OnQXClickListener() {
            zdPop.dismiss();
        }
    };

    private TieziAdapter.DianClickListener dianClickListener = new TieziAdapter.DianClickListener() {
        @Override
        public void onDianClickListener(int pos) {

//            setShowPop(zdPop,liner_tiezi);
//            zdPop.showAtLocation(liner_tiezi, Gravity.BOTTOM, 0, 0);

            showDDDDialog(Gravity.BOTTOM,R.style.Bottom_Top_aniamtion);

        }
    };

    private PopupWindow.OnDismissListener onDismissListener = new PopupWindow.OnDismissListener() {
        @Override
        public void onDismiss() {
            // TODO Auto-generated method stub
            setWindowTranslucence(1.0f);
        }
    };

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){

            case R.id.liner_tiezi://帖子
                text_pin.setTextColor(getResources().getColor(R.color.text_orage));
                v1.setVisibility(View.VISIBLE);
                text_zan.setTextColor(getResources().getColor(R.color.black_san));
                v2.setVisibility(View.INVISIBLE);
                recy_card.setLayoutManager(new LinearLayoutManager(MydynamicActivity.this));
                recy_card.removeItemDecoration(recycleViewDivider);
                recy_card.setNestedScrollingEnabled(false);
                TieziAdapter tieziAdapter= new TieziAdapter(R.layout.tz_item_view, mList);
                recy_card.setAdapter(tieziAdapter);

                break;

            case R.id.liner_dongtai://动态
                text_zan.setTextColor(getResources().getColor(R.color.text_orage));
                v2.setVisibility(View.VISIBLE);
                text_pin.setTextColor(getResources().getColor(R.color.black_san));
                v1.setVisibility(View.INVISIBLE);

                recy_card.setLayoutManager(new GridLayoutManager(MydynamicActivity.this,2));
                recycleViewDivider = new RecycleViewDivider(MydynamicActivity.this, GridLayoutManager.HORIZONTAL, 5, getResources().getColor(R.color.transparent));
                recy_card.addItemDecoration(recycleViewDivider);
//                recy_card.addItemDecoration(new GridDivider(MydynamicActivity.this, 20, this.getResources().getColor(R.color.white)));
                recy_card.setNestedScrollingEnabled(false);

                final DongTaiAdapter tagAdapter  = new DongTaiAdapter(R.layout.tui_recyitem, mList);

                recy_card.setAdapter(tagAdapter);

                tagAdapter.setOnItemClickListener(onItemClickListener1);
                break;

            case R.id.tv_jfzd:

                shumaDialog(Gravity.CENTER,R.style.Alpah_aniamtion);

                DDdialog.dismiss();

                break;
            case R.id.tv_sc:
                DDdialog.dismiss();
                break;
            case R.id.tv_close:

                DDdialog.dismiss();

                break;
            case R.id.iv_gz:

                showGZDialog(Gravity.BOTTOM, R.style.Bottom_Top_aniamtion);

                break;
            case R.id.iv_zyfh:
                finish();
                break;
            case R.id.text_guanzhu:

                intent=new Intent(MydynamicActivity.this, MyFllowActivity.class);
                startActivity(intent);

                break;
            case R.id.text_fensi://粉丝
                intent=new Intent(MydynamicActivity.this, MyFansiActivity.class);
                startActivity(intent);
                break;
            case R.id.text_collect:
                intent=new Intent(MydynamicActivity.this, MyCollectionActivity.class);
                startActivity(intent);
                break;
            case R.id.img_tou:
                Intent intent1=new Intent(this,PersonActivity.class);
                startActivity(intent1);
                break;
            case R.id.rl_bg:

                showphotoDialog(Gravity.BOTTOM, R.style.Bottom_Top_aniamtion);

                break;

            case R.id.ll_wx:
                SharebyWeixin(MydynamicActivity.this);
                break;
            case R.id.ll_pyq:
                SharebyWeixincenter(MydynamicActivity.this);
                break;
            case R.id.ll_qq:
                Log.e("TAG","点击1111333333");
                SharebyQQ(MydynamicActivity.this);
                break;
            case R.id.ll_kj:
                SharebyQzon(MydynamicActivity.this);
                break;
            case R.id.ll_wb:
                break;
            case R.id.quxiao:
                dialog_fx.dismiss();
                break;

        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }



    private BaseQuickAdapter.OnItemClickListener onItemClickListener1 = new BaseQuickAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            Intent intent = new Intent(MydynamicActivity.this, VideoActivity.class);
            startActivity(intent);

        }
    };


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
                    File file = new File(cutPath);
                    Log.d("TAg",cutPath);
                    Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    Drawable fromPath = Drawable.createFromPath(cutPath);
                    rl_bg.setBackground(fromPath);

                    /*
                       质量压缩
                     */
//                    FileOutputStream baos = null;
//                    try {
//
//                        baos = new FileOutputStream(filess);
//                        bitmap.compress(Bitmap.CompressFormat.JPEG, 2, baos);
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    }

//                    list.clear();
//                    list.add(cutPath);
//                    photoworks();
                    break;
            }
        }
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    //下拉刷新
    private void initRefresh() {
        //DefaultHeader/Footer是SpringView已经实现的默认头/尾之一
        //更多还有MeituanHeader、AliHeader、RotationHeader等如上图7种
        sv_tz.setType(SpringView.Type.FOLLOW);
        sv_tz.setFooter(new DefaultFooter(MydynamicActivity.this));
        sv_tz.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
//                Toast.makeText(mContext,"下拉刷新中",Toast.LENGTH_SHORT).show();
                // list.clear();
                // 网络请求;
                // mStarFragmentPresenter.queryData();
                //一分钟之后关闭刷新的方法
                finishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
//                Toast.makeText(mContext,"玩命加载中...",Toast.LENGTH_SHORT).show();
                finishFreshAndLoad();
            }
        });
    }

    /**
     * 关闭加载提示
     */
    private void finishFreshAndLoad() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                sv_tz.onFinishFreshAndLoad();
            }
        }, 2000);
    }
    private TextView text_sure;
    private void shumaDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(MydynamicActivity.this);
        final BaseDialog dialog = builder.setViewId(R.layout.dialog_zd)
                //设置dialogpadding
                .setPaddingdp(0, 10, 0, 10)
                //设置显示位置
                .setGravity(grary)
                //设置动画
                .setAnimation(animationStyle)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(false)
                //设置监听事件
                .builder();
        dialog.show();
        text_sure = dialog.getView(R.id.text_sure);
        TextView tv_xxgz = dialog.getView(R.id.tv_xxgz);
        tv_xxgz.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG ); //下划线
        TextView text_pause = dialog.getView(R.id.text_pause);
        //知道了
        text_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        //取消
        text_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        tv_xxgz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MydynamicActivity.this, TopRuleActivity.class);
                startActivity(intent);
            }
        });
        dialog.show();
    }


    private void cgDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(MydynamicActivity.this);
        final BaseDialog dialog = builder.setViewId(R.layout.dialog_dtcg)
                //设置dialogpadding
                .setPaddingdp(0, 10, 0, 10)
                //设置显示位置
                .setGravity(grary)
                //设置动画
                .setAnimation(animationStyle)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(false)
                //设置监听事件
                .builder();
        dialog.show();
    }

}
