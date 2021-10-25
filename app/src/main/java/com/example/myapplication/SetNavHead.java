package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class SetNavHead extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_header_main);

        long User_Id = getIntent().getLongExtra("USER_ID", 0);
        String Nickname = getIntent().getStringExtra("NICKNAME");
        String plmage = getIntent().getStringExtra("PROFILE_IMG");
        String Email = getIntent().getStringExtra("Email");
        Log.e("allN",""+ Nickname);
        Log.e("allN",""+ Email);

        TextView test = (TextView)findViewById(R.id.nick);
        test.setText(Nickname);

        Intent intent = new Intent(SetNavHead.this, MainActivity.class);
        startActivity(intent);


    }

}
