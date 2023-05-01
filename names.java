// COMP2161 T00705586 Huy Mirko Nov 2022

package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class names extends AppCompatActivity {

    // declare variables and objects
    Button buttonSave, buttonBack;
    EditText field1, field2;
    String name1, name2;
    SharedPreferences shareNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_names);
        getSupportActionBar().hide();
        Log.i("TAG", "names.java: onCreate();");

        // shared preferences
        shareNames = getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorNames = shareNames.edit();

        // assign objects to XML elements
        field1 = (EditText)findViewById(R.id.editNameP1);
        field2 = (EditText)findViewById(R.id.editNameP2);
        buttonSave = findViewById(R.id.saveNames);
        buttonBack = findViewById(R.id.goNamesToMenu);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TAG", "names.java: Save button clicked");
                name1 = field1.getText().toString();
                name2 = field2.getText().toString();
                editorNames.putString("sharedP1name", name1);
                editorNames.putString("sharedP2name", name2);
                editorNames.commit();
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentNamesToMenu = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentNamesToMenu);
            }
        });
    }
}