package zhyj.dqjt.com.zhihuiyanjiao.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import zhyj.dqjt.com.zhihuiyanjiao.R;

/**
 * Created by Mr赵 on 2018/4/4.
 */

public class MsgitmeAdapter extends BaseAdapter {
    private List<String> list=new ArrayList<>();
    private Context ctx;

    public MsgitmeAdapter(List<String> list, Context ctx) {
        this.list = list;
        this.ctx = ctx;
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
    public View getView(int position, View view, ViewGroup viewGroup) {
        final Viewhodel hodel;
        if(view==null){
            hodel=new Viewhodel();
              view=View.inflate(ctx, R.layout.msg_item,null);
            hodel.be_read = view.findViewById(R.id.be_read);
            hodel.text_tile = view.findViewById(R.id.text_tile);
            view.findViewById(R.id.liner1);
            view.setTag(hodel);
        }else{
            hodel= (Viewhodel) view.getTag();
        }




        return view;
    }
    public class Viewhodel{
        View be_read;
        TextView text_tile;
    }
}
