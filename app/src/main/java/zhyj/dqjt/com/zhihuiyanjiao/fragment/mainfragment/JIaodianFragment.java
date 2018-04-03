package zhyj.dqjt.com.zhihuiyanjiao.fragment.mainfragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import zhyj.dqjt.com.zhihuiyanjiao.R;
import zhyj.dqjt.com.zhihuiyanjiao.adapter.Jiaodianadapter;
import zhyj.dqjt.com.zhihuiyanjiao.base.BaseFragment;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class JIaodianFragment extends BaseFragment {

    private ListView jiao_list;
    private SpringView jiao_spring;

    @Override
    protected int setContentView() {
        return R.layout.fragment_jiaodian;
    }

    @Override
    protected void lazyLoad() {

        View contentView = getContentView();
        jiao_list = contentView.findViewById(R.id.jiaodan_list);
        jiao_spring = contentView.findViewById(R.id.jiao_springview);
        Jiaodianadapter jiaodianadapter=new Jiaodianadapter(getActivity());
         jiao_list.setAdapter(jiaodianadapter);

          jiao_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                  Intent intent=new Intent(getActivity(),JiaoDetailActivity.class);
                  startActivity(intent);
              }
          });
        jiao_spring.setFooter(new DefaultFooter(getActivity()));
        jiao_spring.setHeader(new DefaultHeader(getActivity()));
    }
}
