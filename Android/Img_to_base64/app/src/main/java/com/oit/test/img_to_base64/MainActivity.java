package com.oit.test.img_to_base64;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    Button btn1;
    Button btn2;
    //TextView tv;
    ImageView image;
    byte[] imageBytes;
    String imageString;
    EditText et;
    String TAG = "MainActivity ImageStr:";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                imageBytes = baos.toByteArray();
                imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                //tv = (TextView) findViewById(R.id.tv_base);
                //tv.setText(imageString);
                //tv.setMovementMethod(new ScrollingMovementMethod());
                et = (EditText)findViewById(R.id.et);
                et.setText(imageString);
                et.setMovementMethod(new ScrollingMovementMethod());
                Log.d(TAG, imageString);
                image = (ImageView) findViewById(R.id.img);
                image.setImageDrawable(null);
            }
        });

        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                imageBytes = Base64.decode(imageString, Base64.DEFAULT);
                Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                image.setImageBitmap(decodedImage);
                //tv = (TextView) findViewById(R.id.tv_base);
                //tv.setText(null);
                et.setText(null);
            }
        });

        /*tv = (TextView) findViewById(R.id.tv_base);
        tv.setMovementMethod(new ScrollingMovementMethod());*/
    }
}
