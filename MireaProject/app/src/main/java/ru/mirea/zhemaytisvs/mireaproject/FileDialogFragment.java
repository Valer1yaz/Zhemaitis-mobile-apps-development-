package ru.mirea.zhemaytisvs.mireaproject;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class FileDialogFragment extends DialogFragment {

    public interface FileDialogListener {
        void onDialogSave(String filename, String content);
    }

    private FileDialogListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof FileDialogListener) {
            listener = (FileDialogListener) context;
        } else if (getParentFragment() instanceof FileDialogListener) {
            listener = (FileDialogListener) getParentFragment();
        } else {
            throw new RuntimeException("Must implement FileDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        EditText inputFilename = new EditText(getContext());
        inputFilename.setHint("Имя файла");
        inputFilename.setInputType(InputType.TYPE_CLASS_TEXT);

        EditText inputContent = new EditText(getContext());
        inputContent.setHint("Содержимое");
        inputContent.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        inputContent.setMinLines(4);

        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50, 20, 50, 20);
        layout.addView(inputFilename);
        layout.addView(inputContent);

        return new AlertDialog.Builder(requireActivity())
                .setTitle("Создание файла")
                .setView(layout)
                .setPositiveButton("Сохранить", (dialog, which) -> {
                    String filename = inputFilename.getText().toString();
                    String content = inputContent.getText().toString();
                    listener.onDialogSave(filename, content);
                })
                .setNegativeButton("Отмена", null)
                .create();
    }
}