package com.example.android.qrcodescanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

// Implements ZXingScannerView.ResultHandler.
public class ScanCodeActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    ZXingScannerView ScannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_code);

        // Call ZXingScannerView Constructor & Send This [ScanCodeActivity] As Param.
        ScannerView = new ZXingScannerView(this);
        // Set Content View Of This Constructor.
        setContentView(ScannerView);
    }

    // Call handleResult Override Method.
    @Override
    public void handleResult(Result result) {

        // Get ResultQRCode TextView From MainActivity & Set Text As Result Of Scanning.
        MainActivity.ResultQRCode.setText(result.getText());
        // Active Back Pressed To Back To MainActivity.
        onBackPressed();

    }

    // When Start.
    @Override
    protected void onPostResume() {
        super.onPostResume();

        // set Result Handler Take This [ScanCodeActivity] As Param.
        ScannerView.setResultHandler(this);
        // Start Camera To Start Scanning.
        ScannerView.startCamera();
    }

    // When Stop
    @Override
    protected void onPause() {
        super.onPause();
        // Stop Camera To Stop Scanning.
        ScannerView.stopCamera();
    }
}
