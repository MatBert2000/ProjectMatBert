package com.example.anybreak;

import android.content.Intent;
import android.os.Bundle;
import android.net.Uri;
import android.widget.ImageButton;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class mainnews extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PostAdapter postAdapter;
    private static ArrayList<Post> postList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activitymainnews);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.addItemDecoration(new access_post(0)); // Отступ 20 пикселей


        // Получаем список изображений из createpost
        ArrayList<String> imageUris = getIntent().getStringArrayListExtra("imageUris");
        if (imageUris != null) {
            for (String uriString : imageUris) {
                postList.add(new Post("User", "Эта моя новая машина! Это М5 в кузове F90 на 700 л.с.!",
                        0, 0, 0, Uri.parse(uriString)));
            }
        }

        postAdapter = new PostAdapter(this, postList);
        recyclerView.setAdapter(postAdapter);
        postAdapter.notifyDataSetChanged();

        // Кнопка "Добавить пост"
        ImageButton plusButton = findViewById(R.id.plus);
        plusButton.setOnClickListener(v -> {
            Intent intent = new Intent(mainnews.this, createpost.class);
            startActivity(intent);
        });

        // Кнопка "Профиль"
        ImageButton profileButton = findViewById(R.id.profile);
        profileButton.setOnClickListener(v -> {
            Intent intent = new Intent(mainnews.this, profile.class);
            startActivity(intent);
        });
    }
}
