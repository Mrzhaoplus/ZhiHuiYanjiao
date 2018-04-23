package www.diandianxing.com.diandianxing.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.bean.PoiAddressBean;

/**
 * Created by Administrator on 2018/4/4.
 */

public class LocationAdapter extends BaseQuickAdapter<PoiAddressBean, BaseViewHolder> {

    public LocationAdapter(@LayoutRes int layoutResId, @Nullable ArrayList<PoiAddressBean> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, PoiAddressBean item) {

        TextView tv_wz_title=helper.getView(R.id.tv_wz_title);

        TextView tv_wz_content=helper.getView(R.id.tv_wz_content);

        tv_wz_title.setText(item.detailAddress);
        tv_wz_content.setText(item.getText());

    }
}
