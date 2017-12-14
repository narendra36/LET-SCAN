package com.tmsnith.emotionsense.Service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;


import com.cloudinary.Cloudinary;
import com.cloudinary.android.Utils;
import com.cloudinary.utils.ObjectUtils;

import com.tmsnith.emotionsense.Utils.SharedPref;


import java.util.Map;


public class UploadService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    private static final String UPLOAD_SERVICE = "Upload";
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String URL_IMAGE = "imageUrl";
    private static final String UPLOADING_START = "start";
    private static final String UPLOADING_FINISH = "finish";
    private static final String UPLOADING_ERROR = "error";
    private static final String REGISTER_ROLL_NO = "rollNoRegister";
    private static final String ROLL_NO = "rollNo";
    private static final String WORK = "work";
    private String UrlToSend;
    private SharedPref sharedPref;

    public UploadService() {
        super("UploadService");
    }

    public UploadService(String name) {
        super(name);

    }

    @Override
    protected void onHandleIntent(Intent intent) {

        if (intent != null) {
            if (intent.hasExtra(UPLOAD_SERVICE)) {
                String imageUrl = "";
                if (intent.hasExtra(URL_IMAGE)) {
                    imageUrl = intent.getStringExtra(URL_IMAGE);
                    Cloudinary cloudinary = new Cloudinary(Utils.cloudinaryUrlFromContext(getApplicationContext()));
                    try {
                        Intent i = new Intent(UPLOADING_START);
                        i.putExtra(WORK, "Image");
                        //sendBroadcast(i);
                        Map map = cloudinary.uploader().upload(imageUrl.trim(), ObjectUtils.emptyMap());
                        Log.d("image", (String) map.get("url"));
                        Toast.makeText(getApplicationContext(), "final url", Toast.LENGTH_SHORT).show();
                        UrlToSend = (String) map.get("url");
                        sharedPref = new SharedPref(getApplicationContext());
                        sharedPref.setUrlToSend(UrlToSend);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Intent i = new Intent();
                        i.setAction(UPLOADING_ERROR);
                        i.putExtra(WORK, "Image");
                        sendBroadcast(i);
                    }
                } else {
                    Intent i = new Intent(UPLOADING_START);
                    i.putExtra(WORK, "Image");
                    sendBroadcast(i);
                }

            }
        }
    }


}
