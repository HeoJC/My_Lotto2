package com.example.mylotto2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

public class manual extends AppCompatActivity {

    EditText winNum1 , winNum2 , winNum3 , winNum4 , winNum5 , winNum6 ;
    TextView win1 , win2 , win3 , win4 , win5 , win6 , win7 , win8 , win9 , win10 , win11 , win12 , win13 , win14 , win15 , win16 , win17 , win18 , win19 , win20 , win21 , win22 , win23 , win24 , win25 , win26 , win27 , win28 , win29 , win30 , win31 , win32 , win33 , win34 , win35 , win36 , win37 , win38 , win39 , win40 , win41 , win42 , win43 , win44 , win45 ;
    Button winNumBtn ;
    Intent intent = new Intent() ;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        intent = getIntent();

        winNum1 = findViewById(R.id.winNum1) ;
        winNum2 = findViewById(R.id.winNum2) ;
        winNum3 = findViewById(R.id.winNum3) ;
        winNum4 = findViewById(R.id.winNum4) ;
        winNum5 = findViewById(R.id.winNum5) ;
        winNum6 = findViewById(R.id.winNum6) ;

        win1 = findViewById(R.id.win1) ;
        win2 = findViewById(R.id.win2) ;
        win3 = findViewById(R.id.win3) ;
        win4 = findViewById(R.id.win4) ;
        win5 = findViewById(R.id.win5) ;
        win6 = findViewById(R.id.win6) ;
        win7 = findViewById(R.id.win7) ;
        win8 = findViewById(R.id.win8) ;
        win9 = findViewById(R.id.win9) ;
        win10 = findViewById(R.id.win10) ;
        win11 = findViewById(R.id.win11) ;
        win12 = findViewById(R.id.win12) ;
        win13 = findViewById(R.id.win13) ;
        win14 = findViewById(R.id.win14) ;
        win15 = findViewById(R.id.win15) ;
        win16 = findViewById(R.id.win16) ;
        win17 = findViewById(R.id.win17) ;
        win18 = findViewById(R.id.win18) ;
        win19 = findViewById(R.id.win19) ;
        win20 = findViewById(R.id.win20) ;
        win21 = findViewById(R.id.win21) ;
        win22 = findViewById(R.id.win22) ;
        win23 = findViewById(R.id.win23) ;
        win24 = findViewById(R.id.win24) ;
        win25 = findViewById(R.id.win25) ;
        win26 = findViewById(R.id.win26) ;
        win27 = findViewById(R.id.win27) ;
        win28 = findViewById(R.id.win28) ;
        win29 = findViewById(R.id.win29) ;
        win30 = findViewById(R.id.win30) ;
        win31 = findViewById(R.id.win31) ;
        win32 = findViewById(R.id.win32) ;
        win33 = findViewById(R.id.win33) ;
        win34 = findViewById(R.id.win34) ;
        win35 = findViewById(R.id.win35) ;
        win36 = findViewById(R.id.win36) ;
        win37 = findViewById(R.id.win37) ;
        win38 = findViewById(R.id.win38) ;
        win39 = findViewById(R.id.win39) ;
        win40 = findViewById(R.id.win40) ;
        win41 = findViewById(R.id.win41) ;
        win42 = findViewById(R.id.win42) ;
        win43 = findViewById(R.id.win43) ;
        win44 = findViewById(R.id.win44) ;
        win45 = findViewById(R.id.win45) ;

        List<TextView> list1 = Arrays.asList(win1,win2,win3,win4,win5,win6,win7,win8,win9,win10,win11,win12,win13,win14,win15,win16,win17,win18,win19,win20,win21,win22,win23,win24,win25,win26,win27,win28,win29,win30,win31,win31,win32,win33,win34,win35,win36,win37,win38,win39,win40,win41,win42,win43,win44,win45);
        List<EditText> list2 = Arrays.asList(winNum1, winNum2, winNum3, winNum4, winNum4, winNum5, winNum6) ;

        winNumBtn.setOnClickListener( v -> {
            for (int j = 1 ; j < 7 ; j++) {
                for (int i = 1 ; i < 46 ; i++) {
                    if (Integer.parseInt(list2.get(j).getText().toString()) == i) {
                        list1.get(i).setText(Integer.parseInt(list1.get(i).getText().toString()) + 1) ;
                    }
                }
            }
        });

    }

}
