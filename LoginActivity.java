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

public class LoginActivity extends AppCompatActivity {
    private Toolbar actionbarLogin;
    private EditText textEmail,textPassword;
    private Button btnLogin,btnRegisterLogin;

    private FirebaseAuth auth;
    private FirebaseUser currentUser;

    public void init(){

        actionbarLogin=(Toolbar)findViewById(R.id.actionbarLogin);
        setSupportActionBar(actionbarLogin);
        getSupportActionBar().setTitle("Giriş Yap");
        auth=FirebaseAuth.getInstance();
        currentUser=auth.getCurrentUser();

        textEmail=(EditText) findViewById(R.id.textEmailLogin);
        textPassword=(EditText) findViewById(R.id.textPasswordLogin);
        btnLogin=(Button) findViewById(R.id.btnLogin);
        btnRegisterLogin=(Button)findViewById(R.id.btnLoginRegister)

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
        btnRegisterLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToRegisterActivity();
            }
        });

    }

    private void goToRegisterActivity() {

        Intent registerIntent=new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(registerIntent);
        finish();
    }

    private void loginUser() {

        String email=textEmail.getText().toString();
        String password=textPassword.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"email alanı boş olamaz!",Toast.LENGTH_LONG).show();
        }
        else if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"parola alanı boş olamaz!",Toast.LENGTH_LONG).show();
        }
        else{
            btnLogin.setEnabled(false);

            auth.signInWithEmailAndPasword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>(){
                @Override
                public void onComplete(@NonNull Task <AuthResult> task){

                    if(task.isSuccessful()){
                        Toast.makeText(LoginActivity.this,"Giriş Başarılı! ",Toast.LENGTH_LONG).show();
                        Intent mainIntent = new Intent(LoginActivity.this,MainActivity2.class);
                        startActivity(mainIntent);
                        finish();
                    }else{
                        Toast.makeText(LoginActivity.this,"Giriş Başarısız!",Toast.LENGTH_LONG).show();
                        btnLogin.setEnabled(true);
                    }

                }

            }
        }
    }
}