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

public class WorkingWithFilesFragment extends Fragment {

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

    public void updateFileList() {
        if (getContext() == null) return;
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

}