package com.example.sqllitedatabase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InsertDataFirebaseActivity extends AppCompatActivity {
    EditText edtun,edtpass;
    Button btnAdd;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_data);
        edtun = (EditText)findViewById(R.id.edt_username);
        edtpass = (EditText)findViewById(R.id.edt_password);
        btnAdd =(Button) findViewById(R.id.btn_add);
        FirebaseApp.initializeApp(this);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
         myRef = database.getReference("User");


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strun = edtun.getText().toString();
                String strpass = edtpass.getText().toString();

                String struId = myRef.push().getKey();
                ContactModel contact = new ContactModel();
                contact.setUserName(strun);
                contact.setPassword(strpass);
                contact.setConID(struId);
                myRef.child(struId).setValue(contact);
                edtun.setText("");
                edtpass.setText("");

                Intent i = new Intent(InsertDataFirebaseActivity.this, Main2Activity.class);
               i.putExtra("FIREBASE_KEY","Firebase");

               startActivity(i);
                finish();
            }
        });
    }
}
