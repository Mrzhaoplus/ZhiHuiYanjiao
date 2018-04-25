package www.diandianxing.com.diandianxing.presenter;

import www.diandianxing.com.diandianxing.ShujuBean.GZ_person_Bean;
import www.diandianxing.com.diandianxing.interfase.GZ_person_model_interfase;
import www.diandianxing.com.diandianxing.interfase.GZ_person_presenter_interfase;
import www.diandianxing.com.diandianxing.model.GZ_person_model;

/**
 * Created by Mr赵 on 2018/4/25.
 */

public class GZ_person_presenter implements GZ_person_model_interfase {

    private final GZ_person_model gz_person_model;
    private  GZ_person_presenter_interfase jiekou;

    public GZ_person_presenter(GZ_person_presenter_interfase jiekou) {
        this.jiekou=jiekou;
        gz_person_model = new GZ_person_model(this);
    }
    public void getpath(int pageNo,String uid){
        gz_person_model.getpath(pageNo,uid);
    }
    public void getkong(){
      if(jiekou!=null){
          jiekou=null;
      }
    }

    @Override
    public void getsuccess(GZ_person_Bean guanzhu) {
        jiekou.getsuccess(guanzhu);
    }
}
