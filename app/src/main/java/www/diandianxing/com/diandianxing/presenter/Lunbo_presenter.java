package www.diandianxing.com.diandianxing.presenter;

import www.diandianxing.com.diandianxing.ShujuBean.Lunbo_Bean;
import www.diandianxing.com.diandianxing.interfase.LunModel_interfase;
import www.diandianxing.com.diandianxing.interfase.LunPresenter_interfase;
import www.diandianxing.com.diandianxing.model.Lunbo_model;

/**
 * Created by Mr赵 on 2018/4/18.
 */

public class Lunbo_presenter implements LunModel_interfase {

    private  Lunbo_model lunbo_model;
    private  LunPresenter_interfase jiekou;

    public Lunbo_presenter(LunPresenter_interfase jiekou) {
        lunbo_model = new Lunbo_model(this);
        this.jiekou=jiekou;
    }
    public void getString(){
        lunbo_model.getString();
    }
    public void getkong(){
      if(jiekou!=null){
          jiekou=null;
      }
    }

    @Override
    public void getsuccess(Lunbo_Bean lunbo) {
        jiekou.getsuccess(lunbo);
    }
}
