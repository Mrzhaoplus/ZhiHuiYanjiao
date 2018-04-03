package zhyj.dqjt.com.zhihuiyanjiao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zhyj.dqjt.com.zhihuiyanjiao.R;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 *
 *  发现Fragment 中 达人 适配器
 */

public class Masteradapter extends RecyclerView.Adapter<Masteradapter.Myviewholder> {

    private Context context;
    List<String> list = new ArrayList<>();

    public Masteradapter(Context context) {
        this.context = context;
        data();
    }

    private void data() {
        for (int i = 0; i < 10; i++) {

            list.add(i + "");

        }
    }

    @Override
    public Myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        Myviewholder myviewholer = new Myviewholder(LayoutInflater.from(context).inflate(R.layout.daren_item, parent, false));


        return myviewholer;
    }

    @Override
    public void onBindViewHolder(Myviewholder holder, int position) {
        Log.d("TAG",list.size()+"");
        holder.da_zan.setText(list.get(position).toString());
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
            holder.text_dengji.setText(list.get(position).toString());
        }

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
        public Myviewholder(View  rootView) {
            super(rootView);
           text_dengji= rootView.findViewById(R.id.text_dengji);
            this.img_tou = (ImageView) rootView.findViewById(R.id.img_tou);
            this.text_name = (TextView) rootView.findViewById(R.id.text_name);
            this.da_zan = (TextView) rootView.findViewById(R.id.da_zan);
            this.imageView2 = (ImageView) rootView.findViewById(R.id.imageView2);
            this.item_back = (ImageView) rootView.findViewById(R.id.item_back);

        }
    }


}
