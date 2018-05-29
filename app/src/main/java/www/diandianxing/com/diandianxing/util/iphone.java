package www.diandianxing.com.diandianxing.util;

import android.R.string;
import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;

public class iphone {

	private Context mContext;
	
	private JSToolListener jsToolListener;
	
	public iphone(Context mContext, JSToolListener jsToolListener){
		this.mContext=mContext;
		this.jsToolListener=jsToolListener;
	}
	//js调用JAVA方法
	@JavascriptInterface
	public void insertRow(){
		jsToolListener.OperationRelevant("insertRow", null, null);

	}

	//js调用JAVA方法
	@JavascriptInterface
	public void share(String title){
		jsToolListener.OperationRelevant("share", title, null);
	}


	@JavascriptInterface
	public String getInterface(){
		return "iphone";
	}
}
