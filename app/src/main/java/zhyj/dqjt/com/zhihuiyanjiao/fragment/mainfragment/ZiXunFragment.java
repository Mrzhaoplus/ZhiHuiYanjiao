package zhyj.dqjt.com.zhihuiyanjiao.fragment.mainfragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.liaoinstan.springview.widget.SpringView;

import zhyj.dqjt.com.zhihuiyanjiao.R;
import zhyj.dqjt.com.zhihuiyanjiao.adapter.ZiXunAdapter;
import zhyj.dqjt.com.zhihuiyanjiao.base.BaseFragment;

/**
 * Created by Mr赵 on 2018/4/2.
 */

public class ZiXunFragment extends BaseFragment {


    private ListView lv;
    private SpringView sv;

    @Override
    protected int setContentView() {
        return R.layout.fragment_yanjiaozixun;
    }

    @Override
    protected void lazyLoad() {
        View contentView = getContentView();
        this.lv = contentView.findViewById(R.id.list_view);
        this.sv = contentView.findViewById(R.id.zixun_sv);
        //创建适配器
        ZiXunAdapter ziXunAdapter = new ZiXunAdapter(getActivity());
        lv.setAdapter(ziXunAdapter);




        //刷新加载事件
        sv.setType(SpringView.Type.FOLLOW);
        sv.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {


                    }
                }, 5000);
                sv.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {


                    }
                }, 5000);
                sv.onFinishFreshAndLoad();

            }
        });


    }
}
