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
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.io.IOException;



public class GetLanguages {
    MainActivity mainAct;
    String receivedResponse;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
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

    public String[] run() throws Exception {
        Request request = new Request.Builder()
                .url("https://www.googleapis.com/language/translate/v2/languages?key=AIzaSyDfaPuvxOjHzqowgruZ53m-jBUpAO7c3hg")
                .build();
        Response response = client.newCall(request).execute();
        String[] result = extractLangs(response.toString());
        return result;
    }




//
//    public void run() throws Exception {
//        Request request = new Request.Builder()
//                .url("https://www.googleapis.com/language/translate/v2/languages?key=AIzaSyDfaPuvxOjHzqowgruZ53m-jBUpAO7c3hg")
//                .build();
//        client.newCall(request).enqueue(new Callback() {
//            @Override public void onFailure(Request request, IOException throwable) {
//                throwable.printStackTrace();
//            }
//
//            @Override public void onResponse(Response response) throws IOException {
//                if (!response.isSuccessful())
//                    throw new IOException("Unexpected code " + response);
//                Log.i("responseBody", response.body().string());
//                receivedResponse = response.body().toString();
//                mainAct.setSpinnerElements(receivedResponse);
//
//            }
//
//
//        });




    public static String[] extractLangs(String response) {
        String[] lang_names = new String[1];
        JSONObject jsonRootObject = new JSONObject();
        try {
            try {
                jsonRootObject = new JSONObject(response);
            } catch (org.json.JSONException r) {
                r.printStackTrace();
            }
            JSONObject dataArray = jsonRootObject.optJSONObject("data");
            JSONArray langs = dataArray.optJSONArray("languages");
            lang_names = new String[langs.length()];
            for (int i = 0; i < langs.length(); i++) {
                String current_lang = langs.getJSONObject(i).optString("language");
                lang_names[i] = current_lang;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return lang_names;
    }

}