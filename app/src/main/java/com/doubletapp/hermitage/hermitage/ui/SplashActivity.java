package com.doubletapp.hermitage.hermitage.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.doubletapp.hermitage.hermitage.R;
import com.doubletapp.hermitage.hermitage.model.Art;
import com.doubletapp.hermitage.hermitage.model.Exhibit;
import com.doubletapp.hermitage.hermitage.utils.Data;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    public static final String TAG = "SplashActivity";
    private Handler mHandler;
    
    public static void start(Context context) {
        Intent starter = new Intent(context, SplashActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makeData();
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                TabActivity.start(SplashActivity.this);
            }
        }, 1000);
        setContentView(R.layout.activity_splash);
    }

    private void makeData() {
        makeExhibits();
    }

    private void makeExhibits() {
        List<Exhibit> list = new ArrayList<>();
        list.add(Exhibit.build()
                .setImage(R.drawable.exhibit_0)
                .setName("Мост Кумонокакэ в горах Гёдосан близ города Асикага")
                .setSubscription("Серия гравюр «Необычные виды прославленных мостов различных провинций» (Сёкоку мэйкё: киран 諸国名橋奇覧)\n")
                .create());
        list.add(Exhibit.build()
                .setImage(R.drawable.exhibit_1)
                .setName("Комати из храма Сэкидэра")
                .setSubscription("Серия гравюр «Семь изображений Комати в современном обличье» (Фу:рю: нана Комати 風流七小町)")
                .create());
        list.add(Exhibit.build()
                .setImage(R.drawable.exhibit_2)
                .setName("Луна над мысом")
                .setSubscription("Серия гравюр Сто знаменитых видов Эдо (Мэйсё Эдо хяккэй 名所江戸百景)")
                .create());
        list.add(Exhibit.build()
                .setImage(R.drawable.exhibit_3)
                .setName("Храм Кинрюдзан в районе Асакуса")
                .setSubscription("Серия гравюр Сто знаменитых видов Эдо (Мэйсё Эдо хяккэй 名所江戸百景)")
                .create());
        list.add(Exhibit.build()
                .setImage(R.drawable.exhibit_4)
                .setName("Городские улицы в парадном убранстве в праздник Танабата")
                .setSubscription("Серия гравюр Сто знаменитых видов Эдо (Мэйсё Эдо хяккэй 名所江戸百景)")
                .create());
        list.add(Exhibit.build()
                .setImage(R.drawable.exhibit_5)
                .setName("Комати из храма Сэкидэра")
                .setSubscription("Серия гравюр «Семь изображений Комати в современном обличье» (Фу:рю: нана Комати 風流七小町)")
                .create());
        list.add(Exhibit.build()
                .setImage(R.drawable.exhibit_6)
                .setName("Вид гавани Сиба")
                .setSubscription("Серия гравюр «Семь изображений Комати в современном обличье» (Фу:рю: нана Комати 風流七小町)")
                .create());
        list.add(Exhibit.build()
                .setImage(R.drawable.exhibit_2)
                .setName("Плотина на реке Отонаси в районе Одзи, в просторечии называемая Отаки - Великий водопад")
                .setSubscription("Серия гравюр Сто знаменитых видов Эдо (Мэйсё Эдо хяккэй 名所江戸百景)")
                .create());
        list.add(Exhibit.build()
                .setImage(R.drawable.exhibit_8)
                .setName("Кувшин")
                .setSubscription("Предметы прикладного искусства, быта и этнографии")
                .create());
        list.add(Exhibit.build()
                .setImage(R.drawable.exhibit_9)
                .setName("Кувшин")
                .setSubscription("Предметы прикладного искусства, быта и этнографии")
                .create());
        list.add(Exhibit.build()
                .setImage(R.drawable.exhibit_10)
                .setName("Банка")
                .setSubscription("Медь; ковка, гравировка, лужение")
                .create());
        list.add(Exhibit.build()
                .setImage(R.drawable.exhibit_11)
                .setName("Бутыль")
                .setSubscription("Бронза (латунь); литье, ковка, гравировка")
                .create());
        list.add(Exhibit.build()
                .setImage(R.drawable.exhibit_12)
                .setName("Светильник-факел")
                .setSubscription("Общая высота: 43 см; высота резервуара: 16,5 см; высота основания: 35,8 см")
                .create());
        list.add(Exhibit.build()
                .setImage(R.drawable.exhibit_13)
                .setName("Ваза с крышкой")
                .setSubscription("Бронза (латунь); ковка, гравировка")
                .create());
        list.add(Exhibit.build()
                .setImage(R.drawable.exhibit_14)
                .setName("Кубок")
                .setSubscription("Бронза (латунь); литье, ковка, гравировка")
                .create());
        list.add(Exhibit.build()
                .setImage(R.drawable.exhibit_15)
                .setName("Котел с крышкой")
                .setSubscription("Медь; ковка, гравировка, лужение")
                .create());
        Data.exhibits = list;
    }
}
