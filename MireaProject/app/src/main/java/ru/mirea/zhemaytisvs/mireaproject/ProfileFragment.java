package ru.mirea.zhemaytisvs.mireaproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {
    private EditText editName, editAge, editGroup, editListNumber;
    private Button btnSave;
    private SharedPreferences preferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        editName = view.findViewById(R.id.editName);
        editAge = view.findViewById(R.id.editAge);
        editGroup = view.findViewById(R.id.editGroup);
        editListNumber = view.findViewById(R.id.editListNumber);
        btnSave = view.findViewById(R.id.btnSave);

        preferences = requireActivity().getSharedPreferences("user_profile", Context.MODE_PRIVATE);

        // Загрузка ранее сохранённых данных
        editName.setText(preferences.getString("name", ""));
        editAge.setText(preferences.getString("age", ""));
        editGroup.setText(preferences.getString("group", ""));
        editListNumber.setText(preferences.getString("listNumber", ""));

        btnSave.setOnClickListener(v -> {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("name", editName.getText().toString());
            editor.putString("age", editAge.getText().toString());
            editor.putString("group", editGroup.getText().toString());
            editor.putString("listNumber", editListNumber.getText().toString());
            editor.apply();
            Toast.makeText(getContext(), "Данные сохранены", Toast.LENGTH_SHORT).show();
        });

        return view;
    }
}
