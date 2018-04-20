package www.diandianxing.com.diandianxing.presenter;

import www.diandianxing.com.diandianxing.ShujuBean.LuKuang_Bean;
import www.diandianxing.com.diandianxing.interfase.Lukuang_model_interfase;
import www.diandianxing.com.diandianxing.interfase.Lukuang_presenter_interfase;
import www.diandianxing.com.diandianxing.model.Lukuang_model;

/**
 * Created by Mr赵 on 2018/4/19.
 */

public class Lukuang_presenter implements Lukuang_model_interfase {

    private final Lukuang_model lukuang_model;
    private  Lukuang_presenter_interfase jiekou;

    public Lukuang_presenter(Lukuang_presenter_interfase jiekou) {
        this.jiekou=jiekou;
        lukuang_model = new Lukuang_model(this);

    }
    public void getpath(int type,String token){
   lukuang_model.getpath(type,token);
    }
    public void getkong(){
       if(jiekou!=null){
           jiekou=null;
       }
    }

    @Override
    public void getsuccess(LuKuang_Bean luKuang_bean) {
        jiekou.getsuccess(luKuang_bean);
    }
}
