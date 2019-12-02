package com.example.sqllitedatabase;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyBaseAdapter extends BaseAdapter {
    Context context;
    ArrayList<ContactModel> cn;



    public MyBaseAdapter(Context context, ArrayList<ContactModel> cn) {
        this.context = context;
        this.cn = cn;
    }


    @Override
    public int getCount() {
        return cn.size();
    }

    @Override
    public Object getItem(int position) {
        return cn.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInflater.inflate(R.layout.raw_list, null,false);

        final ContactModel contactModel = (ContactModel)getItem(position);
        TextView tvName = (TextView) convertView.findViewById(R.id.tv_name);
        TextView tvPassword = (TextView) convertView.findViewById(R.id.tv_password);
        tvName.setText(cn.get(position).getUserName());
        tvPassword.setText(cn.get(position).getPassword());

        convertView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String srtun = cn.get(position).getUserName();
        String strid = contactModel.getConID();
        String strpass = contactModel.getPassword();
        Log.e("Adapter","id  "+strid);


        Intent i = new Intent(context, UpdateActivity.class);
        i.putExtra("UN_KEY",srtun);
        i.putExtra("ID_KEY",strid);
        i.putExtra("PASS_KEY",strpass);
        i.putExtra("FIREBASE_UPDATE","firebaseupdate");
        context.startActivity(i);
    }
});

        return convertView;
    }
}
