package www.diandianxing.com.diandianxing.adapter;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.VideoActivity;
import www.diandianxing.com.diandianxing.bean.GuanzhuJD;
import www.diandianxing.com.diandianxing.bean.PaiKeInfo;

/**
 * Created by Administrator on 2018/4/8.
 */

public class DongTaiAdapter extends BaseQuickAdapter<PaiKeInfo, BaseViewHolder> {

    public DongTaiAdapter(@LayoutRes int layoutResId, @Nullable List<PaiKeInfo> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, final PaiKeInfo item) {

        RelativeLayout rl_dt = helper.getView(R.id.rl_dt);

        ImageView item_img=helper.getView(R.id.item_img);

        ImageView item_tou=helper.getView(R.id.item_tou);

        TextView item_zan=helper.getView(R.id.item_zan);





        if(helper.getAdapterPosition()%2!=0){

            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) rl_dt.getLayoutParams();

            layoutParams.rightMargin=10;
            rl_dt.setLayoutParams(layoutParams);


        }

        Glide.with(mContext).load(item.imageUrl).into(item_img);

        Glide.with(mContext).load(item.pic).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(item_tou);


        item_zan.setText(item.dianZanCount);



        //设置点击事件
        item_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, VideoActivity.class);
                intent.putExtra("pk",item);
                mContext.startActivity(intent);
            }
        });


    }
}
