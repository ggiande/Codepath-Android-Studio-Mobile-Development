package com.example.hellogiancarlo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.graphics.Color;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //private final int ORANGE = 0xFFFF3300;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.paintBackground).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //get background id
                View bg = findViewById(R.id.main_Background);// get Any child View
                bg.setBackgroundColor(randomColor());
            }
        });
            // Grab view byt id, and set an on click listener to the id.
            // When on click listener is called, the method will be called.
        findViewById(R.id.textColorButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("OnClickListener", "EditTextView color button clicked. ");
                TextView textElement = (EditText) findViewById(R.id.textView);
                textElement.setTextColor(randomColor()); //this is green color
            }
        });
        findViewById(R.id.buttonForDefaultTextString).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("OnClickListener","String should be set to 'dedicatedButtonString'. ");
                //String stringToUse = getString(R.string.dedicatedButtonString);
                TextView textElement = (EditText) findViewById(R.id.textView);
                textElement.setText(getString(R.string.dedicatedButtonString));
                //dedicatedButtonString
            }
        });

    }

    private int randomColor() {
        int color;
        Random rand = new Random();
        color = Color.argb(255, rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
        return color;
    }

}
