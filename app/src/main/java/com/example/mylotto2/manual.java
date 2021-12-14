package com.example.mylotto2;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class manual extends AppCompatActivity {

    EditText winNum1 , winNum2 , winNum3 , winNum4 , winNum5 , winNum6 , count , winmoney , winnumbers ;
    Button winNumBtn , moveBtn2 , resetBtn ;
    JsonObject jsonObject ;
    RequestQueue requestQueue ;

    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manual);

        winNum1 = findViewById(R.id.winNum1) ;
        winNum2 = findViewById(R.id.winNum2) ;
        winNum3 = findViewById(R.id.winNum3) ;
        winNum4 = findViewById(R.id.winNum4) ;
        winNum5 = findViewById(R.id.winNum5) ;
        winNum6 = findViewById(R.id.winNum6) ;
        winmoney = findViewById(R.id.winmoney) ;
        winnumbers = findViewById(R.id.winnumbers) ;
        count = findViewById(R.id.count) ;

        winNumBtn = findViewById(R.id.winNumBtn) ;
        moveBtn2 = findViewById(R.id.moveBtn2) ;
        resetBtn = findViewById(R.id.resetBtn) ;

        winNumBtn.setOnClickListener( v -> {
            String count2 = count.getText().toString() ;
            if (count2.equals("")) {
                Toast.makeText(this,"회차를 입력하세요",Toast.LENGTH_SHORT).show() ;
                return ;
            }

            if(requestQueue == null) {
                requestQueue = Volley.newRequestQueue(getApplicationContext()) ;
            }

            String url = "https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=" + count2 ;

            StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    jsonObject = (JsonObject) JsonParser.parseString(response) ;
                    winNum1.setText(String.valueOf(jsonObject.get("drwtNo1"))) ;
                    winNum2.setText(String.valueOf(jsonObject.get("drwtNo2"))) ;
                    winNum3.setText(String.valueOf(jsonObject.get("drwtNo3"))) ;
                    winNum4.setText(String.valueOf(jsonObject.get("drwtNo4"))) ;
                    winNum5.setText(String.valueOf(jsonObject.get("drwtNo5"))) ;
                    winNum6.setText(String.valueOf(jsonObject.get("drwtNo6"))) ;

                    String money = String.valueOf(jsonObject.get("firstWinamnt")) ;
                    money = "1등 수령액 : " + money.replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "원" ;
                    winmoney.setText(money) ;

                    String no1 , no2 , no3 , no4 , no5 , no6 , date , date2;
                    no1 = String.valueOf(jsonObject.get("drwtNo1")) ;
                    no2 = String.valueOf(jsonObject.get("drwtNo2")) ;
                    no3 = String.valueOf(jsonObject.get("drwtNo3")) ;
                    no4 = String.valueOf(jsonObject.get("drwtNo4")) ;
                    no5 = String.valueOf(jsonObject.get("drwtNo5")) ;
                    no6 = String.valueOf(jsonObject.get("drwtNo6")) ;
                    date = String.valueOf(jsonObject.get("drwNoDate")) ;
                    date2 = date.substring(1,date.length()-1) ;
                    String winnumber = "" ;
                    winnumber = "날짜 : " + date2 + " , "  + count2 + "회차 : " + no1 + " , " + no2 + " , "  + no3 + " , "  +
                            no4 + " , "  + no5 + " , "  + no6 ;

                    winnumbers.setText(winnumbers.getText() + "\n" + winnumber) ;

                } }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) { } }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<>(); return params; } };
            request.setShouldCache(false); requestQueue.add(request);
        });

        moveBtn2.setOnClickListener( v -> {
            Intent intent = new Intent(this , MainActivity.class) ;
            startActivity(intent) ;
        });

        resetBtn.setOnClickListener( v -> {
            winnumbers.setText("") ;
            winNum1.setText("") ;
            winNum2.setText("") ;
            winNum3.setText("") ;
            winNum4.setText("") ;
            winNum5.setText("") ;
            winNum6.setText("") ;
            winmoney.setText("") ;
            count.setText("") ;
        });

    }

}
