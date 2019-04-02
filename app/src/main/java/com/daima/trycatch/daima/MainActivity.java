package com.daima.trycatch.daima;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

    public ImageButton imageButton1;

    public ImageButton imageButton2;

    public ImageButton imageButton3;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent homeIntent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(homeIntent);
                    finish();
                    return true;
                case R.id.navigation_ai:
                    Intent aiIntent = new Intent(MainActivity.this, AI.class);
                    startActivity(aiIntent);

                    return true;
                case R.id.navigation_notifications:
                    Intent signup = new Intent(MainActivity.this, SignUp.class);
                    startActivity(signup);

                    return true;
            }
            return false;
        }
    };

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener2
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent homeIntent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(homeIntent);
                    finish();
                    return true;

                case R.id.navigation_chat:
                    Intent chatIntent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(chatIntent);
                    return true;

                case R.id.navigation_ai:
                    Intent aiIntent = new Intent(MainActivity.this, AI.class);
                    startActivity(aiIntent);
                    return true;

                case R.id.navigation_doctor:
                    Intent docIntent = new Intent(MainActivity.this, AI.class);
                    startActivity(docIntent);
                    return true;

                case R.id.navigation_notifications:
                    Intent signup = new Intent(MainActivity.this, SignUp.class);
                    startActivity(signup);

                    return true;
            }
            return false;
        }
    };

    @SuppressLint("RestrictedApi")
    private void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVHelper", "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e("BNVHelper", "Unable to change value of shift mode", e);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sp = getSharedPreferences("out", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("key", 1);
        editor.apply();


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        BottomNavigationView navigation2 = (BottomNavigationView) findViewById(R.id.navigation_signin);
        navigation2.setSelectedItemId(R.id.navigation_home);
        navigation2.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener2);
        disableShiftMode(navigation2);

        imageButton1 = (ImageButton) findViewById(R.id.imageButton1);
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(MainActivity.this, MonthSelect.class);
                startActivity(homeIntent);
            }
        });

        imageButton2 = (ImageButton) findViewById(R.id.dietimageButton);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(MainActivity.this, DietSelect.class);
                startActivity(homeIntent);
            }
        });

        imageButton3 = (ImageButton) findViewById(R.id.imageButton2);
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


}
