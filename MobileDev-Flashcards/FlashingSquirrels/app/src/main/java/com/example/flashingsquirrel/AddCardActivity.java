package com.example.flashingsquirrel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        findViewById(R.id.cancelButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // RESULT_CANCELLED
                Log.d("OnClickListener", "Exited Add Card Activity. ");
            }
        });


        //Add hint and add question are the edit text values.
        findViewById(R.id.savingButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String questionString = ((EditText) findViewById(R.id.AddQuestion)).getText().toString();
                String answerString = ((EditText) findViewById(R.id.AddHint)).getText().toString();
                Intent data = new Intent(); // i want to pass data

                // Here we pass data from addcard activity to any activity that wants to accept out key.
                data.putExtra("question", questionString); // here is the data I want pass
                data.putExtra("answer", answerString);
                setResult(RESULT_OK, data); //  actually send the data
                finish();
                Log.d("OnClickListener", "New Flashcard has been saved. ");
            }
        });



    }
}