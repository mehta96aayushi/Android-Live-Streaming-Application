package com.example.dell.livestreaming;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by Dell on 02-04-2017.
 */

public class SessionManager {
    private static String TAG = SessionManager.class.getSimpleName();

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "userid";
    private static final String KEY_USERID = "isLoggedIn";
    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setUserID(String userid) {

        editor.putString(KEY_USERID,userid);
        editor.commit();
        Log.d(TAG, "User login session modified!");
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(KEY_USERID, false);
    }

    public String getUserID()
    {
        return pref.getString(KEY_USERID, "");
    }

    public void removeUser()
    {
        if(pref.getString(KEY_USERID,"")!=null)
        {
            editor.remove(KEY_USERID);
            editor.commit();
        }
    }
}
