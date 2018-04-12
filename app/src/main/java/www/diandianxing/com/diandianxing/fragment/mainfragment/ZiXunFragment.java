package www.diandianxing.com.diandianxing.fragment.mainfragment;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import www.diandianxing.com.diandianxing.ZixunDitailsActivity;
import www.diandianxing.com.diandianxing.adapter.ZiXunAdapter;
import www.diandianxing.com.diandianxing.base.BaseFragment;
import www.diandianxing.com.diandianxing.R;

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

        //listview点击事件
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), ZixunDitailsActivity.class);
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
