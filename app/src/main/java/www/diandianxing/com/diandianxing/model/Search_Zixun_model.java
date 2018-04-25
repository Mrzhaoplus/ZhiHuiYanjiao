package www.diandianxing.com.diandianxing.model;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import www.diandianxing.com.diandianxing.ShujuBean.zixun_Bean;
import www.diandianxing.com.diandianxing.interfase.Seach_Zixun_modelinterfase;
import www.diandianxing.com.diandianxing.interfase.Zixun_modelinterfase;
import www.diandianxing.com.diandianxing.util.MyRetrofit;

/**
 * Created by Mr赵 on 2018/4/25.
 */

public class Search_Zixun_model {
    private final Zixun_modelinterfase jiekou;

    public Search_Zixun_model(Zixun_modelinterfase jiekou) {
        this.jiekou=jiekou;
    }
    public void getpath(int type,String token,int typeNo,String searchtext){
        Observable<zixun_Bean> zixun_beanObservable = MyRetrofit.getSerViceAPI().sear_zixun(type,token,typeNo,searchtext);
        zixun_beanObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<zixun_Bean>() {
                    @Override
                    public void accept(zixun_Bean zixun_bean) throws Exception {
                        jiekou.getsuccess(zixun_bean);
                    }
                });
    }

}
