package com.example.lostandfound;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ShowItemsActivity extends AppCompatActivity {
    ListView listView;
    DBHelper dbHelper;
    ArrayList<String> itemList;
    ArrayList<Integer> idList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_items);

        listView = findViewById(R.id.listView);
        dbHelper = new DBHelper(this);
        itemList = new ArrayList<>();
        idList = new ArrayList<>();

        loadItems();

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(ShowItemsActivity.this, ItemDetailsActivity.class);
            intent.putExtra("itemId", idList.get(i));
            startActivity(intent);
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        loadItems();
    }

    private void loadItems() {
        Cursor cursor = dbHelper.getAllItems();
        itemList.clear();
        idList.clear();

        while (cursor.moveToNext()) {
            idList.add(cursor.getInt(0));
            itemList.add(cursor.getString(1) + ": " + cursor.getString(3));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemList);
        listView.setAdapter(adapter);
    }
}
