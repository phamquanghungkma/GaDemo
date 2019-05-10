package com.example.gademo.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gademo.App.MyApplication;
import com.example.gademo.R;

import static android.view.View.*;

public class MainActivity extends AppCompatActivity {
    private static String TAG = MainActivity.class.getSimpleName();

    Button btnSendEvent, btnException, btnAppCrash, btnLoadFragment, btnSend,btnDownload;
    EditText editTextInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDownload = ( Button)findViewById(R.id.btnSendEvent2);
        btnSendEvent = (Button) findViewById(R.id.btnSendEvent);
        btnException = (Button) findViewById(R.id.btnException);
        btnAppCrash = (Button) findViewById(R.id.btnAppCrash);

        editTextInput = (EditText) findViewById(R.id.editText);
        btnSend = (Button)findViewById(R.id.btnSend);


        btnSendEvent.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                // Tracking Event
                MyApplication.getInstance().trackEvent("Book", "Download", "Send event example");

                Toast.makeText(getApplicationContext(), "Event \'Book\' \'Download\' \'Event example\' is sent. Check it on Google Analytics Dashboard!", Toast.LENGTH_LONG).show();
            }
        });
        btnDownload.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication.getInstance().trackEvent("Sofware","Download","Download event example");
                Toast.makeText(getApplicationContext(),"Event \'Sowftware\' \'Download\' \'Event example\' is sent. Check it on Google Analytics Dashboard!",Toast.LENGTH_LONG).show();
            }
        });
        btnAppCrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), getString(R.string.toast_app_crash), Toast.LENGTH_LONG).show();

                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        int answer = 12 / 0;
                    }
                };

                Handler h = new Handler();
                h.postDelayed(r, 1500);
            }
        });
        btnException.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String name = null;
                    if (name.equals("ravi")) {
                        /* Never comes here as it throws null pointer exception */
                    }
                } catch (Exception e) {
                    // Tracking exception
                    MyApplication.getInstance().trackException(e);

                    Toast.makeText(getApplicationContext(), getString(R.string.toast_track_exception), Toast.LENGTH_LONG).show();

                    Log.e(TAG, "Exception: " + e.getMessage());
                }
            }
        });
        btnSend.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication.getInstance().trackEvent(editTextInput.getText().toString(), "Search", "Send event example");
                Toast.makeText(getApplicationContext(), "Event \'editTextInput.getText().toString()\' \'Search\' \'Event example\' is sent. Check it on Google Analytics Dashboard!", Toast.LENGTH_LONG).show();
            }

        });




    }

}
