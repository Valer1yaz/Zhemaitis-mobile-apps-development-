package ru.mirea.zhemaytisvs.looper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class MyLooper extends Thread {
    public Handler mHandler;
    private final Handler mainHandler;

    public MyLooper(Handler mainHandler) {
        this.mainHandler = mainHandler;
    }

    @Override
    public void run() {
        Looper.prepare();
        mHandler = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(Message msg) {
                Bundle data = msg.getData();
                String ageStr = data.getString("AGE");
                String prof = data.getString("PROF");
                int age = 0;
                try {
                    age = Integer.parseInt(ageStr);
                } catch (NumberFormatException ignored) {
                }

                Log.d("MyLooper", "Получил: возраст=" + age + ", профессия=" + prof);

                try {
                    Thread.sleep(age * 1000L);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                String result = String.format("Обработанные данные: возраст=%d, профессия=%s", age, prof);

                Message reply = Message.obtain();
                Bundle b = new Bundle();
                b.putString("result", result);
                reply.setData(b);
                mainHandler.sendMessage(reply);
            }
        };
        Looper.loop();
    }
}