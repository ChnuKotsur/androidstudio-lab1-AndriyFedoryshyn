package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {


    ImageView imageViewFlag ;
    ImageView imageViewBack;
    Resources resources;
    TextView topText;
    TextView AnthemText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] items = getResources().getStringArray(R.array.countries_array);
        Spinner countries = (Spinner) findViewById(R.id.spinnerCountry);
        ArrayAdapter<String> adapter = new CustomSpinnerAdapter(this, R.layout.spinner_item_with_flag, Arrays.asList(items));
        countries.setAdapter(adapter);

    }

    public void onClickSwitchCountry(View view){
        Spinner countries = (Spinner) findViewById(R.id.spinnerCountry);
        imageViewFlag = findViewById(R.id.imageViewFlag);
        imageViewBack = findViewById(R.id.imageViewBackground);
        resources = getResources();
        topText = (TextView) findViewById(R.id.textTop);
        AnthemText = (TextView) findViewById(R.id.textViewAnthem);

        String country = String.valueOf(countries.getSelectedItem());
        topText.setText(country);

        switch (country) {
            case "Djibouti" :
                imageViewFlag.setImageResource(R.drawable.flag_djibouti);
                AnthemText.setText(resources.getString(R.string.hymn_djibouti));
                break;
            case "Saint Vincent":
                imageViewFlag.setImageResource(R.drawable.flag_saint_vincent);
                AnthemText.setText(resources.getString(R.string.hymn_saint_vincent));
                break;
            case "Georgia":
                imageViewFlag.setImageResource(R.drawable.flag_georgia);
                AnthemText.setText(resources.getString(R.string.hymn_georgia));

                break;
            case "Bahrain" :
                imageViewFlag.setImageResource(R.drawable.flag_bahrain);
                AnthemText.setText(resources.getString(R.string.hymn_bahrain));
                break;
            case "Malta" :
                imageViewFlag.setImageResource(R.drawable.flag_malta);
                AnthemText.setText(resources.getString(R.string.hymn_malta));
                break;
            default:
                break;
        }

    }
}
