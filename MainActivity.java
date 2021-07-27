package com.example.myfirstchatapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnWelcomeLogin, btnWelcomeRegister;
    public void init(){
        btnWelcomeLogin=(Button)findViewById(R.id.btnWelcomeLogin);
        btnWelcomeRegister=(Button)findViewById(R.id.btnWelcomeRegister);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        btnWelcomeLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLogin= new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intentLogin);
                finish();
            }
        });
        btnWelcomeRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentRegister=new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intentRegister);
                finish();
            }
        });

    }
}