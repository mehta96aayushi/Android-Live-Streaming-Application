package com.example.dell.livestreaming;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Dell on 02-04-2017.
 */

public class Homepage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage_layout);
    }
    public void broadcastlive(View view)
    {
        Intent intent1=new Intent(this,broadcastlive.class);
        startActivity(intent1);
    }
    public void watchlive(View view)
    {
        Intent intent2=new Intent(this,watchlive.class);
        startActivity(intent2);
    }
}
