package www.diandianxing.com.diandianxing.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import www.diandianxing.com.diandianxing.Login.LoginActivity;
import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.bean.PingLunInfo;
import www.diandianxing.com.diandianxing.fragment.mainfragment.JiaoDetailActivity;
import www.diandianxing.com.diandianxing.fragment.minefragment.MydynamicActivity;
import www.diandianxing.com.diandianxing.interfase.HuiFuClickListener;
import www.diandianxing.com.diandianxing.util.Api;
import www.diandianxing.com.diandianxing.util.DividerItemDecoration;
import www.diandianxing.com.diandianxing.util.MyUtils;
import www.diandianxing.com.diandianxing.util.SpUtils;

/**
 * Created by Mr赵 on 2018/4/9.
 */

public class JiaoLiuyanAdapter extends RecyclerView.Adapter<JiaoLiuyanAdapter.ViewHolder> {
   private Context context;
    private List<PingLunInfo> list;
    public HuiFuClickListener huiFuClickListener;
    private String tzId;//帖子ID
    private String zrname;
    public JiaoLiuyanAdapter(Context context, List<PingLunInfo> list, HuiFuClickListener huiFuClickListener,String tzId,String zrname) {
        this.context = context;
        this.list=list;
        this.huiFuClickListener=huiFuClickListener;
        this.tzId=tzId;
        this.zrname=zrname;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.video_pinglun, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        final PingLunInfo pingLunInfo = list.get(position);

        holder.name.setText(pingLunInfo.nickName);

        holder.address.setText(MyUtils.stampToDate(pingLunInfo.createTime));

        holder.pinglun.setText(pingLunInfo.content);

        Glide.with(context).load(pingLunInfo.pic).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(holder.img_tou);

        if(pingLunInfo.customReplayLists.size()>0){
            holder.rv_zj.setLayoutManager(new LinearLayoutManager(context));
            holder.rv_zj.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));
            holder.rv_zj.setNestedScrollingEnabled(false);

            ZiJiAdapter ziJiAdapter = new ZiJiAdapter(R.layout.item_zjpl,pingLunInfo.customReplayLists);

            holder.rv_zj.setAdapter(ziJiAdapter);
            ziJiAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    int guid = SpUtils.getInt(context, "guid", 0);
                    if(guid!=2){
                        context.startActivity(new Intent(context,LoginActivity.class));
                    }else{
                        if(tzId.equals(pingLunInfo.userId)){//是否是自己
                            huiFuClickListener.OnHYDataClickListener();
                        }else {
                            String userid = SpUtils.getString(context,"userid",null);
                            Log.e("TAG", "不是自己==userid===" + userid + ",tzId===" + tzId + ",pingLunInfo.userId=" + pingLunInfo.userId);
                            if (userid.equals(pingLunInfo.userId)) {//回复贴子的主人

                                huiFuClickListener.OnHuiFuClickListener(pingLunInfo.id, tzId, zrname);

                            } else if (userid.equals(tzId)) {//主人回复别人

                                huiFuClickListener.OnHuiFuClickListener(pingLunInfo.id, pingLunInfo.userId, pingLunInfo.nickName);

                            } else {//第三者不评论
                                Log.e("TAG", "第三者");
                                huiFuClickListener.OnHYDataClickListener();
                            }
                        }
                    }

                }
            });
        }else{
            holder.rv_zj.setVisibility(View.GONE);
        }
        holder.img_tou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, MydynamicActivity.class);

                intent.putExtra("uid",pingLunInfo.userId);

                context.startActivity(intent);

            }
        });



        holder.ll_pl_jd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(tzId.equals(pingLunInfo.userId)){//是否是自己
                    huiFuClickListener.OnHYDataClickListener();
                }else{
                    String userid = SpUtils.getString(context,"userid",null);

                    Log.e("TAG","不是自己==userid==="+userid+",tzId==="+tzId+",pingLunInfo.userId="+pingLunInfo.userId);
                    if(userid.equals(pingLunInfo.userId)){//回复贴子的主人

                        huiFuClickListener.OnHuiFuClickListener(pingLunInfo.id,tzId,zrname);

                    }else if(userid.equals(tzId)){//主人回复别人

                        huiFuClickListener.OnHuiFuClickListener(pingLunInfo.id,pingLunInfo.userId,pingLunInfo.nickName);

                    }else{//第三者不评论
                        Log.e("TAG","第三者");
                        huiFuClickListener.OnHYDataClickListener();
                    }

                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView img_tou;
        public TextView name;
        public TextView address;
        public TextView pinglun;
        public RecyclerView rv_zj;
        public LinearLayout ll_pl_jd;
        public ViewHolder(View itemView) {
            super(itemView);
            img_tou = itemView.findViewById(R.id.img_tou);
            name = itemView.findViewById(R.id.text_name);
            address = itemView.findViewById(R.id.da_address);
            pinglun = itemView.findViewById(R.id.pinglun);
            rv_zj=itemView.findViewById(R.id.rv_zj);
            ll_pl_jd=itemView.findViewById(R.id.ll_pl_jd);
        }
    }
}
