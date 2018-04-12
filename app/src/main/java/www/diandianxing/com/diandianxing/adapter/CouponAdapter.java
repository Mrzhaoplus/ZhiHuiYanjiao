package www.diandianxing.com.diandianxing.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

import www.diandianxing.com.diandianxing.R;

/**
 * Created by Administrator on 2018/4/11.
 */

public class CouponAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    private int type;

    public CouponAdapter(@LayoutRes int layoutResId, @Nullable ArrayList<String> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {


        LinearLayout ll_zt = helper.getView(R.id.ll_zt);
        TextView tv_zt = helper.getView(R.id.tv_zt);


        switch (type){
            case 0:
                ll_zt.setBackgroundResource(R.drawable.yhj_ys_3x);
                tv_zt.setBackgroundResource(android.R.color.transparent);
                tv_zt.setText("立即购买");

                break;
            case 1:
                ll_zt.setBackgroundResource(R.drawable.yhj_ys_3x);
                tv_zt.setBackgroundResource(R.drawable.ysy);
                tv_zt.setText("");
                break;
            case 2:

                ll_zt.setBackgroundResource(R.drawable.yhj_ws_3x);
                tv_zt.setBackgroundResource(R.drawable.yhq);
                tv_zt.setText("");

                break;
        }



    }

    public void setType(int type){

        this.type=type;

    }

}
