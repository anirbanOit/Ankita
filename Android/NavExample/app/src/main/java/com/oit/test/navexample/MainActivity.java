package com.oit.test.navexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout dLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar tool;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setNavigationDrawer();

        tool = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tool);

        dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, dLayout, R.string.Open, R.string.Close);
        dLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setNavigationDrawer() {
        dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.navigation);

        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                Fragment frag = null;
                int itemId = menuItem.getItemId();
                /*if (itemId == R.id.first) {
                    frag = new FirstFragment();
                } else if (itemId == R.id.second) {
                    frag = new SecondFragment();
                } else if (itemId == R.id.third) {
                    frag = new ThirdFragment();
                }*/

                switch (itemId) {
                    case R.id.first:
                        intent = new Intent(MainActivity.this, FirstFragment.class);
                        startActivity(intent);
                        break;
                    case R.id.second:
                        intent = new Intent(MainActivity.this, SecondFragment.class);
                        startActivity(intent);
                        break;
                    case R.id.third:
                        intent = new Intent(MainActivity.this, ThirdFragment.class);
                        startActivity(intent);
                        break;
                    default:
                        //return super.onOptionsItemSelected(item);
                        return true;
                }

                Toast.makeText(getApplicationContext(), menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                if (frag != null) {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    //transaction.replace(R.id.frame, frag); // replace a Fragment with Frame Layout
                    transaction.commit(); // commit the changes
                    dLayout.closeDrawers(); // close the all open Drawer Views
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        //return actionBarDrawerToggle.onOptionsItemSelected(item) || return super.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }
}