package www.diandianxing.com.diandianxing.fragment.minefragment;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;

import www.diandianxing.com.diandianxing.adapter.TieziAdapter;
import www.diandianxing.com.diandianxing.base.BaseFragment;
import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.bean.GuanzhuJD;

/**
 * Created by ASUS on 2018/3/22.
 */

public class TieziFragment extends BaseFragment {

    private SpringView sv_tz;
    private RecyclerView recy_card;
    List<GuanzhuJD> mList=new ArrayList<>();
    @Override
    protected int setContentView() {
        return R.layout.fragment_tiezi;
    }

    @Override
    protected void lazyLoad() {

        View contentView = getContentView();
        Log.e("TAG","执行搭配");
        recy_card=contentView.findViewById(R.id.recy_card);
        sv_tz=contentView.findViewById(R.id.sv_tz);
        recy_card.setLayoutManager(new LinearLayoutManager(getActivity()));
        recy_card.setNestedScrollingEnabled(false);
        TieziAdapter tieziAdapter= new TieziAdapter(R.layout.tz_item_view,mList);
        recy_card.setAdapter(tieziAdapter);

        initRefresh();

    }

    //下拉刷新
    private void initRefresh() {
        //DefaultHeader/Footer是SpringView已经实现的默认头/尾之一
        //更多还有MeituanHeader、AliHeader、RotationHeader等如上图7种
        sv_tz.setType(SpringView.Type.FOLLOW);
        sv_tz.setFooter(new DefaultFooter(getActivity()));
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

}
