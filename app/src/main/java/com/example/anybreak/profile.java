package com.example.anybreak;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activityprofile), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button editProfileTextTextView = findViewById(R.id.buttoneditprofele);
        editProfileTextTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile.this, editprofile.class);
                startActivity(intent);
            }
        });
        Button securityTextTextView = findViewById(R.id.buttonsecurity);
        securityTextTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile.this, security.class);
                startActivity(intent);
            }
        });;
        Button likedTextTextView = findViewById(R.id.buttonliked);
        likedTextTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile.this, liked.class);
                startActivity(intent);
            }
        });
        Button helpTextTextView = findViewById(R.id.buttonhelp);
        helpTextTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile.this, helpsupport.class);
                startActivity(intent);
            }
        });
        Button logoutTextTextView = findViewById(R.id.buttonlogout);
        logoutTextTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile.this, registration.class);
                startActivity(intent);
            }
        });
    }
}