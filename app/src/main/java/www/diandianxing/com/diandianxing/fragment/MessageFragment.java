package www.diandianxing.com.diandianxing.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import www.diandianxing.com.diandianxing.base.BaseFragment;
import www.diandianxing.com.diandianxing.fragment.messagefragment.Commentfragment;
import www.diandianxing.com.diandianxing.fragment.messagefragment.Praisefragment;
import www.diandianxing.com.diandianxing.R;
/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class MessageFragment extends BaseFragment implements View.OnClickListener {
    private LinearLayout liner_pinlun;
    private LinearLayout liner_zan;
    private View vi,v2;
    private Commentfragment commentfragment;//评论
    private Praisefragment praisefragment;//点赞
    private Fragment currentf;
    private TextView text_zan;
    private TextView text_pin;

    @Override
    protected int setContentView() {
        return R.layout.fragment_message;
    }

    @Override
    protected void lazyLoad() {
        View contentView = getContentView();
        liner_pinlun = contentView.findViewById(R.id.liner_pinlun);
        liner_zan = contentView.findViewById(R.id.liner_dianzan);
        text_zan = contentView.findViewById(R.id.text_zan);
        text_pin = contentView.findViewById(R.id.text_pin);
        vi = contentView.findViewById(R.id.v1);
        v2 = contentView.findViewById(R.id.v2);
        liner_pinlun.setOnClickListener(this);
        liner_zan.setOnClickListener(this);
        if(commentfragment==null){
            commentfragment=new Commentfragment();

        }
        addFagment(commentfragment);

    }


    public void addFagment(Fragment f){

        //得到管理类
        FragmentManager childFragmentManager = getChildFragmentManager();
        //开启事务
        FragmentTransaction fragmentTransaction =childFragmentManager.beginTransaction();

        if(currentf!=null){
            //若当前fragment不为空隐藏
            fragmentTransaction.hide(currentf);

        }
        if(!f.isAdded()){
            //判断当前fragment有没有被加载
            fragmentTransaction.add(R.id.fram_layout,f);
        }
        fragmentTransaction.show(f);//显示当前fragment
        fragmentTransaction.commit();
        currentf=f;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.liner_dianzan:
                text_zan.setTextColor(getResources().getColor(R.color.text_orage));
                text_pin.setTextColor(getResources().getColor(R.color.black_san));
                v2.setVisibility(View.VISIBLE);
                vi.setVisibility(View.INVISIBLE);
                if(praisefragment==null){
                    praisefragment=new Praisefragment();
                }
                addFagment(praisefragment);

                break;
            case R.id.liner_pinlun:
                text_pin.setTextColor(getResources().getColor(R.color.text_orage));
                text_zan.setTextColor(getResources().getColor(R.color.black_san));
                vi.setVisibility(View.VISIBLE);
                v2.setVisibility(View.INVISIBLE);
                if(commentfragment==null){
                    commentfragment=new Commentfragment();
                }
                addFagment(commentfragment);
                break;
        }
    }
}