package com.ex.quizpythonofppt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ex.quizpythonofppt.R;


public class MainActivity2 extends AppCompatActivity {

    Button G1;
    Button GO1;
    Button G2;
    Button G3;
    Button G4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        G1 = findViewById(R.id.btn10);
        G2 = findViewById(R.id.btn20);
        GO1 = findViewById(R.id.btnR);
        G3 = findViewById(R.id.btn30);
        G4 = findViewById(R.id.btn40);
        G4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this , MainActivity5.class);
                startActivity(intent);
            }
        });
        G3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this , MainActivity4.class);
                startActivity(intent);

            }
        });
        G2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this , MainActivity3.class);
                startActivity(intent);
            }
        });

        GO1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this , MainActivity.class);
                startActivity(intent);
            }
        });
        G1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this , QuezActivity.class);
                startActivity(intent);
            }
        });
    }
}