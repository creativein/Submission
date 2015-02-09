package com.thetechboys.submission;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.thetechboys.submission.model.Submission;
import com.thetechboys.submission.service.RestClient;
import com.thetechboys.submission.utils.SimpleGestureFilter;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by niraj.kumar.chauhan on 04-02-2015.
 */
public class MainActivity extends Activity {
    private SimpleGestureFilter detector;
    private ImageButton rateUp, rateDown;
    private TextSwitcher textSwitcher;
    private List<Submission> posts;
    int currentIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // detector = new SimpleGestureFilter(this,this);
        setContentView(R.layout.activity_main);
        rateUp = (ImageButton) findViewById(R.id.rateUp);
        rateDown = (ImageButton) findViewById(R.id.rateDown);
        textSwitcher = (TextSwitcher) findViewById(R.id.submissionTxt);
        Animation in = AnimationUtils.loadAnimation(MainActivity.this,android.R.anim.fade_in);
        Animation out = AnimationUtils.loadAnimation(MainActivity.this,android.R.anim.fade_out);
        textSwitcher.setInAnimation(in);
        textSwitcher.setOutAnimation(out);
        textSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView myText = new TextView(MainActivity.this);
                myText.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                myText.setTextSize(18);
                myText.setTextColor(Color.BLACK);
                myText.setTypeface(Typeface.MONOSPACE);
                return myText;
            }
        });

        try {
            new RestClient().getApiService().getSubmissions(new Callback<List<Submission>>() {
                @Override
                public void success(List<Submission> submissions, Response response) {
                    //Toast.makeText(MainActivity.this, "Success:  Total no of posts " + submissions.size(), Toast.LENGTH_LONG).show();
                    posts = submissions;
                    currentIndex = 0;
                    textSwitcher.setText(posts.get(currentIndex).getSubmission());
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(MainActivity.this, "Can't reached to server at this moment. Try Again Later!", Toast.LENGTH_LONG).show();
                }
            });

        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "Oops This application is going to die.", Toast.LENGTH_LONG).show();
        }
    }

    private void showNxtPost(){
        --currentIndex;
        if(currentIndex<0){
            currentIndex = posts.size() - 1;
        }

        textSwitcher.setText(posts.get(currentIndex).getSubmission());

    }

    private void showPrvPost(){
        ++currentIndex;
        if(currentIndex > (posts.size()-1)){
            currentIndex = 0;
        }

        textSwitcher.setText(posts.get(currentIndex).getSubmission());
    }

}
