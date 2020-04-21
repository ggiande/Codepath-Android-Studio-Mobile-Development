package com.example.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
//import android.os.FileUtils;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_ITEM_TEXT = "item_text";
    public static final String KEY_ITEM_POSITION = "item_position";
    public static final int EDIT_TEXT_CODE = 20;

    List<String> items;

    Button btnAdd;
    EditText etItem;
    RecyclerView rvItems;
    ItemsAdapter itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Views as reference
        btnAdd = findViewById(R.id.btnAdd);
        etItem = findViewById(R.id.etItem);
        rvItems = findViewById(R.id.rvitems);
        loadItems();
//        etItem.setText("Im doing this from the java file.");
//         new Empty Array List
//        items = new ArrayList<>();

        ItemsAdapter.OnLongClickListener onLongClickListener = new ItemsAdapter.OnLongClickListener(){
            @Override
            public void onItemLongClicked(int position) {
                // Delete the item from the model
                items.remove(position);
                // Notify the adapter
                itemsAdapter.notifyItemRemoved(position);
                Snackbar.make(findViewById(R.id.btnAdd),"Item successfully removed ", Snackbar.LENGTH_SHORT).show();
                saveItems();
            }
        };

        ItemsAdapter.OnClickListener onClickListener = new ItemsAdapter.OnClickListener() {
            @Override
            public void onItemClicked(int position) {
                Log.d("MainActivity", "Single click at position" + position);
                // Create the new activity
                Intent i = new Intent(MainActivity.this, EditActivity.class);
                // Pass the data being edited
                i.putExtra(KEY_ITEM_TEXT, items.get(position));
                i.putExtra(KEY_ITEM_POSITION, position);
                // Display the activity
                startActivityForResult(i, EDIT_TEXT_CODE);
            }
        };

        itemsAdapter = new ItemsAdapter(items, onLongClickListener, onClickListener);
        rvItems.setAdapter(itemsAdapter);
        rvItems.setLayoutManager(new LinearLayoutManager(this));

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todoItem = etItem.getText().toString();
                // Add item to the Model
                items.add(todoItem);
                // Notify adapter that an item is inserted
                itemsAdapter.notifyItemInserted(items.size() - 1);
                // Clear the edit text once submitted
                etItem.setText("");
                // Instead of a toast, I decided to use a snackbar
                Toast.makeText(getApplicationContext(), "Item was added", Toast.LENGTH_SHORT).show();
//                Snackbar.make(findViewById(R.id.btnAdd),"Item successfully added ", Snackbar.LENGTH_SHORT).show();
                saveItems();
            }
        });


    }

    // handle the result of the edit activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == EDIT_TEXT_CODE) {
            // Retrieve the updated text value
            String itemText = data.getStringExtra(KEY_ITEM_TEXT);
            //Extract the original position of the edited item from the key position
            int position = data.getExtras().getInt(KEY_ITEM_POSITION);
            // update the model at the right position with new item text
            items.set(position, itemText);
            // notify the adapter
            itemsAdapter.notifyItemChanged(position);
            // persist the changes
            saveItems();
//            Snackbar.make(findViewById(R.id.btnSave), "Item successfully updated", Snackbar.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), "Item successfully updated", Toast.LENGTH_SHORT).show();


        } else {
            Log.w("MainActivity", "Unknown call to onActivityResult");
        }
    }

    // Outside of on create
    // returns file of list
    private File getDataFile(){
        return new File(getFilesDir(), "data.txt");
    }
    // This function will load items by reading every line of the data file
    private void loadItems(){
        try {
            items = new ArrayList<>(FileUtils.readLines(getDataFile(), Charset.defaultCharset()));
        } catch (IOException e) {
//            e.printStackTrace();
            Log.e("Main Activity", "Error reading items", e);
            items = new ArrayList<>();

        }
    }
    // This function saves items by writing them into the data file
    private void saveItems(){
        try {
            FileUtils.writeLines(getDataFile(), items);
        } catch (IOException e) {
//            e.printStackTrace();
            Log.e("Main Activity", "Error Writing Items", e);
        }
    }


}