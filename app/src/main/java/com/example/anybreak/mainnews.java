package com.example.anybreak;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.ViewOutlineProvider;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.jsibbold.zoomage.ZoomageView;

import java.util.ArrayList;

public class mainnews extends AppCompatActivity {

    private LinearLayout imageContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activitymainnews);

        imageContainer = findViewById(R.id.imageContainer);

        // Получаем список изображений из createpost
        ArrayList<String> imageUris = getIntent().getStringArrayListExtra("imageUris");
        if (imageUris != null) {
            for (String uriString : imageUris) {
                addImageWithBackground(Uri.parse(uriString));
            }
        }
        // кнопка плюса
        ImageButton plusButton = findViewById(R.id.plus);
        plusButton.setOnClickListener(v -> {
            Intent intent = new Intent(mainnews.this, createpost.class);
            startActivity(intent);
        });
        //  ес чо кнопка, профиля!
        ImageButton profileButton = findViewById(R.id.profile);
        profileButton.setOnClickListener(v -> {
            Intent intent = new Intent(mainnews.this, profile.class);
            startActivity(intent);
        });

    }




    private void addImageWithBackground(Uri imageUri) {
        // Создаем контейнер для изображения и фона
        FrameLayout imageWrapper = new FrameLayout(this);
        imageWrapper.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        // Создаем фоновое изображение (серая подложка с закругленными углами)
        ImageView background = new ImageView(this);
        background.setImageResource(R.drawable.mainnewspost);  // Подложка
        FrameLayout.LayoutParams backgroundParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
        );
        // Устанавливаем отступы для фона
        backgroundParams.setMargins(35, 50, 45, 50); // отступы сверху, снизу, слева и справа
        background.setLayoutParams(backgroundParams);
        background.setScaleType(ImageView.ScaleType.CENTER_CROP);  // Центрируем и обрезаем изображение
        background.setClipToOutline(true);  // Чтобы подложка имела скругленные углы
        background.setOutlineProvider(ViewOutlineProvider.BACKGROUND); // Для скругления углов подложки

        // Создаем основное изображение с зумом
        ZoomageView zoomageView = new ZoomageView(this);
        zoomageView.setImageURI(imageUri);
        FrameLayout.LayoutParams zoomageParams = new FrameLayout.LayoutParams(
                570,  // Размер изображения (меньше, чем подложка)
                500   // Размер изображения (меньше, чем подложка)
        );
        zoomageParams.gravity = android.view.Gravity.CENTER;  // Центрируем изображение
        zoomageView.setLayoutParams(zoomageParams);
        zoomageView.setAdjustViewBounds(true);  // Сохраняем пропорции изображения
        zoomageView.setScaleType(ImageView.ScaleType.CENTER_CROP);  // Обрезаем изображение

        // Добавляем фоновое изображение и зумируемое изображение в контейнер
        imageWrapper.addView(background);
        imageWrapper.addView(zoomageView);

        // Добавляем контейнер с картинкой в основной контейнер
        imageContainer.addView(imageWrapper);
    }
}
