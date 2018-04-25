package www.diandianxing.com.diandianxing.presenter;

import www.diandianxing.com.diandianxing.ShujuBean.LuKuang_Bean;
import www.diandianxing.com.diandianxing.interfase.Lukuang_model_interfase;
import www.diandianxing.com.diandianxing.interfase.Lukuang_presenter_interfase;

/**
 * Created by Mr赵 on 2018/4/25.
 */

public class Search_lukuang_presenter implements Lukuang_model_interfase {

    private final Search_lukuang_model search_lukuang_model;
    private  Lukuang_presenter_interfase jiekou;

    public Search_lukuang_presenter(Lukuang_presenter_interfase jiekou) {
        this.jiekou=jiekou;
        search_lukuang_model = new Search_lukuang_model(this);
    }
    public void getpath(int pageNo,String token,String searchText){
        search_lukuang_model.getpath(pageNo,token,searchText);
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
