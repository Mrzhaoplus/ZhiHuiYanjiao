package www.diandianxing.com.diandianxing.presenter;

import www.diandianxing.com.diandianxing.interfase.SouLadel_model_interfise;
import www.diandianxing.com.diandianxing.interfase.SouLadel_presenter_interfise;
import www.diandianxing.com.diandianxing.model.SouLabel_bean;
import www.diandianxing.com.diandianxing.model.SouLabel_model;

/**
 * Created by Mr赵 on 2018/4/19.
 */

public class SouLabel_Presenter implements SouLadel_model_interfise {

    private final SouLabel_model souLabel_model;
    private  SouLadel_presenter_interfise jiekou;

    public SouLabel_Presenter(SouLadel_presenter_interfise jiekou) {
        this.jiekou=jiekou;
        souLabel_model = new SouLabel_model(this);
    }
    public void getpath(String token){
        souLabel_model.getpath(token);
    }
    public void getkong(){
          if(jiekou!=null){
              jiekou=null;
          }
    }

    @Override
    public void getsuccess(SouLabel_bean souLabel_bean) {
        jiekou.getsuccess(souLabel_bean);
    }
}
