package www.diandianxing.com.diandianxing.adapter;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import www.diandianxing.com.diandianxing.TPDetailActivity;
import www.diandianxing.com.diandianxing.bean.GuanzhuJD;
import www.diandianxing.com.diandianxing.util.MyUtils;

/**
 * Created by Administrator on 2018/4/3.
 */

public class TieziAdapter extends BaseQuickAdapter<GuanzhuJD, BaseViewHolder> {

    OnItemClickListener mOnItemClickListener;

    DianClickListener dianClickListener;

    private boolean isxs;


    public TieziAdapter(@LayoutRes int layoutResId, @Nullable List<GuanzhuJD> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(final BaseViewHolder helper, GuanzhuJD item) {

        RecyclerView rv_tp = helper.getView(R.id.rv_tp);
        final ImageView iv_ddd = helper.getView(R.id.iv_ddd);
        final TextView text_share = helper.getView(R.id.text_share);
        final LinearLayout ll_xq = helper.getView(R.id.ll_xq);
        RelativeLayout rl_title_xs = helper.getView(R.id.rl_title_xs);
        TextView item_count =helper.getView(R.id.item_count);
        TextView text_collect=helper.getView(R.id.text_collect);
        TextView text_zan=helper.getView(R.id.text_zan);
        TextView tv_nian=helper.getView(R.id.tv_nian);
        TextView tv_yue=helper.getView(R.id.tv_yue);

        item_count.setText(item.postContent);

        text_collect.setText(item.collectCount);

        text_zan.setText(item.dianZanCount);

        String time = MyUtils.stampNYToDate(item.updateTime);

        String[] split = time.split("-");

        tv_nian.setText(split[0].substring(2,split[0].length()));

        tv_yue.setText(split[1]+"月");

        if(isxs){
            rl_title_xs.setVisibility(View.VISIBLE);
        }else {
            rl_title_xs.setVisibility(View.GONE);
        }

        rv_tp.setLayoutManager(new GridLayoutManager(mContext,3));
        rv_tp.setNestedScrollingEnabled(false);
        TPAdapter1 tpAdapter1 = new TPAdapter1(mContext,item.imagesList);
        rv_tp.setAdapter(tpAdapter1);
//        changegameAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                        Intent intent = new Intent(mContext, TPDetailActivity.class);
//                        intent.putExtra("size",mList.size());
//                        intent.putExtra("position",position);
//                mContext.startActivity(intent);
//
//            }
//        });
        iv_ddd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dianClickListener.onDianClickListener(helper.getAdapterPosition());
            }
        });
        text_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.FxClickListener(helper.getAdapterPosition());
            }
        });
        ll_xq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.ItemClick(null,helper.getAdapterPosition());
            }
        });
    }
    public class TPAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public TPAdapter(@LayoutRes int layoutResId, @Nullable ArrayList<String> data) {
            super(layoutResId, data);
        }
        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }

    }


    public void setXS(boolean isxs){

        this.isxs = isxs;

    }

    public interface DianClickListener{
        void onDianClickListener(int pos);
    }

    public void setOnDianClickListener(DianClickListener dianClickListener){
        this.dianClickListener=dianClickListener;
    }

    //设置接口回调用于adapter监听
    public interface OnItemClickListener{
        void ItemClick(View view, int position);
        void FxClickListener(int pos);
        void ScClickListener(int pos);
        void DzClickListener(int pos);

    }
    public void SetOnItemClickListener(OnItemClickListener listener){

        this.mOnItemClickListener=listener;

    }
}
