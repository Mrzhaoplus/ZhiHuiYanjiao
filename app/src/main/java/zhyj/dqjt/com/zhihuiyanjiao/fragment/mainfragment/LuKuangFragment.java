package zhyj.dqjt.com.zhihuiyanjiao.fragment.mainfragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import zhyj.dqjt.com.zhihuiyanjiao.LukuangDitailsActivity;
import zhyj.dqjt.com.zhihuiyanjiao.R;
import zhyj.dqjt.com.zhihuiyanjiao.adapter.LuKuangAdapter;
import zhyj.dqjt.com.zhihuiyanjiao.base.BaseFragment;

/**
 * Created by Mr赵 on 2018/4/2.
 */

public class LuKuangFragment extends BaseFragment {


    private SpringView sv;
    private ListView lv;

    @Override
    protected int setContentView() {
        return R.layout.fragment_shishilukuang;
    }

    @Override
    protected void lazyLoad() {
        View contentView = getContentView();
        sv = contentView.findViewById(R.id.lu_sv);
        lv = contentView.findViewById(R.id.list_view);
        //适配器
        LuKuangAdapter luKuangAdapter = new LuKuangAdapter(getActivity());
        lv.setAdapter(luKuangAdapter);
         lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 Intent intent = new Intent(getActivity(), LukuangDitailsActivity.class);
                 startActivity(intent);
             }
         });

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
        sv.setFooter(new DefaultFooter(getActivity()));
        sv.setHeader(new DefaultHeader(getActivity()));
    }
}
