package www.diandianxing.com.diandianxing.presenter;

import www.diandianxing.com.diandianxing.ShujuBean.DianzanAndFenxiang_Bean;
import www.diandianxing.com.diandianxing.ShujuBean.QuxiaozanAndFenxiang_Bean;
import www.diandianxing.com.diandianxing.interfase.Dianzan_model_interfase;
import www.diandianxing.com.diandianxing.interfase.Dianzan_presenter_interfase;
import www.diandianxing.com.diandianxing.interfase.Quxiaozan_model_interfase;
import www.diandianxing.com.diandianxing.interfase.Quxiaozan_presenter_interfase;
import www.diandianxing.com.diandianxing.model.Dianzan_model;
import www.diandianxing.com.diandianxing.model.Quxiaozan_model;

/**
 * Created by Mr赵 on 2018/4/20.
 */

public class Quxiaozan_presenter implements Quxiaozan_model_interfase {

    private  Quxiaozan_presenter_interfase jiekou;
    private final Quxiaozan_model dianzan_model;

    public Quxiaozan_presenter(Quxiaozan_presenter_interfase jiekou) {
        this.jiekou=jiekou;
        dianzan_model = new Quxiaozan_model(this);
    }
    public void setpath(String tohen,int objId,int obj_type,int operation_type){
        dianzan_model.setpath(tohen,objId,obj_type,operation_type);
    }
    public void getkong(){
    if(jiekou!=null){
        jiekou=null;
    }
    }


    @Override
    public void setsuccess(QuxiaozanAndFenxiang_Bean quxiao) {
        jiekou.setsuccess(quxiao);
    }
}
