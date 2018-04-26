package www.diandianxing.com.diandianxing.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import www.diandianxing.com.diandianxing.VideoActivity;
import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.bean.PaiKeInfo;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 *
 *   发现 fragment 中 推荐和 关注的适配器
 */

public class Tujianadapter extends BaseAdapter {
     private Context context;
     private List<PaiKeInfo> list;
    public Tujianadapter(Context context,List<PaiKeInfo> list) {
        this.context = context;
        this.list=list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        Viewholer holder;
        if(view==null){
          view=  View.inflate(context, R.layout.tui_recyitem,null);
            holder=new Viewholer();
           holder.tui_img=view.findViewById(R.id.item_img);
           holder.tui_tou=  view.findViewById(R.id.item_tou);
           holder.tui_zan= view.findViewById(R.id.item_zan);
            view.setTag(holder);
        }
        else {
          holder= (Viewholer) view.getTag();
        }


        final PaiKeInfo paiKeInfo = list.get(i);

        Glide.with(context).load(paiKeInfo.imageUrl).into(holder.tui_img);

        Glide.with(context).load(paiKeInfo.pic).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(holder.tui_tou);


        holder.tui_zan.setText(paiKeInfo.dianZanCount);



        //设置点击事件
        holder.tui_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, VideoActivity.class);

                intent.putExtra("pk",paiKeInfo);

                context.startActivity(intent);
            }
        });

        return view;
    }
    class Viewholer{
        ImageView tui_img,tui_tou;
        TextView tui_zan;
    }
}
