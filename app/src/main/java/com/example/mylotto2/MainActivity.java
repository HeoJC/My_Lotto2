package com.example.mylotto2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    Button createNum , saveNum , moveBtn ;
    EditText countNum , genNum1 , genNum2 , genNum3 , genNum4 , genNum5 , genNum6 ;
    TextView genNum ;
    ListView listDiary ;
    DBHelper dbHelper ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(getApplicationContext()) ;
        ArrayList<LottoVO> list = LottoDAO.selectList(dbHelper) ;
        Adapter adapter = new Adapter(list) ;
        listDiary = findViewById(R.id.listDiary) ;
        listDiary.setAdapter(adapter) ;

        createNum = findViewById(R.id.createNum) ;
        saveNum = findViewById(R.id.saveNum) ;
        moveBtn = findViewById(R.id.moveBtn) ;
        genNum = findViewById(R.id.genNum) ;
        countNum = findViewById(R.id.countNum) ;
        genNum1 = findViewById(R.id.genNum1) ;
        genNum2 = findViewById(R.id.genNum2) ;
        genNum3 = findViewById(R.id.genNum3) ;
        genNum4 = findViewById(R.id.genNum4) ;
        genNum5 = findViewById(R.id.genNum5) ;
        genNum6 = findViewById(R.id.genNum6) ;

        Set<Integer> set = new HashSet<>() ;

        createNum.setOnClickListener(v -> {
                if (genNum.getText() != "") {
                    genNum.setText("") ;
                    genNum1.setText("") ;
                    genNum2.setText("") ;
                    genNum3.setText("") ;
                    genNum4.setText("") ;
                    genNum5.setText("") ;
                    genNum6.setText("") ;
                } else {
                    set.clear() ;
                    while(set.size() < 6) {
                        int r = (int) (Math.random() * 45) + 1;
                        set.add(r);
                    }

                    List list2 = new ArrayList(set) ;
                    Collections.sort(list2);

                    genNum.setText(String.valueOf(list2)) ;
                    genNum1.setText(String.valueOf(list2.get(0))) ;
                    genNum2.setText(String.valueOf(list2.get(1))) ;
                    genNum3.setText(String.valueOf(list2.get(2))) ;
                    genNum4.setText(String.valueOf(list2.get(3))) ;
                    genNum5.setText(String.valueOf(list2.get(4))) ;
                    genNum6.setText(String.valueOf(list2.get(5))) ;
                }
        });

        saveNum.setOnClickListener(v -> {
            LottoVO lottoVO = new LottoVO() ;

            String count2 = countNum.getText().toString() ;
            if (count2.equals("")) {
                Toast.makeText(this,"회차를 입력하세요",Toast.LENGTH_SHORT).show() ;
                return ;
            }
            String number2 = genNum.getText().toString() ;
            System.out.println(number2);
            if (number2.equals("")) {
                Toast.makeText(this, "번호를 생성하세요",Toast.LENGTH_SHORT).show() ;
                return ;
            }

            lottoVO.setCount(countNum.getText().toString()) ;
            lottoVO.setNumber(genNum.getText().toString()) ;
            LottoDAO.insert(dbHelper , lottoVO) ;

            Intent intent = getIntent() ;
            finish() ;
            startActivity(intent) ;
        });

        listDiary.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("삭제")
                        .setNegativeButton("삭제", (dialogInterface, i) -> {
                            LottoDAO.delete(dbHelper , list.get(position).getCount());
                            list.remove(position) ;
                            Intent intent = getIntent() ;
                            finish() ;
                            startActivity(intent) ;
                            ((Adapter)listDiary.getAdapter()).notifyDataSetChanged() ;
                        })
                        .create()
                        .show();
            }
        });

        moveBtn.setOnClickListener( v -> {
            Intent intent = new Intent(this , manual.class) ;
            startActivity(intent) ;
        });
    }
}