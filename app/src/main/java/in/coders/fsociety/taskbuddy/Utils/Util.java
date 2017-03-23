package in.coders.fsociety.taskbuddy.Utils;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Util {
    public  static ApiInterface getRetrofitService(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient.Builder oBuilder = new OkHttpClient.Builder();
        oBuilder.addNetworkInterceptor(loggingInterceptor);
        oBuilder.connectTimeout(15l, TimeUnit.SECONDS);
        oBuilder.readTimeout(15l,TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://taskbuddy.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).
                client(oBuilder.build()).
                build();

        ApiInterface service = retrofit.create(ApiInterface.class);
        return service;
    }

}
