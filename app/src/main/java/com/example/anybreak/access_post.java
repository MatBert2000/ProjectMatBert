package com.example.anybreak;

import android.graphics.Rect;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class access_post extends RecyclerView.ItemDecoration {  // исправлено наследование
    private final int spaceHeight;

    public access_post(int spaceHeight) {
        this.spaceHeight = spaceHeight;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.bottom = spaceHeight; // Отступ снизу для каждого поста
    }
}
