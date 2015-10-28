package com.yahoo.myapp.socialphoto.activity;

import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.TypefaceSpan;
import android.widget.TextView;

import com.yahoo.myapp.socialphoto.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Rect windowSize = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(windowSize);

        TextView tv = (TextView) findViewById(R.id.tvHello);
        tv.setText(windowSize.height() + "");

//        ForegroundColorSpan fc = new ForegroundColorSpan(Color.BLACK);
//        SpannableStringBuilder ssb = new SpannableStringBuilder("mytext");
//        ssb.setSpan(fc, 0, "mytext".length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);



    }
}
