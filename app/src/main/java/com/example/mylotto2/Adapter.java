package com.example.mylotto2;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {

    ArrayList<LottoVO> data ;

    public Adapter(ArrayList<LottoVO> data) {this.data = data ;}
    public int getCount() {return data.size();}
    public Object getItem(int position) {return data.get(position);}
    public long getItemId(int position) {return position;}

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext()) ;
        view = inflater.inflate(R.layout.lietview_item , viewGroup , false) ;
        TextView buyCount = view.findViewById(R.id.buyCount) ;
        TextView buyNum = view.findViewById(R.id.buyNum) ;
        buyCount.setText(data.get(i).getCount()) ;
        buyNum.setText(data.get(i).getNumber()) ;

        return view;
    }
}
