package com.example.android.qrcodescanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button ScanQRCodeBtn;

    // public static Allow Me Use It Into ScanCodeActivity.
    public static TextView ResultQRCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ScanQRCodeBtn = (Button) findViewById(R.id.scan_btn);

        ResultQRCode = (TextView) findViewById(R.id.scan_result_txt);

        ScanQRCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ScanCodeActivity.class);
                startActivity(intent);
            }
        });

    }
}
