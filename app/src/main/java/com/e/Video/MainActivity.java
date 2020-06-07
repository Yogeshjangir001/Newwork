package com.e.Video;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity  {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navlistener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navlistener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedfragment = null;

            switch (menuItem.getItemId()){
                case R.id.home:
                    selectedfragment = new Home();
                    break;
                case R.id.discover:
                    selectedfragment = new Discover();
                    break;

                case R.id.add:
                    selectedfragment = new Add();
                    break;
                case R.id.inbox:
                    selectedfragment = new Inbox();
                    break;
                case R.id.about:
                    selectedfragment = new Me();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,  selectedfragment).commit();

            return true;
        }
    };
}





