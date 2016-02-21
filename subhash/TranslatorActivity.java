package com.example.sgubba.camera2indico;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.sairamkrishna.myapplication.R;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class TranslatorActivity extends Activity {

    private final OkHttpClient client = new OkHttpClient();

    public void run() throws Exception {
        Request request = new Request.Builder()
                .url("https://www.googleapis.com/language/translate/v2?key=AIzaSyDfaPuvxOjHzqowgruZ53m-jBUpAO7c3hg&source=en&target="+"de"+"&q="+"Hello")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Request request, IOException throwable) {
                throwable.printStackTrace();
            }

            @Override public void onResponse(Response response) throws IOException {
                if (!response.isSuccessful())
                    throw new IOException("Unexpected code " + response);
                Log.i("responseBody", response.body().string());
            }
        });
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView output = (TextView) findViewById(R.id.textView1);
        String strJson= TranslatorActivity.run();
        String data = "";
        try {
            JSONObject jsonRootObject = new JSONObject(strJson);

            //Get the instance of JSONArray that contains JSONObjects
            JSONObject jsonObject1 = jsonRootObject.optJSONObject("data");
            JSONArray jsonArray = jsonObject1.optJSONArray("translations");

            //Iterate the jsonArray and print the info of JSONObjects
            for(int i=0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

//                int id = Integer.parseInt(jsonObject.optString("id").toString());
                String name = jsonObject.optString("translatedText").toString();
//                float salary = Float.parseFloat(jsonObject.optString("salary").toString());

                data += name;
            }
            output.setText(data);
        } catch (JSONException e) {e.printStackTrace();}
    }
}