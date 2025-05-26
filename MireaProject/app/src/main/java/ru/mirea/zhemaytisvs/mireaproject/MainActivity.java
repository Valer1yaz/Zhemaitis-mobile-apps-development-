package ru.mirea.zhemaytisvs.mireaproject;

import java.io.File;
import android.os.Bundle;
import android.view.Menu;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.zhemaytisvs.mireaproject.R;
import ru.mirea.zhemaytisvs.mireaproject.databinding.ActivityMainBinding;
import android.os.Bundle;
import android.view.Menu;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.navigation.NavigationView;
import androidx.navigation.fragment.NavHostFragment;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


import ru.mirea.zhemaytisvs.mireaproject.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements FileDialogFragment.FileDialogListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_data, R.id.nav_webview, R.id.nav_background, R.id.nav_compass, R.id.nav_camera, R.id.nav_microphone, R.id.nav_profile, R.id.nav_files)
                .setOpenableLayout(drawer)
                .build();

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_content_main);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    // Implement the onDialogSave() method from FileDialogListener
    @Override
    public void onDialogSave(String filename, String content) {
        try {
            File file = new File(getFilesDir(), filename);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(content.getBytes());
            fos.close();
            Toast.makeText(this, "Файл сохранён", Toast.LENGTH_SHORT).show();
            // Inform WorkingWithFilesFragment to update its list
            NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.nav_host_fragment_content_main);
            WorkingWithFilesFragment fragment = (WorkingWithFilesFragment) navHostFragment.getChildFragmentManager()
                    .getFragments()
                    .get(0);


            if (fragment != null) {
                fragment.updateFileList(); // Call a public method in the fragment
            }



        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Ошибка сохранения файла", Toast.LENGTH_SHORT).show();
        }
    }
}