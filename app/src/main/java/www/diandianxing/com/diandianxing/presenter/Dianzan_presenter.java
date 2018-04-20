package www.diandianxing.com.diandianxing.presenter;

import www.diandianxing.com.diandianxing.ShujuBean.DianzanAndFenxiang_Bean;
import www.diandianxing.com.diandianxing.interfase.Dianzan_model_interfase;
import www.diandianxing.com.diandianxing.interfase.Dianzan_presenter_interfase;
import www.diandianxing.com.diandianxing.model.Dianzan_model;

/**
 * Created by Mr赵 on 2018/4/20.
 */

public class Dianzan_presenter implements Dianzan_model_interfase {

    private final Dianzan_model dianzan_model;
    private  Dianzan_presenter_interfase jiekou;

    public Dianzan_presenter(Dianzan_presenter_interfase jiekou) {
        this.jiekou=jiekou;
        dianzan_model = new Dianzan_model(this);
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
    public void setsuccess(DianzanAndFenxiang_Bean zan) {
          jiekou.setsuccess(zan);
    }
}
