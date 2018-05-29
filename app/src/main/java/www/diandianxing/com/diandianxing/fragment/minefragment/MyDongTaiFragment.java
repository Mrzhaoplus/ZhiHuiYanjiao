package www.diandianxing.com.diandianxing.fragment.minefragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.widget.SpringView;
import com.luck.picture.lib.decoration.RecycleViewDivider;
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
import www.diandianxing.com.diandianxing.VideoActivity;
import www.diandianxing.com.diandianxing.adapter.DongTaiAdapter;
import www.diandianxing.com.diandianxing.adapter.Tujianadapter;
import www.diandianxing.com.diandianxing.base.BaseFragment;
import www.diandianxing.com.diandianxing.bean.PaiKeInfo;
import www.diandianxing.com.diandianxing.fragment.paikefragment.GuanzhuFragment;
import www.diandianxing.com.diandianxing.util.Api;
import www.diandianxing.com.diandianxing.util.NetUtil;
import www.diandianxing.com.diandianxing.util.PaiKeZZListener;
import www.diandianxing.com.diandianxing.util.SpUtils;

/**
 * Created by Administrator on 2018/5/5.
 */

public class MyDongTaiFragment extends BaseFragment implements PaiKeZZListener{

    private RecyclerView recy_card;
    private SpringView sv_tz;
    private int pageNo=1;
    private List<PaiKeInfo> list = new ArrayList<>();
    private DongTaiAdapter tagAdapter;
    private String uid;
    RecycleViewDivider recycleViewDivider;
    @Override
    protected int setContentView() {
        return R.layout.fragment_my_tzdt;
    }

    @Override
    protected void lazyLoad() {
        View contentView = getContentView();
        recy_card=contentView.findViewById(R.id.recy_card);
        sv_tz=contentView.findViewById(R.id.sv_tz);
        Bundle arguments = getArguments();
        uid = arguments.getString("uid");
        recy_card.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recycleViewDivider = new RecycleViewDivider(getActivity(), GridLayoutManager.HORIZONTAL, 5, getResources().getColor(R.color.transparent));
        recy_card.addItemDecoration(recycleViewDivider);
//                recy_card.addItemDecoration(new GridDivider(MydynamicActivity.this, 20, this.getResources().getColor(R.color.white)));
        recy_card.setNestedScrollingEnabled(false);
        finishFreshAndLoad();

    }


