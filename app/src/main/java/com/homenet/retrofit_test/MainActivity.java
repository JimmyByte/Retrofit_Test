package com.homenet.retrofit_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.buttonPanel);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doRetrofit();
            }
        });
    }

    private void doRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.kuaidi100.com/")
                .addConverterFactory(GsonConverterFactory.create())//可以接收自定义的Gson，当然也可以不传
                .build();

        BlogService service = retrofit.create(BlogService.class);

        Call<Reception> call = service.getBlog();

        //异步
        /*call.enqueue(new Callback<Reception>() {
            @Override
            public void onResponse(Call<Reception> call, Response<Reception> response) {
                Log.e("-----==  ", response.body().getData().size()+"");
            }

            @Override
            public void onFailure(Call<Reception> call, Throwable t) {
                Log.e("----++ ", t.toString());
            }

        });*/

        // 发送网络请求（同步）
        Response<Reception> response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对返回数据进行处理
        System.out.println(response.body().getData().size());
    }
}
