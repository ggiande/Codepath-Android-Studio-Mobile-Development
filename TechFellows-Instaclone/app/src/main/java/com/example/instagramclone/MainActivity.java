package com.example.instagramclone;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import fragments.ComposeFragment;
import fragments.PostsFragment;
import fragments.ProfileFragment;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    final FragmentManager fragmentManager = getSupportFragmentManager();

    // Bottom Nav Bar
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = new Fragment();
                // Removed switch case, deprecated since API 14
                if (menuItem.getItemId() == R.id.action_home) {
                    // Update fragment
//                    Toast.makeText(MainActivity.this, "Home Button has been selected", Toast.LENGTH_SHORT).show();
                    fragment = new PostsFragment();
                } else if (menuItem.getItemId() == R.id.action_profile) {
                    // Update fragment
//                    Toast.makeText(MainActivity.this, "Profile button has been clicked", Toast.LENGTH_SHORT).show();
                    fragment = new ProfileFragment();
                } else if (menuItem.getItemId() == R.id.action_compose) {
//                    Toast.makeText(MainActivity.this, "Compose button has been clicked", Toast.LENGTH_SHORT).show();
                    fragment = new ComposeFragment();
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });
        // Set default selection
        bottomNavigationView.setSelectedItemId(R.id.action_home);
    }
}
