package com.faucongz.emitcall;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;


public class signupactivity extends AppCompatActivity {

    FirebaseAuth auth;
    EditText emailbox , passwordBox,namebox ;
    Button loginBtn ,createbtn;
    LottieAnimationView animationView;
    LinearLayout loadingview;



    FirebaseFirestore database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupactivity);

        database = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();



        emailbox = findViewById(R.id.emailbox);
        passwordBox = findViewById(R.id.passwordBox);
        namebox = findViewById(R.id.namebox);



        loginBtn = findViewById(R.id.loginbtn);
        createbtn = findViewById(R.id.createbtn);
        animationView = findViewById(R.id.animation_view);
        loadingview = findViewById(R.id.loadingview);




        createbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadingview.setVisibility(View.VISIBLE);
                animationView.playAnimation();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                },3000);



                String email,pass,name;
                email = emailbox.getText().toString();
                pass = passwordBox.getText().toString();
                name = namebox.getText().toString();


                final User user = new User();
                user.setEmail(email);
                user.setPass(pass);
                user.setName(name);


                auth.createUserWithEmailAndPassword(email ,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            database.collection("Users")
                                    .document().set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                    startActivity(new Intent(signupactivity.this,LoginActivity.class));

                                }
                            });
                            Toast.makeText(signupactivity.this, "Account is created", Toast.LENGTH_SHORT).show();

                        }else{

                            Toast.makeText(signupactivity.this,task.getException().getLocalizedMessage() , Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signupactivity.this,LoginActivity.class));
            }
        });






            }


    }
