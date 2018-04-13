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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.TPDetailActivity;

/**
 * Created by Administrator on 2018/4/3.
 */

public class TieziAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    OnItemClickListener mOnItemClickListener;

    DianClickListener dianClickListener;

    private boolean isxs;


    public TieziAdapter(@LayoutRes int layoutResId, @Nullable ArrayList<String> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(final BaseViewHolder helper, String item) {

        RecyclerView rv_tp = helper.getView(R.id.rv_tp);
        final ImageView iv_ddd = helper.getView(R.id.iv_ddd);
        final TextView text_share = helper.getView(R.id.text_share);
        final LinearLayout ll_xq = helper.getView(R.id.ll_xq);
        RelativeLayout rl_title_xs = helper.getView(R.id.rl_title_xs);
        final ArrayList<String> mList=new ArrayList<>();
        if (mList.size()<=0){
            mList.add("");
            mList.add("");
            mList.add("");
        }

        if(isxs){
            rl_title_xs.setVisibility(View.VISIBLE);
        }else {
            rl_title_xs.setVisibility(View.GONE);
        }

        rv_tp.setLayoutManager(new GridLayoutManager(mContext,3));
        rv_tp.setNestedScrollingEnabled(false);
        TPAdapter changegameAdapter = new TPAdapter(R.layout.tpp_item_view, mList);
        rv_tp.setAdapter(changegameAdapter);
        changegameAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Intent intent = new Intent(mContext, TPDetailActivity.class);
                        intent.putExtra("size",mList.size());
                        intent.putExtra("position",position);
                mContext.startActivity(intent);

            }
        });
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
