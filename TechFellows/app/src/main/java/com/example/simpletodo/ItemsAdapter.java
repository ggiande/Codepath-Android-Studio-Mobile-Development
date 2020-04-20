package com.example.simpletodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// Responsible for displaying data from the model into a row in the recycler view
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {
    List<String> items;
    public ItemsAdapter(List<String> items) {
    this.items = items;
    }

    @NonNull
    @Override
    // Responsible for creating each view
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Use layout inflator to inflate a view
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        // wrap it inside a view holder and return it
        return new ViewHolder(todoView);
    }

    // Responsible for taking data at a particular position and putting it into a view holder
    // Responsible for binding data to a particular vie holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Grab the item at the position
        String item = items.get(position);
        // Bind the item into the specified view holder
        holder.bind(item);
    }

    @Override
    // Simply the number of items available in the data
    // Tells the recycler view how many items are on the list
    public int getItemCount() {
//        return 0;
        return items.size();
    }

    // Container to provide easy access to views that represent each row of the list
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }
        // Update the view inside of the viewholder with the data string item
        public void bind(String item) {
            tvItem.setText(item);
        }
    }
}
