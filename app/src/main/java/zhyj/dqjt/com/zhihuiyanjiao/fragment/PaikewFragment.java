package zhyj.dqjt.com.zhihuiyanjiao.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import zhyj.dqjt.com.zhihuiyanjiao.R;
import zhyj.dqjt.com.zhihuiyanjiao.base.BaseFragment;
import zhyj.dqjt.com.zhihuiyanjiao.fragment.paikefragment.DarenFragment;
import zhyj.dqjt.com.zhihuiyanjiao.fragment.paikefragment.GuanzhuFragment;
import zhyj.dqjt.com.zhihuiyanjiao.fragment.paikefragment.TuijianFragment;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

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

    @Override
    protected int setContentView() {
        return R.layout.fragment_paike;
    }

    @Override
    protected void lazyLoad() {
        View contentView = getContentView();
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
        text_guanzhu.setTextColor(getResources().getColor(R.color.black_san));
        if (tuijianFragment == null) {
            tuijianFragment = new TuijianFragment();
        }
        addFragments(tuijianFragment);
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

        }
    }

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
