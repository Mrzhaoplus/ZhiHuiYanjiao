package www.diandianxing.com.diandianxing.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.compress.Luban;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import www.diandianxing.com.diandianxing.Login.LoginActivity;
import www.diandianxing.com.diandianxing.MainActivity;
import www.diandianxing.com.diandianxing.ReleaseShootoffVidoActivity;
import www.diandianxing.com.diandianxing.base.BaseFragment;
import www.diandianxing.com.diandianxing.bean.MsgBus;
import www.diandianxing.com.diandianxing.fragment.paikefragment.DarenFragment;
import www.diandianxing.com.diandianxing.fragment.paikefragment.GuanzhuFragment;
import www.diandianxing.com.diandianxing.fragment.paikefragment.TuijianFragment;
import www.diandianxing.com.diandianxing.util.GlobalParams;
import www.diandianxing.com.diandianxing.util.SpUtils;
import www.diandianxing.com.diandianxing.util.XCPopwindow;
import www.diandianxing.com.diandianxing.R;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

@SuppressLint("ValidFragment")
public class PaikewFragment extends BaseFragment implements View.OnClickListener {
    private Fragment currentf;
    public View rootView;
    public ImageView tv_back;
    public View v1;
    public View v2;
    public View v3;
    public ImageView tv_pai;
    public RelativeLayout relar;
    public FrameLayout find_layout;
    private LinearLayout liner1;
    private LinearLayout liner2;
    private LinearLayout liner3;
    private TextView text_guanzhu;
    private TextView text_tuijian;
    private TextView text_daren;
    GuanzhuFragment guanzhuFragment;
    TuijianFragment tuijianFragment;
    DarenFragment darenFragment;

    String msg;

    @SuppressLint("ValidFragment")
    public PaikewFragment(String msg){

        this.msg=msg;

    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_paike;
    }

    @Override
    protected void lazyLoad() {
        View contentView = getContentView();
//        EventBus.getDefault().register(this);
        tv_back =(ImageView) contentView.findViewById(R.id.tv_back);
        this.tv_back = (ImageView)  contentView.findViewById(R.id.tv_back);
        this.v1 = (View)  contentView.findViewById(R.id.v1);
        this.v2 = (View) contentView.findViewById(R.id.v2);
        this.v3 = (View)  contentView.findViewById(R.id.v3);
        this.tv_pai = (ImageView)  contentView.findViewById(R.id.tv_pai);
        this.find_layout = (FrameLayout)  contentView.findViewById(R.id.find_layout);
        liner1 = contentView.findViewById(R.id.liner1);
        liner2 = contentView.findViewById(R.id.liner2);
        liner3 = contentView.findViewById(R.id.liner3);
        text_guanzhu = contentView.findViewById(R.id.text_guanzhu);
        text_tuijian = contentView.findViewById(R.id.text_tuijian);
        text_daren = contentView.findViewById(R.id.text_daren);
         liner1.setOnClickListener(this);
         liner2.setOnClickListener(this);
         liner3.setOnClickListener(this);
         tv_pai.setOnClickListener(this);

//        if("关注".equals(msg)){
//            text_guanzhu.setTextColor(getResources().getColor(R.color.text_orage));
//            v1.setVisibility(View.VISIBLE);
//            v2.setVisibility(View.INVISIBLE);
//            v3.setVisibility(View.INVISIBLE);
//            text_daren.setTextColor(getResources().getColor(R.color.black_san));
//            text_tuijian.setTextColor(getResources().getColor(R.color.black_san));
//            if (guanzhuFragment == null) {
//                guanzhuFragment = new GuanzhuFragment();
//            }else {
//                Intent intent = new Intent();
//                intent.setAction(GlobalParams.LOGING_SX);
//                getActivity().sendBroadcast(intent);
//            }
//            addFragments(guanzhuFragment);
//
//        }else{
            text_guanzhu.setTextColor(getResources().getColor(R.color.black_san));
            if (tuijianFragment == null) {
                tuijianFragment = new TuijianFragment();
            }
            addFragments(tuijianFragment);
//        }


    }
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void XXX(MsgBus messageEvent) {
//
//        if("关注".equals(messageEvent.tiaozhuan)){
//
//        }
//    }


    public void Qiehuan(){
        Log.e("TAG","发送到拍客方法");
//        text_guanzhu.setTextColor(getResources().getColor(R.color.text_orage));
//        v1.setVisibility(View.VISIBLE);
//        v2.setVisibility(View.INVISIBLE);
//        v3.setVisibility(View.INVISIBLE);
//        text_daren.setTextColor(getResources().getColor(R.color.black_san));
//        text_tuijian.setTextColor(getResources().getColor(R.color.black_san));
//        if (guanzhuFragment == null) {
//            guanzhuFragment = new GuanzhuFragment();
//        }else {
//            Intent intent = new Intent();
//            intent.setAction(GlobalParams.LOGING_SX);
//            getActivity().sendBroadcast(intent);
//        }
//        addFragments(guanzhuFragment);
        text_tuijian.setTextColor(getResources().getColor(R.color.text_orage));
        v2.setVisibility(View.VISIBLE);
        v1.setVisibility(View.INVISIBLE);
        v3.setVisibility(View.INVISIBLE);
        text_daren.setTextColor(getResources().getColor(R.color.black_san));
        text_guanzhu.setTextColor(getResources().getColor(R.color.black_san));
        if (tuijianFragment == null) {
            tuijianFragment = new TuijianFragment();
        }
        addFragments(tuijianFragment);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.liner1:
                text_guanzhu.setTextColor(getResources().getColor(R.color.text_orage));
                v1.setVisibility(View.VISIBLE);
                v2.setVisibility(View.INVISIBLE);
                v3.setVisibility(View.INVISIBLE);
               text_daren.setTextColor(getResources().getColor(R.color.black_san));
               text_tuijian.setTextColor(getResources().getColor(R.color.black_san));
                if (guanzhuFragment == null) {
                    guanzhuFragment = new GuanzhuFragment();
                }
                addFragments(guanzhuFragment);
                break;
            case R.id.liner2:
                text_tuijian.setTextColor(getResources().getColor(R.color.text_orage));
                v2.setVisibility(View.VISIBLE);
                v1.setVisibility(View.INVISIBLE);
                v3.setVisibility(View.INVISIBLE);
                text_daren.setTextColor(getResources().getColor(R.color.black_san));
                 text_guanzhu.setTextColor(getResources().getColor(R.color.black_san));
                if (tuijianFragment == null) {
                    tuijianFragment = new TuijianFragment();
                }
                addFragments(tuijianFragment);
                break;
            case R.id.liner3:
                 text_daren.setTextColor(getResources().getColor(R.color.text_orage));
                v3.setVisibility(View.VISIBLE);
                v1.setVisibility(View.INVISIBLE);
                v2.setVisibility(View.INVISIBLE);
                text_tuijian.setTextColor(getResources().getColor(R.color.black_san));
                text_guanzhu.setTextColor(getResources().getColor(R.color.black_san));
                if (darenFragment == null) {
                    darenFragment = new DarenFragment();
                }
                addFragments(darenFragment);
                break;
                case R.id.tv_pai:
                    //点击弹出Pop框
                    XCPopwindow addPopwindow = new XCPopwindow((MainActivity) getActivity(),getStatusBarHeight(getActivity()));
                    addPopwindow.showMoreWindow(view);
                    addPopwindow.setOnAlbumClickListener(albumClickListener);
                    break;

        }
    }

