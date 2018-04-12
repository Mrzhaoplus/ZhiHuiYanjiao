package www.diandianxing.com.diandianxing.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

import www.diandianxing.com.diandianxing.R;

/**
 * Created by Administrator on 2018/4/8.
 */

public class DongTaiAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public DongTaiAdapter(@LayoutRes int layoutResId, @Nullable ArrayList<String> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

        RelativeLayout rl_dt = helper.getView(R.id.rl_dt);

        if(helper.getAdapterPosition()%2!=0){

            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) rl_dt.getLayoutParams();

            layoutParams.rightMargin=10;
            rl_dt.setLayoutParams(layoutParams);


        }


    }
}
