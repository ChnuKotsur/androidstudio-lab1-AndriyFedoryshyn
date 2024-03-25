package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Locale;

public class CreateMessageActivity extends AppCompatActivity {

    ImageView imageViewFlag ;
    ImageView imageViewBack;
    Resources resources;
    TextView topText;
    TextView AnthemText;

    private int seconds = 0;
    private boolean running;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] items = getResources().getStringArray(R.array.countries_array);
        Spinner countries = (Spinner) findViewById(R.id.spinnerCountry);
        ArrayAdapter<String> adapter = new CustomSpinnerAdapter(this, R.layout.spinner_item_with_flag, Arrays.asList(items));
        countries.setAdapter(adapter);

        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }
        runTimer();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
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

    public void onSendMessage(View view) {
        EditText messageView = (EditText) findViewById(R.id.message);
        String messageText = messageView.getText().toString();
        Log.d("Message", "Message sent: " + messageText);
        Intent intent = new Intent(this, ReceiveMessageActivity.class);
        intent.putExtra("message", messageText);
        startActivity(intent);
    }

    public void onShareMessage(View view) {
        EditText messageView = findViewById(R.id.message);
        String messageText = messageView.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, messageText);

        startActivity(intent);
    }


    public void onClickStart(View view) {
        running = true;
    }
    //Stop the stopwatch running when the Stop button is clicked.
    public void onClickStop(View view) {
        super.onStop();
        running = false;
    }
    //Reset the stopwatch when the Reset button is clicked.
    public void onClickReset(View view) {
        running = false;
        seconds = 0;
    }

    private void runTimer() {
        final TextView timeView = (TextView)findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format(Locale.getDefault(),
                        "%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

}
