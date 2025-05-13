package com.example.lostandfound;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CreateAdvertActivity extends AppCompatActivity {
    EditText name, phone, description, date, location;
    RadioButton rbLost, rbFound;
    Button btnSave;
    DBHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_advert);

        name = findViewById(R.id.etName);
        phone = findViewById(R.id.etPhone);
        description = findViewById(R.id.etDescription);
        date = findViewById(R.id.etDate);
        location = findViewById(R.id.etLocation);
        rbLost = findViewById(R.id.rbLost);
        rbFound = findViewById(R.id.rbFound);
        btnSave = findViewById(R.id.btnSave);

        dbHelper = new DBHelper(this);

        btnSave.setOnClickListener(v -> {
            String type = rbLost.isChecked() ? "Lost" : "Found";
            boolean inserted = dbHelper.insertItem(
                    type,
                    name.getText().toString(),
                    phone.getText().toString(),
                    description.getText().toString(),
                    date.getText().toString(),
                    location.getText().toString()
            );

            if (inserted) {
                Toast.makeText(CreateAdvertActivity.this, "Saved successfully", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(CreateAdvertActivity.this, "Failed to save", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
