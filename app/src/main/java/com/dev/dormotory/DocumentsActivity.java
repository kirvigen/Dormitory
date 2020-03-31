package com.dev.dormotory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.FileUtils;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DocumentsActivity extends AppCompatActivity {
    ImageView back;
    String[] urls = new String[]{"https://raw.githubusercontent.com/kirvigen/dermotoryApp-Android/3d4f5a2079f957bf2a162749d6b886e6aac6f765/document1.pdf",
    "https://raw.githubusercontent.com/kirvigen/dermotoryApp-Android/3d4f5a2079f957bf2a162749d6b886e6aac6f765/document2.pdf",
    "https://raw.githubusercontent.com/kirvigen/dermotoryApp-Android/3d4f5a2079f957bf2a162749d6b886e6aac6f765/document3.pdf"};
    View document1,document2,document3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documents);
        findViewById(R.id.back).setOnClickListener(v -> {
            onBackPressed();
            finish();
        });

        document1 = findViewById(R.id.document1);
        document2 = findViewById(R.id.document2);
        document3 = findViewById(R.id.document3);
        document1.setOnClickListener(v -> {
            OpenFile(urls[0]);
        });
        document2.setOnClickListener(v -> {
            OpenFile(urls[1]);
        });
        document3.setOnClickListener(v -> {
            OpenFile(urls[2]);
        });
    }
    private void OpenFile(String name){
//        File targetFile = new File(getExternalCacheDir()+"/"+name+".pdf");
//        try {
//            InputStream pdf = getAssets().open(name+".pdf");
//            OutputStream outStream = new FileOutputStream(targetFile);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//                FileUtils.copy(pdf, outStream);
//            }else{
//                byte[] buffer = new byte[pdf.available()];
//                pdf.read(buffer);
//                outStream.write(buffer);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setDataAndType(Uri.parse(targetFile.getAbsolutePath()),"application/pdf");
//        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.setData(Uri.parse(name));
        startActivity(intent);
//        Intent intent2 = Intent.createChooser(intent, "Open File");
//        try {
//            startActivity(intent2);
//        } catch (ActivityNotFoundException e) {
//
//        }
    }

}
