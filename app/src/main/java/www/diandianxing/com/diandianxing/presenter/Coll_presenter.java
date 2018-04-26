package www.diandianxing.com.diandianxing.presenter;

import www.diandianxing.com.diandianxing.ShujuBean.Coll_Bean;
import www.diandianxing.com.diandianxing.interfase.Coll_model_interfase;
import www.diandianxing.com.diandianxing.interfase.Coll_presenter_interfase;
import www.diandianxing.com.diandianxing.model.Coll_model;

/**
 * Created by Mr赵 on 2018/4/26.
 */

public class Coll_presenter implements Coll_model_interfase {

    private final Coll_model coll_model;
    private  Coll_presenter_interfase jiekou;

    public Coll_presenter(Coll_presenter_interfase jiekou) {
        this.jiekou=jiekou;
        coll_model = new Coll_model(this);
    }
    public void getpath(int page,String token){
        coll_model.getpath(page,token);
    }
    public void getkong(){
        if(jiekou!=null){
            jiekou=null;
        }
    }
    @Override
    public void getsuccess(Coll_Bean coll_bean) {
        jiekou.getsuccess(coll_bean);
    }
}
