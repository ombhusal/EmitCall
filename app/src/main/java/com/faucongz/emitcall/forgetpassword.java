package com.faucongz.emitcall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

public class forgetpassword extends AppCompatActivity {

    private EditText emailText;
    private Button reset;

    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);

        emailText = (EditText) findViewById(R.id.emailEdit);
        reset = (Button) findViewById(R.id.resetBtn);

        auth = FirebaseAuth.getInstance();

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });
    }
    private void resetPassword(){

        String email = emailText.getText().toString().trim();
        if(email.isEmpty()){
            emailText.setError("Email is required");
            emailText.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailText.setError("Please provide valid email");
        }

        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<Void> task) {

                if (task.isSuccessful()){
                    Toast.makeText(forgetpassword.this, "Check your email is sent ", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(forgetpassword.this, "Try again Something went wrong ", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}