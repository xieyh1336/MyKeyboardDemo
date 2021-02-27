package com.example.mykeyboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.example.mykeyboard.util.GlobalLayoutListener;
import com.example.mykeyboard.util.OnKeyboardChangedListener;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = findViewById(R.id.et_text);
        FrameLayout myKeyboard = findViewById(R.id.my_keyboard);
        RelativeLayout relativeLayout = findViewById(R.id.rootView);
        MyKeyboardBuilder myKeyboardBuilder = new MyKeyboardBuilder.Builder(this)
                .setEditText(editText)
                .setRootView(relativeLayout)
                .setViewGroup(myKeyboard)
                .build();
        myKeyboardBuilder.setHeight(700);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            editText.setShowSoftInputOnFocus(false);
        }
    }
}