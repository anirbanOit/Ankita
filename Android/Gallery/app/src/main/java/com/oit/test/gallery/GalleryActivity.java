
package com.oit.test.gallery;


import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;



public class GalleryActivity extends Activity {

    GridView grid;
    FloatingActionButton fab;

    String[] web = {
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "11",
            "12",
            "13",
            "14",
            "15",
            "16",
            "17",
            "18",
            "19",
            "20",
            "21"

    } ;
    int[] imageId = {
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e,
            R.drawable.f,
            R.drawable.g,
            R.drawable.h,
            R.drawable.i,
            R.drawable.j,
            R.drawable.k,
            R.drawable.l,
            R.drawable.m,
            R.drawable.n,
            R.drawable.o,
            R.drawable.p,
            R.drawable.q,
            R.drawable.r,
            R.drawable.s,
            R.drawable.t,
            R.drawable.u,

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomGrid adapter = new CustomGrid(GalleryActivity.this, web, imageId);
        grid=findViewById(R.id.grid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(GalleryActivity.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();

            }
        });

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action
                Intent intent = new Intent(GalleryActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}