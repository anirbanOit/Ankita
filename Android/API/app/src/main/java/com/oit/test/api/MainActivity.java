package com.oit.test.api;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static int RESULT_LOAD = 1;
    private static final int REQUEST_WRITE_PERMISSION = 786;
    static boolean setFemaleFlag = false;
    static boolean setMaleFlag = false;
    private APIService mAPIService;
    byte[] imageBytes;
    String imageString, img_Decodable_Str, encoded;
    ImageView imgView, dob;
    private DatePickerDialog datePickerDialog;
    EditText dateEditText, fname, lname;
    Intent intent;
    Button btn_female, btn_male;
    Spinner dept;
    Calendar dateSelected = Calendar.getInstance();
    TextView gender;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermission();

        fname = (EditText) findViewById(R.id.txt_fname);
        lname = (EditText) findViewById(R.id.txt_lname);
        gender = (TextView) findViewById(R.id.gender);
        dob = (ImageView) findViewById(R.id.img_dob);
        imgView = (ImageView) findViewById(R.id.photo);
        mAPIService = ApiUtils.getAPIService();
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadImagefromGallery(view);
            }
        });
        dateEditText = (EditText) findViewById(R.id.txt_dob);
        btn_female = (Button) findViewById(R.id.btn_female);
        btn_male = (Button) findViewById(R.id.btn_male);
        dept = (Spinner) findViewById(R.id.drop_dept);

        valid();
        gen();
        datePicker();
        department();
        submit();
        preview();

        LinearLayout linear_layout = (LinearLayout) findViewById(R.id.linear_layout);
        linear_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        });
    }

    //send post
    public void sendPost(final String firstName, String lastName, final String gender, String date, final String photo, final String department) {

        Post model = new Post();
        model.setFirstName(firstName);
        model.setLastName(lastName);
        model.setGender(gender);
        model.setDob(date);
        model.setPhoto(photo);
        model.setDept(department);

        mAPIService.savePost(model).enqueue(new Callback<Post>() {

            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                //Toast.makeText(MainActivity.this, "Successful", Toast.LENGTH_LONG).show();
                Log.d(TAG, "post submitted to API." + response.body().toString());
                AlertDialog.Builder builder;
                builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Add User")
                        .setMessage("Data has been inserted successfully.")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete

                                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                                startActivity(intent);
                                    /*fname.setText(null);
                                    lname.setText(null);

                                    ShapeDrawable shapedrawable = new ShapeDrawable();
                                    shapedrawable.setShape(new RectShape());
                                    shapedrawable.getPaint().setColor(Color.BLACK);
                                    shapedrawable.getPaint().setStrokeWidth(10f);
                                    shapedrawable.getPaint().setStyle(Paint.Style.STROKE);

                                    btn_female.setBackgroundDrawable(shapedrawable);
                                    btn_male.setBackgroundDrawable(shapedrawable);
                                    dept.setSelection(0);
                                    dateEditText.setText(null);
                                    imgView.setImageResource(R.drawable.img);*/
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .show();
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.d(TAG, "Unable to submit post to API.");
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        String item = adapterView.getItemAtPosition(position).toString();
        Toast.makeText(adapterView.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) { }

    //to load image from gallery
    public void loadImagefromGallery(View view) {
        // Create intent to Open Image applications like Gallery, Google Photos
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        startActivityForResult(galleryIntent, RESULT_LOAD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // When an Image is picked
        if (requestCode == RESULT_LOAD && resultCode == RESULT_OK && null != data) {
            // Get the Image from data

            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            // Get the cursor
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            // Move to first row
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            img_Decodable_Str = cursor.getString(columnIndex);
            cursor.close();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Bitmap bitmap = BitmapFactory.decodeFile(img_Decodable_Str);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            imageBytes = baos.toByteArray();
            imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);
            encoded = imageString;

            imgView = (ImageView) findViewById(R.id.photo);
            // Set the Image in ImageView after decoding the String
            imgView.setImageBitmap(BitmapFactory.decodeFile(img_Decodable_Str));
            img_Decodable_Str = null;

        } else {
            Toast.makeText(this, "Pick Image", Toast.LENGTH_LONG).show();
        }
    }

    //for permission
    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_PERMISSION);
        }
    }

    //Validating First Name and Last Name
    void valid() {

        final String ePattern = "^[A-Z]+[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$";
        final String datePattern = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
        fname.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        lname.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);

        /* Validation of Fname  */
        if (fname != null) {
            fname.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    Pattern p = Pattern.compile(ePattern);
                    Matcher m = p.matcher(fname.getText().toString().trim());

                    if (!m.matches()) {
                        fname.setError("First letter should be capital and must contain atlest 2 characters");
                        fname.requestFocus();
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {
                }
            });
        }

        /* Validation of Lname  */
        lname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (fname.getText().toString().trim().length() == 0) {
                    fname.setError("Firstname is not entered");
                    fname.requestFocus();
                } else {
                    Pattern p = Pattern.compile(ePattern);
                    Matcher m = p.matcher(lname.getText().toString().trim());

                    if (!m.matches()) {
                        lname.setError("First letter should be capital and must contain atlest 2 characters");
                        lname.requestFocus();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        /* Validation of Date  */
        dateEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (lname.getText().toString().trim().length() == 0) {
                    lname.setError("Lastname is not entered");
                    lname.requestFocus();
                } else {
                    Pattern p = Pattern.compile(datePattern);
                    Matcher m = p.matcher(dateEditText.getText().toString().trim());

                    if (!m.matches()) {
                        dateEditText.setError("Give correct date format");
                        dateEditText.requestFocus();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    //for Gender selection
    public void gen() {

        //Female select
        btn_female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setMaleFlag = new GenderRadio(btn_female, btn_male).SettingGenderFlag(true, setMaleFlag);
                gender.setText(R.string.female);
            }
        });

        //Male select
        btn_male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFemaleFlag = new GenderRadio(btn_female, btn_male).SettingGenderFlag(false, true);
                gender.setText(R.string.male);
            }
        });
    }

    //to set the date
    void datePicker() {
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int y = dateSelected.get(Calendar.YEAR);
                int m = dateSelected.get(Calendar.MONTH);
                int d = dateSelected.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(MainActivity.this, AlertDialog.THEME_HOLO_DARK, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String s = (monthOfYear + 1) + "/" + (dayOfMonth) + "/" + year;
                        dateEditText.setText(s);
                    }
                }, y, m, d);
                datePickerDialog.getDatePicker().setMaxDate(dateSelected.getTimeInMillis());

                datePickerDialog.show();
            }
        });
    }

    //to select Department from the spinner
    void department() {
        dept.getOnItemSelectedListener();
        List<String> categories = new ArrayList<String>();
        categories.add("Options");
        categories.add("HR");
        categories.add("Android");
        categories.add("iOS");
        categories.add("Dot Net");
        categories.add("PHP");
        categories.add("Salesforce");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        dept.setAdapter(dataAdapter);
    }

    void submit() {
        Button btn_submit = (Button) findViewById(R.id.btn_add);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if (fname.equals("") && lname.equals("") && dateEditText.equals("")) {

                    final String firstName = fname.getText().toString().trim();
                    final String lastName = lname.getText().toString().trim();
                    final String gen = gender.getText().toString().trim();
                    final String date = dateEditText.getText().toString().trim();
                    final String dep = dept.getSelectedItem().toString().trim();


                    if (encoded == null) {
                        AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
                        adb.setTitle("WARNING");
                        adb.setMessage("Are you sure you want to upload with Default Image? ");
                        adb.setCancelable(false);
                        adb.setPositiveButton("YES,I'm Sure", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                progressDialog = new ProgressDialog(MainActivity.this);
                                progressDialog.setMessage("Please Wait...");
                                //progressDialog.show();
                                if (gen.equals("Male")) {
                                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.male);
                                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                                    byte[] imageBytes = byteArrayOutputStream.toByteArray();
                                    encoded = Base64.encodeToString(imageBytes, Base64.DEFAULT);

                                } else {
                                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.female);
                                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                                    byte[] imageBytes = byteArrayOutputStream.toByteArray();
                                    encoded = Base64.encodeToString(imageBytes, Base64.DEFAULT);

                                }
                                sendPost(firstName, lastName, gen, date, encoded, dep);
                            }
                        });
                        adb.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
                                adb.setMessage("Please Select One Profile Image ");
                                dialog.dismiss();
                            }
                        });
                        adb.setIcon(android.R.drawable.ic_dialog_alert);
                        adb.show();
                    }
                    else {
                        sendPost(firstName, lastName, gen, date, encoded, dep);
                    }
                //}
                /*else {
                    Toast.makeText(MainActivity.this, "Give valid data to Add", Toast.LENGTH_LONG).show();
                }*/
            }
        });
    }

    //to go to Preview page
    void preview() {
        Button btn_list = (Button) findViewById(R.id.btn_preview);
        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, PreviewActivity.class);
                startActivity(intent);
                //finish();
            }
        });
    }

    @Override
    public void onBackPressed() {

        //  super.onBackPressed();
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        // Setting Dialog Title
        alertDialog.setTitle("LOGOUT");
        alertDialog.setMessage("Do you want to Logout??");
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                //dialog.cancel();
            }
        });
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                finish();
            }
        });
        alertDialog.show();
    }
}