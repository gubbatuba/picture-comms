 /**
 * Created by Pet on 2/20/16.
 */

 package com.example.pet.whackproject;

import android.util.Log;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;



public class GetLanguages {

        public static final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");




        String post(String url, String json) throws IOException {
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }
    private final OkHttpClient client = new OkHttpClient();

    public void run() throws Exception {
        Request request = new Request.Builder()
                .url("https://www.googleapis.com/language/translate/v2/languages?key=AIzaSyDfaPuvxOjHzqowgruZ53m-jBUpAO7c3hg")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Request request, IOException throwable) {
                throwable.printStackTrace();
            }

            @Override public void onResponse(Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);


                Headers responseHeaders = response.headers();

                      Log.i("B4responseBody","fdfgf");
                    Log.i("responseBody", response.body().string());

                Log.i("AfterresponseBody","fdfgf");

                for (int i = 0; i < responseHeaders.size(); i++) {
                    Log.i("responseHeaders",responseHeaders.name(i) + ": " + responseHeaders.value(i));
                }

            }
        });
    }

    }
