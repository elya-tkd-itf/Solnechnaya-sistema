package com.example.solnechnayasistema;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;
import android.view.View;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

@SuppressLint("DrawAllocation")
public class MyView extends View {
    int N = 5;
    int[] x = new int[N];
    int[] y = new int[N];
    int[] vx = new int[N];
    int[] vy = new int[N];
    int[] L = new int[N];
    int[] Red = new int[N];
    int[] Green = new int[N];
    int[] Blue = new int[N];
    int[] R = new int[N];
    int z = -1;
    double a = 0, ha = Math.PI / 180;

    void fillArrayRandom(int[] a, int min, int max) {
        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * (max - min + 1)) + min;
        }
    }

    void makeBalls() {
        fillArrayRandom(x, 50, 250);
        fillArrayRandom(y, 50, 250);
        fillArrayRandom(vx, -50, 100);
        fillArrayRandom(vy, -50, 100);
        fillArrayRandom(L, 3, 10);
        fillArrayRandom(Red, 50, 255);
        fillArrayRandom(Green, 50, 255);
        fillArrayRandom(Blue, 50, 255);
        fillArrayRandom(R, 20, 40);
    }

    void moveBalls() {
        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) {
                x[i] = this.getWidth() / 2 + (int) (L[i] * vx[i] * Math.cos(a));
                y[i] = this.getHeight() / 2 + (int) (z * L[i] * vy[i] * Math.sin(a));
            } else {
                x[i] = this.getWidth() / 2 + (int) (L[i] * vx[i] * Math.cos(a));
                y[i] = this.getHeight() / 2 + (int) (L[i] * vy[i] * Math.sin(a));
            }
        }
        a = a + ha;
    }

    MyView(Context context) {
        super(context);
        makeBalls();
        MyTimer timer = new MyTimer();
        timer.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawCircle(this.getWidth() / 2, this.getHeight() / 2, 70, paint);
        paint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < N; i++) {
            paint.setColor(Color.argb(200, Red[i], Green[i], Blue[i]));
            canvas.drawCircle(x[i], y[i], R[i], paint);
            paint.setTextSize(30.0f);
            paint.setColor(Color.BLACK);
            canvas.drawText("P " + i + " (" + x[i] + ", " + y[i] + ")", x[i] + 10, y[i] - 15, paint);
        }
    }
    void nextFrame() {
        moveBalls();
        invalidate();
    }
    class MyTimer extends CountDownTimer {
        MyTimer() {
            super(1000000, 1);
        }
        @Override
        public void onTick(long millisUntilFinished) {
            // TODO Auto-generated method stub
            nextFrame();
        }
        @Override
        public void onFinish() {
            // TODO Auto-generated method stub
        }
    }
}
