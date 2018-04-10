package zhyj.dqjt.com.zhihuiyanjiao.fragment.mainfragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import zhyj.dqjt.com.zhihuiyanjiao.R;
import zhyj.dqjt.com.zhihuiyanjiao.adapter.GuanzhuAdapter;
import zhyj.dqjt.com.zhihuiyanjiao.base.BaseFragment;

/**
 * Created by Mr赵 on 2018/4/3.
 */

public class GuanzhuFragment extends BaseFragment {

    private ListView lv;

    @Override
    protected int setContentView() {
        return R.layout.frag_gz;
    }

    @Override
    protected void lazyLoad() {
        View contentView = getContentView();
        lv = contentView.findViewById(R.id.lv);
        GuanzhuAdapter guanzhuAdapter = new GuanzhuAdapter(getActivity());
        lv.setAdapter(guanzhuAdapter);
         lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 Intent intent = new Intent(getActivity(), JiaoDetailActivity.class);
                 startActivity(intent);
             }
         });

    }
}
