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
import www.diandianxing.com.diandianxing.bean.CouponInfo;

/**
 * Created by Administrator on 2018/4/11.
 */

public class CouponAdapter extends BaseQuickAdapter<CouponInfo, BaseViewHolder> {


    private int type;

    public CouponAdapter(@LayoutRes int layoutResId, @Nullable ArrayList<CouponInfo> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, CouponInfo item) {


        LinearLayout ll_zt = helper.getView(R.id.ll_zt);
        TextView tv_zt = helper.getView(R.id.tv_zt);
        TextView tv_money=helper.getView(R.id.tv_money);
        TextView tv_time_yxq=helper.getView(R.id.tv_time_yxq);
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


        tv_money.setText(item.money);
        String[] split1 = item.finish_time.split(" ");
        String[] split2 = split1[0].split("-");
        tv_time_yxq.setText("有效期至："+split2[0]+"."+split2[1]+"."+split2[2]);



    }

    public void setType(int type){

        this.type=type;

    }

}
