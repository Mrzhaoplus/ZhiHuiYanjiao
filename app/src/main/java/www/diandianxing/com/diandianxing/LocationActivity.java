package www.diandianxing.com.diandianxing;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.nearby.NearbyInfo;
import com.amap.api.services.nearby.NearbySearch;
import com.amap.api.services.nearby.NearbySearchFunctionType;
import com.amap.api.services.nearby.NearbySearchResult;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.chad.library.adapter.base.BaseQuickAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import www.diandianxing.com.diandianxing.adapter.LocationAdapter;
import www.diandianxing.com.diandianxing.base.BaseActivity;
import www.diandianxing.com.diandianxing.bean.MsgBus;
import www.diandianxing.com.diandianxing.bean.PoiAddressBean;
import www.diandianxing.com.diandianxing.sousuo.DCSearchActivity;
import www.diandianxing.com.diandianxing.sousuo.SearchAdapter;
import www.diandianxing.com.diandianxing.util.EventMessage;
import www.diandianxing.com.diandianxing.util.MyContants;
import www.diandianxing.com.diandianxing.R;
import www.diandianxing.com.diandianxing.util.SpUtils;
import www.diandianxing.com.diandianxing.util.ToastUtils;

/**
 * Created by Administrator on 2018/4/4.
 */

public class LocationActivity extends BaseActivity implements PoiSearch.OnPoiSearchListener{

    private RecyclerView rv_wz;

    private ImageView iv_ss;


    private ImageView include_back;
    private String keyWord = "";// 要输入的poi搜索关键字
    private PoiResult poiResult; // poi返回的结果
    private int currentPage = 0;// 当前页面，从0开始计数
    private PoiSearch.Query query;// Poi查询条件类
    private PoiSearch poiSearch;// POI搜索
    private ArrayList<PoiAddressBean> data;
    LocationAdapter locationAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyContants.windows(this);
        setContentView(R.layout.activity_location);
        rv_wz= (RecyclerView) findViewById(R.id.rv_wz);
        iv_ss= (ImageView) findViewById(R.id.iv_ss);
        include_back= (ImageView) findViewById(R.id.include_back);
        WZ();
        rv_wz.setLayoutManager(new LinearLayoutManager(LocationActivity.this));
        rv_wz.setNestedScrollingEnabled(false);
        include_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        iv_ss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LocationActivity.this,SearchLocationActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void WZ(){
        currentPage = 0;
        //不输入城市名称有些地方搜索不到
        query = new PoiSearch.Query(keyWord, "", "北京");// 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）


        //这里没有做分页加载了,默认给50条数据
        query.setPageSize(50);// 设置每页最多返回多少条poiitem
        query.setPageNum(currentPage);// 设置查第一页

        poiSearch = new PoiSearch(this, query);
        String lat = SpUtils.getString(this, "la", null);
        String lon = SpUtils.getString(this, "lo", null);
        double v = Double.valueOf(lat).doubleValue();
        double v1 = Double.valueOf(lon).doubleValue();
        poiSearch.setBound(new PoiSearch.SearchBound(new LatLonPoint(v,
                v1), 1000));//设置周边搜索的中心点以及半径
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.searchPOIAsyn();
    }

    @Override
    public void onPoiSearched(PoiResult result, int rCode) {
        if (rCode == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null && result.getQuery() != null) {  // 搜索poi的结果
                if (result.getQuery().equals(query)) {  // 是否是同一条
                    poiResult = result;
                    //自己创建的数据集合
                    data = new ArrayList<PoiAddressBean>();
                    // 取得搜索到的poiitems有多少页
                    List<PoiItem> poiItems = poiResult.getPois();// 取得第一页的poiitem数据，页数从数字0开始
                    List<SuggestionCity> suggestionCities = poiResult
                            .getSearchSuggestionCitys();// 当搜索不到poiitem数据时，会返回含有搜索关键字的城市信息
                    for(PoiItem item : poiItems){
                        //获取经纬度对象
                        LatLonPoint llp = item.getLatLonPoint();
                        double lon = llp.getLongitude();
                        double lat = llp.getLatitude();

                        String title = item.getTitle();
                        String text = item.getSnippet();
                        String provinceName = item.getProvinceName();
                        String cityName = item.getCityName();
                        String adName = item.getAdName();
                        Log.e("TAG","位置数据："+item.getSnippet());

                        data.add(new PoiAddressBean(String.valueOf(lon), String.valueOf(lat), title, text,provinceName,
                                cityName,adName));
                    }
                    locationAdapter  = new LocationAdapter(R.layout.location_item_view, data);
                    rv_wz.setAdapter(locationAdapter);
                    /*
		   listview跳目点击事件
		  */
                    locationAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                            PoiAddressBean poiAddressBean = data.get(position);

                            MsgBus msgBus = new MsgBus();
                            msgBus.msg=poiAddressBean.detailAddress;
                            EventBus.getDefault().postSticky(msgBus);
                            finish();

                        }
                    });
                }
            } else {
            }
        } else {
            ToastUtils.show(LocationActivity.this, rCode,1);
        }

    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }
}
