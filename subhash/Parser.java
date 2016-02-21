package com.example.sgubba.camera2indico;
import android.app.Activity;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Parser(Bundle savedInstanceState) {
        super.Parser(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView output = (TextView) findViewById(R.id.textView1);
        String strJson= TranslatorActivity.run();
        String data = Trans;
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
