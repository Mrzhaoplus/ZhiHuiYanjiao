package www.diandianxing.com.diandianxing.fragment.paikefragment;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import www.diandianxing.com.diandianxing.adapter.Masteradapter;
import www.diandianxing.com.diandianxing.base.BaseFragment;
import www.diandianxing.com.diandianxing.util.DividerItemDecoration;
import www.diandianxing.com.diandianxing.R;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class DarenFragment extends BaseFragment {

    private RecyclerView daren_recycler;
    private SpringView spring_view;

    @Override
    protected int setContentView() {
        return R.layout.fragment_daren;
    }

    @Override
    protected void lazyLoad() {
        View contentView = getContentView();
        daren_recycler = contentView.findViewById(R.id.daren_recycler);
        spring_view = contentView.findViewById(R.id.spring_view);
        Masteradapter masteradapter =new Masteradapter(getActivity());
        daren_recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        daren_recycler.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        daren_recycler.setAdapter(masteradapter);
        spring_view.setType(SpringView.Type.FOLLOW);
        daren_recycler.setNestedScrollingEnabled(false);
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
