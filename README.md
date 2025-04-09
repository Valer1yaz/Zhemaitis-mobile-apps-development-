**  Практическая работа 1. Отчёт по выполнению**

В первой части практической работы произведена установка среды разработки Android Studio. Далее создан первый проект Lesson 1, в котором созданы модули под каждое задание: layouttype, control_lesson1, buttonclicker.

**  Модуль layouttype**

Свободный layout основанный на относительных связях с parent

![image](https://github.com/user-attachments/assets/64ddeba3-ddb0-47d7-a29e-96383bd89718)

Lineral layout

![image](https://github.com/user-attachments/assets/b00919b4-f057-46a6-bec0-0e6748e62a7d)

Table layout

![image](https://github.com/user-attachments/assets/8f5f0d56-0873-48d7-bbb0-99a71c789523)

**  Модуль control_lesson1**

Страница с использованием текста, полей вода, кнопок и картинок

![image](https://github.com/user-attachments/assets/f2b8dc8a-cc05-49e7-9c49-785570f9ec02)

Страница с кнопками. Вертикальная версия

![image](https://github.com/user-attachments/assets/2224184c-b35f-4191-83b3-e11dd248ae53)

Горизонтальная версия

![image](https://github.com/user-attachments/assets/cb979666-ca05-4143-8fae-ff2f6cc38fe0)

**  Модуль ButtonClicker**

При нажатии на кнопку "Кто я" выводится текст с номером в группе

![image](https://github.com/user-attachments/assets/aaaf4161-0e9f-45e2-b0aa-1f4b545ab4d3)

При нажатии на кнопку "Это не я" отмечается чекбокс и появлятеся текст "это не я"

![image](https://github.com/user-attachments/assets/d288ade5-d883-4b0b-9be5-1773e605f375)

Activity_main.xml
```
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/tvOut"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <TextView
        android:id="@+id/textViewStudent"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello World!"
        app:layout_constraintBottom_toTopOf="@+id/checkBox"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <Button
        android:id="@+id/btnWhoAmI"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Who am i"
        app:layout_constraintBottom_toTopOf="@+id/checkBox"
        app:layout_constraintEnd_toStartOf="@+id/btnItIsNotMe"
        app:layout_constraintHorizontal_bias="1.0"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textViewStudent" />

    <Button
        android:id="@+id/btnItIsNotMe"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:onClick="onMyButtonClick"


        android:text="Its not me"
        app:layout_constraintBottom_toTopOf="@+id/checkBox"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toEndOf="@+id/btnWhoAmI"
        app:layout_constraintTop_toBottomOf="@+id/textViewStudent" />

    <CheckBox
        android:id="@+id/checkBox"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="CheckBox"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textViewStudent" />

</androidx.constraintlayout.widget.ConstraintLayout>

```

MainActivity.java
```
package ru.mirea.zhemaytisvs.buttonclicker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private TextView textViewStudent;
    private Button btnWhoAmI;
    private Button btnItIsNotMe;
    private CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        textViewStudent = findViewById(R.id.textViewStudent);
        btnWhoAmI = findViewById(R.id.btnWhoAmI);
        checkBox = findViewById(R.id.checkBox);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.tvOut), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        View.OnClickListener oclBtnWhoAmI = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewStudent.setText("Мой номер по списку № 9");
            }
        };
        btnWhoAmI.setOnClickListener(oclBtnWhoAmI);
    }

    public void onMyButtonClick(View view) {
        boolean isChecked = !checkBox.isChecked();
        checkBox.setChecked(isChecked);

        if (isChecked) {
            textViewStudent.setText("Это не я");
        } else {
            textViewStudent.setText("Мой номер по списку № 9");
        }

    }
}
```

