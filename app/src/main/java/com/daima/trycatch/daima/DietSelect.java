package com.daima.trycatch.daima;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.util.Log;

public class DietSelect extends Activity implements OnItemSelectedListener{


    public Spinner spinner1;
    public Spinner spinner2;
    public Spinner spinner3;
    public Button filterB;
    String sp1;
    String sp2;
    String sp3;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent homeIntent = new Intent(DietSelect.this, MainActivity.class);
                    startActivity(homeIntent);
                    finish();
                    return true;
                case R.id.navigation_ai:
                    Intent aiIntent = new Intent(DietSelect.this, AI.class);
                    startActivity(aiIntent);

                    return true;
                case R.id.navigation_notifications:
                    Intent signup = new Intent(DietSelect.this, SignUp.class);
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
                    Intent homeIntent = new Intent(DietSelect.this, MainActivity.class);
                    startActivity(homeIntent);
                    finish();
                    return true;

                case R.id.navigation_chat:
                    Intent chatIntent = new Intent(DietSelect.this, MainActivity.class);
                    startActivity(chatIntent);
                    return true;

                case R.id.navigation_ai:
                    Intent aiIntent = new Intent(DietSelect.this, AI.class);
                    startActivity(aiIntent);
                    return true;

                case R.id.navigation_doctor:
                    Intent docIntent = new Intent(DietSelect.this, AI.class);
                    startActivity(docIntent);
                    return true;

                case R.id.navigation_notifications:
                    Intent signup = new Intent(DietSelect.this, SignUp.class);
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_select);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_home);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        BottomNavigationView navigation2 = (BottomNavigationView) findViewById(R.id.navigation_signin);
        navigation2.setSelectedItemId(R.id.navigation_home);
        navigation2.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener2);
        disableShiftMode(navigation2);


        filterB = (Button)findViewById(R.id.filterButton);


        // Spinner element
        final Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        final Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        final Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);

        // Spinner click listener
        spinner1.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);
        spinner3.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories1 = new ArrayList<String>();
        categories1.add("First Trimester");
        categories1.add("Second Trimester");
        categories1.add("Third Trimester");

        List<String> categories2 = new ArrayList<String>();
        categories2.add("Veg");
        categories2.add("Non Veg");

        List<String> categories3 = new ArrayList<String>();
        categories3.add("North Indian");
        categories3.add("South Indian");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories1);
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories3);

        // Drop down layout style - list view with radio button
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner1.setAdapter(dataAdapter1);
        spinner2.setAdapter(dataAdapter2);
        spinner3.setAdapter(dataAdapter3);

        filterB.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {


                sp1 = String.valueOf(spinner1.getSelectedItem());
                sp2 = String.valueOf(spinner2.getSelectedItem());
                sp3 = String.valueOf(spinner3.getSelectedItem());

                if(sp1 == "First Trimester" && sp2 == "Veg" && sp3 == "North Indian")
                {
                    Intent intent = new Intent(DietSelect.this , north_veg_Tri_1.class);
                    startActivity(intent);
                }
                if(sp1 == "First Trimester" && sp2 == "Non Veg" && sp3 == "North Indian")
                {
                    Intent intent = new Intent(DietSelect.this , north_nonveg_Tri_1.class);
                    startActivity(intent);
                }
                if(sp1 == "First Trimester" && sp2 == "Veg" && sp3 == "South Indian")
                {
                    Intent intent = new Intent(DietSelect.this , south_veg_Tri_1.class);
                    startActivity(intent);
                }
                if(sp1 == "First Trimester" && sp2 == "Non Veg" && sp3 == "South Indian")
                {
                    Intent intent = new Intent(DietSelect.this , south_nonveg_Tri_1.class);
                    startActivity(intent);
                }
                if(sp1 == "Second Trimester" && sp2 == "Veg" && sp3 == "North Indian")
                {
                    Intent intent = new Intent(DietSelect.this , north_veg_Tri_2.class);
                    startActivity(intent);
                }
                if(sp1 == "Second Trimester" && sp2 == "Non Veg" && sp3 == "North Indian")
                {
                    Intent intent = new Intent(DietSelect.this , north_nonveg_Tri_2.class);
                    startActivity(intent);
                }
                if(sp1 == "Second Trimester" && sp2 == "Veg" && sp3 == "South Indian")
                {
                    Intent intent = new Intent(DietSelect.this , south_veg_Tri_2.class);
                    startActivity(intent);
                }
                if(sp1 == "Second Trimester" && sp2 == "Non Veg" && sp3 == "South Indian")
                {
                    Intent intent = new Intent(DietSelect.this , south_nonveg_Tri_2.class);
                    startActivity(intent);
                }
                if(sp1 == "Third Trimester" && sp2 == "Veg" && sp3 == "North Indian")
                {
                    Intent intent = new Intent(DietSelect.this , north_veg_Tri_3.class);
                    startActivity(intent);
                }
                if(sp1 == "Third Trimester" && sp2 == "Non Veg" && sp3 == "North Indian")
                {
                    Intent intent = new Intent(DietSelect.this , north_nonveg_Tri_3.class);
                    startActivity(intent);
                }
                if(sp1 == "Third Trimester" && sp2 == "Veg" && sp3 == "South Indian")
                {
                    Intent intent = new Intent(DietSelect.this , south_veg_Tri_3.class);
                    startActivity(intent);
                }
                if(sp1 == "Third Trimester" && sp2 == "Non Veg" && sp3 == "South Indian")
                {
                    Intent intent = new Intent(DietSelect.this , south_nonveg_Tri_3.class);
                    startActivity(intent);
                }
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner ite

    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }




}