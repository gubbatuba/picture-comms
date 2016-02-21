package com.example.pet.whackproject;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.util.HashMap;
import io.indico.Indico;
import io.indico.network.IndicoCallback;
import io.indico.results.IndicoResult;
import io.indico.utils.IndicoException;


public class MainActivity extends AppCompatActivity {
    private static String topResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Indico.init(this, getString(R.string.indico_api_key), null);
        setContentView(R.layout.activity_main);
        MainActivity mainA = new MainActivity();
        GetLanguages languages = new GetLanguages(mainA);
//        String response = langauages.get("http://www.roundsapp.com/post", json);

        try {
            languages.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

        setContentView(R.layout.activity_main);
        //setSpinnerElements("English");
        try {
            languages.run();
        } catch (Exception e) {
            e.printStackTrace();
        }


//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

       //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /* called when the user clicks the take a picture button */
    public void takePic (View v){

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, TAKE_PICTURE);
    }

//    public void setSpinnerElements (String response){
//        Spinner dropdown = (Spinner)findViewById(R.id.langaugesSpinner);
////        new ArrayList<String> elements = new ArrayList<String>;
////        ArrayList.add(input);
////        String[] items = new String[]{"1", "2", "three"};
//        //String[] test = new String[] {input};

        String[] test = GetLanguages.extractLangs(response);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, test);
        dropdown.setAdapter(adapter);
    }

    // helper method
    public void showResults(String result) {
        TextView text = (TextView) findViewById(R.id.TranslatedResult);
        text.setText(result);
    }


    /* utilizing the indico API for image referencing */

    private static final int TAKE_PICTURE = 1;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PICTURE:
                if (resultCode == Activity.RESULT_OK) {
//                    Uri selectedImage = imageUri;
//                    getContentResolver().notifyChange(selectedImage, null);
//                    ImageView imageView = (ImageView) findViewById(R.id.ImageView);
//                    ContentResolver cr = getContentResolver();
//                    Bitmap bitmap;
                    try {
//                        bitmap = android.provider.MediaStore.Images.Media
//                                .getBitmap(cr, selectedImage);
//                        Log.e("Dafuq", imageUri.toString());
                        Indico.imageRecognition.predict(data.getData(), new HashMap<String, Object>(){{
                            put("top_n",1);
                        }
                        }, new IndicoCallback<IndicoResult>() {

                            @Override
                            public void handle(IndicoResult result) throws IndicoException {
                                Toast.makeText(MainActivity.this, result.getImageRecognition().toString(), Toast.LENGTH_LONG).show();
                                for (String key :result.getImageRecognition().keySet())

                                topResult = result.getImageRecognition().keySet().toString();
                                showResults(topResult);
                                Log.i("resultKeys", topResult);

                            }
                        });
////                        Toast.makeText(this, selectedImage.toString(),
//                                Toast.LENGTH_LONG).show();
                    } catch (IndicoException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
//                    catch (Exception e) {
//                        Toast.makeText(this, "Failed to load", Toast.LENGTH_SHORT)
//                                .show();
//                        Log.e("Camera", e.toString());
//                    }
                }
        }
    }






}
