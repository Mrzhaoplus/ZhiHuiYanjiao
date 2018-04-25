package www.diandianxing.com.diandianxing.fragment.paikefragment;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
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

import www.diandianxing.com.diandianxing.ReleaseShootoffVidoActivity;
import www.diandianxing.com.diandianxing.adapter.Jiaodianadapter;
import www.diandianxing.com.diandianxing.adapter.Tujianadapter;
import www.diandianxing.com.diandianxing.base.BaseFragment;
import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.bean.GuanzhuJD;
import www.diandianxing.com.diandianxing.bean.MsgBus;
import www.diandianxing.com.diandianxing.bean.PaiKeInfo;
import www.diandianxing.com.diandianxing.util.Api;
import www.diandianxing.com.diandianxing.util.MyGridView;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class GuanzhuFragment extends BaseFragment {
    private MyGridView tui_recycler;
    private SpringView spring_view;
    private int pageNo=1;
    Tujianadapter tujianadapter;
    private List<PaiKeInfo> list = new ArrayList<>();



    @Override
    protected int setContentView() {
        return R.layout.fragment_guanzhu;
    }
    @Override
    protected void lazyLoad() {
        View contentView =getContentView();
        tui_recycler = contentView.findViewById(R.id.tui_recycler);
        spring_view = contentView.findViewById(R.id.spring_view);


        networklist();

        spring_view.setType(SpringView.Type.FOLLOW);
        spring_view.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        list.clear();
                        pageNo=1;
                        networklist();

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
                        networklist();
                    }
                },0);
                spring_view.onFinishFreshAndLoad();

            }
        });
         spring_view.setHeader(new DefaultHeader(getActivity()));
        spring_view.setFooter(new DefaultFooter(getActivity()));
    }


    private void networklist() {

        HttpParams params = new HttpParams();
        params.put("pageNo", pageNo);
        params.put("token", Api.token);
        Log.d("TAG","数据内容"+params.toString());
        OkGo.<String>post(Api.BASE_URL +"app/paike/follow")
                .tag(this)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        String body = response.body();
                        Log.d("TAG", "拍客关注" + body);
                        JSONObject jsonobj = null;
                        try {
                            jsonobj = new JSONObject(body);
                            int code = jsonobj.getInt("code");
                            JSONArray datas = jsonobj.getJSONArray("datas");
                            List<PaiKeInfo> paiKeInfos = new ArrayList<PaiKeInfo>();
                            if (code == 200) {
                                for(int i=0;i<datas.length();i++){

                                    JSONObject jo = datas.getJSONObject(i);
                                    PaiKeInfo paiKeInfo = new PaiKeInfo();
                                    paiKeInfo.id=jo.getString("id");
                                    paiKeInfo.imageUrl=jo.getString("imageUrl");
                                    paiKeInfo.mvUrl=jo.getString("mvUrl");
                                    paiKeInfo.userId=jo.getString("userId");
                                    paiKeInfo.address=jo.getString("address");
                                    paiKeInfo.userName=jo.getString("userName");
                                    paiKeInfo.userLevel=jo.getString("userLevel");
                                    paiKeInfo.isRecommend=jo.getString("isRecommend");
                                    paiKeInfo.pkOperation=jo.getString("pkOperation");
                                    paiKeInfo.createTime=jo.getString("createTime");
                                    paiKeInfo.isDeleted=jo.getString("isDeleted");
                                    paiKeInfo.pkTitle=jo.getString("pkTitle");
                                    paiKeInfo.imagesUrl=jo.getString("imagesUrl");
                                    paiKeInfo.pkContent=jo.getString("pkContent");
                                    paiKeInfo.commentCount=jo.getString("commentCount");
                                    paiKeInfo.dianZanCount=jo.getString("dianZanCount");
                                    paiKeInfo.collectCount=jo.getString("collectCount");
                                    paiKeInfo.relayCount=jo.getString("relayCount");
                                    paiKeInfo.nickName=jo.getString("nickName");
                                    paiKeInfo.pic=jo.getString("pic");
                                    paiKeInfo.nickName=jo.getString("nickName");


                                    paiKeInfo.paths = new ArrayList<String>();
                                    if(paiKeInfo.imagesUrl.length()>0){

                                        JSONArray ja = jo.getJSONArray("paths");
                                        for(int j=0;j<ja.length();j++){

                                            paiKeInfo.paths.add(ja.getString(j));

                                        }

                                    }

                                    paiKeInfos.add(paiKeInfo);


                                }
                                if(pageNo>1){

                                    if(paiKeInfos.size()<=0){

                                        Toast.makeText(getActivity(),Api.TOAST,Toast.LENGTH_SHORT).show();

                                    }else{
                                        list.addAll(paiKeInfos);
                                    }

                                }else{

                                    list.addAll(paiKeInfos);

                                }
                                if(tujianadapter==null){
                                    tujianadapter=new Tujianadapter(getActivity(),list);
                                    tui_recycler.setAdapter(tujianadapter);
                                }else{

                                    tujianadapter.notifyDataSetChanged();
                                }


                            } else {
                                Toast.makeText(getActivity(),jsonobj.getString("msg"),Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("TAG","解析失败了！！！");
                        }
                    }
                });
    }


}
