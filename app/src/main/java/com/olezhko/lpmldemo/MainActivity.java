package com.olezhko.lpmldemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //catching incoming intent
        Intent incomingIntent = getIntent();
        //getting our user data fro it
        String[] userData = incomingIntent.getStringArrayExtra("userData");
        //displaying email in the Toast
        Toast.makeText(this, "email:" + userData[0], Toast.LENGTH_LONG).show();

        List<Cafe> cafes = new ArrayList<>();
        cafes.add(new Cafe("McDonalds", "pr. Svobody, 12", R.drawable.burger));
        cafes.add(new Cafe("Kredens", "pr. Svobody, 14", R.drawable.coffee));
        cafes.add(new Cafe("Urban Coffee", "vul. Bandery, 12", R.drawable.coffee));
        cafes.add(new Cafe("Celentano", "vul. Bandery, 30", R.drawable.pizza));

        ListView cafeListView = findViewById(R.id.cafe_list);
        ArrayAdapter cafeAdapter = new CafeAdapter(this, cafes);
        cafeListView.setAdapter(cafeAdapter);

        fab = findViewById(R.id.fab);
        //initializing View for dialog along with editTexts
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_contents, null);
        EditText cafeNameEditText = dialogView.findViewById(R.id.name);
        EditText cafeAddressEditText = dialogView.findViewById(R.id.address);

        AlertDialog.Builder builder = new AlertDialog.Builder(this).setTitle("Choose your poison")
                .setView(dialogView)
                .setNegativeButton("Cancel", ((dialog, which) -> dialog.cancel()))
                .setPositiveButton("Add Cafe", ((dialog, which) -> {
                    //getting data from editTexts
                    String name = cafeNameEditText.getText().toString();
                    String address = cafeAddressEditText.getText().toString();
                    cafes.add(new Cafe(name, address, R.drawable.cafe));
                    /* Notifying adapter that data changes and making
                        it do necessary changes inside
                    */
                    cafeAdapter.notifyDataSetChanged();
                    dialog.dismiss();

                }));

        fab.setOnClickListener(v -> {
            /*
                As view is set into dialog it gains parent so we removing it if it exists
             */
            if (dialogView.getParent() != null) {
                ((ViewGroup) dialogView.getParent()).removeView(dialogView);
                //clearing editTexts from previous data
                cafeNameEditText.setText("");
                cafeAddressEditText.setText("");
            }
            builder.create().show();
        });
    }
}

