package com.tmsnith.emotionsense.Fragments;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tmsnith.emotionsense.R;
import com.tmsnith.emotionsense.RequestModels.DocumentModel;
import com.tmsnith.emotionsense.RequestModels.ImageUrlModel;
import com.tmsnith.emotionsense.RequestModels.SingleDocument;
import com.tmsnith.emotionsense.Resoponse.FileUploadResponseModel;
import com.tmsnith.emotionsense.Resoponse.SingleFaceImageResponse;
import com.tmsnith.emotionsense.Resoponse.TextResponse;
import com.tmsnith.emotionsense.Service.UploadService;
import com.tmsnith.emotionsense.Utils.ApiInterface;
import com.tmsnith.emotionsense.Utils.SharedPref;
import com.tmsnith.emotionsense.Utils.Util;

import com.ipaulpro.afilechooser.utils.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

//import  com.ipaulpro.afilechooser.utils
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.app.Activity.RESULT_OK;
import static android.provider.ContactsContract.CommonDataKinds.Organization.TITLE;
import static android.provider.MediaStore.Video.VideoColumns.DESCRIPTION;
import static android.support.v7.appcompat.R.id.add;
import static android.support.v7.appcompat.R.id.contentPanel;
import static com.ipaulpro.afilechooser.R.layout.file;

/**
 * A simple {@link Fragment} subclass.
 */
public class imageFragment extends Fragment {


    private Button upload_button;

    public imageFragment() {
        // Required empty public constructor
    }

    SharedPref sharedPref;
    private SharedPreferences.Editor editor;
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final String TAG = "Upload News Feed";
    private Button select_image, upload_image;
    private ImageView image_to_upload;
    private static final String UPLOAD_SERVICE = "Upload";
    private static final String URL_IMAGE = "imageUrl";
    private static final String URL_TO_SEND = "UrlToSend";
    private static final String WORK = "work";
    private String imageUrl = "";
    int PICK_IMAGE_MULTIPLE = 1;
    public String UrlToSend = "";
    final String API = "587f02304e5442a78a054fb4e36ba173";
    final String Content_type = "application/json";
    private ArrayList<SingleFaceImageResponse> singleImageResponse;
    private SingleFaceImageResponse singleFaceImageResponse;
    private ProgressBar pb_anger, pb_contempt, pb_disgust, pb_fear, pb_happiness, pb_neutral, pb_sadness, pb_surprise;
    private double anger, contempt, disgust, fear, happiness, neutral, sadness, surprise;
    private TextView per_anger, per_contempt, per_disgust, per_fear, per_happiness, per_neutral, per_sadness, per_surprise;
    ProgressDialog progress;
    MultipartBody.Part file1 ;
    ArrayList<Uri> mArrayUri;
    String pdfUrl;
    ArrayList<RequestBody> rbody;
    ProgressBar loadingbar;
    Button download_btn;
    Uri pdfuri;
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_image, container, false);

        loadingbar = (ProgressBar)v.findViewById(R.id.loading);
        select_image = (Button) v.findViewById(R.id.select_image);
        image_to_upload = (ImageView) v.findViewById(R.id.image_to_upload);
        upload_image = (Button) v.findViewById(R.id.upload_image);
        download_btn = (Button)v.findViewById(R.id.download_btn);
        image_to_upload = (ImageView) v.findViewById(R.id.image_to_upload);
        select_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createChooser();
            }
        });
        download_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //pdfuri = Uri.parse("http://www.google.com"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, pdfuri);
                startActivity(intent);
            }
        });

        upload_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), UploadService.class);
                i.putExtra(UPLOAD_SERVICE, true);
                //String type = imageUrl.getClass().getName();

               //Toast.makeText(getContext(), "image="+imageUrl, Toast.LENGTH_SHORT).show();

                if(imageUrl!="" && imageUrl!=null) {
                    Log.d("image url", "" + imageUrl.toString());

                    /*
                    if (!imageUrl.toString().equals(""))
                        i.putExtra(URL_IMAGE, imageUrl.toString());
                    getActivity().startService(i);
                    sharedPref = new SharedPref(getContext());
                    UrlToSend = sharedPref.getUrlToSend();
                    Log.d("UrlTOSend", " " + UrlToSend);
                    if (UrlToSend.equals("")) {
                        return;
                    }
                    //Toast.makeText(getContext(), "retrofit is calling ", Toast.LENGTH_SHORT).show();*/
                    download_btn.setVisibility(View.GONE);
                    loadingbar.setVisibility(View.VISIBLE);

                    retrofit();
                }
                else
                {
                    Toast.makeText(getContext(), "Please Select Image", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return v;
    }

    public static imageFragment get() {
        return new imageFragment();
    }


    private void createChooser() {
        if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 121);
            }

            return;

        }

        //Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        //intent.setType("image/*");
        //startActivityForResult(Intent.createChooser(intent, "CHOOSE PHOTO"), PICK_IMAGE_REQUEST);
        /*
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"), 1);
        */

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"),1);

    }
