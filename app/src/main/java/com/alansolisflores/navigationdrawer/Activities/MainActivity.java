package com.alansolisflores.navigationdrawer.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.alansolisflores.navigationdrawer.Fragments.AlertFragment;
import com.alansolisflores.navigationdrawer.Fragments.EmailFragment;
import com.alansolisflores.navigationdrawer.Fragments.InfoFragment;
import com.alansolisflores.navigationdrawer.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, DrawerLayout.DrawerListener {

    private DrawerLayout drawerLayout;

   private Toolbar toolbar;

   private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolbar();
        this.drawerLayout = findViewById(R.id.drawer_layout);
        this.navigationView = findViewById(R.id.nav_view);
        this.navigationView.setNavigationItemSelectedListener(this);
        this.drawerLayout.addDrawerListener(this);
    }

    private void setToolbar()
    {
        this.toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.email_title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                //Open left menu
                this.drawerLayout.openDrawer(GravityCompat.START);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Fragment fragment = null;

        switch (menuItem.getItemId()){
            case R.id.emailItem:
                fragment = new EmailFragment();
                break;
            case R.id.dialerItem:
                Toast.makeText(this,menuItem.getTitle(),Toast.LENGTH_SHORT).show();
                break;
            case R.id.alertItem:
                fragment = new AlertFragment();
                break;
            case R.id.infoItem:
                fragment = new InfoFragment();
                break;
            case R.id.mapItem:
                Toast.makeText(this,menuItem.getTitle(),Toast.LENGTH_SHORT).show();
                break;
        }

        if(fragment != null){
              getSupportFragmentManager()
                      .beginTransaction()
                      .add(R.id.nav_host_fragment,fragment)
                      .commit();

              menuItem.setChecked(true);
              getSupportActionBar().setTitle(menuItem.getTitle());
              this.drawerLayout.closeDrawers();
        }

        return true;
    }

    @Override
    public void onDrawerSlide(@NonNull View view, float v) {

    }

    @Override
    public void onDrawerOpened(@NonNull View view) {
        Toast.makeText(this,R.string.open_drawer_message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDrawerClosed(@NonNull View view) {
        Toast.makeText(this,R.string.close_drawer_message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDrawerStateChanged(int i) {

    }

    public void toggleDrawer(boolean isOpen){
       this.drawerLayout.closeDrawers();
    }
}
