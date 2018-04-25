package www.diandianxing.com.diandianxing.model;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import www.diandianxing.com.diandianxing.ShujuBean.GZ_person_Bean;
import www.diandianxing.com.diandianxing.interfase.GZ_person_model_interfase;
import www.diandianxing.com.diandianxing.util.MyRetrofit;

/**
 * Created by Mr赵 on 2018/4/25.
 */

public class GZ_person_model {
    private final GZ_person_model_interfase jiekou;

    public GZ_person_model(GZ_person_model_interfase jiekou) {
        this.jiekou=jiekou;

    }
    public void getpath(int pageNo,String uid){
        Observable<GZ_person_Bean> gz_person_beanObservable = MyRetrofit.getSerViceAPI().gz_person(pageNo, uid);
        gz_person_beanObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GZ_person_Bean>() {
                    @Override
                    public void accept(GZ_person_Bean gz_person_bean) throws Exception {
                           jiekou.getsuccess(gz_person_bean);
                    }
                });
    }
}
