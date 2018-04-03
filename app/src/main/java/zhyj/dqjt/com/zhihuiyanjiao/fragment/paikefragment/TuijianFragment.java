package zhyj.dqjt.com.zhihuiyanjiao.fragment.paikefragment;

import android.os.Handler;
import android.view.View;
import android.widget.GridView;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import zhyj.dqjt.com.zhihuiyanjiao.R;
import zhyj.dqjt.com.zhihuiyanjiao.adapter.Tujianadapter;
import zhyj.dqjt.com.zhihuiyanjiao.base.BaseFragment;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class TuijianFragment extends BaseFragment {

    private GridView  tui_recycler;
    private SpringView spring_view;

    @Override
    protected int setContentView() {
        return R.layout.fragment_tuijian;
    }

    @Override
    protected void lazyLoad() {
        View contentView =getContentView();
        tui_recycler = contentView.findViewById(R.id.tui_recycler);
        Tujianadapter tujianadapter=new Tujianadapter(getActivity());
         tui_recycler.setAdapter(tujianadapter);
        spring_view = contentView.findViewById(R.id.spring_view);
        spring_view.setType(SpringView.Type.FOLLOW);
        spring_view.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                },5000);
                spring_view.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                },5000);
                spring_view.onFinishFreshAndLoad();
            }
        });

         spring_view.setHeader(new DefaultHeader(getActivity()));
        spring_view.setFooter(new DefaultFooter(getActivity()));

    }
}
