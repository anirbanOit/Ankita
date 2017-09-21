package com.oit.test.gallery;


        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.text.InputType;
        import android.view.View;
        import android.view.inputmethod.InputMethodManager;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.RelativeLayout;
        import java.util.regex.Matcher;
        import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        final EditText username = (EditText) findViewById(R.id.email);
        final EditText password = (EditText) findViewById(R.id.pwd);

        final String ePattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+[a-z]";

        final String pPattern = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";

        final ImageView show = (ImageView)findViewById(R.id.show);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!flag){
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    show.setImageResource(R.drawable.hide_pass);
                    password.setSelection(password.getText().length());
                    flag = true;
                }else {
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    show.setImageResource(R.drawable.show_pass);
                    password.setSelection(password.getText().length());
                    flag = false;
                }
            }
        });

        Button btnWelcome=(Button)findViewById(R.id.btnWelcome);
        btnWelcome.setOnClickListener(new View.OnClickListener() {


            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if(username.getText().toString().trim().length()==0){
                    username.setError("Username is not entered");
                    username.requestFocus();
                }
                else
                {
                    Pattern p = Pattern.compile(ePattern);
                    Matcher m = p.matcher(username.getText().toString().trim());

                    if(!m.matches()){
                        username.setError("E-mail must contain only letters, numbers, '@' and '.'");
                        username.requestFocus();
                    }
                }

                if(password.getText().toString().trim().length()==0){
                    password.setError("Password is not entered");
                    password.requestFocus();
                }
                else
                {
                    Pattern p1 = Pattern.compile(pPattern);
                    Matcher m1 = p1.matcher(password.getText().toString().trim());
                    if(!m1.matches()){
                        password.setError("Password must be minimum of 8 characters and must contain at least one capital letter, one number and one special character");
                        password.requestFocus();
                    }
                    else{

                        //Toast.makeText(MainActivity.this,"Welcome Admin",Toast.LENGTH_SHORT).show();

                        intent = new Intent(MainActivity.this,WelcomeActivity.class);
                        startActivity(intent);
                        finish();

                    }
                }
            }
        });

        RelativeLayout layout = (RelativeLayout)findViewById(R.id.layout);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        });
    }
}