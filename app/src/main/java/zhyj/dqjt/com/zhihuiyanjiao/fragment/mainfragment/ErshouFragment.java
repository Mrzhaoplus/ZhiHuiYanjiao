package zhyj.dqjt.com.zhihuiyanjiao.fragment.mainfragment;

import android.view.View;
import android.widget.ListView;

import zhyj.dqjt.com.zhihuiyanjiao.R;
import zhyj.dqjt.com.zhihuiyanjiao.adapter.TwoCardAdapter;
import zhyj.dqjt.com.zhihuiyanjiao.adapter.ZhaolingAdapter;
import zhyj.dqjt.com.zhihuiyanjiao.base.BaseFragment;

/**
 * Created by Mr赵 on 2018/4/3.
 */

public class ErshouFragment extends BaseFragment {
    private ListView lv;
    @Override
    protected int setContentView() {
        return R.layout.frag_ershou;
    }

    @Override
    protected void lazyLoad() {
        View contentView = getContentView();
        lv = contentView.findViewById(R.id.lv);
        TwoCardAdapter zhaolingAdapter = new TwoCardAdapter(getActivity());
        lv.setAdapter(zhaolingAdapter);
    }
}
