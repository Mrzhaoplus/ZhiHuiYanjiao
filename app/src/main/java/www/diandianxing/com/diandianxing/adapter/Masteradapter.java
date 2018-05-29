package www.diandianxing.com.diandianxing.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import www.diandianxing.com.diandianxing.bean.PaiKeDRInfo;
import www.diandianxing.com.diandianxing.fragment.minefragment.MydynamicActivity;
import www.diandianxing.com.diandianxing.R;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 *
 *  发现Fragment 中 达人 适配器
 */

public class Masteradapter extends RecyclerView.Adapter<Masteradapter.Myviewholder> {

    private Context context;
    List<PaiKeDRInfo> list ;

    public Masteradapter(Context context,List<PaiKeDRInfo> list) {
        this.context = context;
        this.list=list;
    }


    @Override
    public Myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        Myviewholder myviewholer = new Myviewholder(LayoutInflater.from(context).inflate(R.layout.daren_item, parent, false));


        return myviewholer;
    }

    @Override
    public void onBindViewHolder(Myviewholder holder, final int position) {
        Log.d("TAG",list.size()+"");

        final PaiKeDRInfo paiKeDRInfo=list.get(position);
        Glide.with(context).load(paiKeDRInfo.pic).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(holder.img_tou);
        holder.text_name.setText(paiKeDRInfo.nickName);
        Log.e("TAG","点赞数：：："+paiKeDRInfo.zan);
        holder.da_zan.setText(paiKeDRInfo.zan);
        holder.tv_dj.setText(paiKeDRInfo.userLevel);


        if(position==0){
            holder.text_dengji.setBackgroundResource(R.drawable.item_one);
            holder.text_dengji.setText("");
        }
        else if(position==1){
            holder.text_dengji.setBackgroundResource(R.drawable.item_two);
            holder.text_dengji.setText("");
        }
        else if(position==2){
            holder.text_dengji.setBackgroundResource(R.drawable.item_three);
            holder.text_dengji.setText("");
        }
        else {
           holder.text_dengji.setBackgroundResource(R.drawable.img_yuan) ;
            holder.text_dengji.setText(paiKeDRInfo.sort);
        }
        //点击事件
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MydynamicActivity.class);
                intent.putExtra("uid",paiKeDRInfo.uid);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class Myviewholder extends RecyclerView.ViewHolder {

        public ImageView img_tou;
        public TextView text_name;
        public TextView da_zan,text_dengji;
        public ImageView imageView2;
        public ImageView item_back;
        public ImageView item_dengji;
        public View view;
public TextView tv_dj;
        public Myviewholder(View  rootView) {
            super(rootView);
            view = rootView;
            text_dengji= rootView.findViewById(R.id.text_dengji);
            this.img_tou = (ImageView) rootView.findViewById(R.id.img_tou);
            this.text_name = (TextView) rootView.findViewById(R.id.text_name);
            this.da_zan = (TextView) rootView.findViewById(R.id.da_zan);
            this.imageView2 = (ImageView) rootView.findViewById(R.id.imageView2);
            this.item_back = (ImageView) rootView.findViewById(R.id.item_back);
            this.tv_dj=rootView.findViewById(R.id.tv_dj);

        }
    }


}
