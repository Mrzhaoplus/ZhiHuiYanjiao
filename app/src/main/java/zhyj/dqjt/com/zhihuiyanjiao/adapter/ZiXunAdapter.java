package zhyj.dqjt.com.zhihuiyanjiao.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/4/2.
 */

public class ZiXunAdapter extends BaseAdapter {
      private   Context context;
      private List<String> list=new ArrayList<>();
        private int IMAGE_ONE=1;
        private int IMAGE_NO=0;
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
    public int getItemViewType(int position) {
        if(position==1||position==4){
          return IMAGE_NO;
        }
        return IMAGE_ONE;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(getItemViewType(i)==IMAGE_ONE){

        }
        return null;
    }
    //优化有图片
    class ViewHodelOne{
     TextView text_tile;
     ImageView imag;
     TextView text_date;
     TextView text_zan;
    }
    //优化没图片
        class ViewHodelNo{
        TextView text_tile;
        TextView text_date;
        TextView text_zan;
    }
}
