package com.example.dell.livestreaming;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Dell on 02-04-2017.
 */
public class MainActivity extends AppCompatActivity {
    private ActionBarDrawerToggle mToggle;
    private static String url = "http://bin2580.16mb.com/library_services/akash/service/sdp_livestreaming_login.php";
    public static final String ARRAY_NAME = "UserDetails";
    private final static String TAG = "SID";
    private static final String TAG_STATUS = "status";
    private static final String TAG_ERROR = "error_msg";
   // private static final String TAG_SINGLE_BRANCH = "SINGLE_BRANCH";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button rter = (Button) findViewById(R.id.button2);
        Button lgin = (Button) findViewById(R.id.button1);
        lgin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoadData().execute();
            }
        });
        rter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Register.class));
            }
        });
    }

    private class LoadData extends AsyncTask<Void, Void, Void> {
        String al[];
        String al1[];
        String us[];
        String pw[];
        boolean flag = false;
        EditText edit_text = (EditText) findViewById(R.id.editText2);
        String user = edit_text.getText().toString();
        EditText edit_text1 = (EditText) findViewById(R.id.editText3);
        String pass = edit_text1.getText().toString();
        ProgressDialog pDialog;
        JSONArray users = null;
        Intent j = new Intent(MainActivity.this, Homepage.class);

        @Override
        protected Void doInBackground(Void... params) {
            Looper.prepare();
            Handler handler = new Handler();
            handler = new Handler(getApplicationContext().getMainLooper());
            WebRequest webreq = new WebRequest();
            String jsonStr = webreq.makeWebServiceCall(url, WebRequest.GET);
            ParseJSON(jsonStr);
            for (int i = 0; i < al.length; i++) {
                if (user.equals(us[i]) && pass.equals(pw[i])) {
                    flag = true;
                    SessionManager sm = new SessionManager(getApplicationContext());
                    sm.setUserID(user);
                    startActivity(j);
                }
            }
            if (flag == false) {
                handler.post(new Runnable() {
                    public void run() {
                        Toast.makeText(MainActivity.this, "Incorrect Username or Password !", Toast.LENGTH_LONG).show();
                    }
                });
            }
            Log.d(TAG, "json string" + jsonStr);
            return null;
        }

        private void ParseJSON(String json) {


            if (json != null) {
                try {
                    JSONObject jsonObj = new JSONObject(json);
                    users = jsonObj.getJSONArray(ARRAY_NAME);

                    al = new String[users.length()];
                    al1 = new String[users.length()];
                    for (int i = 0; i < users.length(); i++) {
                        JSONObject c = users.getJSONObject(i);
                        String b = c.getString("username");
                        al[i] = b;
                        String k = c.getString("password");
                        al1[i] = k;
                    }
                    us = al.clone();
                    pw = al1.clone();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }
        }
    }



}






