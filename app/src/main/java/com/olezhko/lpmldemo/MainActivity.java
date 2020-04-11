package com.olezhko.lpmldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent incomingIntent = getIntent();
        String[] userData = incomingIntent.getStringArrayExtra("userData");
        Toast.makeText(this, "email:" + userData[0], Toast.LENGTH_LONG).show();

        List<Cafe> cafes = new ArrayList<>();
        cafes.add(new Cafe("McDonalds", "pr. Svobody, 12", R.drawable.burger));
        cafes.add(new Cafe("Kredens", "pr. Svobody, 14", R.drawable.coffee));
        cafes.add(new Cafe("Urban Coffee", "vul. Bandery, 12", R.drawable.coffee));
        cafes.add(new Cafe("Celentano", "vul. Bandery, 30", R.drawable.pizza));

        ListView cafeListView = findViewById(R.id.cafe_list);
        ArrayAdapter cafeAdapter = new CafeAdapter(this, cafes);
        cafeListView.setAdapter(cafeAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_inside, null);
        fab.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel())
                    .setPositiveButton("AddCafe", ((dialog, which) -> {
                        EditText nameEditText = view.findViewById(R.id.cafe_name_text);
                        EditText addressEditText = view.findViewById(R.id.cafe_address_text);
                        String name = nameEditText.getText().toString();
                        String address = addressEditText.getText().toString();
                        cafes.add(new Cafe(name, address, R.drawable.cafe));
                        dialog.dismiss();
                    })).setView(view)
                    .setTitle("Choose Your Poison")
                    .create()
                    .show();
        });

    }
}

