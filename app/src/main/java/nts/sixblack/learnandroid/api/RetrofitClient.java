package nts.sixblack.learnandroid.api;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class RetrofitClient {
    private static RetrofitClient instance = null;
    private static String token = TokenResponse.accessToken;

    private API myAPI;

    private RetrofitClient(){
//        OkHttpClient.Builder client = new OkHttpClient.Builder();
//
//        client.addInterceptor(new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//
//                Request request = chain.request();
//                Request.Builder newRequest = request.newBuilder().addHeader("Authorization", "Bearer "+token);
//
//                return chain.proceed(newRequest.build());
//            }
//        }).build();

        Retrofit retrofit = new Retrofit.Builder()
//                .client(client)
                .baseUrl(API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        myAPI = retrofit.create(API.class);
    }



    public static RetrofitClient getInstance() {
        if (instance == null){
            instance = new RetrofitClient();
            return instance;
        } else {
            return instance;
        }
    }

    public API getMyAPI(){
        return myAPI;
    }
}
