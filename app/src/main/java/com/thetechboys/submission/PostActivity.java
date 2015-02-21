package com.thetechboys.submission;

import android.app.Activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.thetechboys.submission.service.RestClient;

import org.json.JSONException;
import org.json.JSONObject;

import javax.security.auth.callback.Callback;

import cn.pedant.SweetAlert.SweetAlertDialog;
import mehdi.sakout.fancybuttons.FancyButton;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by LENOVO PC on 10-02-2015.
 */
public class PostActivity extends Activity {
    Context mContext = this;
    private String DEBUG_TAG = "PostActivity";
    private FancyButton btnPost;
    private EditText submissionTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        btnPost = (FancyButton) findViewById(R.id.post);
        submissionTxt = (EditText) findViewById(R.id.editText);
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(PostActivity.this,"This part is in progress",Toast.LENGTH_LONG).show();
                final SweetAlertDialog pDialog = new SweetAlertDialog(mContext, SweetAlertDialog.PROGRESS_TYPE);
                pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                pDialog.setTitleText("Sending");
                pDialog.setCancelable(false);
                pDialog.show();
                final SweetAlertDialog sDialog  = new SweetAlertDialog(mContext, SweetAlertDialog.SUCCESS_TYPE);

                try{
                   new RestClient().getApiService().postSubmission(submissionTxt.getText().toString(),new retrofit.Callback<JSONObject>() {
                       @Override
                       public void success(JSONObject jsonObject, Response response) {
                             try{
                                 if(jsonObject.getBoolean("success")== true){
                                     pDialog.dismissWithAnimation();
                                    sDialog.setTitleText("Good job!")
                                             .setContentText("Sent Successfully!")
                                             .show();
                                 }
                             }catch (JSONException je){
                                 pDialog.dismissWithAnimation();
                                 sDialog.setTitleText("Oops").setContentText("Can't able to save the submission!").show();
                             }
                       }

                       @Override
                       public void failure(RetrofitError error) {
                           pDialog.dismissWithAnimation();
                           new SweetAlertDialog(mContext, SweetAlertDialog.ERROR_TYPE)
                                   .setTitleText("Oops...")
                                   .setContentText("Can't talk to server this moment!!!")
                                   .show();
                       }
                   });
                }catch (Exception e){
                    pDialog.dismissWithAnimation();
                    new SweetAlertDialog(mContext, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText("Something went wrong!")
                            .show();
                }

            }
        });
    }
}
