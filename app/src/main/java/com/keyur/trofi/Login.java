package com.keyur.trofi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.security.identity.AccessControlProfileId;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hbb20.CountryCodePicker;

public class Login extends AppCompatActivity {
    Button loginBtn;
    EditText phoneNo;
    CountryCodePicker countryCodePicker;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();

        loginBtn=(Button)findViewById(R.id.loginBtn);
        phoneNo=(EditText)findViewById(R.id.loginNumber);
        countryCodePicker=(CountryCodePicker)findViewById(R.id.loginCountryCode);


        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if( mFirebaseUser != null ){
                    Intent i = new Intent(getApplicationContext(), HomePage.class);
                    overridePendingTransition(R.anim.slide_out,R.anim.slide_in);
                    startActivity(i);
                    finish();
                }
                else{
                    Toast.makeText(Login.this,"Please Login",Toast.LENGTH_SHORT).show();
                }
            }
        };


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number=phoneNo.getText().toString().trim();
                String pNo="+"+countryCodePicker.getFullNumber()+number;
                if(number.isEmpty()){
                    phoneNo.setError("Number is empty");
                    phoneNo.requestFocus();
                }
                else if(number.length()<10){
                    phoneNo.setError("Number seems wrong");
                    phoneNo.requestFocus();
                }
                else{
                    Intent intent = new Intent(getApplicationContext(), OTP.class);
                    intent.putExtra("pNo",pNo);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
                    finish();
                }
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}