    //下拉刷新
    private void initRefresh() {
        //DefaultHeader/Footer是SpringView已经实现的默认头/尾之一
        //更多还有MeituanHeader、AliHeader、RotationHeader等如上图7种
        sv_tz.setType(SpringView.Type.FOLLOW);
        sv_tz.setFooter(new DefaultFooter(getActivity()));
        sv_tz.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
//                Toast.makeText(mContext,"下拉刷新中",Toast.LENGTH_SHORT).show();
                // list.clear();
                // 网络请求;
                // mStarFragmentPresenter.queryData();
                //一分钟之后关闭刷新的方法

                list.clear();
                pageNo=1;
                if(NetUtil.checkNet(getActivity())){

                    finishFreshAndLoad();
                }else{
                    Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onLoadmore() {
//                Toast.makeText(mContext,"玩命加载中...",Toast.LENGTH_SHORT).show();
                pageNo++;
                if(NetUtil.checkNet(getActivity())){
                    finishFreshAndLoad();
                }else{
                    Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                }
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

                    networkPaiKe();

                sv_tz.onFinishFreshAndLoad();
            }
        }, 0);
    }


    private void networkPaiKe() {
        HttpParams params = new HttpParams();

        params.put("userId", uid);
        params.put("pageNo", pageNo);
        Log.d("TAG","数据内容"+params.toString());
        OkGo.<String>post(Api.BASE_URL +"app/user/paikebyuser")
                .tag(this)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        Log.d("TAG", "拍客成功：" + body);
                        JSONObject jsonobj = null;
                        try {
                            jsonobj = new JSONObject(body);
                            int code = jsonobj.getInt("code");
                            JSONArray datas = jsonobj.getJSONArray("datas");
                            List<PaiKeInfo> paiKeInfos = new ArrayList<PaiKeInfo>();
                            if (code == 200) {
                                for (int i = 0; i < datas.length(); i++) {

                                    JSONObject jo = datas.getJSONObject(i);
                                    PaiKeInfo paiKeInfo = new PaiKeInfo();
                                    paiKeInfo.pkid = jo.getString("id");
                                    paiKeInfo.imageUrl = jo.getString("imageUrl");
                                    paiKeInfo.mvUrl = jo.getString("mvUrl");
                                    paiKeInfo.userId = jo.getString("userId");
                                    paiKeInfo.address = jo.getString("address");
                                    paiKeInfo.userName = jo.getString("userName");
                                    paiKeInfo.userLevel = jo.getString("userLevel");
                                    paiKeInfo.isRecommend = jo.getString("isRecommend");
                                    paiKeInfo.pkOperation = jo.getString("pkOperation");
//                                    paiKeInfo.createTime = jo.getString("createTime");
                                    paiKeInfo.isDeleted = jo.getString("isDeleted");
                                    paiKeInfo.pkTitle = jo.getString("pkTitle");
                                    paiKeInfo.imagesUrl = jo.getString("imagesUrl");
                                    paiKeInfo.pkContent = jo.getString("pkContent");
                                    paiKeInfo.commentCount = jo.getString("commentCount");
                                    paiKeInfo.dianZanCount = jo.getString("dianZanCount");
                                    paiKeInfo.collectCount = jo.getString("collectCount");
                                    paiKeInfo.relayCount = jo.getString("relayCount");
                                    paiKeInfo.nickName = jo.getString("nickName");
                                    paiKeInfo.pic = jo.getString("pic");
                                    paiKeInfo.nickName = jo.getString("nickName");
                                    paiKeInfo.isZan=jo.getString("isZan");

//                                    paiKeInfo.paths = new ArrayList<String>();
//                                    if (paiKeInfo.imagesUrl.length() > 0) {
//
//                                        JSONArray ja = jo.getJSONArray("paths");
//                                        for (int j = 0; j < ja.length(); j++) {
//
//                                            paiKeInfo.paths.add(ja.getString(j));
//
//                                        }
//
//                                    }

                                    paiKeInfos.add(paiKeInfo);


                                }
                                if (pageNo > 1) {

                                    if (paiKeInfos.size() <= 0) {

                                        Toast.makeText(getActivity(), Api.TOAST, Toast.LENGTH_SHORT).show();

                                    } else {
                                        list.addAll(paiKeInfos);
                                    }
                                } else {
                                    list.clear();
                                    list.addAll(paiKeInfos);

                                }

                                Log.e("TAG", "推荐数据：：" + list.size());

                                if (tagAdapter == null) {
                                    tagAdapter = new DongTaiAdapter(R.layout.tui_recyitem, list,MyDongTaiFragment.this);

                                    recy_card.setAdapter(tagAdapter);

                                    tagAdapter.setOnItemClickListener(onItemClickListener1);
                                } else {

                                    tagAdapter.notifyDataSetChanged();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("TAG","解析失败了！！！");
                        }
                    }
                });
    }

    private BaseQuickAdapter.OnItemClickListener onItemClickListener1 = new BaseQuickAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            Intent intent = new Intent(getActivity(), VideoActivity.class);
            startActivity(intent);

        }
    };


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

                                list.get(pos).iszan="1";
                                list.get(pos).zannum=Integer.parseInt(list.get(pos).zannum)+1+"";

                                if (tagAdapter == null) {
                                    tagAdapter = new DongTaiAdapter(R.layout.tui_recyitem, list,MyDongTaiFragment.this);

                                    recy_card.setAdapter(tagAdapter);

                                    tagAdapter.setOnItemClickListener(onItemClickListener1);
                                } else {

                                    tagAdapter.notifyDataSetChanged();
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

                                list.get(pos).iszan="0";
                                list.get(pos).zannum=Integer.parseInt(list.get(pos).zannum)-1+"";
                                if (tagAdapter == null) {
                                    tagAdapter = new DongTaiAdapter(R.layout.tui_recyitem, list,MyDongTaiFragment.this);

                                    recy_card.setAdapter(tagAdapter);

                                    tagAdapter.setOnItemClickListener(onItemClickListener1);
                                } else {

                                    tagAdapter.notifyDataSetChanged();
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
