package zhyj.dqjt.com.zhihuiyanjiao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zhyj.dqjt.com.zhihuiyanjiao.R;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class Commentadapter extends RecyclerView.Adapter<Commentadapter.Myviewholder> {
    private Context context;
    List<String> list=new ArrayList<>();
    public Commentadapter(Context context) {
        this.context = context;
        data();

    }

    private void data() {

        for (int i = 0; i <10 ; i++) {
              list.add("12-2"+i);


        }
    }

    @Override
    public Myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        Myviewholder myviewholder = new Myviewholder(LayoutInflater.from(context).inflate(R.layout.comment_item, parent, false));
        return myviewholder;
    }

    @Override
    public void onBindViewHolder(Myviewholder holder, int position) {
            holder.da_zan.setText("天安门\r\r "+list.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class Myviewholder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView img_tou;
        public TextView text_name;
        public TextView da_zan;
        public ImageView imageView2;
        public RelativeLayout relan;
        public TextView text_username;
        public LinearLayout liners;
        public Myviewholder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.img_tou = (ImageView) rootView.findViewById(R.id.img_tou);
            this.text_name = (TextView) rootView.findViewById(R.id.text_name);
            this.da_zan = (TextView) rootView.findViewById(R.id.da_zan);
            this.imageView2 = (ImageView) rootView.findViewById(R.id.imageView2);
            this.relan = (RelativeLayout) rootView.findViewById(R.id.relan);
            this.text_username = (TextView) rootView.findViewById(R.id.text_username);
            this.liners = (LinearLayout) rootView.findViewById(R.id.liners);
        }
    }

}
