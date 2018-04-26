package www.diandianxing.com.diandianxing.fragment.mainfragment;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import www.diandianxing.com.diandianxing.ShujuBean.zixun_Bean;
import www.diandianxing.com.diandianxing.ZixunDitailsActivity;
import www.diandianxing.com.diandianxing.adapter.ZiXunAdapter;
import www.diandianxing.com.diandianxing.base.BaseFragment;
import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.bean.Evebtbus_fragment;
import www.diandianxing.com.diandianxing.interfase.Sear_Zixun_presenterlinterfase;
import www.diandianxing.com.diandianxing.interfase.Zixun_presenterinterfase;
import www.diandianxing.com.diandianxing.presenter.Search_Zixun_presenter;
import www.diandianxing.com.diandianxing.presenter.Zixun_presenter;
import www.diandianxing.com.diandianxing.util.Api;

/**
 * Created by Mr赵 on 2018/4/2.
 */

public class ZiXunFragment extends BaseFragment implements Zixun_presenterinterfase {
    private ListView lv;
    private SpringView sv;
   private int typeNo=1;
   List<zixun_Bean.DatasBean>list=new ArrayList<>();
    private Zixun_presenter zixun_presenter = new Zixun_presenter(this);
    private Search_Zixun_presenter search_zixun = new Search_Zixun_presenter(this);
    private ZiXunAdapter ziXunAdapter;
     public String content;
     public boolean flag=true;
    private int typeid;

    public ZiXunFragment() {
    }
    public ZiXunFragment(String content) {
        this.content = content;
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_yanjiaozixun;
    }

    @Override
    protected void lazyLoad() {
        View contentView = getContentView();
        this.lv = contentView.findViewById(R.id.list_view);
        this.sv = contentView.findViewById(R.id.zixun_sv);
        if(flag==true){
            //注册
            EventBus.getDefault().register(this);
            flag=false;
        }

        if(typeid==1){
            search_zixun.getpath(2, Api.token,typeNo,content);
        }else{
            zixun_presenter.getpath(2, Api.token,typeNo);
        }

        //创建适配器
        ziXunAdapter = new ZiXunAdapter(getActivity(),list);
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
                        list.clear();
                        typeNo=1;
                        zixun_presenter.getpath(2, Api.token,typeNo);
                        ziXunAdapter.notifyDataSetChanged();
                    }
                }, 0);
                sv.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                         typeNo++;
                        zixun_presenter.getpath(2, Api.token,typeNo);
                        ziXunAdapter.notifyDataSetChanged();
                    }
                }, 0);
                sv.onFinishFreshAndLoad();

            }
        });
        sv.setFooter(new DefaultFooter(getActivity()));
        sv.setHeader(new DefaultHeader(getActivity()));

    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void fangfa(Evebtbus_fragment eveen){
        typeid = eveen.getTypeid();

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        zixun_presenter.kong();
        search_zixun.getkong();
        //注销
        EventBus.getDefault().unregister(this);
        EventBus.getDefault().removeAllStickyEvents();
    }

    @Override
    public void getsuccess(zixun_Bean zixun) {
        if(zixun.getCode().equals("200")){
            List<zixun_Bean.DatasBean> datas = zixun.getDatas();
            if(typeNo>1){
                Log.e("TAG","=====sj========="+datas.size());
                if(datas.size()>0){
                    list.addAll(datas);
                }else{
                    Toast.makeText(getActivity(),Api.TOAST,Toast.LENGTH_SHORT).show();
                }
            }else{
                list.addAll(datas);
            }

            ziXunAdapter.notifyDataSetChanged();
        }
    }
}
