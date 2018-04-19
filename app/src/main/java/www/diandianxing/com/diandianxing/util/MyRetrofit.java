package www.diandianxing.com.diandianxing.util;

import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mr赵 on 2018/4/18.
 */

public class MyRetrofit {
    public static OkHttpClient okHttpClient;
    public static SerViceAPI serViceAPI;
    /**
     * 优先执行
     */
    static {
        getOkHttpClient();
    }
    public static OkHttpClient getOkHttpClient(){
        if(okHttpClient==null){
            synchronized (OkHttpClient.class){
                if(okHttpClient==null){
                    okHttpClient=new OkHttpClient.Builder()
                            .connectTimeout(15, TimeUnit.SECONDS)
                            .writeTimeout(15, TimeUnit.SECONDS)
                            .readTimeout(15, TimeUnit.SECONDS)
                            //  .addInterceptor(new CommonParamsInterceptor())
                            .build();
                }
            }
        }
        return okHttpClient;
    }

    public static SerViceAPI getSerViceAPI(){


        if(serViceAPI==null){
            synchronized (OkHttpClient.class){
                if(serViceAPI==null){
                    serViceAPI=onCreate(SerViceAPI.class,Api.BASE_URL);
                }
            }
        }
        return serViceAPI;
    }

    //传值--
    public static <T> T onCreate(Class<T>tClass,String url){
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        return retrofit.create(tClass);
    }

    /**
     * 公共参数拦截器
     */
    private static class CommonParamsInterceptor implements Interceptor {

        //拦截的方法
        @Override
        public Response intercept(Chain chain) throws IOException {

            //获取到请求
            Request request = chain.request();
            //获取请求的方式
            String method = request.method();
            //获取请求的路径
            String oldUrl = request.url().toString();

            Log.e("---+++++++拦截器",request.url()+"---"+request.method()+"--"+request.header("User-agent"));

            //要添加的公共参数...map
            Map<String,String> map = new HashMap<>();
            map.put("source","android");

            if ("GET".equals(method)){
                Log.d("method", "intercept: "+"get");
                // 1.http://www.baoidu.com/login                --------？ key=value&key=value
                // 2.http://www.baoidu.com/login?               --------- key=value&key=value
                // 3.http://www.baoidu.com/login?mobile=11111    -----&key=value&key=value

                StringBuilder stringBuilder = new StringBuilder();//创建一个stringBuilder

                stringBuilder.append(oldUrl);

                if (oldUrl.contains("?")){
                    //?在最后面....2类型
                    if (oldUrl.indexOf("?") == oldUrl.length()-1){

                    }else {
                        //3类型...拼接上&
                        stringBuilder.append("&");
                    }
                }else {
                    //不包含? 属于1类型,,,先拼接上?号
                    stringBuilder.append("?");
                }

                //添加公共参数....
                for (Map.Entry<String,String> entry: map.entrySet()) {
                    //拼接
                    stringBuilder.append(entry.getKey())
                            .append("=")
                            .append(entry.getValue())
                            .append("&");
                }

                //删掉最后一个&符号
                if (stringBuilder.indexOf("&") != -1){
                    stringBuilder.deleteCharAt(stringBuilder.lastIndexOf("&"));
                }

                String newUrl = stringBuilder.toString();//新的路径

                //拿着新的路径重新构建请求
                request = request.newBuilder()
                        .url(newUrl)
                        .build();


            }else if ("POST".equals(method)){
                Log.d("method", "intercept: "+"post");
                //先获取到老的请求的实体内容
                RequestBody oldRequestBody = request.body();//....之前的请求参数,,,需要放到新的请求实体内容中去

                //如果请求调用的是上面doPost方法
                if (oldRequestBody instanceof FormBody){
                    FormBody oldBody = (FormBody) oldRequestBody;

                    //构建一个新的请求实体内容
                    FormBody.Builder builder = new FormBody.Builder();
                    //1.添加老的参数
                    for (int i=0;i<oldBody.size();i++){
                        Log.d("old"+i, "intercept: "+oldBody.name(i)+oldBody.value(i));
                        builder.add(oldBody.name(i),oldBody.value(i));
                    }
                    //2.添加公共参数
                    for (Map.Entry<String,String> entry:map.entrySet()) {
                        Log.d("common", "intercept: "+entry.getKey()+entry.getValue());
                        builder.add(entry.getKey(),entry.getValue());
                    }

                    FormBody newBody = builder.build();//新的请求实体内容

                    //构建一个新的请求
                    request = request.newBuilder()
                            .url(oldUrl)
                            .post(newBody)
                            .build();
                }


            }


            Response response = chain.proceed(request);

            return response;
        }
    }



}

