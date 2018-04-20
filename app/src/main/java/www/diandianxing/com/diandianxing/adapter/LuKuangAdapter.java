package www.diandianxing.com.diandianxing.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.ShujuBean.LuKuang_Bean;
import www.diandianxing.com.diandianxing.util.ImageLoder;

/**
 * Created by Mr赵 on 2018/4/3.
 */

public class LuKuangAdapter extends BaseAdapter {
    private Context context;
    List<LuKuang_Bean.DatasBean> list;

    public LuKuangAdapter(Context context, List<LuKuang_Bean.DatasBean> datas) {
        this.context = context;
        this.list = datas;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
         ViewHolder vh;
        if(view==null){
           vh=new ViewHolder();
           view=View.inflate(context,R.layout.lukuang_adapter,null);
            vh.imag = view.findViewById(R.id.jiankong);
            vh.text_tile = view.findViewById(R.id.text_tile);
            vh.text_date = view.findViewById(R.id.text_date);
            view.setTag(vh);
        }else{
            vh= (ViewHolder) view.getTag();
        }
        ImageLoader.getInstance().displayImage(list.get(i).getImgUrl(),vh.imag, ImageLoder.getDefaultOption());
        String dateToString = getDateToString(String.valueOf(list.get(i).getCreateTime()/1000));
        vh.text_date.setText(dateToString);
        String trafficTitle = list.get(i).getTrafficTitle();
        if(trafficTitle.length()>20){
            vh.text_tile.setText(trafficTitle.substring(0,15)+".....");
        }else{
            vh.text_tile.setText(trafficTitle);
        }



        return view;
    }
    class ViewHolder{
        ImageView imag;
        TextView text_tile;
        TextView text_date;
    }
    //  时间戳转为日期  /年/月/日
    public static String getDateToString(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long lcc_time = Long.valueOf(time);
        String format = sdf.format(new Date(lcc_time * 1000L));
        return format;
    }
}
