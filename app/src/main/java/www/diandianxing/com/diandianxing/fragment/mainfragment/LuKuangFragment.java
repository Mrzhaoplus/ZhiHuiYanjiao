package www.diandianxing.com.diandianxing.fragment.mainfragment;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;

import www.diandianxing.com.diandianxing.LukuangDitailsActivity;
import www.diandianxing.com.diandianxing.ShujuBean.LuKuang_Bean;
import www.diandianxing.com.diandianxing.adapter.LuKuangAdapter;
import www.diandianxing.com.diandianxing.base.BaseFragment;
import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.interfase.Lukuang_presenter_interfase;
import www.diandianxing.com.diandianxing.presenter.Lukuang_presenter;

/**
 * Created by Mr赵 on 2018/4/2.
 */

public class LuKuangFragment extends BaseFragment implements Lukuang_presenter_interfase {


    private SpringView sv;
    private ListView lv;
    private Lukuang_presenter lukuang_presenter;
    private int type=1;
    List<List<LuKuang_Bean.DatasBean>>list=new ArrayList();
    private LuKuangAdapter luKuangAdapter;

    @Override
    protected int setContentView() {
        return R.layout.fragment_shishilukuang;
    }

    @Override
    protected void lazyLoad() {
        View contentView = getContentView();
        sv = contentView.findViewById(R.id.lu_sv);
        lv = contentView.findViewById(R.id.list_view);
         //创建引用
        lukuang_presenter = new Lukuang_presenter(this);
        lukuang_presenter.getpath(type);



        //刷新加载事件
        sv.setType(SpringView.Type.FOLLOW);
        sv.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        type=1;
                        lukuang_presenter.getpath(type);
                        luKuangAdapter.notifyDataSetChanged();

                    }
                }, 5000);
                sv.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        type++;
                        lukuang_presenter.getpath(type);
                        luKuangAdapter.notifyDataSetChanged();
                    }
                }, 5000);
                sv.onFinishFreshAndLoad();

            }
        });
        sv.setFooter(new DefaultFooter(getActivity()));
        sv.setHeader(new DefaultHeader(getActivity()));
    }

    @Override
    public void getsuccess(LuKuang_Bean luKuang_bean) {
        if(luKuang_bean.getCode().equals("200")){
            final List<LuKuang_Bean.DatasBean> datas = luKuang_bean.getDatas();

            //适配器
            luKuangAdapter = new LuKuangAdapter(getActivity(),datas);
            lv.setAdapter(luKuangAdapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(getActivity(), LukuangDitailsActivity.class);
                    intent.putExtra("id",datas.get(i).getId());
                    startActivity(intent);
                }
            });


        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        lukuang_presenter.getkong();
    }
}
