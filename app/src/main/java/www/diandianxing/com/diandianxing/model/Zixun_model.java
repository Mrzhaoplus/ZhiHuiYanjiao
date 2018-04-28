package www.diandianxing.com.diandianxing.model;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import www.diandianxing.com.diandianxing.ShujuBean.zixun_Bean;
import www.diandianxing.com.diandianxing.interfase.Zixun_modelinterfase;
import www.diandianxing.com.diandianxing.util.MyRetrofit;

/**
 * Created by Mr赵 on 2018/4/18.
 */

public class Zixun_model {
    private final Zixun_modelinterfase home_zixun_modelinterfase;
    public Zixun_model(Zixun_modelinterfase jiekou) {
        home_zixun_modelinterfase =jiekou;
    }
    public void getpath(int type,String token,int typeNo){
        Observable<zixun_Bean> zixun = MyRetrofit.getSerViceAPI().zixun(type,token,typeNo);
        zixun.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<zixun_Bean>() {
                    @Override
                    public void accept(zixun_Bean home_zixun_bean) throws Exception {
                              home_zixun_modelinterfase.getsuccess(home_zixun_bean);



                    }
                });
    }
}
