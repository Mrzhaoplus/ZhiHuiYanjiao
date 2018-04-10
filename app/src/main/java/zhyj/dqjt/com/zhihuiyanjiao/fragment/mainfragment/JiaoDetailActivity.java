package zhyj.dqjt.com.zhihuiyanjiao.fragment.mainfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import zhyj.dqjt.com.zhihuiyanjiao.R;
import zhyj.dqjt.com.zhihuiyanjiao.adapter.JiaoLiuyanAdapter;
import zhyj.dqjt.com.zhihuiyanjiao.adapter.TPAdapter1;
import zhyj.dqjt.com.zhihuiyanjiao.adapter.TieziAdapter;
import zhyj.dqjt.com.zhihuiyanjiao.adapter.Video_pinglun_Adapter;
import zhyj.dqjt.com.zhihuiyanjiao.base.BaseActivity;
import zhyj.dqjt.com.zhihuiyanjiao.fragment.minefragment.MydynamicActivity;
import zhyj.dqjt.com.zhihuiyanjiao.util.DividerItemDecoration;
import zhyj.dqjt.com.zhihuiyanjiao.util.MyContants;

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
        button_fabu = (Button) findViewById(R.id.button_fabu);
        /**
         *点击事件
         */
        tv_back.setOnClickListener(this);
        img_tou.setOnClickListener(this);
        jiao_Recycler.setLayoutManager(new GridLayoutManager(this,3));
        jiao_Recycler.setNestedScrollingEnabled(false);
        TPAdapter1 tpAdapter1 = new TPAdapter1(this);
        jiao_Recycler.setAdapter(tpAdapter1);
        jiao_pinglun.setLayoutManager(new LinearLayoutManager(this));
        jiao_pinglun.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
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



        }
    }

}
