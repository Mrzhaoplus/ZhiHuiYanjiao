package www.diandianxing.com.diandianxing.fragment.paikefragment;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
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

import www.diandianxing.com.diandianxing.adapter.Masteradapter;
import www.diandianxing.com.diandianxing.adapter.Tujianadapter;
import www.diandianxing.com.diandianxing.base.BaseFragment;
import www.diandianxing.com.diandianxing.bean.PaiKeDRInfo;
import www.diandianxing.com.diandianxing.bean.PaiKeInfo;
import www.diandianxing.com.diandianxing.util.Api;
import www.diandianxing.com.diandianxing.util.DividerItemDecoration;
import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.util.NetUtil;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class DarenFragment extends BaseFragment {

    private RecyclerView daren_recycler;
    private SpringView spring_view;
    private int pageNo=1;
    private Masteradapter masteradapter;
    private List<PaiKeDRInfo> list = new ArrayList<>();
    @Override
    protected int setContentView() {
        return R.layout.fragment_daren;
    }

    @Override
    protected void lazyLoad() {
        View contentView = getContentView();
        daren_recycler = contentView.findViewById(R.id.daren_recycler);
        spring_view = contentView.findViewById(R.id.spring_view);
        if(NetUtil.checkNet(getActivity())){
            networklist();
        }else{
            Toast.makeText(getActivity(), "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
        }
        daren_recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        daren_recycler.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        spring_view.setType(SpringView.Type.FOLLOW);
        daren_recycler.setNestedScrollingEnabled(false);
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
        params.put("token", Api.token);
        Log.d("TAG","数据内容"+params.toString());
        OkGo.<String>post(Api.BASE_URL +"app/paike/paikedrb")
                .tag(this)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        String body = response.body();
                        Log.d("TAG", "拍客达人" + body);
                        JSONObject jsonobj = null;
                        try {
                            jsonobj = new JSONObject(body);
                            int code = jsonobj.getInt("code");
                            JSONArray datas = jsonobj.getJSONArray("datas");
                            List<PaiKeDRInfo> paiKeDRInfos = new ArrayList<PaiKeDRInfo>();
                            if (code == 200) {
                                for(int i=0;i<datas.length();i++){

                                    JSONObject jo = datas.getJSONObject(i);
                                    PaiKeDRInfo paiKeDRInfo = new PaiKeDRInfo();
                                    paiKeDRInfo.id=jo.getString("id");
                                    paiKeDRInfo.uid=jo.getString("uid");
                                    paiKeDRInfo.pic=jo.getString("pic");
                                    paiKeDRInfo.wechat=jo.getString("wechat");
                                    paiKeDRInfo.tencentQq=jo.getString("tencentQq");
                                    paiKeDRInfo.microblog=jo.getString("microblog");
                                    paiKeDRInfo.wechatName=jo.getString("wechatName");
                                    paiKeDRInfo.qqName=jo.getString("qqName");
                                    paiKeDRInfo.sinaName=jo.getString("sinaName");
                                    paiKeDRInfo.uname=jo.getString("uname");
                                    paiKeDRInfo.identPic=jo.getString("identPic");
                                    paiKeDRInfo.identNum=jo.getString("identNum");
                                    paiKeDRInfo.nickName=jo.getString("nickName");
                                    paiKeDRInfo.sort=jo.getString("sort");
                                    paiKeDRInfo.zan=jo.getString("zan");
                                    paiKeDRInfo.userLevel=jo.getString("userLevel");

                                    paiKeDRInfos.add(paiKeDRInfo);


                                }
                                if(pageNo>1){

                                    if(paiKeDRInfos.size()<=0){

                                        Toast.makeText(getActivity(),Api.TOAST,Toast.LENGTH_SHORT).show();

                                    }else{
                                        list.addAll(paiKeDRInfos);
                                    }

                                }else{

                                    list.addAll(paiKeDRInfos);

                                }
                                if(masteradapter==null){
                                    masteradapter =new Masteradapter(getActivity(),list);
                                    daren_recycler.setAdapter(masteradapter);
                                }else{
                                    masteradapter.notifyDataSetChanged();
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
