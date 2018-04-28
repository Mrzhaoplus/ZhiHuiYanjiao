package www.diandianxing.com.diandianxing;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import www.diandianxing.com.diandianxing.adapter.MsgitmeAdapter;
import www.diandianxing.com.diandianxing.bean.Event_dian;
import www.diandianxing.com.diandianxing.bean.FragEventBug;
import www.diandianxing.com.diandianxing.bean.MessInfo;
import www.diandianxing.com.diandianxing.util.Api;
import www.diandianxing.com.diandianxing.util.BaseDialog;
import www.diandianxing.com.diandianxing.util.MyContants;
import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.util.NetUtil;
import www.diandianxing.com.diandianxing.util.SpUtils;

public class MsgItmeActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView img_back;
    private ListView list_view;
   private List<MessInfo> list=new ArrayList<>();
   private Boolean flag=true;
    private SpringView sv_sj;
    private int pageNo=1;
    MsgitmeAdapter msgitmeAdapter;
    private int postion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyContants.windows(this);
        setContentView(R.layout.activity_msg_itme);
        initView();
    }

    private void initView() {
        if(flag){
            //注册
            EventBus.getDefault().register(this);
            flag=false;
        }
        img_back = (ImageView) findViewById(R.id.img_back);
        list_view = (ListView) findViewById(R.id.list_view);
        sv_sj= (SpringView) findViewById(R.id.sv_sj);
        initRefresh();

        img_back.setOnClickListener(this);
        /*
        * 适配器
        * */
        finishFreshAndLoad();

        /*
        * 点击事件
        * */
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                postion = i;
                Intent intent = new Intent(MsgItmeActivity.this, MagXiangqingActivity.class);
                startActivity(intent);
            }
        });

//        tv_fb.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//
//            }
//        });

        list_view.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                shumaDialog(Gravity.CENTER,R.style.Alpah_aniamtion,i);
                return true;
            }
        });


    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void fangfa(Event_dian eveen){
        int dian = eveen.getDian();
        if(dian==1){
            list.get(postion).is_read=1+"";
            msgitmeAdapter.notifyDataSetChanged();
        }
    }
    //下拉刷新
    private void initRefresh() {
        //DefaultHeader/Footer是SpringView已经实现的默认头/尾之一
        //更多还有MeituanHeader、AliHeader、RotationHeader等如上图7种
        sv_sj.setType(SpringView.Type.FOLLOW);
        sv_sj.setHeader(new DefaultHeader(MsgItmeActivity.this));
        sv_sj.setFooter(new DefaultFooter(MsgItmeActivity.this));
        sv_sj.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
//                Toast.makeText(mContext,"下拉刷新中",Toast.LENGTH_SHORT).show();
                // list.clear();
                // 网络请求;
                // mStarFragmentPresenter.queryData();
                //一分钟之后关闭刷新的方法
                list.clear();
                pageNo=1;
                finishFreshAndLoad();
            }
            @Override
            public void onLoadmore() {
//                Toast.makeText(mContext,"玩命加载中...",Toast.LENGTH_SHORT).show();
                pageNo++;
                finishFreshAndLoad();
            }
        });
    }

    /**
     * 关闭加载提示
     */
    private void finishFreshAndLoad() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(NetUtil.checkNet(MsgItmeActivity.this)){
                    networkmoney();
                }else{
                    Toast.makeText(MsgItmeActivity.this, "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                }
                sv_sj.onFinishFreshAndLoad();
            }
        }, 0);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_back:
                finish();
                break;
        }
    }


    private void networkdelete(int id) {

        HttpParams params = new HttpParams();
        params.put("id", id);
        params.put("token", Api.token);
        Log.d("TAG","数据内容"+params.toString());
        OkGo.<String>post(Api.BASE_URL +"app/information/deleteCommentOrZan")
                .tag(this)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        Log.d("TAG", "消息列表删除" + body);
                        JSONObject jsonobj = null;
                        try {
                            jsonobj = new JSONObject(body);
                            int code = jsonobj.getInt("code");
                            if (code == 200) {

                                list.clear();
                                pageNo=1;
                                finishFreshAndLoad();

                            } else {
                                Toast.makeText(MsgItmeActivity.this,jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("TAG","解析失败了！！！");
                        }
                    }
                });
    }


    private void networkmoney() {

        HttpParams params = new HttpParams();
        params.put("pageNo", pageNo);
        params.put("token", Api.token);
        Log.d("TAG","数据内容"+params.toString());
        OkGo.<String>post(Api.BASE_URL +"app/home/pushMsg")
                .tag(this)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        String body = response.body();
                        Log.d("TAG", "消息列表" + body);
                        JSONObject jsonobj = null;
                        try {
                            jsonobj = new JSONObject(body);
                            int code = jsonobj.getInt("code");
                            JSONArray datas = jsonobj.getJSONArray("datas");
                            List<MessInfo> messInfos = new ArrayList<MessInfo>();

                            if (code == 200) {
                                for(int i=0;i<datas.length();i++){

                                    JSONObject jo = datas.getJSONObject(i);

                                    MessInfo messInfo = new MessInfo();
                                    messInfo.createTime=jo.getString("createTime");
                                    messInfo.id=jo.getString("id");
                                    messInfo.is_read=jo.getString("is_read");
                                    messInfo.isDelete=jo.getString("isDelete");
                                    messInfo.msgContent=jo.getString("msgContent");
                                    messInfo.msgTitle=jo.getString("msgTitle");
                                    messInfos.add(messInfo);
                                }
                                if(pageNo>1){

                                    if(messInfos.size()<=0){

                                        Toast.makeText(MsgItmeActivity.this,Api.TOAST,Toast.LENGTH_SHORT).show();

                                    }else{
                                        list.addAll(messInfos);
                                    }

                                }else{

                                    list.addAll(messInfos);

                                }

                                if(msgitmeAdapter==null){
                                    msgitmeAdapter = new MsgitmeAdapter(list, MsgItmeActivity.this);
                                    list_view.setAdapter(msgitmeAdapter);
                                }else{

                                    msgitmeAdapter.notifyDataSetChanged();
                                }


                            } else {
                                Toast.makeText(MsgItmeActivity.this,jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("TAG","解析失败了！！！");
                        }
                    }
                });
    }

/*
* 对话框
* */
    private void shumaDialog(int grary, int animationStyle, final int position) {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        final BaseDialog dialog = builder.setViewId(R.layout.dialog_shanchu)
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
        TextView text_sure = dialog.getView(R.id.text_sure);

        TextView text_pause = dialog.getView(R.id.text_pause);
        //删除
        text_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(NetUtil.checkNet(MsgItmeActivity.this)){
                    networkdelete(position);
                }else{
                    Toast.makeText(MsgItmeActivity.this, "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
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
    protected void onDestroy() {
        super.onDestroy();
        //注销
        EventBus.getDefault().unregister(this);
        EventBus.getDefault().removeAllStickyEvents();
    }
}
