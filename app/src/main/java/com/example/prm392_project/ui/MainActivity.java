package com.example.prm392_project.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.example.prm392_project.MainApplication;
import com.example.prm392_project.R;

import com.example.prm392_project.ui.login.LoginActivity;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.prm392_project.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    public static final String SHARED_PREFS = "shared_prefs";
    public static final String USERNAME_KEY = "username_key";
    public static final String TOKEN = "token";
    public static final String Role = "user";
    public static final String CREDENTIAL = "credential";
    public static SharedPreferences sharedpreferences;
    public static String username, token,role, credential;

    private NavigationView mainNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        this.SetupData();
        setSupportActionBar(binding.appBarMain.toolbar);
        this.SetupNavBar();
        this.SetupClickEventButton();
    }

    private void SetupData(){
        // initializing our shared preferences.
        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        username = sharedpreferences.getString(USERNAME_KEY, null);
        token = sharedpreferences.getString(TOKEN, null);
        role = sharedpreferences.getString(Role,"user");
        credential = sharedpreferences.getString(CREDENTIAL, "HE123456 - 012345679");
    }

    private void SetupClickEventButton(){
        Menu navigationMenu = this.mainNavigationView.getMenu();
        navigationMenu.findItem(R.id.nav_logout).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {
                OnClickLogout();
                return true;
            }
        });
    }



    private void OnClickLogout(){
        sharedpreferences.edit().clear().commit();
        Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(loginIntent);
    }



    private void SetupNavBar(){
        //setup fragment for nav
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_category, R.id.nav_chat, R.id.nav_account,R.id.nav_admin)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        this.mainNavigationView = navigationView;

        TextView tvUsername = navigationView.getHeaderView(0).findViewById(R.id.tvUsername);
        tvUsername.setText("Account: "+username);
        TextView tvUserInfo = navigationView.getHeaderView(0).findViewById(R.id.tvUserInfo);
        tvUserInfo.setText(credential);
        //check user is admin ?
        Menu navigationMenu = navigationView.getMenu();
        navigationMenu.findItem(R.id.nav_admin).setVisible(role.equals("Admin"));
    }



    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}