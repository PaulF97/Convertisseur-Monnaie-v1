package com.example.convertisseur2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button backToConversion = (Button) findViewById(R.id.buttonBack);
        backToConversion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToConversionMethod();
            }
        });
    }

    private void backToConversionMethod() {
        Intent switchActivityIntent = new Intent(this, MainActivity.class);
        startActivity(switchActivityIntent);
    }

}