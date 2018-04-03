package zhyj.dqjt.com.zhihuiyanjiao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
 */

public class Homeadapter extends RecyclerView.Adapter<Homeadapter.MyviewHolder> {

    private Context context;
    private List<String>   list = new ArrayList<>();

    public Homeadapter(Context context) {
        this.context = context;
        data();
    }

    private void data() {
        for (int i = 0; i <6; i++) {
             list.add(i+"");
        }
    }

    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyviewHolder myviewHolder = new MyviewHolder(LayoutInflater.from(context).inflate(R.layout.homeadapter, parent, false));

        return myviewHolder;
    }

    @Override
    public void onBindViewHolder(MyviewHolder holder, int position) {

            holder.text_zan.setText(list.get(position).toString()+"12");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        public ImageView img_pho;
        public TextView text_date;
        public TextView text_zan;

        public MyviewHolder(View rootView) {
            super(rootView);
            this.img_pho = (ImageView) rootView.findViewById(R.id.img_pho);
            this.text_date = (TextView) rootView.findViewById(R.id.text_date);
            this.text_zan = (TextView) rootView.findViewById(R.id.text_zan);
        }
    }


}
