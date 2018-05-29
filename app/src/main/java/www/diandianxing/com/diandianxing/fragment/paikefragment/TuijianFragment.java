package www.diandianxing.com.diandianxing.fragment.paikefragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import www.diandianxing.com.diandianxing.Login.LoginActivity;
import www.diandianxing.com.diandianxing.adapter.Tujianadapter;
import www.diandianxing.com.diandianxing.base.BaseFragment;
import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.bean.PaiKeInfo;
import www.diandianxing.com.diandianxing.bean.TuijianTable;
import www.diandianxing.com.diandianxing.util.Api;
import www.diandianxing.com.diandianxing.util.GlobalParams;
import www.diandianxing.com.diandianxing.util.MyGridView;
import www.diandianxing.com.diandianxing.util.NetUtil;
import www.diandianxing.com.diandianxing.util.PaiKeZZListener;
import www.diandianxing.com.diandianxing.util.SpUtils;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class TuijianFragment extends BaseFragment implements PaiKeZZListener{

    private MyGridView tui_recycler;
    private SpringView spring_view;
    private int pageNo=1;
    private List<PaiKeInfo> list = new ArrayList<>();
    Tujianadapter tujianadapter;
    @Override
    protected int setContentView() {
        return R.layout.fragment_tuijian;
    }

    @Override
    protected void lazyLoad() {
        View contentView =getContentView();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(GlobalParams.LOGING_SX);
        intentFilter.addAction(GlobalParams.GZ);
        getActivity().registerReceiver(broadcastReceiver,intentFilter);
        tui_recycler = contentView.findViewById(R.id.tui_recycler);
        spring_view = contentView.findViewById(R.id.spring_view);
        if(NetUtil.checkNet(getActivity())){
            networklist();
        }else{
            Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();

            List<TuijianTable> tujian = new ArrayList<>();

            tujian.addAll(DataSupport.findAll(TuijianTable.class));

            for(int i=0;i<tujian.size();i++){

                TuijianTable tuijianTable = tujian.get(i);
                PaiKeInfo paiKeInfo = new PaiKeInfo();
                paiKeInfo.pkid=tuijianTable.pkid;
                paiKeInfo.imageUrl=tuijianTable.imageUrl;
                paiKeInfo.mvUrl=tuijianTable.mvUrl;
                paiKeInfo.userId=tuijianTable.userId;
                paiKeInfo.address=tuijianTable.address;
                paiKeInfo.userName=tuijianTable.userName;
                paiKeInfo.userLevel=tuijianTable.userLevel;
                paiKeInfo.isRecommend=tuijianTable.isRecommend;
                paiKeInfo.pkOperation=tuijianTable.pkOperation;
                paiKeInfo.createTime=tuijianTable.createTime;
                paiKeInfo.isDeleted=tuijianTable.isDeleted;
                paiKeInfo.pkTitle=tuijianTable.pkTitle;
                paiKeInfo.imagesUrl=tuijianTable.imagesUrl;
                paiKeInfo.pkContent=tuijianTable.pkContent;
                paiKeInfo.commentCount=tuijianTable.commentCount;
                paiKeInfo.dianZanCount=tuijianTable.dianZanCount;
                paiKeInfo.collectCount=tuijianTable.collectCount;
                paiKeInfo.relayCount=tuijianTable.relayCount;
                paiKeInfo.nickName=tuijianTable.nickName;
                paiKeInfo.pic=tuijianTable.pic;
                paiKeInfo.nickName=tuijianTable.nickName;
                paiKeInfo.isZan=tuijianTable.isZan;
                String[] split=null;
                paiKeInfo.paths = new ArrayList<>();
                if(tuijianTable.paths!=null&&tuijianTable.paths.length()>0){

                    split= tuijianTable.paths.split(",");

                    for(int j=0;j<split.length;j++){
                        paiKeInfo.paths.add(split[j]);
                    }

                }



                list.add(paiKeInfo);
            }
            if(tujianadapter==null){
                tujianadapter=new Tujianadapter(getActivity(),list,TuijianFragment.this);
                tui_recycler.setAdapter(tujianadapter);
            }else{

                tujianadapter.notifyDataSetChanged();
            }




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

                            List<TuijianTable> tujian = new ArrayList<>();

                            tujian.addAll(DataSupport.findAll(TuijianTable.class));

                            list.clear();

                            for(int i=0;i<tujian.size();i++){

                                TuijianTable tuijianTable = tujian.get(i);
                                PaiKeInfo paiKeInfo = new PaiKeInfo();
                                paiKeInfo.pkid=tuijianTable.pkid;
                                paiKeInfo.imageUrl=tuijianTable.imageUrl;
                                paiKeInfo.mvUrl=tuijianTable.mvUrl;
                                paiKeInfo.userId=tuijianTable.userId;
                                paiKeInfo.address=tuijianTable.address;
                                paiKeInfo.userName=tuijianTable.userName;
                                paiKeInfo.userLevel=tuijianTable.userLevel;
                                paiKeInfo.isRecommend=tuijianTable.isRecommend;
                                paiKeInfo.pkOperation=tuijianTable.pkOperation;
                                paiKeInfo.createTime=tuijianTable.createTime;
                                paiKeInfo.isDeleted=tuijianTable.isDeleted;
                                paiKeInfo.pkTitle=tuijianTable.pkTitle;
                                paiKeInfo.imagesUrl=tuijianTable.imagesUrl;
                                paiKeInfo.pkContent=tuijianTable.pkContent;
                                paiKeInfo.commentCount=tuijianTable.commentCount;
                                paiKeInfo.dianZanCount=tuijianTable.dianZanCount;
                                paiKeInfo.collectCount=tuijianTable.collectCount;
                                paiKeInfo.relayCount=tuijianTable.relayCount;
                                paiKeInfo.nickName=tuijianTable.nickName;
                                paiKeInfo.pic=tuijianTable.pic;
                                paiKeInfo.nickName=tuijianTable.nickName;
                                paiKeInfo.isZan=tuijianTable.isZan;
                                String[] split=null;
                                paiKeInfo.paths = new ArrayList<>();
                                if(tuijianTable.paths!=null&&tuijianTable.paths.length()>0){

                                    split= tuijianTable.paths.split(",");

                                    for(int j=0;j<split.length;j++){
                                        paiKeInfo.paths.add(split[j]);
                                    }

                                }



                                list.add(paiKeInfo);
                            }
                            if(tujianadapter==null){
                                tujianadapter=new Tujianadapter(getActivity(),list,TuijianFragment.this);
                                tui_recycler.setAdapter(tujianadapter);
                            }else{

                                tujianadapter.notifyDataSetChanged();
                            }
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

                            List<TuijianTable> tujian = new ArrayList<>();

                            tujian.addAll(DataSupport.findAll(TuijianTable.class));

                            list.clear();

                            for(int i=0;i<tujian.size();i++){

                                TuijianTable tuijianTable = tujian.get(i);
                                PaiKeInfo paiKeInfo = new PaiKeInfo();
                                paiKeInfo.pkid=tuijianTable.pkid;
                                paiKeInfo.imageUrl=tuijianTable.imageUrl;
                                paiKeInfo.mvUrl=tuijianTable.mvUrl;
                                paiKeInfo.userId=tuijianTable.userId;
                                paiKeInfo.address=tuijianTable.address;
                                paiKeInfo.userName=tuijianTable.userName;
                                paiKeInfo.userLevel=tuijianTable.userLevel;
                                paiKeInfo.isRecommend=tuijianTable.isRecommend;
                                paiKeInfo.pkOperation=tuijianTable.pkOperation;
                                paiKeInfo.createTime=tuijianTable.createTime;
                                paiKeInfo.isDeleted=tuijianTable.isDeleted;
                                paiKeInfo.pkTitle=tuijianTable.pkTitle;
                                paiKeInfo.imagesUrl=tuijianTable.imagesUrl;
                                paiKeInfo.pkContent=tuijianTable.pkContent;
                                paiKeInfo.commentCount=tuijianTable.commentCount;
                                paiKeInfo.dianZanCount=tuijianTable.dianZanCount;
                                paiKeInfo.collectCount=tuijianTable.collectCount;
                                paiKeInfo.relayCount=tuijianTable.relayCount;
                                paiKeInfo.nickName=tuijianTable.nickName;
                                paiKeInfo.pic=tuijianTable.pic;
                                paiKeInfo.nickName=tuijianTable.nickName;
                                paiKeInfo.isZan=tuijianTable.isZan;
                                String[] split=null;
                                paiKeInfo.paths = new ArrayList<>();
                                if(tuijianTable.paths!=null&&tuijianTable.paths.length()>0){

                                    split= tuijianTable.paths.split(",");

                                    for(int j=0;j<split.length;j++){
                                        paiKeInfo.paths.add(split[j]);
                                    }

                                }



                                list.add(paiKeInfo);
                            }
                            if(tujianadapter==null){
                                tujianadapter=new Tujianadapter(getActivity(),list,TuijianFragment.this);
                                tui_recycler.setAdapter(tujianadapter);
                            }else{

                                tujianadapter.notifyDataSetChanged();
                            }

                        }
                    }
                },0);
                spring_view.onFinishFreshAndLoad();
            }
        });
        spring_view.setHeader(new DefaultHeader(getActivity()));
        spring_view.setFooter(new DefaultFooter(getActivity()));

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(broadcastReceiver);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();

            if(GlobalParams.LOGING_SX.equals(action)){

                list.clear();
                pageNo=1;
                if(NetUtil.checkNet(getActivity())){
                    networklist();
                }else{
                    Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();

                    List<TuijianTable> tujian = new ArrayList<>();

                    tujian.addAll(DataSupport.findAll(TuijianTable.class));

                    list.clear();

                    for(int i=0;i<tujian.size();i++){

                        TuijianTable tuijianTable = tujian.get(i);
                        PaiKeInfo paiKeInfo = new PaiKeInfo();
                        paiKeInfo.pkid=tuijianTable.pkid;
                        paiKeInfo.imageUrl=tuijianTable.imageUrl;
                        paiKeInfo.mvUrl=tuijianTable.mvUrl;
                        paiKeInfo.userId=tuijianTable.userId;
                        paiKeInfo.address=tuijianTable.address;
                        paiKeInfo.userName=tuijianTable.userName;
                        paiKeInfo.userLevel=tuijianTable.userLevel;
                        paiKeInfo.isRecommend=tuijianTable.isRecommend;
                        paiKeInfo.pkOperation=tuijianTable.pkOperation;
                        paiKeInfo.createTime=tuijianTable.createTime;
                        paiKeInfo.isDeleted=tuijianTable.isDeleted;
                        paiKeInfo.pkTitle=tuijianTable.pkTitle;
                        paiKeInfo.imagesUrl=tuijianTable.imagesUrl;
                        paiKeInfo.pkContent=tuijianTable.pkContent;
                        paiKeInfo.commentCount=tuijianTable.commentCount;
                        paiKeInfo.dianZanCount=tuijianTable.dianZanCount;
                        paiKeInfo.collectCount=tuijianTable.collectCount;
                        paiKeInfo.relayCount=tuijianTable.relayCount;
                        paiKeInfo.nickName=tuijianTable.nickName;
                        paiKeInfo.pic=tuijianTable.pic;
                        paiKeInfo.nickName=tuijianTable.nickName;
                        paiKeInfo.isZan=tuijianTable.isZan;
                        String[] split=null;
                        paiKeInfo.paths = new ArrayList<>();
                        if(tuijianTable.paths!=null&&tuijianTable.paths.length()>0){

                            split= tuijianTable.paths.split(",");

                            for(int j=0;j<split.length;j++){
                                paiKeInfo.paths.add(split[j]);
                            }

                        }



                        list.add(paiKeInfo);
                    }
                    if(tujianadapter==null){
                        tujianadapter=new Tujianadapter(getActivity(),list,TuijianFragment.this);
                        tui_recycler.setAdapter(tujianadapter);
                    }else{

                        tujianadapter.notifyDataSetChanged();
                    }

                }

            }else if(GlobalParams.GZ.equals(action)){

                list.clear();
                pageNo=1;
                if(NetUtil.checkNet(getActivity())){
                    networklist();
                }else{
                    Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                }

            }

        }
    };

    private void networklist() {

        HttpParams params = new HttpParams();
        params.put("pageNo", pageNo);
        params.put("token", SpUtils.getString(getActivity(),"token",null));
        Log.d("TAG","数据内容"+params.toString());
        OkGo.<String>post(Api.BASE_URL +"app/paike/recommend")
                .tag(this)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        String body = response.body();
                        Log.e("TAG", "拍客关注" + body);
                        JSONObject jsonobj = null;
                        try {
                            jsonobj = new JSONObject(body);
                            int code = jsonobj.getInt("code");
                            JSONArray datas = jsonobj.getJSONArray("datas");
                            List<PaiKeInfo> paiKeInfos = new ArrayList<PaiKeInfo>();
                            List<TuijianTable> tuijan = new ArrayList<TuijianTable>();
                            if (code == 200) {
                                for(int i=0;i<datas.length();i++){

                                    JSONObject jo = datas.getJSONObject(i);
                                    PaiKeInfo paiKeInfo = new PaiKeInfo();
                                    TuijianTable t = new TuijianTable();
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


                                    t.pkid=jo.getString("id");
                                    t.imageUrl=jo.getString("imageUrl");
                                    t.mvUrl=jo.getString("mvUrl");
                                    t.userId=jo.getString("userId");
                                    t.address=jo.getString("address");
                                    t.userName=jo.getString("userName");
                                    t.userLevel=jo.getString("userLevel");
                                    t.isRecommend=jo.getString("isRecommend");
                                    t.pkOperation=jo.getString("pkOperation");
                                    t.createTime=jo.getString("createTime");
                                    t.isDeleted=jo.getString("isDeleted");
                                    t.pkTitle=jo.getString("pkTitle");
                                    t.imagesUrl=jo.getString("imagesUrl");
                                    t.pkContent=jo.getString("pkContent");
                                    t.commentCount=jo.getString("commentCount");
                                    t.dianZanCount=jo.getString("dianZanCount");
                                    t.collectCount=jo.getString("collectCount");
                                    t.relayCount=jo.getString("relayCount");
                                    t.nickName=jo.getString("nickName");
                                    t.pic=jo.getString("pic");
                                    t.isZan=jo.getString("isZan");



                                    paiKeInfo.paths = new ArrayList<String>();
                                    if(paiKeInfo.imagesUrl.length()>0){

                                        JSONArray ja = jo.getJSONArray("paths");
                                        for(int j=0;j<ja.length();j++){

                                            paiKeInfo.paths.add(ja.getString(j));
                                            if(j==0){
                                                t.paths=ja.getString(j);
                                            }else{
                                                t.paths=t.paths+","+ja.getString(j);
                                            }
                                        }
                                    }

                                    paiKeInfos.add(paiKeInfo);
                                    tuijan.add(t);

                                }
                                if(pageNo>1){

                                    if(paiKeInfos.size()<=0){

                                        Toast.makeText(getActivity(),Api.TOAST,Toast.LENGTH_SHORT).show();

                                    }else{
                                        list.addAll(paiKeInfos);

                                        DataSupport.saveAll(tuijan);

                                    }

                                }else{


                                    DataSupport.deleteAll(TuijianTable.class);

                                    list.addAll(paiKeInfos);

//                                    List<TuijianTable> ss= new ArrayList<TuijianTable>();
//
//                                    for(int i=0;i<5;i++){
//                                        TuijianTable t = new TuijianTable();
//
//                                        t.setAddress("1");
//                                        t.setImageUrl("2");
//                                        t.setMvUrl("3");
//                                        t.userId="25";
//                                        t.pkid="8";
//
//                                        t.paths = new String[5];
//                                        t.paths[0]="000000000";
//
//                                        ss.add(t);
//                                    }

                                    DataSupport.saveAll(tuijan);

                                }

                                Log.e("TAG","推荐数据：："+list.size());

                                if(tujianadapter==null){
                                    tujianadapter=new Tujianadapter(getActivity(),list,TuijianFragment.this);
                                    tui_recycler.setAdapter(tujianadapter);
                                }else{

                                    tujianadapter.notifyDataSetChanged();
                                }


                            }else if(code==201){

                                startActivity(new Intent(getActivity(),LoginActivity.class));

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
                                    tujianadapter=new Tujianadapter(getActivity(),list,TuijianFragment.this);
                                    tui_recycler.setAdapter(tujianadapter);
                                }else{

                                    tujianadapter.notifyDataSetChanged();
                                }


                            } else if(code==201){

                                startActivity(new Intent(getActivity(),LoginActivity.class));

                            }else {
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
                                    tujianadapter=new Tujianadapter(getActivity(),list,TuijianFragment.this);
                                    tui_recycler.setAdapter(tujianadapter);
                                }else{

                                    tujianadapter.notifyDataSetChanged();
                                }
                            }else if(code==201){

                                startActivity(new Intent(getActivity(),LoginActivity.class));

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
