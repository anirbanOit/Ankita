package com.oit.test.api;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PreviewActivity extends AppCompatActivity implements ClickListener{

    APIService api;
    public EditText search;
    MyAdapter adapter;
    RecyclerView showdata_rv;
    ProgressDialog p;
    List<Post> studentslist = new ArrayList<>();
    List<Post> mFilteredList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        p = new ProgressDialog(PreviewActivity.this);
        p.setMessage("Loading...");
        p.show();
        search = (EditText) findViewById(R.id.et_search);
        showdata_rv = (RecyclerView) findViewById(R.id.showdata_lv);
        api = RetroClient.getApiService();
        Call<List<Post>> call = api.getMyJSON();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                studentslist.addAll(response.body());
                mFilteredList.addAll(response.body());

                List Studentlist = new ArrayList<Post>();
                for (int i = 0; i < studentslist.size(); i++) {
                    Post obj;
                    obj = (Post) studentslist.get(i);
                    obj.setDecodedImage(decodeImage(obj.getPhoto()));
                    Studentlist.add(obj);
                }

                setAdapter();
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(PreviewActivity.this, "failure", Toast.LENGTH_LONG).show();
            }
        });

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String srch;
                srch = charSequence.toString().toLowerCase();
                mFilteredList.clear();
                if (srch.isEmpty()) {
                    mFilteredList.addAll(studentslist);
                } else {
                    for (Post student : studentslist) {
                        if (student.getFirstName().toLowerCase().contains(srch) || student.getLastName().toLowerCase().contains(srch)) {
                            mFilteredList.add(student);
                        }
                    }
                    if (mFilteredList.isEmpty()) {
                        Toast.makeText(PreviewActivity.this, "No Data Found", Toast.LENGTH_LONG).show();
                    }
                }
                setAdapter();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        LinearLayout linear_layout = (LinearLayout) findViewById(R.id.linear_Layout);
        linear_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        });
    }

    private void setAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(PreviewActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        showdata_rv.setLayoutManager(layoutManager);
        adapter = new MyAdapter(mFilteredList, PreviewActivity.this);
        showdata_rv.setAdapter(adapter);
    }

    @Override
    public void showDialog(View v, int position) {
        final Dialog dialog = new Dialog(v.getContext());
        dialog.setContentView(R.layout.dialog);
        // set the custom dialog components - text, image and button
        ImageView pic = dialog.findViewById(R.id.pic);
        pic.setImageBitmap(studentslist.get(position).getDecodedImage());
        TextView name = dialog.findViewById(R.id.name);
        name.setText(studentslist.get(position).getFirstName() + " " + studentslist.get(position).getLastName());
        TextView gender = dialog.findViewById(R.id.gender);
        gender.setText(studentslist.get(position).getGender());
        TextView dob = dialog.findViewById(R.id.dob);
        dob.setText(studentslist.get(position).getDob());
        TextView depart = dialog.findViewById(R.id.depart);
        depart.setText(studentslist.get(position).getDept());
        dialog.show();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name_tv;
        ImageView image;
        TextView age;
        TextView dept;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            name_tv = itemView.findViewById(R.id.name_tv);
            image = itemView.findViewById(R.id.studphoto_iv);
            age = itemView.findViewById(R.id.age_tv);
            dept = itemView.findViewById(R.id.dept_tv);
        }

        @Override
        public void onClick(View view) {
            showDialog(view, getPosition());
        }
    }

    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        private List<Post> studentslist;

        public MyAdapter(List<Post> studentslist, PreviewActivity previewActivity) {
            this.studentslist = studentslist;
        }

        @Override

        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = getLayoutInflater();
            View v = inflater.inflate(R.layout.row_list, parent, false);
            return new MyViewHolder(v);

        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {

            p.dismiss();
            holder.name_tv.setText(studentslist.get(position).getFirstName() + " " + studentslist.get(position).getLastName());
            age_calculation(holder, studentslist.get(position).getDob());
            holder.dept.setText(studentslist.get(position).getDept());
            holder.image.setImageBitmap(studentslist.get(position).getDecodedImage());
        }

        @Override
        public int getItemCount() {

            return studentslist.size();
        }

        //age calculation
        void age_calculation(MyViewHolder holder, String dob) {
            char seperator;
            if (dob.matches("([0-9]{2}).*")) {
                seperator = dob.charAt(2);
            } else {
                seperator = dob.charAt(1);
            }
            String[] dateParts = dob.split(String.valueOf(seperator));
            //String day = dateParts[1];
            String month = dateParts[0];
            String year = dateParts[2];
            //Toast.makeText(Main2Activity.this," " + seperator,Toast.LENGTH_SHORT).show();
            int monthint = Integer.parseInt(month);
            int yearint = Integer.parseInt(year);
            Calendar c = Calendar.getInstance();
            int current = c.get(Calendar.YEAR);
            int monthcur = c.get(Calendar.MONTH);
            if (monthint < monthcur) {
                holder.age.setText("" + (current - yearint) + " years");
            } else if (current != yearint) {
                holder.age.setText("" + ((current - yearint) - 1) + " years");
            } else {
                holder.age.setText("" + (current - yearint) + " years");
            }
        }

    }

    //base64 to image code
    public Bitmap decodeImage(String imageString) {
        byte[] imageBytes = android.util.Base64.decode(imageString, android.util.Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        return decodedImage;
    }


}