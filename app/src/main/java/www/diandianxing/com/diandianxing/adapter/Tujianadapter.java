package www.diandianxing.com.diandianxing.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import www.diandianxing.com.diandianxing.VideoActivity;
import www.diandianxing.com.diandianxing.R;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 *
 *   发现 fragment 中 推荐和 关注的适配器
 */

public class Tujianadapter extends BaseAdapter {
     private Context context;
     private List<String> list=new ArrayList<>();
    public Tujianadapter(Context context) {
        this.context = context;
        data();
    }

    private void data() {
        for (int i = 0; i <10; i++) {
             list.add("");
        }
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
       //下载数据
        holder.tui_zan.setText(list.get(i).toString()+"");
        //设置点击事件
        holder.tui_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, VideoActivity.class);
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
