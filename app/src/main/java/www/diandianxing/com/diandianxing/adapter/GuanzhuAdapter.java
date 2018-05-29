package www.diandianxing.com.diandianxing.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import www.diandianxing.com.diandianxing.Login.LoginActivity;
import www.diandianxing.com.diandianxing.ShujuBean.DianzanAndFenxiang_Bean;
import www.diandianxing.com.diandianxing.ShujuBean.Live_gunzhu_Bean;
import www.diandianxing.com.diandianxing.bean.GuanzhuJD;
import www.diandianxing.com.diandianxing.bean.Imagebean;
import www.diandianxing.com.diandianxing.fragment.mainfragment.JiaoDetailActivity;
import www.diandianxing.com.diandianxing.fragment.minefragment.MydynamicActivity;
import www.diandianxing.com.diandianxing.interfase.List_view;
import www.diandianxing.com.diandianxing.interfase.RecyGetonclick;
import www.diandianxing.com.diandianxing.util.BaseDialog;
import www.diandianxing.com.diandianxing.util.ImageLoder;
import www.diandianxing.com.diandianxing.util.MyUtils;
import www.diandianxing.com.diandianxing.util.ShareListener;
import www.diandianxing.com.diandianxing.util.SpUtils;
import www.diandianxing.com.diandianxing.util.ToastUtils;
import www.diandianxing.com.diandianxing.R;

/**
 * Created by Mr赵 on 2018/4/3.
 */

public class GuanzhuAdapter extends BaseAdapter {
    private Context context;
    private ShareListener shareListener;
    List<Live_gunzhu_Bean.DatasBean>lists;
    private GuanzhuJD guanzhuJD;
    private List_view jiekou;
    private boolean flag=false;

    public GuanzhuAdapter(Context context, ShareListener shareListener, List<Live_gunzhu_Bean.DatasBean> lists ) {
        this.context = context;
        this.shareListener = shareListener;
        this.lists = lists;
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
       final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_duoone, null);
            holder.img_tou = (ImageView) convertView.findViewById(R.id.img_tou);
            holder.rl_nr=convertView.findViewById(R.id.rl_nr);
            holder.text_name = (TextView) convertView.findViewById(R.id.text_name);
            holder.da_address = (TextView) convertView.findViewById(R.id.da_address);
            holder.imageView2 = (ImageView) convertView.findViewById(R.id.imageView2);
            holder.text_dengji = (TextView) convertView.findViewById(R.id.text_dengji);
            holder.item_count = (TextView) convertView.findViewById(R.id.item_count);
            holder.item_recycler = (RecyclerView) convertView.findViewById(R.id.item_recycler);
            holder.text_colltet = (TextView)     convertView .findViewById(R.id.text_collect);
            holder.text_zan = (TextView)     convertView .findViewById(R.id.text_zan);
            holder.text_share = (TextView)     convertView .findViewById(R.id.text_share);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.rl_nr.getLayoutParams();
        layoutParams.topMargin=20;
        holder.rl_nr.setLayoutParams(layoutParams);

