package com.tmsnith.emotionsense.Utils;

import android.content.Context;
import android.content.SharedPreferences;


public class SharedPref{
        private SharedPreferences sharedPreferences;
        private SharedPreferences.Editor editor;

        private static final String PREF_NAME="UserInfo";
        private static final String LOGIN_STATUS="loginstatus";
        private static final String USER_ID="apikey";
        private static final String url="URL";
        private static final String URL_TO_SEND = "UrlToSend";

        public SharedPref(Context context){
            sharedPreferences = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
        }

        public void setLoginStatus(boolean isLogIn){
            editor.putBoolean(LOGIN_STATUS,isLogIn);
            editor.commit();
        }

        public boolean getLoginStatus(){
            return sharedPreferences.getBoolean(LOGIN_STATUS,false);
        }

        public void setUserId(String userId){
            editor.putString(USER_ID,userId);
            editor.commit();
        }

        public String getUserId(){
            return sharedPreferences.getString(USER_ID,"");
        }

        public void setUrlToSend(String UrlToSend){
            editor.putString(URL_TO_SEND,UrlToSend);
            editor.commit();
        }
        public String getUrlToSend() {
            return sharedPreferences.getString(URL_TO_SEND,"");
        }
}
