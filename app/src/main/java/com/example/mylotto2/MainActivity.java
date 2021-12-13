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
    EditText countNum ;
    TextView genNum ;
    ListView listDiary ;
    DBHelper dbHelper ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(getApplicationContext()) ;
        ArrayList<LottoVO> list = LottoDAO.selectList(dbHelper) ;
        listDiary = findViewById(R.id.listDiary) ;
        Adapter adapter = new Adapter(list) ;
        listDiary.setAdapter(adapter) ;

        createNum = findViewById(R.id.createNum) ;
        saveNum = findViewById(R.id.saveNum) ;
        moveBtn = findViewById(R.id.moveBtn) ;
        genNum = findViewById(R.id.genNum) ;
        Set<Integer> set = new HashSet<>() ;

        createNum.setOnClickListener(v -> {
                if (genNum.getText() != "") {
                    genNum.setText("") ;
                } else {
                    set.clear() ;
                    while(set.size() < 6) {
                        int r = (int) (Math.random() * 45) + 1;
                        set.add(r);
                    }
                    List list2 = new ArrayList(set) ;
                    Collections.sort(list2);

                    genNum.setText(String.valueOf(list2)) ;
                }
        });

        saveNum.setOnClickListener(v -> {
            LottoVO lottoVO = new LottoVO() ;
            countNum = findViewById(R.id.countNum) ;
            genNum = findViewById(R.id.genNum) ;

            String count2 = countNum.getText().toString() ;
            if (count2.equals("")) {
                Toast.makeText(this,"회차를 입력하세요",Toast.LENGTH_SHORT).show() ;
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