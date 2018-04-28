package www.diandianxing.com.diandianxing.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.ShujuBean.Fenlei_Bean;
import www.diandianxing.com.diandianxing.bean.GuanzhuJD;
import www.diandianxing.com.diandianxing.fragment.mainfragment.JiaoDetailActivity;
import www.diandianxing.com.diandianxing.fragment.minefragment.MydynamicActivity;
import www.diandianxing.com.diandianxing.interfase.List_view;
import www.diandianxing.com.diandianxing.util.BaseDialog;
import www.diandianxing.com.diandianxing.util.ShareListener;
import www.diandianxing.com.diandianxing.util.ToastUtils;

/**
 * Created by Mr赵 on 2018/4/3.
 */

public class MeiJiaodianAdapter extends BaseAdapter {
    private Context context;
    List<Fenlei_Bean.DatasBean>lists;

    private ShareListener shareListener;
    private GuanzhuJD guanzhuJD;
    private List_view jiekou;
    private onlick click;

    public MeiJiaodianAdapter(Context context, List<Fenlei_Bean.DatasBean> lists, ShareListener shareListener) {
        this.context = context;
        this.lists = lists;
        this.shareListener = shareListener;
    }

    public void getclick(List_view jiekou){
        this.jiekou=jiekou;
    }
    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int i) {
        return lists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        final Tuijiantieadapter.ViewHolder holder;
        int type = getItemViewType(position);
        if (convertView == null) {
            holder = new Tuijiantieadapter.ViewHolder();

            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_tuiduoone, null);
            holder.img_tou = (ImageView) convertView.findViewById(R.id.img_tou);
            holder.text_name = (TextView) convertView.findViewById(R.id.text_name);
            holder.da_address = (TextView) convertView.findViewById(R.id.da_address);
            holder.imageView2 = (ImageView) convertView.findViewById(R.id.imageView2);
            holder.text_dengji = (TextView) convertView.findViewById(R.id.text_dengji);
            holder.item_count = (TextView) convertView.findViewById(R.id.item_count);
            holder.item_recycler = (RecyclerView) convertView.findViewById(R.id.item_recycler);
            holder.text_colltet = (TextView)     convertView .findViewById(R.id.text_collect);
            holder.text_zan = (TextView)     convertView .findViewById(R.id.text_zan);
            holder.text_share = (TextView)     convertView .findViewById(R.id.text_share);
            holder.rela_guanzhu = convertView.findViewById(R.id.rela_guanzhu);


            convertView.setTag(holder);
        } else {
            holder = (Tuijiantieadapter.ViewHolder) convertView.getTag();
        }

        holder.text_colltet.setText(lists.get(position).getCollectCount()+"");
        holder.text_zan.setText(lists.get(position).getDianZanCount()+"");
        if(lists.get(position).getIs_collect()==0){
            Drawable nav_up=context.getResources().getDrawable(R.drawable.icon_collect);
            nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
            holder.text_colltet.setCompoundDrawables(nav_up, null, null, null);

        }else{
            Drawable nav_up=context.getResources().getDrawable(R.drawable.shouchang_xz_icon_3x);
            nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
            holder.text_colltet.setCompoundDrawables(nav_up, null, null, null);
        }
        if(lists.get(position).getIs_zan()==0){
            Drawable nav_up=context.getResources().getDrawable(R.drawable.icon_dianzan);
            nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
            holder.text_zan.setCompoundDrawables(nav_up, null, null, null);

        }else{
            Drawable nav_up=context.getResources().getDrawable(R.drawable.dianzan_xz_icon_3x);
            nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
            holder.text_zan.setCompoundDrawables(nav_up, null, null, null);

        }

        //关注
        holder.rela_guanzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.guanzhu_click(position);

            }
        });
        //分享
        holder.text_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFXDialog(Gravity.BOTTOM, R.style.Bottom_Top_aniamtion);
            }
        });
        //收藏
        holder.text_colltet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int collectCount = lists.get(position).getCollectCount();
                if(lists.get(position).getIs_collect()==0){
                    holder.text_colltet.setText(collectCount+"");
                    jiekou.onclick(position,1,lists.get(position).getId(),lists.get(position).getCollectCount(),collectCount);
                    //lists.get(position).setIs_collect(1);
                }else if(lists.get(position).getIs_collect()==1){
                    holder.text_colltet.setText(collectCount+"");
                    jiekou.onclick(position,1,lists.get(position).getId(),lists.get(position).getCollectCount(),collectCount);
                    // lists.get(position).setIs_collect(0);
                }
            }
        });
        //点赞
        holder.text_zan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int dianZanCount = lists.get(position).getDianZanCount();
                if(lists.get(position).getIs_zan()==0){
                    holder.text_zan.setText(dianZanCount+"");
                    jiekou.onclick(position,0,lists.get(position).getId(),1,dianZanCount);
                }else if(lists.get(position).getIs_zan()==1){
                    holder.text_zan.setText(dianZanCount+"");
                    jiekou.onclick(position,0,lists.get(position).getId(),0,dianZanCount);
                }
            }
        });
        List<String> imagesList = lists.get(position).getImagesList();
        holder.item_recycler.setLayoutManager(new GridLayoutManager(context,3));
        holder.item_recycler.setNestedScrollingEnabled(false);
        TPAdapter1 tpAdapter1 = new TPAdapter1(context,imagesList);
        holder.item_recycler.setAdapter(tpAdapter1);
        //添加数据
        Glide.with(context).load(lists.get(position).getPic()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(holder.img_tou);
           holder.img_tou.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent intent = new Intent(context, MydynamicActivity.class);
                   intent.putExtra("uid",lists.get(position).getUserId()+"");
                   context.startActivity(intent);
               }
           });
        String dateToString = getDateToString(String.valueOf(lists.get(position).getCreateTime() / 1000));
        holder.item_count.setText(lists.get(position).getPostContent());
        holder.text_dengji.setText(lists.get(position).getUserLevel());
        holder.da_address.setText(dateToString+" "+lists.get(position).getAddress());
        final Fenlei_Bean.DatasBean datasBean = lists.get(position);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guanzhuJD = new GuanzhuJD();
                guanzhuJD.address=datasBean.getAddress();
                guanzhuJD.imagesList=datasBean.getImagesList();
                guanzhuJD.id= String.valueOf(datasBean.getId());
                guanzhuJD.dianZanCount= String.valueOf(datasBean.getDianZanCount());
                guanzhuJD.createTime= String.valueOf(datasBean.getCreateTime());
                guanzhuJD.commentCount= String.valueOf(datasBean.getCommentCount());
                guanzhuJD.collectCount= String.valueOf(datasBean.getCollectCount());
                guanzhuJD.is_collect= String.valueOf(datasBean.getIs_collect());
                guanzhuJD.is_focus= String.valueOf(datasBean.getIs_focus());
                guanzhuJD.is_fx= String.valueOf(datasBean.getIs_fx());
                guanzhuJD.is_zan= String.valueOf(datasBean.getIs_zan());
                guanzhuJD.isDeleted= String.valueOf(datasBean.getIsDeleted());
                guanzhuJD.pic=datasBean.getPic();
                guanzhuJD.postContent=datasBean.getPostContent();
                guanzhuJD.postFlage= String.valueOf(datasBean.getPostFlage());
                guanzhuJD.postImge=datasBean.getPostImge();
                guanzhuJD.postStatus= String.valueOf(datasBean.getPostStatus());
                guanzhuJD.postTitle=datasBean.getPostTitle();
                guanzhuJD.postType=datasBean.getPostType();
                guanzhuJD.relayCount= String.valueOf(datasBean.getRelayCount());
                guanzhuJD.updateTime= String.valueOf(datasBean.getUpdateTime());
                guanzhuJD.userId= String.valueOf(datasBean.getUserId());
                guanzhuJD.userLevel=datasBean.getUserLevel();
                guanzhuJD.userName=datasBean.getUserName();
                Intent intent=new Intent(context,JiaoDetailActivity.class);
                intent.putExtra("guanzhu",guanzhuJD);
                context.startActivity(intent);
            }
        });
        return convertView;
    }




    public static class ViewHolder {

        public RecyclerView item_recycler;
        public ImageView img_tou;
        public TextView text_name;
        public TextView da_address;
        public ImageView imageView2;
        public TextView text_dengji;
        public TextView item_count;
        public RecyclerView recy_grade;
        public TextView text_share,text_colltet,text_zan;
        public RelativeLayout rela_guanzhu;
    }





    private LinearLayout ll_wx,ll_pyq,ll_qq,ll_kj,ll_wb;
    private TextView quxiao;

    private void showFXDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(context);
        //设置触摸dialog外围是否关闭
        //设置监听事件
        final Dialog dialog = builder.setViewId(R.layout.sharing_pop_item_view)
                //设置dialogpadding
                .setPaddingdp(0, 0, 0, 0)
                //设置显示位置
                .setGravity(grary)
                //设置动画
                .setAnimation(animationStyle)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(true)
                //设置监听事件
                .builder();

        dialog.show();
        ll_wx=dialog.findViewById(R.id.ll_wx);
        ll_pyq=dialog.findViewById(R.id.ll_pyq);
        ll_qq=dialog.findViewById(R.id.ll_qq);
        ll_kj=dialog.findViewById(R.id.ll_kj);
        ll_wb=dialog.findViewById(R.id.ll_wb);
        quxiao=dialog.findViewById(R.id.quxiao);
        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        ll_wx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareListener.OnShareListener(0);
            }
        });
        ll_pyq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareListener.OnShareListener(1);
            }
        });
        ll_qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareListener.OnShareListener(2);
            }
        });
        ll_kj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareListener.OnShareListener(3);
            }
        });
        ll_wb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareListener.OnShareListener(4);
            }
        });
    }


    //  时间戳转为日期  /年/月/日
    public static String getDateToString(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long lcc_time = Long.valueOf(time);
        String format = sdf.format(new Date(lcc_time * 1000L));
        return format;
    }


    public void guanzhu_click(onlick click){
        this.click=click;
    }



}