        final Live_gunzhu_Bean.DatasBean datasBean = lists.get(position);
        //分享
        holder.text_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFXDialog(Gravity.BOTTOM, R.style.Bottom_Top_aniamtion,position);
            }
        });

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
                guanzhuJD.objName=datasBean.getObjName();
                Intent intent=new Intent(context,JiaoDetailActivity.class);
                intent.putExtra("guanzhu",guanzhuJD);

                intent.putExtra("title",guanzhuJD.objName);

                context.startActivity(intent);
            }
        });


        if(lists.get(position).getIs_collect()==0){
            Drawable nav_up=context.getResources().getDrawable(R.drawable.icon_collect);
            nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
            holder.text_colltet.setCompoundDrawables(nav_up, null, null, null);
            if(lists.get(position).getCollectCount()==0){
                holder.text_colltet.setText("收藏");
            }else {
                holder.text_colltet.setText(lists.get(position).getCollectCount()+"");
            }

        }else{
            Drawable nav_up=context.getResources().getDrawable(R.drawable.shouchang_xz_icon_3x);
            nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
            holder.text_colltet.setCompoundDrawables(nav_up, null, null, null);
            if(lists.get(position).getCollectCount()==0){
                holder.text_colltet.setText("收藏");
            }else {
                holder.text_colltet.setText(lists.get(position).getCollectCount()+"");
            }
        }
        if(lists.get(position).getIs_zan()==0){
            Drawable nav_up=context.getResources().getDrawable(R.drawable.icon_dianzan);
            nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
            holder.text_zan.setCompoundDrawables(nav_up, null, null, null);
            if(lists.get(position).getDianZanCount()==0){
                holder.text_zan.setText("点赞");
            }else{
                holder.text_zan.setText(lists.get(position).getDianZanCount()+"");
            }
        }else{
            Drawable nav_up=context.getResources().getDrawable(R.drawable.dianzan_xz_icon_3x);
            nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
            holder.text_zan.setCompoundDrawables(nav_up, null, null, null);
            if(lists.get(position).getDianZanCount()==0){
                holder.text_zan.setText("点赞");
            }else{
                holder.text_zan.setText(lists.get(position).getDianZanCount()+"");
            }
        }
        if(isjd){

            holder.text_colltet.setEnabled(true);
            holder.text_zan.setEnabled(true);
        }


        //收藏
        holder.text_colltet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int guid = SpUtils.getInt(context, "guid", 0);
                if(guid!=2){
                    context.startActivity(new Intent(context,LoginActivity.class));
                }else{


                    holder.text_colltet.setEnabled(false);

                    int collectCount = lists.get(position).getCollectCount();
                    if(lists.get(position).getIs_collect()==0){
//                        holder.text_colltet.setText(collectCount+"");
                        jiekou.onclick(position,1,lists.get(position).getId(),1,collectCount);
                        //lists.get(position).setIs_collect(1);
                    }else if(lists.get(position).getIs_collect()==1){
//                        holder.text_colltet.setText(collectCount+"");
                        jiekou.onclick(position,1,lists.get(position).getId(),0,collectCount);
                        // lists.get(position).setIs_collect(0);
                    }
                }

            }
        });






        //点赞
        holder.text_zan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int guid = SpUtils.getInt(context, "guid", 0);
                if(guid!=2){
                    context.startActivity(new Intent(context,LoginActivity.class));
                }else{
                    holder.text_zan.setEnabled(false);
                    int dianZanCount = lists.get(position).getDianZanCount();
                    if(lists.get(position).getIs_zan()==0){
//                        holder.text_zan.setText(dianZanCount+"");
                        jiekou.onclick(position,0,lists.get(position).getId(),1,dianZanCount);
                    }else if(lists.get(position).getIs_zan()==1){
//                        holder.text_zan.setText(dianZanCount+"");
                        jiekou.onclick(position,0,lists.get(position).getId(),0,dianZanCount);
                    }
                }
            }
        });
        List<String> imagesList = lists.get(position).getImagesList();

        if(imagesList.size()>3){
            holder.item_recycler.setLayoutManager(new GridLayoutManager(context,3));
        }else{
            holder.item_recycler.setLayoutManager(new GridLayoutManager(context,imagesList.size()));
        }


        holder.item_recycler.setNestedScrollingEnabled(false);
        TPAdapter1 tpAdapter1 = new TPAdapter1(context,imagesList);
        holder.item_recycler.setAdapter(tpAdapter1);

        holder.item_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        holder.img_tou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, MydynamicActivity.class);
                intent.putExtra("uid",lists.get(position).getUserId()+"");
                context.startActivity(intent);

            }
        });
           holder.text_name.setText(lists.get(position).getUserName());
        String address = lists.get(position).getAddress();


        Glide.with(context).load(lists.get(position).getPic()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(holder.img_tou);
            holder.item_count.setText(lists.get(position).getPostContent());
        holder.text_dengji.setText(lists.get(position).getUserLevel());

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String end = sf.format(date);
        String fb = MyUtils.stampToDate(lists.get(position).getUpdateTime()+"");

        String time = MyUtils.dateDiff(fb, end, "yyyy-MM-dd HH:mm:ss",lists.get(position).getUpdateTime()+"");

        holder.da_address.setText(time+" "+address);


        return convertView;
    }


    boolean isjd=true;

    public void setEnable(boolean isjd){
        this.isjd=isjd;
    }


    public static class ViewHolder {

        public RecyclerView item_recycler;
        public ImageView img_tou;
        public TextView text_name;
        public TextView da_address;
        public ImageView imageView2;
        public TextView text_dengji;
        public TextView item_count;
        public TextView text_share,text_colltet,text_zan;
        public RelativeLayout rl_nr;
    }

    private LinearLayout ll_wx,ll_pyq,ll_qq,ll_kj,ll_wb;
    private TextView quxiao;
    private void showFXDialog(int grary, int animationStyle, final int pos) {
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
                shareListener.OnShareListener(0,pos);
            }
        });
        ll_pyq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareListener.OnShareListener(1,pos);
            }
        });
        ll_qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareListener.OnShareListener(2,pos);
            }
        });
        ll_kj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareListener.OnShareListener(3,pos);
            }
        });
        ll_wb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareListener.OnShareListener(4,pos);
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

}
