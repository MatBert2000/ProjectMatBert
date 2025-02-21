package com.example.anybreak;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;
import java.util.Map;

public class registration extends AppCompatActivity {

    private FirebaseAuth mAuth;  // Объявление FirebaseAuth
    private FirebaseFirestore firestore;
    private EditText loginEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityregistration);  // Убедитесь, что ваш layout правильный

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        loginEditText = findViewById(R.id.emailEditText);  // Получение ссылки на EditText для логина
        passwordEditText = findViewById(R.id.passwordEditText);  // Получение ссылки на EditText для пароля

        ImageButton signinButton = findViewById(R.id.SigninB);
        signinButton.setOnClickListener(v -> {
            String login = loginEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            if (login.isEmpty() || password.isEmpty() || !login.contains("@")) {
                Toast.makeText(registration.this, "Неправильно введены данные.", Toast.LENGTH_SHORT).show();
            } else {
                // Переход на основную активность
                Intent intent = new Intent(registration.this, mainnews.class);
                startActivity(intent);

                // Сохранение данных в Firestore
                Map<String, Object> user = new HashMap<>();
                user.put("Email", login);
                user.put("Password", password);

                firestore.collection("users").add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });

        // Кнопка для перехода на экран регистрации
        ImageButton signupButton = findViewById(R.id.signupB);
        signupButton.setOnClickListener(v -> {
            // Переход на экран регистрации
            Intent intent = new Intent(registration.this, signup.class);
            startActivity(intent);
        });
    }
}