    private static int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = res.getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }

    private XCPopwindow.AlbumClickListener albumClickListener = new XCPopwindow.AlbumClickListener() {
        @Override
        public void OnAlbumClickListener() {
            int guid = SpUtils.getInt(mContext, "guid", 0);
            if(guid!=2){
                mContext.startActivity(new Intent(mContext,LoginActivity.class));
            }else{
                requestPhoto();
            }
        }
    };

    private void requestPhoto() {
        // 进入相册 以下是例子：不需要的api可以不写
        PictureSelector.create(getActivity())
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
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == getActivity().RESULT_OK) {
//            Log.e("TAG","图片返回");
//            switch (requestCode) {
//                case PictureConfig.CHOOSE_REQUEST:
//                    Log.e("TAG","图片选择结果回调");
//                    // 图片选择结果回调
//                    selectList = PictureSelector.obtainMultipleResult(data);
//                    listAll.addAll(selectList);
//                    selectList.clear();
//                    Info info = new Info();
//                    info.list = new ArrayList<>();
//                    info.list.addAll(listAll);
//                    Intent intent = new Intent(getActivity(),ReleaseShootoffActivity.class);
//                    intent.putExtra("list",info);
//                    startActivity(intent);
//                    break;
//            }
//        }
//    }


    private void addFragments(Fragment f) {
        // 第一步：得到fragment管理类
        FragmentManager manager = getChildFragmentManager();
        // 第二步：开启一个事务
        FragmentTransaction transaction = manager.beginTransaction();

        if (currentf != null) {
            //每次把前一个fragment给隐藏了
            transaction.hide(currentf);
        }

        //isAdded:判断当前的fragment对象是否被加载过
        if (!f.isAdded()) {
            // 第三步：调用添加fragment的方法 第一个参数：容器的id 第二个参数：要放置的fragment的一个实例对象
            transaction.add(R.id.find_layout, f);
        }
        //显示当前的fragment
        transaction.show(f);

        // 第四步：提交
        transaction.commit();

        currentf = f;
    }
}
