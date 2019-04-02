package com.daima.trycatch.daima;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SignUpMobile extends AppCompatActivity {


    private Button sendotp;
    private Button enterotp;
    private EditText usermobile;
    private EditText userotp;
    private int mButton = 0;
    ProgressDialog progressDialog;
    private int renter=0;
    private String mVerificationId;
    private EditText name;
    private String namee;

    private FirebaseAuth mAuth;


    private PhoneAuthProvider.ForceResendingToken mResendToken;


    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent homeIntent = new Intent(SignUpMobile.this, MainActivity.class);
                    startActivity(homeIntent);
                    finish();
                    return true;
                case R.id.navigation_ai:
                    Intent aiIntent = new Intent(SignUpMobile.this, AI.class);
                    startActivity(aiIntent);

                    return true;
                case R.id.navigation_notifications:
                    Intent signup = new Intent(SignUpMobile.this, SignUp.class);
                    startActivity(signup);

                    return true;
            }
            return false;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_mobile);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        mAuth=FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        if(user!=null){
            Intent intent = new Intent(SignUpMobile.this,MainActivity.class);
            startActivity(intent);
            finish();
        }


        userotp=(EditText)findViewById(R.id.enterOtp);
       enterotp = (Button)findViewById(R.id.VerifyOtp);
        sendotp = (Button) findViewById(R.id.otpLogin);
        usermobile = (EditText)findViewById(R.id.mobileText);
        name = (EditText)findViewById(R.id.name);


        sendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                   namee = name.getText().toString();
                String phonenumber = usermobile.getText().toString();
                 if ((TextUtils.isEmpty(phonenumber)) ) {
                    Toast.makeText(getApplicationContext(), "Please enter the phone number to continue.", Toast.LENGTH_SHORT).show();
                    return;
                }else {


                    if (mButton == 0) {
                        usermobile.setVisibility(View.VISIBLE);
                        sendotp.setEnabled(false);
                        usermobile.setEnabled(false);
                        phonenumber = usermobile.getText().toString();
                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                "+91" + phonenumber,
                                60,
                                java.util.concurrent.TimeUnit.SECONDS,
                                SignUpMobile.this,
                                mCallbacks

                        );

                        Intent intent = new Intent(SignUpMobile.this, MainActivity2.class);


                    }
                }
            }
        });

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                String OTP = phoneAuthCredential.getSmsCode();
                userotp.setText(OTP);


                signInWithPhoneAuthCredential(phoneAuthCredential);


            }


            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(getApplicationContext(),"Error occurred while Sending OTP",Toast.LENGTH_LONG).show();
               usermobile.setEnabled(true);
               sendotp.setVisibility(View.VISIBLE);
               sendotp.setEnabled(true);
               //progressDialog.cancel();
                enterotp.setVisibility(View.INVISIBLE);


            }


            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.


                // Save verification ID and resending token so we can use them later

                mButton=1;
                //progressDialog.dismiss();
                  name.setVisibility(View.INVISIBLE);
                 mVerificationId = verificationId;
                mResendToken = token;
                sendotp.setEnabled(true);
                usermobile.setVisibility(View.VISIBLE);
                usermobile.setVisibility(View.INVISIBLE);
                userotp.setVisibility(View.VISIBLE);
                usermobile.setVisibility(View.INVISIBLE);
                userotp.setVisibility(View.VISIBLE);
                 sendotp.setVisibility(View.INVISIBLE);
                enterotp.setVisibility(View.VISIBLE);

            }
        };



        enterotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String OTP = userotp.getText().toString();
                if(!TextUtils.isEmpty(OTP)){
                    sendotp.setEnabled(false);
                    String verificationCode = userotp.getText().toString();
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, verificationCode);
                    // progressDialog.setMessage("Verifying OTP");
                    // progressDialog.show();
                    signInWithPhoneAuthCredential(credential);
                }
                else{
                    Toast.makeText(getApplicationContext(),"OTP cannot be empty",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }




    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        FirebaseAuth.getInstance();
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser User_Mob = FirebaseAuth.getInstance().getCurrentUser();
                            assert User_Mob != null;
                            String mob = User_Mob.getPhoneNumber();

                            FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
                            assert mob != null;
                            Map<String, Object> docData = new HashMap<>();



                            mFirestore.collection("Users").document(mob).collection("UserDetails").document(namee).set(docData);

                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = task.getResult().getUser();
                            Intent mainintent= new Intent(SignUpMobile.this, MainActivity2.class);
                            //progressDialog.dismiss();
                            startActivity(mainintent);
                            finish();
                            // ...
                        } else {
                            // Sign in failed, display a message and update the UI
                            usermobile.setEnabled(true);
                            sendotp.setEnabled(true);
                            //progressDialog.cancel();
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                if(renter==0) {
                                    Toast.makeText(getApplicationContext(), "You have entered an invalid OTP", Toast.LENGTH_LONG).show();

                                }
                            }
                        }
                    }
                });









    }


}

