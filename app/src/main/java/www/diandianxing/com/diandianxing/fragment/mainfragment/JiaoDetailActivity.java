package www.diandianxing.com.diandianxing.fragment.mainfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import www.diandianxing.com.diandianxing.adapter.JiaoLiuyanAdapter;
import www.diandianxing.com.diandianxing.adapter.TPAdapter1;
import www.diandianxing.com.diandianxing.base.BaseActivity;
import www.diandianxing.com.diandianxing.fragment.minefragment.MydynamicActivity;
import www.diandianxing.com.diandianxing.util.BaseDialog;
import www.diandianxing.com.diandianxing.util.DividerItemDecoration;
import www.diandianxing.com.diandianxing.util.MyContants;
import www.diandianxing.com.diandianxing.R;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class JiaoDetailActivity extends BaseActivity implements View.OnClickListener {


    private ImageView tv_back;
    private ImageView img_collect;
    private RelativeLayout real;
    private TextView text_title;
    private ImageView img_tou;
    private TextView text_name;
    private TextView da_address;
    private ImageView imageView2;
    private TextView text_dengji;
    private RelativeLayout rela_guanzhu;
    private TextView text_count;
    private RecyclerView jiao_Recycler;
    private TextView text_share;
    private TextView text_zan;
    private RecyclerView jiao_pinglun;
    private TextView liuyan;
    private EditText ed_text;
    private Button button_fabu;
    private TextView tv_jdxq_gz;
    private TextView text_sure;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyContants.windows(this);
        setContentView(R.layout.activity_jiaodetail);
        initView();

    }


    private void initView() {
        tv_back = (ImageView) findViewById(R.id.tv_back);
        img_collect = (ImageView) findViewById(R.id.img_collect);
        real = (RelativeLayout) findViewById(R.id.real);
        text_title = (TextView) findViewById(R.id.text_title);
        img_tou = (ImageView) findViewById(R.id.img_tou);
        text_name = (TextView) findViewById(R.id.text_name);
        da_address = (TextView) findViewById(R.id.da_address);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        text_dengji = (TextView) findViewById(R.id.text_dengji);
        rela_guanzhu = (RelativeLayout) findViewById(R.id.rela_guanzhu);
        text_count = (TextView) findViewById(R.id.text_count);
        jiao_Recycler = (RecyclerView) findViewById(R.id.jiao_Recycler);
        text_share = (TextView) findViewById(R.id.text_share);
        text_zan = (TextView) findViewById(R.id.text_zan);
        jiao_pinglun = (RecyclerView) findViewById(R.id.jiao_pinglun);
        liuyan = (TextView) findViewById(R.id.text_liuyan);
        ed_text = (EditText) findViewById(R.id.ed_text);
        tv_jdxq_gz= (TextView) findViewById(R.id.tv_jdxq_gz);
        button_fabu = (Button) findViewById(R.id.button_fabu);
        /**
         *点击事件
         */
        tv_back.setOnClickListener(this);
        img_tou.setOnClickListener(this);
        tv_jdxq_gz.setOnClickListener(this);
        jiao_Recycler.setLayoutManager(new GridLayoutManager(this,3));
        jiao_Recycler.setNestedScrollingEnabled(false);
        TPAdapter1 tpAdapter1 = new TPAdapter1(this);
        jiao_Recycler.setAdapter(tpAdapter1);
        jiao_pinglun.setLayoutManager(new LinearLayoutManager(this));
        jiao_pinglun.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        jiao_pinglun.setNestedScrollingEnabled(false);
        JiaoLiuyanAdapter jiaoLiuyanAdapter = new JiaoLiuyanAdapter(this);
        jiao_pinglun.setAdapter(jiaoLiuyanAdapter);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.tv_back:
                finish();
                break;
            case R.id.img_tou:
                Intent intent = new Intent(JiaoDetailActivity.this, MydynamicActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_jdxq_gz:
                shumaDialog(Gravity.CENTER,R.style.Alpah_aniamtion);
                break;


        }
    }


    private void shumaDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(JiaoDetailActivity.this);
        final BaseDialog dialog = builder.setViewId(R.layout.dialog_guanzhu)
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
        text_sure = dialog.getView(R.id.text_sure);

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
        dialog.show();
    }

}
