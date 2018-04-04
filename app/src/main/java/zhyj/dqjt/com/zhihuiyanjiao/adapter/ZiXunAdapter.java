package zhyj.dqjt.com.zhihuiyanjiao.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zhyj.dqjt.com.zhihuiyanjiao.R;

/**
 * Created by Mr赵 on 2018/4/2.
 */

public class ZiXunAdapter extends BaseAdapter {
      private   Context context;
      private List<String> list=new ArrayList<>();

    public ZiXunAdapter(Context context) {
        this.context = context;
        data();
    }

    private void data() {
        for(int i=0;i<6;i++){
            list.add(i+"");
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
    public int getViewTypeCount() {
        return 2;
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

        return view;
    }
    //优化有图片
    class ViewHodelOne{
     TextView text_tile;
     ImageView imag;
     TextView text_date;
     TextView text_zan;
    }

}
