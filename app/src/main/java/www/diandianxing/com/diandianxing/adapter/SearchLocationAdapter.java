package www.diandianxing.com.diandianxing.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/4/4.
 */

public class SearchLocationAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public SearchLocationAdapter(@LayoutRes int layoutResId, @Nullable ArrayList<String> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
    }
}
