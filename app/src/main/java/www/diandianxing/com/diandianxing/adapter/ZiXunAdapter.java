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
import www.diandianxing.com.diandianxing.ShujuBean.zixun_Bean;
import www.diandianxing.com.diandianxing.util.ImageLoder;

/**
 * Created by Mr赵 on 2018/4/2.
 */

public class ZiXunAdapter extends BaseAdapter {
      private   Context context;
      private List<zixun_Bean.DatasBean> list;
    public ZiXunAdapter(Context context, List<zixun_Bean.DatasBean> datas) {
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
               ViewHodelOne imagone;
              if(view==null){
                 imagone=new ViewHodelOne();
                 view=View.inflate(context, R.layout.zixun_imagone,null);
                 imagone.text_tile=view.findViewById(R.id.text_tile);
                  imagone.imag=view.findViewById(R.id.imag);
                  imagone.text_date=view.findViewById(R.id.text_date);
                  imagone.text_zan=view.findViewById(R.id.text_zan);
                  view.setTag(imagone);
              }else{
                  imagone= (ViewHodelOne) view.getTag();
              }
        ImageLoader.getInstance().displayImage(list.get(i).getSmallImage(),imagone.imag, ImageLoder.getDefaultOption());
              imagone.text_tile.setText(list.get(i).getInfoTitle());
              imagone.text_zan.setText(list.get(i).getDianZanCount()+"点赞");
              String dateToString = getDateToString(String.valueOf(list.get(i).getUpdateTime()/1000));
              imagone.text_date.setText(dateToString);
        return view;
    }
    //优化有图片
    class ViewHodelOne{
     TextView text_tile;
     ImageView imag;
     TextView text_date;
     TextView text_zan;
    }

    //  时间戳转为日期  /年/月/日
    public static String getDateToString(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long lcc_time = Long.valueOf(time);
        String format = sdf.format(new Date(lcc_time * 1000L));
        return format;
    }


}
