package com.example.wallpaperchange;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.util.BitSet;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    WallpaperManager wpm;
    Timer mytimer;
    Drawable dw;
    int prev =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        
        mytimer = new Timer();
        wpm = WallpaperManager.getInstance(this);
        setwallpaper();
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void setwallpaper() {
        mytimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(prev==1){
                    dw = getDrawable(R.drawable.img);
                    prev=2;
                } else if (prev==2) {
                    dw=getDrawable(R.drawable.img1);
                    prev=1;
                }
                Bitmap bitmap = ((BitmapDrawable)dw).getBitmap();
                try {
                    wpm.setBitmap(bitmap);
                }
                catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        },0,5000);
    }
}