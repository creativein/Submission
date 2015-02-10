package com.thetechboys.submission;

import android.app.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by LENOVO PC on 10-02-2015.
 */
public class PostActivity extends Activity {
    private Button btnPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        btnPost = (Button) findViewById(R.id.post);
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PostActivity.this,"This part is in progress",Toast.LENGTH_LONG).show();
            }
        });
    }
}
