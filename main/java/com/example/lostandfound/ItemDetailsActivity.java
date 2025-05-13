package com.example.lostandfound;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ItemDetailsActivity extends AppCompatActivity {
    TextView tvDetails;
    Button btnRemove;
    DBHelper dbHelper;
    int itemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        tvDetails = findViewById(R.id.tvDetails);
        btnRemove = findViewById(R.id.btnRemove);
        dbHelper = new DBHelper(this);

        itemId = getIntent().getIntExtra("itemId", -1);

        String details = dbHelper.getItemDetails(itemId);
        tvDetails.setText(details);

        btnRemove.setOnClickListener(v -> {
            dbHelper.deleteItem(itemId);  // itemId is passed via Intent
            Toast.makeText(this, "Item removed", Toast.LENGTH_SHORT).show();
            finish(); // This returns to ShowItemsActivity
        });
    }
}
