package www.diandianxing.com.diandianxing.fragment.messagefragment;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;

import www.diandianxing.com.diandianxing.ShujuBean.Shanchu_Bean;
import www.diandianxing.com.diandianxing.ShujuBean.Zan_msg_Bean;
import www.diandianxing.com.diandianxing.adapter.Commentadapter;
import www.diandianxing.com.diandianxing.base.BaseFragment;
import www.diandianxing.com.diandianxing.fragment.mainfragment.JiaoDetailActivity;
import www.diandianxing.com.diandianxing.interfase.Shanchu_presenter_interfase;
import www.diandianxing.com.diandianxing.interfase.Zan_msg_presenter_interfase;
import www.diandianxing.com.diandianxing.presenter.Shanchu_presenter;
import www.diandianxing.com.diandianxing.presenter.Zan_Msg_presenter;
import www.diandianxing.com.diandianxing.util.Api;
import www.diandianxing.com.diandianxing.util.BaseDialog;
import www.diandianxing.com.diandianxing.util.DividerItemDecoration;
import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.util.NetUtil;
import www.diandianxing.com.diandianxing.util.ToastUtils;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */
/*
     评论消息
 */
public class Commentfragment extends BaseFragment implements Zan_msg_presenter_interfase, Shanchu_presenter_interfase {

    private RecyclerView comment_relycle;
    private SpringView spring_view;
    private TextView text_sure;
    private int pageNo=1;
    List<Zan_msg_Bean.DatasBean> list=new ArrayList<>();
    private Zan_Msg_presenter zan_msg_presenter= new Zan_Msg_presenter(this);
    private Commentadapter commentadapter;
    Shanchu_presenter shanchu_presenter = new Shanchu_presenter(this);
    private int id;
    private int postion;
    @Override
    protected int setContentView() {
        return R.layout.fragment_comment;
    }

    @Override
    protected void lazyLoad() {
        if(NetUtil.checkNet(getActivity())){
            //获取引用
            zan_msg_presenter.getpath(Api.token,0,pageNo);
        }else{
            Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
        }

        View contentView = getContentView();
        comment_relycle = contentView.findViewById(R.id.comment_recycle);
        spring_view = contentView.findViewById(R.id.spring_view);

        comment_relycle.setNestedScrollingEnabled(false);
        commentadapter = new Commentadapter(getActivity(),list);
        comment_relycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        comment_relycle.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        comment_relycle.setAdapter(commentadapter);
        spring_view.setType(SpringView.Type.FOLLOW);
        spring_view.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                      list.clear();
                      pageNo=1;
                        if(NetUtil.checkNet(getActivity())){
                            //获取引用
                            zan_msg_presenter.getpath(Api.token,0,pageNo);
                        }else{
                            Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                        }

                        commentadapter.notifyDataSetChanged();
                    }
                },0);
                spring_view.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pageNo++;
                        if(NetUtil.checkNet(getActivity())){
                            //获取引用
                            zan_msg_presenter.getpath(Api.token,0,pageNo);
                        }else{
                            Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                        }

                        commentadapter.notifyDataSetChanged();
                    }
                },0);
                spring_view.onFinishFreshAndLoad();
            }
        });
        spring_view.setFooter(new DefaultFooter(getActivity()));
        spring_view.setHeader(new DefaultHeader(getActivity()));

        commentadapter.setOnLongDeleteListener(longDeleteListener);

    }

    private Commentadapter.LongDeleteListener longDeleteListener = new Commentadapter.LongDeleteListener() {
        @Override
        public void OnLongDeleteListener(int pos) {
            postion = pos;
            shumaDialog(Gravity.CENTER,R.style.Alpah_aniamtion);
            id = list.get(pos).getId();
        }
    };
    private void shumaDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(getActivity());
        final BaseDialog dialog = builder.setViewId(R.layout.dialog_delete)
                //设置dialogpadding
                .setPaddingdp(0, 10, 0, 10)
                //设置显示位置
                .setGravity(grary)
                //设置动画
                .setAnimation(animationStyle)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(false)
                //设置监听事件
                .builder();
        dialog.show();
        text_sure = dialog.getView(R.id.text_sure);

        TextView text_pause = dialog.getView(R.id.text_pause);
        //知道了
        text_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //引用
                if(NetUtil.checkNet(getActivity())){
                    //获取引用
                    shanchu_presenter.getpath(Api.token,id);
                }else{
                    Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                }

                dialog.dismiss();
            }
        });
        //取消
        text_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void getsuccess(Zan_msg_Bean zan) {
        if(zan.getCode().equals("200")){
            List<Zan_msg_Bean.DatasBean> datas = zan.getDatas();
            if(pageNo>1){
                if(datas.size()>0){
                    list.addAll(datas);
                }else{
                    Toast.makeText(getActivity(),Api.TOAST,Toast.LENGTH_SHORT).show();
                }
            }else{
                list.addAll(datas);
            }


            commentadapter.notifyDataSetChanged();

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        zan_msg_presenter.getkong();
        shanchu_presenter.getkong();
    }

    @Override
    public void getsuccess(Shanchu_Bean shanchu_bean) {
        if(shanchu_bean.getCode().equals("200")){
            list.remove(postion);
        }else if(shanchu_bean.getCode().equals("201")){
            ToastUtils.showShort(getActivity(),shanchu_bean.getMsg());
        }else if(shanchu_bean.getCode().equals("203")){
            ToastUtils.showShort(getActivity(),shanchu_bean.getMsg());
        }
        else if(shanchu_bean.getCode().equals("500")){
            ToastUtils.showShort(getActivity(),shanchu_bean.getMsg());
        }
        commentadapter.notifyDataSetChanged();
    }
}
