package com.example.myfirstchatapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private Toolbar actionbarRegister;
    private EditText textUsername,textEmail,textPassword;
    private Button btnRegister, btnLoginRegister;

    private FirebaseAuth auth;

    public void init(){
        actionbarRegister=(Toolbar) findViewById(R.id.actionbarRegister);
        setSupportActionBar(actionbarRegister);
        getSupportActionBar().setTitle("Hesap Oluştur");

        auth=FirebaseAuth.getInstance();

        textUsername=(EditText) findViewById(R.id.textusernameRegister);
        textEmail=(EditText) findViewById(R.id.textemailRegister);
        textPassword=(EditText) findViewById(R.id.textparolaRegister);
        btnRegister=(Button) findViewById(R.id.btnRegister);
        btnLoginRegister=(Button)findViewById(R.id.btnLoginRegister);
        }
    }

    private void createNewAccount() {
        String username=textUsername.getText().toString();
        String email=textEmail.getText().toString();
        String password=textPassword.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"email alanı boş olamaz!",Toast.LENGTH_LONG).show();
        }
        else if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"parola alanı boş olamaz!",Toast.LENGTH_LONG).show();
        }
        else{

            auth.createUserWithEmailAndPasword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>(){
                @Override
                public void onComplete(@NonNull Task <AuthResult> task){

                    if(task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this,"Hesabınız Başarılı bir şekilde oluşturuldu ! ",Toast.LENGTH_LONG).show();
                        Intent loginIntent = new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(loginIntent);
                        finish();
                    }else{
                        Toast.makeText(RegisterActivity.this,"Bir Hata Oluştu!",Toast.LENGTH_LONG).show();
                    }

                }

            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        btnRegister.setOnClickListener((View v) -> {
            createNewAccount();
        });
        btnLoginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLoginActivity();
            }
        });
    }

    private void goToLoginActivity() {
    Intent loginIntent=new Intent(RegisterActivity.this,LoginActivity.class);
    startActivity(loginIntent);
    finish();
    }
}