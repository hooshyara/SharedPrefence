package com.example.sharedprefence;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    private UserManager userManager;
    private String gender = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        userManager = new UserManager(this);
        TextInputEditText fulNameET = findViewById(R.id.et_main_name);
        fulNameET.setText(userManager.getFullName());
        TextInputEditText emailET = findViewById(R.id.et_main_email);
        emailET.setText(userManager.getEmail());
        RadioGroup radioGroup = findViewById(R.id.radioGroup_main_gender);
        gender = userManager.getGender();
        if (gender.equalsIgnoreCase("male")){
            radioGroup.check(R.id.radioBTN_main_male);
        }else if (gender.equalsIgnoreCase("female")){
            radioGroup.check(R.id.radioBTN_main_female);
        }

        View save = findViewById(R.id.btn_main_save);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == R.id.radioBTN_main_male){
                    gender = "male";
                }else {
                    gender = "female";
                }
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userManager.saveUserInformation(fulNameET.getText().toString(),
                        emailET.getText().toString(),
                        gender);
            }
        });
    }
}