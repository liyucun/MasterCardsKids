package com.yucun.mastercardsforkids.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.squareup.picasso.Picasso;
import com.yucun.mastercardsforkids.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    @Bind(R.id.name)
    EditText name;
    @Bind(R.id.password)
    EditText password;
    Activity activity = this;
    @Bind(R.id.confirm_login)
    TextView confirmLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        Typeface font = Typeface.createFromAsset(this.getAssets(), "fonts/font.ttf");
        name.setTypeface(font);
        password.setTypeface(font);
        confirmLogin.setTypeface(font);


        confirmLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler=new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog = ProgressDialog.show(activity, null, "Loading...", true, false);
                    }
                });
                ParseUser.logInInBackground(name.getText().toString(), password.getText().toString(), new LogInCallback() {
            public void done(ParseUser user, ParseException e) {

                if (user != null) {
                    // Hooray! The user is logged in.
                    progressDialog.dismiss();
                    startActivity(new Intent(activity, MainActivity.class));

                } else {
                    progressDialog.dismiss();
                    // Signup failed. Look at the ParseException to see what happened.
                    Toast.makeText(getApplication(),"Cannot find the user",Toast.LENGTH_SHORT).show();
                }
            }
        });



            }
        });
    }

}
