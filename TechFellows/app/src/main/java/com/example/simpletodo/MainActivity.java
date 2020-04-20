package com.example.simpletodo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    List<String> items;

    Button btnAdd;
    EditText etItem;
    RecyclerView rvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Views as reference
        btnAdd = findViewById(R.id.btnAdd);
        etItem = findViewById(R.id.etItem);
        rvItems = findViewById(R.id.rvitems);

//        etItem.setText("Im doing this from the java file.");

        items = new ArrayList<>();
        items.add("Buy Milk");
        items.add("Go to the gym");
        items.add("Have Fun!");

        final ItemsAdapter itemsAdapter = new ItemsAdapter(items);
        rvItems.setAdapter(itemsAdapter);
        rvItems.setLayoutManager(new LinearLayoutManager(this));

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todoItem = etItem.getText().toString();
                // Add item to the Model
                items.add(todoItem);
                // Notify adapter that an item is inserted
                itemsAdapter.notifyItemInserted(items.size()-1);
                // Clear the edit text once submitted
                etItem.setText("");
                // Instead of a toat, I decided to use a snackbar
                Toast.makeText(getApplicationContext(), "Item was added", Toast.LENGTH_SHORT).show();
            }
        });


    }
}