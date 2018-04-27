package www.diandianxing.com.diandianxing.model;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import www.diandianxing.com.diandianxing.ShujuBean.DianzanAndFenxiang_Bean;
import www.diandianxing.com.diandianxing.ShujuBean.QuxiaozanAndFenxiang_Bean;
import www.diandianxing.com.diandianxing.interfase.Dianzan_model_interfase;
import www.diandianxing.com.diandianxing.interfase.Quxiaozan_model_interfase;
import www.diandianxing.com.diandianxing.util.MyRetrofit;

/**
 * Created by Mr赵 on 2018/4/20.
 */

public class Quxiaozan_model {
    private final Quxiaozan_model_interfase jiekou;

    public Quxiaozan_model(Quxiaozan_model_interfase jiekou ) {
        this.jiekou=jiekou;

    }
    public void setpath(String tohen,int objId,int obj_type,int operation_type){

        Observable<QuxiaozanAndFenxiang_Bean> quxiaodianzan = MyRetrofit.getSerViceAPI().quxiaodianzan(tohen, objId, obj_type, operation_type);
        quxiaodianzan.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<QuxiaozanAndFenxiang_Bean>() {
                    @Override
                    public void accept(QuxiaozanAndFenxiang_Bean quxiao) throws Exception {
                        jiekou.setsuccess(quxiao);
                    }
                });
    }




}
