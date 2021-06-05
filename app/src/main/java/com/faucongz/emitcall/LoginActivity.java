package com.faucongz.emitcall;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;




public class LoginActivity extends AppCompatActivity {

    private TextView forgetPassword;


    public EditText emailbox , passwordBox;
    CheckBox rememberMe;
    Button loginBtn ,show;

    FirebaseAuth auth;


    ProgressDialog dialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dialog = new ProgressDialog(this);
        dialog.setMessage("please wait");

        auth = FirebaseAuth.getInstance();

        forgetPassword=(TextView) findViewById(R.id.forgetpass);
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this,forgetpassword.class));

            }
        });

        emailbox =(EditText) findViewById(R.id.emailbox);
        passwordBox =(EditText) findViewById(R.id.passwordBox);
        loginBtn = findViewById(R.id.loginbtn);
        show = findViewById(R.id.show);
        rememberMe = findViewById(R.id.rememberMe);







        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                String email,pass;
                email = emailbox.getText().toString();
                pass = passwordBox.getText().toString();



                auth.signInWithEmailAndPassword(email , pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        dialog.dismiss();
                        if(task.isSuccessful())
                        {
                            startActivity(new Intent(LoginActivity.this,dashboard.class));
                        } else {
                            Toast.makeText(LoginActivity.this,task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }


                    }
                });

            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,signupactivity.class));

            }
        });

    }
}