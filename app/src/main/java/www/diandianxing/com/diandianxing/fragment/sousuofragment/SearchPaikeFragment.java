package www.diandianxing.com.diandianxing.fragment.sousuofragment;

import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.adapter.Tujianadapter;
import www.diandianxing.com.diandianxing.base.BaseFragment;
import www.diandianxing.com.diandianxing.bean.PaiKeInfo;
import www.diandianxing.com.diandianxing.fragment.paikefragment.GuanzhuFragment;
import www.diandianxing.com.diandianxing.util.Api;
import www.diandianxing.com.diandianxing.util.GlobalParams;
import www.diandianxing.com.diandianxing.util.MyGridView;
import www.diandianxing.com.diandianxing.util.NetUtil;
import www.diandianxing.com.diandianxing.util.PaiKeZZListener;
import www.diandianxing.com.diandianxing.util.SpUtils;

/**
 * Created by Administrator on 2018/5/8.
 */

public class SearchPaikeFragment extends BaseFragment implements PaiKeZZListener{
    private MyGridView tui_recycler;
    private SpringView spring_view;
    private int pageNo=1;
    Tujianadapter tujianadapter;
    private List<PaiKeInfo> list = new ArrayList<>();
    TextView tv_yw_content;
    private String search="";
    @Override
    protected int setContentView() {
        Bundle arguments = getArguments();
        search=arguments.getString("search");
        return R.layout.fragment_guanzhu;
    }
    @Override
    protected void lazyLoad() {
        View contentView =getContentView();
        tui_recycler = contentView.findViewById(R.id.tui_recycler);
        spring_view = contentView.findViewById(R.id.spring_view);
        tv_yw_content=contentView.findViewById(R.id.tv_yw_content);

        tv_yw_content.setVisibility(View.GONE);



        if(NetUtil.checkNet(getActivity())){
            networklist();
        }else{
            Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
        }
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
                            networklist();
                        }else{
                            Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                        }
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
                            networklist();
                        }else{
                            Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                        }
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
        params.put("search", search);
        params.put("token", SpUtils.getString(getActivity(),"token",null));
        Log.d("TAG","数据内容"+params.toString());
        OkGo.<String>post(Api.BASE_URL +"app/paike/searchbypaike")
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
                                    paiKeInfo.pkid=jo.getString("id");
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
                                    paiKeInfo.isZan=jo.getString("isZan");

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
                                    tv_yw_content.setVisibility(View.GONE);
                                }else{


                                    list.addAll(paiKeInfos);

                                }
                                if(tujianadapter==null){
                                    tujianadapter=new Tujianadapter(getActivity(),list,SearchPaikeFragment.this);
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

    int pos;
    @Override
    public void onPaiKeZZListener(int pos) {

        this.pos=pos;
        PaiKeInfo pk = list.get(pos);

        if(NetUtil.checkNet(getActivity())){
            if(Integer.parseInt(pk.isZan)==0){
                Log.e("TAG","点击点赞");
                network(Integer.parseInt(pk.pkid),2,0);
            }else{
                QXnetwork(Integer.parseInt(pk.pkid),2,0);
            }
        }else{
            Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onPaiKeSCListener(int pos) {

    }

    private void network(int objId , int obj_type, final int operation_type) {

        HttpParams params = new HttpParams();
        params.put("objId", objId);

        params.put("obj_type", obj_type);

        params.put("operation_type",operation_type);

        params.put("token", SpUtils.getString(getActivity(),"token",null));
        Log.d("TAG","数据内容"+params.toString());
        OkGo.<String>post(Api.BASE_URL +"app/home/userOperation")
                .tag(this)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        String body = response.body();
                        Log.d("TAG", "数据" + body);
                        JSONObject jsonobj = null;
                        try {
                            jsonobj = new JSONObject(body);
                            int code = jsonobj.getInt("code");
                            if (code == 200) {

                                list.get(pos).isZan="1";
                                list.get(pos).dianZanCount=Integer.parseInt(list.get(pos).dianZanCount)+1+"";

                                if(tujianadapter==null){
                                    tujianadapter=new Tujianadapter(getActivity(),list,SearchPaikeFragment.this);
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
    private void QXnetwork(int objId , int obj_type, final int operation_type) {

        HttpParams params = new HttpParams();
        params.put("objId", objId);

        params.put("obj_type", obj_type);

        params.put("operation_type",operation_type);

        params.put("token", SpUtils.getString(getActivity(),"token",null));
        Log.d("TAG","数据内容"+params.toString());
        OkGo.<String>post(Api.BASE_URL +"app/home/userCancelOperation")
                .tag(this)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        String body = response.body();
                        Log.d("TAG", "取消数据" + body);
                        JSONObject jsonobj = null;
                        try {
                            jsonobj = new JSONObject(body);
                            int code = jsonobj.getInt("code");
                            if (code == 200) {

                                list.get(pos).isZan="0";
                                list.get(pos).dianZanCount=Integer.parseInt(list.get(pos).dianZanCount)-1+"";
                                if(tujianadapter==null){
                                    tujianadapter=new Tujianadapter(getActivity(),list,SearchPaikeFragment.this);
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
