package com.example.sqllitedatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText edtun, edtpass;
    Button btnAdd;
    DatabaseHandlerClass db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtun = (EditText) findViewById(R.id.edt_username);
        edtpass = (EditText) findViewById(R.id.edt_password);

        btnAdd =(Button) findViewById(R.id.btn_add);

        db = new DatabaseHandlerClass(MainActivity.this);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strun = edtun.getText().toString();
                String strpass = edtpass.getText().toString();

                ContactModel contact = new ContactModel();
                contact.setUserName(strun);
                contact.setPassword(strpass);
                db.insertRecord(contact);
                edtun.setText("");
                edtpass.setText("");
                Intent i = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(i);
                finish();


            }
        });
    }
}
