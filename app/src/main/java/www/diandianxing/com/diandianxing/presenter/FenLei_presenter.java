package www.diandianxing.com.diandianxing.presenter;

import www.diandianxing.com.diandianxing.ShujuBean.Fenlei_Bean;
import www.diandianxing.com.diandianxing.interfase.Fenlei_model_interfase;
import www.diandianxing.com.diandianxing.interfase.Fenlei_presenter_interfase;
import www.diandianxing.com.diandianxing.model.FenLei_model;

/**
 * Created by Mr赵 on 2018/4/23.
 */

public class FenLei_presenter implements Fenlei_presenter_interfase {

    private  FenLei_model fenLei_model;
    private  Fenlei_model_interfase jiekou;

    public FenLei_presenter(Fenlei_model_interfase jiekou) {
        this.jiekou=jiekou;
        fenLei_model = new FenLei_model(this);
    }
    public void getpath(String token,int postTypeId,int page){
        fenLei_model.getpath(token,postTypeId,page);
    }
    public void getkong(){
        if(jiekou!=null){
            jiekou=null;
        }
    }

    @Override
    public void getsuccess(Fenlei_Bean fenlei_bean) {
           jiekou.getsuccess(fenlei_bean);
    }
}
