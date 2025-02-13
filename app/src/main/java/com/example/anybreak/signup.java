package com.example.anybreak;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class signup extends AppCompatActivity {

    private FirebaseFirestore firestore;
    private EditText loginsignup, passwordsignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activitysignup);

        TextView registerText = findViewById(R.id.signin);

        loginsignup = findViewById(R.id.emailsignup);  // Получение ссылки на EditText для логина
        passwordsignup = findViewById(R.id.passwordsignup);  // Получение ссылки на EditText для пароля

        // Настройка кнопки регистрации (перехд на экран входа)
        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Переход на экран входа
                Intent intent = new Intent(signup.this, registration.class);
                startActivity(intent);
            }
        });

        // Настройка кнопки Continue (регистрация)
        ImageButton plusButton = findViewById(R.id.Continue); // Убедитесь, что ID правильный
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = loginsignup.getText().toString();
                String password = passwordsignup.getText().toString();

                if (login.isEmpty() || password.isEmpty() || !login.contains("@")) {
                    Toast.makeText(signup.this, "Неправильно введены данные.", Toast.LENGTH_SHORT).show();
                } else {
                    // Сохранение данных в Firestore
                    Map<String, Object> user = new HashMap<>();
                    user.put("Email", login);
                    user.put("Password", password);

                    firestore = FirebaseFirestore.getInstance();
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

                    // Переход на активность mainnews после успешной регистрации
                    Intent intent = new Intent(signup.this, mainnews.class);
                    startActivity(intent);
                }
            }
        });
    }
}
