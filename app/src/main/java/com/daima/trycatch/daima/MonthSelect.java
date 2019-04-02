package com.daima.trycatch.daima;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.Field;

public class MonthSelect extends AppCompatActivity {

    public Button month1;
    public Button month2;
    public Button month3;
    public Button month4;
    public Button month5;
    public Button month6;
    public Button month7;
    public Button month8;
    public Button month9;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent homeIntent = new Intent(MonthSelect.this, MainActivity.class);
                    startActivity(homeIntent);
                    finish();
                    return true;
                case R.id.navigation_ai:
                    Intent aiIntent = new Intent(MonthSelect.this, AI.class);
                    startActivity(aiIntent);

                    return true;
                case R.id.navigation_notifications:
                    Intent signup = new Intent(MonthSelect.this, SignUp.class);
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
                    Intent homeIntent = new Intent(MonthSelect.this, MainActivity.class);
                    startActivity(homeIntent);
                    finish();
                    return true;

                case R.id.navigation_chat:
                    Intent chatIntent = new Intent(MonthSelect.this, MainActivity.class);
                    startActivity(chatIntent);
                    return true;

                case R.id.navigation_ai:
                    Intent aiIntent = new Intent(MonthSelect.this, AI.class);
                    startActivity(aiIntent);
                    return true;

                case R.id.navigation_doctor:
                    Intent docIntent = new Intent(MonthSelect.this, AI.class);
                    startActivity(docIntent);
                    return true;

                case R.id.navigation_notifications:
                    Intent signup = new Intent(MonthSelect.this, SignUp.class);
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
        setContentView(R.layout.activity_month_select);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        BottomNavigationView navigation2 = (BottomNavigationView) findViewById(R.id.navigation_signin);
        navigation2.setSelectedItemId(R.id.navigation_home);
        navigation2.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener2);
        disableShiftMode(navigation2);

        month1 = (Button)findViewById(R.id.button13);
        month1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(MonthSelect.this, Month1Exercise.class);
                startActivity(homeIntent);

            }
        });

        month2 = (Button)findViewById(R.id.button12);
        month2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(MonthSelect.this, Month2Exercise.class);
                startActivity(homeIntent);

            }
        });

        month3 = (Button)findViewById(R.id.button11);
        month3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(MonthSelect.this, Month3Exercise.class);
                startActivity(homeIntent);

            }
        });

        month4 = (Button)findViewById(R.id.button10);
        month4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(MonthSelect.this, Month4Exercise.class);
                startActivity(homeIntent);
            }
        });

        month5 = (Button)findViewById(R.id.button9);
        month5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(MonthSelect.this, Month5Exercise.class);
                startActivity(homeIntent);
            }
        });

        month6 = (Button)findViewById(R.id.button8);
        month6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(MonthSelect.this, Month6Exercise.class);
                startActivity(homeIntent);
            }
        });

        month7 = (Button)findViewById(R.id.button7);
        month7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(MonthSelect.this, Month7Exercise.class);
                startActivity(homeIntent);
            }
        });

        month8 = (Button)findViewById(R.id.button6);
        month8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(MonthSelect.this, Month8Exercise.class);
                startActivity(homeIntent);
            }
        });

        month9 = (Button)findViewById(R.id.button4);
        month9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(MonthSelect.this, Month9Exercise.class);
                startActivity(homeIntent);
            }
        });
    }
}
