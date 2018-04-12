package www.diandianxing.com.diandianxing.fragment.minefragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.widget.SpringView;
import com.luck.picture.lib.decoration.RecycleViewDivider;

import org.zackratos.ultimatebar.UltimateBar;

import java.util.ArrayList;

import www.diandianxing.com.diandianxing.MyCollectionActivity;
import www.diandianxing.com.diandianxing.TopRuleActivity;
import www.diandianxing.com.diandianxing.adapter.DongTaiAdapter;
import www.diandianxing.com.diandianxing.adapter.TieziAdapter;
import www.diandianxing.com.diandianxing.base.BaseActivity;
import www.diandianxing.com.diandianxing.util.BaseDialog;
import www.diandianxing.com.diandianxing.util.SharingPop;
import www.diandianxing.com.diandianxing.util.ZDPop;
import www.diandianxing.com.diandianxing.R;

/**
 * Created by ASUS on 2018/3/21.
 * 他人主页
 */

public class MydynamicActivity extends BaseActivity implements View.OnClickListener {
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
    ArrayList<String> mList=new ArrayList<>();

    private ZDPop zdPop;
    private SharingPop sharingPop;
    private TextView tv_jfzd,tv_sc,tv_close;
    Dialog DDdialog;
    RecycleViewDivider recycleViewDivider;
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
        v1 = (View) findViewById(R.id.v1);
        liner_tiezi = (LinearLayout) findViewById(R.id.liner_tiezi);
        text_zan = (TextView) findViewById(R.id.text_zan);
        v2 = (View) findViewById(R.id.v2);
        liner_dongtai = (LinearLayout) findViewById(R.id.liner_dongtai);
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


    private void showFXDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        //设置触摸dialog外围是否关闭
        //设置监听事件
        Dialog dialog = builder.setViewId(R.layout.sharing_pop_item_view)
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
        }
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
