package zhyj.dqjt.com.zhihuiyanjiao.fragment.messagefragment;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import zhyj.dqjt.com.zhihuiyanjiao.R;
import zhyj.dqjt.com.zhihuiyanjiao.adapter.Praiseadapter;
import zhyj.dqjt.com.zhihuiyanjiao.base.BaseFragment;
import zhyj.dqjt.com.zhihuiyanjiao.util.DividerItemDecoration;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */
/*
   点赞消息
 */
public class Praisefragment extends BaseFragment {

    private RecyclerView praise_recycle;
    private SpringView spring_view;

    @Override
    protected int setContentView() {
        return R.layout.fragment_praise;
    }
    @Override
    protected void lazyLoad() {
        View contentView = getContentView();
        praise_recycle = contentView.findViewById(R.id.praise_recycle);
        spring_view = contentView.findViewById(R.id.spring_view);
        Praiseadapter praiseadapter=new Praiseadapter(getActivity());
        praise_recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        praise_recycle.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL_LIST));
        praise_recycle.setAdapter(praiseadapter);
        praise_recycle.setNestedScrollingEnabled(false);
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
