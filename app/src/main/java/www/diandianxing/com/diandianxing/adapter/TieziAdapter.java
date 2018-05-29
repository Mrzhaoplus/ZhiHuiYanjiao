package www.diandianxing.com.diandianxing.adapter;

import android.content.Intent;
import android.graphics.drawable.Drawable;
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
import com.luck.picture.lib.decoration.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import www.diandianxing.com.diandianxing.Login.LoginActivity;
import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.TPDetailActivity;
import www.diandianxing.com.diandianxing.bean.GuanzhuJD;
import www.diandianxing.com.diandianxing.util.MyUtils;
import www.diandianxing.com.diandianxing.util.SpUtils;
import www.diandianxing.com.diandianxing.util.StateClickListener;
import www.diandianxing.com.diandianxing.util.divider.RecyclerViewItemDecoration;

import static www.diandianxing.com.diandianxing.R.id.recy_card;

/**
 * Created by Administrator on 2018/4/3.
 */

public class TieziAdapter extends BaseQuickAdapter<GuanzhuJD, BaseViewHolder> {

    OnItemClickListener mOnItemClickListener;

    DianClickListener dianClickListener;

    private boolean isxs;

    private String title;

    private StateClickListener stateClickListener;

    public TieziAdapter(@LayoutRes int layoutResId, @Nullable List<GuanzhuJD> data,String title,StateClickListener stateClickListener) {
        super(layoutResId, data);
        this.title=title;
        this.stateClickListener=stateClickListener;
    }
    @Override
    protected void convert(final BaseViewHolder helper, final GuanzhuJD item) {

        RecyclerView rv_tp = helper.getView(R.id.rv_tp);
        final ImageView iv_ddd = helper.getView(R.id.iv_ddd);
        final TextView text_share = helper.getView(R.id.text_share);
        final LinearLayout ll_xq = helper.getView(R.id.ll_xq);
        RelativeLayout rl_title_xs = helper.getView(R.id.rl_title_xs);
        TextView item_count =helper.getView(R.id.item_count);
        final TextView text_collect=helper.getView(R.id.text_collect);
        final TextView text_zan=helper.getView(R.id.text_zan);
        TextView tv_nian=helper.getView(R.id.tv_nian);
        TextView tv_yue=helper.getView(R.id.tv_yue);
        item_count.setText(item.postContent);

        if(Integer.parseInt(item.collectCount)==0){
            text_collect.setText("收藏");
        }else {
            text_collect.setText(item.collectCount);
        }

        if(Integer.parseInt(item.dianZanCount)==0){
            text_zan.setText("点赞");
        }else{
            text_zan.setText(item.dianZanCount);
        }
        String time = MyUtils.stampNYrToDate(item.updateTime);

        String[] split = time.split("-");

        tv_nian.setText(split[2]);

        tv_yue.setText(split[1]+"月");

        if(isxs){
            rl_title_xs.setVisibility(View.VISIBLE);
        }else {
            rl_title_xs.setVisibility(View.GONE);
        }

        if(item.imagesList.size()>3){
            rv_tp.setLayoutManager(new GridLayoutManager(mContext,3));
        }else{
            rv_tp.setLayoutManager(new GridLayoutManager(mContext,item.imagesList.size()));
        }
//        RecycleViewDivider recycleViewDivider = new RecycleViewDivider(mContext, GridLayoutManager.HORIZONTAL, 5, mContext.getResources().getColor(R.color.transparent));
//        rv_tp.addItemDecoration(recycleViewDivider);
        rv_tp.setNestedScrollingEnabled(false);
        TPAdapter1 tpAdapter1 = new TPAdapter1(mContext,item.imagesList);
        tpAdapter1.setZY(true);
        rv_tp.setAdapter(tpAdapter1);

        if(isjd){

            text_collect.setEnabled(true);
            text_zan.setEnabled(true);
        }

//        if(!"我的主页".equals(title)){    不是自己的主页执行点赞收藏，  现在是自己的主页可以进行点赞收藏
            if(Integer.parseInt(item.is_collect)==0){
                Drawable nav_up=mContext.getResources().getDrawable(R.drawable.icon_collect);
                nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                text_collect.setCompoundDrawables(nav_up, null, null, null);
            }else{
                Drawable nav_up=mContext.getResources().getDrawable(R.drawable.shouchang_xz_icon_3x);
                nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                text_collect.setCompoundDrawables(nav_up, null, null, null);
            }

            if(Integer.parseInt(item.is_zan)==0){
                Drawable nav_up=mContext.getResources().getDrawable(R.drawable.icon_dianzan);
                nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                text_zan.setCompoundDrawables(nav_up, null, null, null);
            }else{
                Drawable nav_up=mContext.getResources().getDrawable(R.drawable.dianzan_xz_icon_3x);
                nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                text_zan.setCompoundDrawables(nav_up, null, null, null);
            }

            //收藏
            text_collect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int guid = SpUtils.getInt(mContext, "guid", 0);
                    if(guid!=2){
                        mContext.startActivity(new Intent(mContext,LoginActivity.class));
                    }else{
                        text_collect.setEnabled(false);
                        if(Integer.parseInt(item.is_collect)==0){

                            stateClickListener.ShouCangClickListener(Integer.parseInt(item.id),0,1,-1,helper.getAdapterPosition());

                        }else{
                            stateClickListener.QuXiaoShouCangClickListener(Integer.parseInt(item.id),0,1,helper.getAdapterPosition());
                        }
                    }

                }
            });
            //点赞
            text_zan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int guid = SpUtils.getInt(mContext, "guid", 0);
                    if(guid!=2){
                        mContext.startActivity(new Intent(mContext,LoginActivity.class));
                    }else{
                        text_zan.setEnabled(false);
                        if(Integer.parseInt(item.is_zan)==0){
                            stateClickListener.DianZanClickListener(Integer.parseInt(item.id),0,0,-1,helper.getAdapterPosition());
                        }else{
                            stateClickListener.QuXiaoDianZanClickListener(Integer.parseInt(item.id),0,0,helper.getAdapterPosition());
                        }
                    }
                }
            });

//        }

        iv_ddd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dianClickListener.onDianClickListener(helper.getAdapterPosition());
            }
        });
        text_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.FxClickListener(helper.getAdapterPosition(),item.objName);
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

    boolean isjd=true;

    public void setEnable(boolean isjd){
        this.isjd=isjd;
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
        void FxClickListener(int pos,String ms);
        void ScClickListener(int pos);
        void DzClickListener(int pos);

    }
    public void SetOnItemClickListener(OnItemClickListener listener){

        this.mOnItemClickListener=listener;

    }
}
