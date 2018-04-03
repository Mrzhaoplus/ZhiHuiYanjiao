package zhyj.dqjt.com.zhihuiyanjiao.fragment.messagefragment;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import zhyj.dqjt.com.zhihuiyanjiao.R;
import zhyj.dqjt.com.zhihuiyanjiao.adapter.Commentadapter;
import zhyj.dqjt.com.zhihuiyanjiao.base.BaseFragment;
import zhyj.dqjt.com.zhihuiyanjiao.util.DividerItemDecoration;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */
/*
     评论消息
 */
public class Commentfragment extends BaseFragment {

    private RecyclerView comment_relycle;
    private SpringView spring_view;

    @Override
    protected int setContentView() {
        return R.layout.fragment_comment;
    }

    @Override
    protected void lazyLoad() {
        View contentView = getContentView();
        comment_relycle = contentView.findViewById(R.id.comment_recycle);
        spring_view = contentView.findViewById(R.id.spring_view);

        comment_relycle.setNestedScrollingEnabled(false);
        Commentadapter commentadapter=new Commentadapter(getActivity());
        comment_relycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        comment_relycle.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL_LIST));
        comment_relycle.setAdapter(commentadapter);
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
        spring_view.setFooter(new DefaultFooter(getActivity()));
        spring_view.setHeader(new DefaultHeader(getActivity()));

    }
}
