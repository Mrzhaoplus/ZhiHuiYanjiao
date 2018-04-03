package zhyj.dqjt.com.zhihuiyanjiao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import zhyj.dqjt.com.zhihuiyanjiao.R;
import zhyj.dqjt.com.zhihuiyanjiao.bean.Imagebean;


/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class Recygradeadapter extends RecyclerView.Adapter<Recygradeadapter.Myadapter> {
      private Context context;
      private List<Imagebean> list;

    public Recygradeadapter(Context context, List<Imagebean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Myadapter onCreateViewHolder(ViewGroup parent, int viewType) {
        Myadapter myadapter=new Myadapter(LayoutInflater.from(context).inflate(R.layout.recygrade_item,parent,false));
        return myadapter;
    }

    @Override
    public void onBindViewHolder(Myadapter holder, int position) {
        Glide.with(context)
                .load(list.get(position).getImage())
                .into(holder.img);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Myadapter extends RecyclerView.ViewHolder {
           ImageView img;
        public Myadapter(View itemView) {
            super(itemView);
           img= (ImageView) itemView.findViewById(R.id.item_img);

        }
    }
}
