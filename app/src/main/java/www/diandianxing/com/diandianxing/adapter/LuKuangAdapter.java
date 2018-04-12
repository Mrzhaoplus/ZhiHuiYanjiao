package www.diandianxing.com.diandianxing.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import www.diandianxing.com.diandianxing.R;

/**
 * Created by Mr赵 on 2018/4/3.
 */

public class LuKuangAdapter extends BaseAdapter {
    private Context context;
    private List<String> list=new ArrayList<>();

    public LuKuangAdapter(Context context) {
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
        if(vh.text_tile.getText().length()>20){
            vh.text_tile.setText(vh.text_tile.getText().toString().substring(0,15)+".....");
        }else{
            vh.text_tile.setText(vh.text_tile.getText().toString());
        }



        return view;
    }
    class ViewHolder{
        ImageView imag;
        TextView text_tile;
        TextView text_date;
    }

}
