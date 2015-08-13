package com.fenjuly.arrowdownloadbutton;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;


import com.fenjuly.library.ArrowDownloadButton;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends ActionBarActivity {

    int count = 0;
    int progress = 0;
    ArrowDownloadButton button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (ArrowDownloadButton)findViewById(R.id.arrow_download_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((count % 2) == 0) {
                    button.startAnimating();
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    progress = progress + 1;
                                    button.setProgress(progress);
                                }
                            });
                        }
                    }, 800, 20);
                } else {
                    button.reset();
                }
                count++;
            }
        });
    }
}
