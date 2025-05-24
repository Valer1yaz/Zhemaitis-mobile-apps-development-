package ru.mirea.zhemaytisvs.mireaproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import ru.mirea.zhemaytisvs.mireaproject.R;

public class WorkingWithFilesFragment extends Fragment implements FileDialogFragment.FileDialogListener {

    private TextView textViewFileList;

    public WorkingWithFilesFragment() {
        // Обязательный пустой конструктор
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_working_with_files, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textViewFileList = view.findViewById(R.id.textViewFileList);
        FloatingActionButton fab = view.findViewById(R.id.fab);

        fab.setOnClickListener(v -> {
            FileDialogFragment dialog = new FileDialogFragment();
            dialog.setTargetFragment(WorkingWithFilesFragment.this, 1);
            dialog.show(getParentFragmentManager(), "FileDialogFragment");
        });

        updateFileList();
    }

    private void updateFileList() {
        File filesDir = requireContext().getFilesDir();
        String[] files = filesDir.list();

        if (files != null && files.length > 0) {
            StringBuilder builder = new StringBuilder();
            for (String file : files) {
                builder.append(file).append("\n");
            }
            textViewFileList.setText(builder.toString());
        } else {
            textViewFileList.setText("Файлы не найдены.");
        }
    }

    @Override
    public void onDialogSave(String filename, String content) {
        try {
            File file = new File(requireContext().getFilesDir(), filename);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(content.getBytes());
            fos.close();
            Toast.makeText(getContext(), "Файл сохранён", Toast.LENGTH_SHORT).show();
            updateFileList();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Ошибка сохранения файла", Toast.LENGTH_SHORT).show();
        }
    }
}