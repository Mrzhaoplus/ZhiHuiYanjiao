package zhyj.dqjt.com.zhihuiyanjiao.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ProgressBar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

import zhyj.dqjt.com.zhihuiyanjiao.R;

/**
 * Created by Administrator on 2018/4/4.
 */

public class ShangChuanAdapter extends BaseQuickAdapter<String, BaseViewHolder> {



    public ShangChuanAdapter(@LayoutRes int layoutResId, @Nullable ArrayList<String> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {



    }
}
