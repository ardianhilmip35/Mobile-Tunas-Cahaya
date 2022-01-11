package com.ardianhilmip.mobiletunascahaya;

import android.content.Context;
import android.content.SharedPreferences;

import com.ardianhilmip.mobiletunascahaya.ModelResponse.User;

public class SharedPrefManager {
    private static String SHARED_PREF_NAME="tunascahaya";
    private SharedPreferences sharedPreferences;
    Context context;
    private SharedPreferences.Editor editor;

    public SharedPrefManager(Context context){
        this.context = context;
    }

    public void saveUser(User user){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putInt("id_pelanggan",user.getId_pelanggan());
        editor.putString("nama",user.getNama());
        editor.putString("email",user.getEmail());
        editor.putString("nomerhp",user.getNomerhp());
        editor.putString("user_password",user.getUser_password());
        editor.putString("alamat_user",user.getAlamat_user());
        editor.putBoolean("logged",true);
        editor.apply();
    }

    public boolean isLoggedIn(){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("logged",false);
    }

    public User getUser(){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return new User(sharedPreferences.getInt("id_pelanggan",-1),
                sharedPreferences.getString("nama",null),
                sharedPreferences.getString("email",null),
                sharedPreferences.getString("nomerhp",null),
                sharedPreferences.getString("user_password",null),
                sharedPreferences.getString("alamat_user",null));
    }


    public void logout(){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
