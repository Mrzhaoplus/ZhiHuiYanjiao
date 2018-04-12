package www.diandianxing.com.diandianxing.util;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import www.diandianxing.com.diandianxing.R;


/**
 * Created by Administrator on 2018/3/2.
 */

public class ZDPop extends PopupWindow implements View.OnClickListener{

    private int resId;
    private Context context;
    private LayoutInflater inflater;
    public View defaultView;
    private TextView tv_jfzd,tv_sc,tv_close;

    private  ZDClickListener zd;

    public ZDPop(Context context, int resId
    ) {
        super(context);
        this.context = context;
        this.resId = resId;
        initPopupWindow();
    }

    public void initPopupWindow() {
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        defaultView = inflater.inflate(this.resId, null);
        defaultView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        setContentView(defaultView);
        tv_jfzd=defaultView.findViewById(R.id.tv_jfzd);
        tv_sc=defaultView.findViewById(R.id.tv_sc);
        tv_close=defaultView.findViewById(R.id.tv_close);
        tv_jfzd.setOnClickListener(this);
        tv_sc.setOnClickListener(this);
        tv_close.setOnClickListener(this);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(FrameLayout.LayoutParams.WRAP_CONTENT);
         setAnimationStyle(R.style.popwin_anim_style);
        setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(android.R.color.transparent)));
        setFocusable(true);
        // setOutsideTouchable(true);
        update();

    }



    /**
     *
     * @return pop的View
     */
    public View getDefaultView() {
        return defaultView;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_jfzd:
                zd.OnZDClickListener();
                break;
            case R.id.tv_sc:
                zd.OnSCClickListener();
                break;
            case R.id.tv_close:

                zd.OnQXClickListener();

                break;
        }

    }

    public interface  ZDClickListener{
        void OnZDClickListener();
        void OnSCClickListener();
        void OnQXClickListener();
    }
    public void setZDClickListener(ZDClickListener zd){
        this.zd=zd;
    }
}
