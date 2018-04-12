package www.diandianxing.com.diandianxing.fragment.mainfragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import www.diandianxing.com.diandianxing.adapter.Tuijiantieadapter;
import www.diandianxing.com.diandianxing.base.BaseFragment;
import www.diandianxing.com.diandianxing.R;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class OldnewFragment extends BaseFragment {
    private ListView jiao_list;
    private SpringView jiao_spring;
    @Override
    protected int setContentView() {
        return R.layout.fragment_oldnew;
    }

    @Override
    protected void lazyLoad() {
        View contentView = getContentView();
        jiao_list = contentView.findViewById(R.id.jiaodan_list);
        jiao_spring = contentView.findViewById(R.id.jiao_springview);
        Tuijiantieadapter jiaodianadapter=new Tuijiantieadapter(getActivity());
        jiao_list.setAdapter(jiaodianadapter);

        jiao_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Intent intent=new Intent(getActivity(),JiaoDetailActivity.class);
                startActivity(intent);
            }
        });
        jiao_spring.setType(SpringView.Type.FOLLOW);
        jiao_spring.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {


                    }
                }, 5000);
                jiao_spring.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {


                    }
                }, 5000);
                jiao_spring.onFinishFreshAndLoad();
            }
        });
        jiao_spring.setFooter(new DefaultFooter(getActivity()));
        jiao_spring.setHeader(new DefaultHeader(getActivity()));

    }
}
