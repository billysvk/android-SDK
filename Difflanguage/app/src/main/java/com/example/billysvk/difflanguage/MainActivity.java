package com.example.billysvk.difflanguage;
import android.app.Activity;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.view.View;
import android.view.View.OnClickListener;

import java.util.Locale;

public class MainActivity extends Activity {
    ListView lv1;
    String  chosenlang="";
    int langpos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv1=(ListView)this.findViewById(R.id.listView1);

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //An AdapterView is a view whose children are determined by an Adapter
            //We use OnItemClickListener because of the listview
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //The <?> indicates Generics. Read more at http://java.sun.com/docs/books/tutorial/java/generics/index.html
                chosenlang = (String) lv1.getItemAtPosition(position);
                langpos = position;
                String langpos1 = (String.valueOf(id));
                String langpos2 = (String.valueOf(position));
                Toast.makeText(getApplicationContext(), chosenlang, Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), langpos1, Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), langpos2, Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void updateconfig(String s)
    {

        String languageToLoad  = s; // change language
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        //Locale represents a language/country/variant combination.
        //Locales are used to alter the presentation of information such as numbers
        // or dates to suit the conventions in the region they describe.
        //The language codes are two-letter lowercase ISO language codes (such as "en") as defined by ISO 639-1.
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        Bundle tempBundle = new Bundle();
        onCreate(tempBundle);

    }
    public void changelang(View v)
    {
        switch(langpos) {
            case 0:
                updateconfig("en");
                break;
            case 1:
                updateconfig("de");
                break;
            case 2:
                updateconfig("fr");
                break;
            default:
                break;
        }
    }
}
