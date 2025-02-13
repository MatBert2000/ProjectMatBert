package com.example.anybreak;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class createpost extends AppCompatActivity {

    private static final int RESULT_LOAD_IMAGE = 1;
    private ImageView imageView;
    private Uri selectedImageUri;
    private ArrayList<String> imageUris = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activitycreatepost);

        imageView = findViewById(R.id.preview);

        ImageButton btnPickImage = findViewById(R.id.addplus);
        btnPickImage.setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, RESULT_LOAD_IMAGE);
        });

        ImageButton publicateButton = findViewById(R.id.publiceted);
        publicateButton.setOnClickListener(v -> {
            if (selectedImageUri != null) {
                imageUris.add(selectedImageUri.toString()); // Добавляем в список
                Intent intent = new Intent(createpost.this, mainnews.class);
                intent.putStringArrayListExtra("imageUris", imageUris);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            if (imageView != null) {
                imageView.setImageURI(selectedImageUri);
            }
        }
    }
}
