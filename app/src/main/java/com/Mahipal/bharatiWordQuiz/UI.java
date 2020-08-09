package com.Mahipal.bharatiWordQuiz;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.MenuItem;
import android.widget.Toast;

import com.Mahipal.bharatiWordQuiz.music.MusicService;
import com.Mahipal.bharatiWordQuiz.ui.home.HomeFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class UI extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    boolean mIsBound = false;
    NavigationView navigationView;
    final MusicService[] mServ = new MusicService[1];
    Boolean flag1;
    ServiceConnection Scon = new ServiceConnection() {

        public void onServiceConnected(ComponentName name, IBinder binder) {
            mServ[0] = ((MusicService.ServiceBinder) binder).getService();
        }

        public void onServiceDisconnected(ComponentName name) {
            mServ[0] = null;
        }
    };
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u_i);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        doBindService();
        Intent music = new Intent();
        music.setClass(this,MusicService.class);
        startService(music);

        navigationView.setNavigationItemSelectedListener(this);

        getSupportActionBar().setTitle(getResources().getString(R.string.menu_home));
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container1,new HomeFragment()).commit();
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            getSupportActionBar().setTitle(getResources().getString(R.string.menu_home));
            doBindService();
            Intent music = new Intent();
            music.setClass(this,MusicService.class);
            startService(music);
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container1,new HomeFragment()).commit();
        }  else if (id == R.id.nav_about) {
            final Dialog dialog = new Dialog(UI.this);

            dialog.setContentView(R.layout.fragment_aboutus);
            dialog.show();
        }
        else if (id == R.id.nav_test1) {
            doBindService();
            Intent music = new Intent();
            music.setClass(this,MusicService.class);
            stopService(music);
            startActivity(new Intent(UI.this,QuizStartActicity.class));

        }
        else if (id == R.id.nav_test2) {
            doBindService();
            Intent music = new Intent();
            music.setClass(this,MusicService.class);
            stopService(music);
            startActivity(new Intent(UI.this,QuixStartActivity2.class));

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    public void doBindService(){
        bindService(new Intent(this,MusicService.class),
                Scon,Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    public void doUnbindService()
    {
        if(mIsBound)
        {
            unbindService(Scon);
            mIsBound = false;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else {
            if (getSupportActionBar().getTitle().equals(getResources().getString(R.string.menu_home))) {
                doUnbindService();
                Intent music = new Intent();
                music.setClass(this,MusicService.class);
                stopService(music);

                startActivity(new Intent(UI.this,ExitPage.class));
            }
            else {
                doBindService();
                Intent music = new Intent();
                music.setClass(this,MusicService.class);
                startService(music);
                navigationView.setCheckedItem(R.id.nav_home);
                getSupportActionBar().setTitle(getResources().getString(R.string.menu_home));
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container1,new HomeFragment()).commit();

            }
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        doBindService();
        Intent music = new Intent();
        music.setClass(this,MusicService.class);
        startService(music);
    }
    @Override
    protected void onStop() {
        super.onStop();
        doUnbindService();
        Intent music = new Intent();
        music.setClass(this,MusicService.class);
        stopService(music);
    }
}