package www.diandianxing.com.diandianxing.presenter;

import www.diandianxing.com.diandianxing.ShujuBean.Fensi_Bean;
import www.diandianxing.com.diandianxing.interfase.FenSi_model_interfase;
import www.diandianxing.com.diandianxing.interfase.FenSi_presenter_interfase;
import www.diandianxing.com.diandianxing.model.Fensi_model;

/**
 * Created by Mr赵 on 2018/4/25.
 */

public class Fensi_presenter implements FenSi_model_interfase {

    private final Fensi_model fensi_model;
    private  FenSi_presenter_interfase jiekou;

    public Fensi_presenter(FenSi_presenter_interfase jiekou) {
        this.jiekou=jiekou;
        fensi_model = new Fensi_model(this);
    }
    public void getpath(int pageNo,String uid){
        fensi_model.getpath(pageNo,uid);
    }
    public void getkong(){
       if(jiekou!=null){
           jiekou=null;
       }
    }

    @Override
    public void getsuccess(Fensi_Bean guanzhu) {
        jiekou.getsuccess(guanzhu);
    }
}
