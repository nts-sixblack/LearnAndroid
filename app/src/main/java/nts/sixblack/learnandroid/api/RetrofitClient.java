package nts.sixblack.learnandroid.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient instance = null;

    private API myAPI;

    private RetrofitClient(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API.BASE_URL)
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