/*

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            Log.v("img url", "-----" + filePath);
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor c = getActivity().getContentResolver().query(filePath, filePathColumn, null, null, null);
            c.moveToFirst();
            String imgDecodableString = c.getString(c.getColumnIndex(filePathColumn[0]));
            c.close();
            image_to_upload.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));
            imageUrl = imgDecodableString;
            Log.v("img url", " " + imgDecodableString);


        }
    }
    */

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub

        if(requestCode==PICK_IMAGE_MULTIPLE){

            if(resultCode==RESULT_OK){
                //data.getParcelableArrayExtra(name);
                //If Single image selected then it will fetch from Gallery
                mArrayUri=new ArrayList<Uri>();
                if(data.getData()!=null){

                    Uri mImageUri=data.getData();
                    mArrayUri.add(mImageUri);
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    /*Cursor c = getActivity().getContentResolver().query(mArrayUri.get(0), filePathColumn, null, null, null);

                    c.moveToFirst();
                    String imgDecodableString = c.getString(c.getColumnIndex(filePathColumn[0]));
                    c.close();*/
                    try {
                        image_to_upload.setImageBitmap(BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(mArrayUri.get(0))));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    imageUrl = mArrayUri.get(0).getPath();
                    //Log.v("img url", " " + imgDecodableString);

                    Log.d("single ====", ""+mImageUri.toString());

                }else{
                    if(data.getClipData()!=null){
                        ClipData mClipData=data.getClipData();

                        for(int i=0;i<mClipData.getItemCount();i++){

                            ClipData.Item item = mClipData.getItemAt(i);
                            Uri uri = item.getUri();
                            Log.d("xyzzzzz=",""+uri);
                            mArrayUri.add(uri);

                        }

                        Log.v("LOG_TAG", "Selected Images"+ mArrayUri.size());

                    }

                }

            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }


 /*   @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                okhttp3.MultipartBody.FORM, descriptionString);
    }

    @NonNull
    private MultipartBody.Part prepareFilePart(String partName, Uri fileUri) {
        // https://github.com/iPaulPro/aFileChooser/blob/master/aFileChooser/src/com/ipaulpro/afilechooser/utils/FileUtils.java
        // use the FileUtils to get the actual file by uri
        File file = FileUtils.getFile(getContext(), fileUri);

        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse(getContext().getContentResolver().getType(fileUri)),
                        file
                );

        // MultipartBody.Part is used to send also the actual file name

        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);

    }

*/

    public void retrofit() {

        //ApiInterface apiservice = com.tmsnith.emotionsense.Utils.Util.getRetrofitService();

        /*
        progress = new ProgressDialog(getActivity());
        progress.setTitle("wait ...");
        progress.setMessage("We are processing Your Image.");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setCancelable(false);
        progress.show();


        singleFaceImageResponse = new SingleFaceImageResponse();
        singleImageResponse = new ArrayList<SingleFaceImageResponse>();
        ImageUrlModel request = new ImageUrlModel();
        request.setUrl(UrlToSend);
        Call<ArrayList<SingleFaceImageResponse>> call =null;// apiservice.sendImageUrl(API, Content_type, request);

        call.enqueue(new Callback<ArrayList<SingleFaceImageResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<SingleFaceImageResponse>> call, Response<ArrayList<SingleFaceImageResponse>> response) {
                ArrayList<SingleFaceImageResponse> model = response.body();
                int status = response.code();
                Log.v("hack", model + "");
                //Toast.makeText(getContext(),""+model,Toast.LENGTH_SHORT).show();

                if (model != null && response.isSuccess()) {

                    //Toast.makeText(getContext(),"Success\n"+new Gson().toJson(response),Toast.LENGTH_LONG).show();
                    Log.v("hack", "Success\n" + model);
                    if (model.size() != 0) {
                        anger = model.get(0).getScores().getAnger() * 100;
                        contempt = model.get(0).getScores().getContempt() * 100;
                        disgust = model.get(0).getScores().getDisgust() * 100;
                        fear = model.get(0).getScores().getFear() * 100;
                        happiness = model.get(0).getScores().getHappiness() * 100;
                        neutral = model.get(0).getScores().getNeutral() * 100;
                        sadness = model.get(0).getScores().getSadness() * 100;
                        surprise = model.get(0).getScores().getSurprise() * 100;
                        progress.dismiss();

                        per_anger.setText(String.format("%.2f", anger) + "%");
                        per_contempt.setText(String.format("%.2f", contempt) + "%");
                        per_disgust.setText(String.format("%.2f", disgust) + "%");
                        per_fear.setText(String.format("%.2f", fear) + "%");
                        per_happiness.setText(String.format("%.2f", happiness) + "%");
                        per_neutral.setText(String.format("%.2f", neutral) + "%");
                        per_sadness.setText(String.format("%.2f", sadness) + "%");
                        per_surprise.setText(String.format("%.2f", surprise) + "%");

                        pb_anger.setProgress((int) anger);
                        pb_contempt.setProgress((int) contempt);
                        pb_disgust.setProgress((int) disgust);
                        pb_fear.setProgress((int) fear);
                        pb_happiness.setProgress((int) happiness);
                        pb_neutral.setProgress((int) neutral);
                        pb_sadness.setProgress((int) sadness);
                        pb_surprise.setProgress((int) surprise);
                    } else {
                        progress.dismiss();
                        Toast.makeText(getContext(), "Please Select the Image", Toast.LENGTH_LONG).show();
                        Log.v("hack", "Please Select the Image");
                    }

                } else {
                    progress.dismiss();
                    Toast.makeText(getContext(), "Please Select the Image", Toast.LENGTH_LONG).show();
                    Log.v("hack", "Please Select the Image");

                }
            }

            @Override
            public void onFailure(Call<ArrayList<SingleFaceImageResponse>> call, Throwable t) {
//                bar.setVisibility(View.GONE);
                progress.dismiss();
                Toast.makeText(getContext(), "Some error occurred!!2", Toast.LENGTH_SHORT).show();
                Log.v("hack", "Some error occurred!!2");
            }
        });
        */
        rbody = new ArrayList<>();

       // file1  = prepareFilePart("file1", mArrayUri.get(0));
        //File file = FileUtils.getFile(getContext(), mArrayUri.get(0));
        //upload2(file);

        for (int i=0;i< mArrayUri.size();i++) {
            //parts.add(prepareFilePart("photos"+i, mArrayUri.get(i)));
            File file = FileUtils.getFile(getContext(), mArrayUri.get(i));
            rbody.add(RequestBody.create(MediaType.parse("image/*"), file));
        }
        upload(rbody);
        Log.v("xyz",""+rbody.size());
        //Log.v("xyz1",""+parts.get(0));


// ... possibly add more parts here

/* add the description part within the multipart request
        RequestBody description = createPartFromString("hello, this is description speaking");


// finally, execute the request
        Call<ResponseBody> call = apiservice.uploadMultipleFilesDynamic(description,file1, parts);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                Log.v("Upload", "success");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Upload error:", t.getMessage());
            }
        });*/
    }

 /*   private void upload2(ArrayList<RequestBody> rbody){
        //RequestBody fbody = RequestBody.create(MediaType.parse("image/*"), file);

        /*
        RequestBody fbody = new MultipartBody.Builder()
                .addFormDataPart("files", file.getName(),
                        RequestBody.create(MediaType.parse("image/*"), file))
                .build();
        */

        //MultipartBody.Part body=MultipartBody.Part.createFormData("files",file.getName(),fbody);

//        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), firstNameField.getText().toString());
//        RequestBody id = RequestBody.create(MediaType.parse("text/plain"), AZUtils.getUserId(this));

 /*       ApiInterface apiservice = com.tmsnith.emotionsense.Utils.Util.getRetrofitService();
        Call<FileUploadResponseModel> call=apiservice.postFileAndGetUrl(rbody);

        call.enqueue(new Callback<FileUploadResponseModel>() {
            public void onResponse(Call<FileUploadResponseModel> call, Response<FileUploadResponseModel> response) {
                Log.v("##","success");
//                Toast.makeText(context,response.body().toString(),Toast.LENGTH_SHORT).show();
//
//            @Override
//            public void onResponse(retrofit.Response<FileUploadResponseModel> response, Retrofit retrofit) {
//                AZUtils.printObject(response.body());
            }

            @Override
            public void onFailure(Call<FileUploadResponseModel> call, Throwable t) {
                Log.v("##","failure");
                t.printStackTrace();
            }
        });
    }
*/
    private void upload(ArrayList<RequestBody> rbody){


       // RequestBody requestBody=RequestBody.create(MediaType.parse("multipart/form-data"),file);
        //MultipartBody.Part body=MultipartBody.Part.createFormData("files",file.getName(),requestBody);

        ApiInterface apiservice = com.tmsnith.emotionsense.Utils.Util.getRetrofitService();
        Call<FileUploadResponseModel> call=apiservice.postFileAndGetUrl(rbody);
        call.enqueue(new Callback<FileUploadResponseModel>() {
            @Override
            public void onResponse(Call<FileUploadResponseModel> call, Response<FileUploadResponseModel> response) {
//                Toast.makeText(context,response.body().toString(),Toast.LENGTH_SHORT).show();
//                System.out.println(response.raw());
                try{
//                    System.out.println(response.body().isSuccess());

                    pdfUrl = response.body().getPdfUrl();
                    loadingbar.setVisibility(View.GONE);
                    download_btn.setVisibility(View.VISIBLE);
                    //Toast.makeText(getContext(), ""+pdfUrl, Toast.LENGTH_SHORT).show();
                    System.out.println(pdfUrl);
                    Log.d("pdfurl", pdfUrl);
                    pdfuri = Uri.parse(pdfUrl);
                }
                catch (NullPointerException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<FileUploadResponseModel> call, Throwable t) {

            }
        });
    }


}

