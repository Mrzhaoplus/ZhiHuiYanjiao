package www.diandianxing.com.diandianxing.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import www.diandianxing.com.diandianxing.ShujuBean.zixun_Bean;
import www.diandianxing.com.diandianxing.interfase.RecyGetonclick;
import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.util.ImageLoder;
import www.diandianxing.com.diandianxing.util.MyUtils;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 *
 */

public class Homeadapter extends RecyclerView.Adapter<Homeadapter.MyviewHolder> {
    List<zixun_Bean.DatasBean> list;
    private Context context;
    private RecyGetonclick click;

    public void getthis(RecyGetonclick click){
        this.click=click;
    }

    public Homeadapter(List<zixun_Bean.DatasBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyviewHolder myviewHolder = new MyviewHolder(LayoutInflater.from(context).inflate(R.layout.homeadapter, parent, false));

        return myviewHolder;
    }

    @Override
    public void onBindViewHolder(MyviewHolder holder, final int position) {

//        ImageLoader.getInstance().displayImage(list.get(position).getSmallImage(),holder.img_pho,ImageLoder.getDefaultOption());
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context).load(list.get(position).getSmallImage()).
                apply(options)
                .into(holder.img_pho);
         holder.neirong.setText(list.get(position).getInfoTitle());
         //时间戳转换为日期
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String end = sf.format(date);
        String fb = MyUtils.stampToDate(list.get(position).getCreateTime()+"");

        String time = MyUtils.dateDiff(fb, end, "yyyy-MM-dd HH:mm:ss",list.get(position).getCreateTime()+"");
        holder.text_date.setText(time);

        holder.text_zan.setText(list.get(position).getDianZanCount()+"");
              //设置recycler点击事件
            if(click!=null){
                holder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        click.onclick(position);
                    }
                });
            }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        public ImageView img_pho;
        public TextView text_date;
        public TextView text_zan;
          public View view;
        public TextView neirong;

        public MyviewHolder(View rootView) {
            super(rootView);
            this.view = rootView;
            neirong = rootView.findViewById(R.id.neirong);
            this.img_pho = (ImageView) rootView.findViewById(R.id.img_pho);
            this.text_date = (TextView) rootView.findViewById(R.id.text_date);
            this.text_zan = (TextView) rootView.findViewById(R.id.text_zan);
        }
    }
    //  时间戳转为日期  /年/月/日
    public static String getDateToString(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long lcc_time = Long.valueOf(time);
        String format = sdf.format(new Date(lcc_time * 1000L));
        return format;
    }


}
