package www.diandianxing.com.diandianxing.model;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import www.diandianxing.com.diandianxing.ShujuBean.Home_zixun_Bean;
import www.diandianxing.com.diandianxing.interfase.Home_Zixun_modelinterfase;
import www.diandianxing.com.diandianxing.interfase.Home_Zixun_presenterinterfase;
import www.diandianxing.com.diandianxing.util.MyRetrofit;

/**
 * Created by Mr赵 on 2018/4/18.
 */

public class Home_Zixun_model {


    private final Home_Zixun_modelinterfase home_zixun_modelinterfase;

    public Home_Zixun_model(Home_Zixun_modelinterfase jiekou) {
        home_zixun_modelinterfase =jiekou;
    }
    public void getpath(int type){
        Observable<Home_zixun_Bean> zixun = MyRetrofit.getSerViceAPI().zixun(type);
        zixun.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Home_zixun_Bean>() {
                    @Override
                    public void accept(Home_zixun_Bean home_zixun_bean) throws Exception {
                        home_zixun_modelinterfase.getsuccess(home_zixun_bean);
                    }
                });
    }
}
