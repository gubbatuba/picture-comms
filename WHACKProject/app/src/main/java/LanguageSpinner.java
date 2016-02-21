import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.pet.whackproject.GetLanguages;
import com.example.pet.whackproject.R;

/**
 * Created by pratool on 2/21/16.
 */
public class LanguageSpinner {
    public void setSpinnerElements (String response){
        Spinner dropdown = (Spinner)findViewById(R.id.langaugesSpinner);
//        new ArrayList<String> elements = new ArrayList<String>;
//        ArrayList.add(input);
//        String[] items = new String[]{"1", "2", "three"};
        //String[] test = new String[] {input};

        String[] test = GetLanguages.extractLangs(response);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, test);
        dropdown.setAdapter(adapter);
    }
}
