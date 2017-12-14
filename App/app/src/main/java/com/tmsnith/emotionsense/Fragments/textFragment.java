package com.tmsnith.emotionsense.Fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tmsnith.emotionsense.R;
import com.tmsnith.emotionsense.RequestModels.DocumentModel;
import com.tmsnith.emotionsense.RequestModels.SingleDocument;
import com.tmsnith.emotionsense.Resoponse.TextResponse;
import com.tmsnith.emotionsense.Utils.ApiInterface;
import com.tmsnith.emotionsense.Utils.Util;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class textFragment extends Fragment {


    EditText text;
    Button submit;
    ProgressBar happieness;
    String inputText;
    TextView percent ,question;

    ProgressDialog progress;

    final String API = "b868ca80b2184f46a3ed464503ea4f2c";
    final String Content_type = "application/json";

    public textFragment() {
        // Required empty public constructor
    }

        DocumentModel request;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


        View v =  inflater.inflate(R.layout.fragment_text, container, false);
        final View v1 =v;
        text = (EditText) v.findViewById(R.id.text);
        submit = (Button) v.findViewById(R.id.submit1);
        happieness = (ProgressBar)v.findViewById(R.id.happienessbar);
        percent = (TextView)v.findViewById(R.id.percent);
        question = (TextView)v.findViewById(R.id.question);

        Log.v("hack", "sucessful1");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
             inputText = "" + text.getText().toString();
                if(inputText.equals(""))
                {
                    return;
                }



                View view = getActivity().getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }

                retrofit();
            }
        });

//        return null;
        return v;
    }


    public void retrofit(){

        ApiInterface apiservice= Util.getRetrofitService();

        progress = new ProgressDialog(getActivity());
        progress.setTitle("wait ...");
        progress.setMessage("We are processing your text.");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setCancelable(false);
        progress.show();

        request = new DocumentModel();

        SingleDocument d= new SingleDocument("id",inputText);

        ArrayList<SingleDocument> list=request.getDocument();
        list.add(d);

        request.setDocument(list);


//        Call<DocumentModel> t =
        Call<TextResponse> call=null;//apiservice.sendText(API, Content_type, request );



        call.enqueue(new Callback<TextResponse>() {
            @Override
            public void onResponse(Call<TextResponse> call, Response<TextResponse> response) {
//                bar.setVisibility(View.GONE);

                TextResponse model=response.body();
                int status=response.code();
                Log.v("hack",model +"");

                if(model!=null && response.isSuccess()){
//                    recyclerView.setVisibility(View.VISIBLE);

//                    list=model.getRestaurants();
//                    adapter.refresh(list);

                    Log.v("hack","Success\n"+model);

                   double score =  model.getDocuments().get(0).getScore();
                    double percentage  = score *100;

                    progress.dismiss();

                    percent.setText((int)percentage + "%");
                    question.setText("\""+inputText+"\"");
                    text.setText("");
                    happieness.setProgress((int)percentage);
//                    happieness.getProgressDrawable().setColorFilter(Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);

                }else{
                    Log.v("hack","Some error occurred!!1");
                    progress.dismiss();
                }
            }

            @Override
            public void onFailure(Call<TextResponse> call, Throwable t) {
//                bar.setVisibility(View.GONE);
                progress.dismiss();
                Log.v("hack","Some error occurred!!2");
            }
        });

}

    public static textFragment get()
    {
        return  new textFragment();
    }

}
