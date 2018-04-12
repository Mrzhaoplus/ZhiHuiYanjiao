package www.diandianxing.com.diandianxing.fragment.mainfragment;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import www.diandianxing.com.diandianxing.LukuangDitailsActivity;
import www.diandianxing.com.diandianxing.adapter.LuKuangAdapter;
import www.diandianxing.com.diandianxing.base.BaseFragment;
import www.diandianxing.com.diandianxing.R;

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
