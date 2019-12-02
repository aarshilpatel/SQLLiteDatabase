package com.example.sqllitedatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateActivity extends AppCompatActivity {

    EditText edtun,edtpass;
    Button btnUpdate,btnDelete;
    private DatabaseHandlerClass db;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        edtun = (EditText) findViewById(R.id.edt_UserName);
        edtpass = (EditText)findViewById(R.id.edt_Password);
        db = new DatabaseHandlerClass(this);

        btnUpdate = (Button) findViewById(R.id.btn_update);
        btnDelete = (Button) findViewById(R.id.btn_delete);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("User");

        Intent i =getIntent();
        String strun = i.getStringExtra("UN_KEY");
        String strpass = i.getStringExtra("PASS_KEY");
        final String strid = i.getStringExtra("ID_KEY");

        Log.e("update","id  "+strid);
        final String strFirebasekey = i.getStringExtra("FIREBASE_UPDATE");

        edtun.setText(strun);
        edtpass.setText(strpass);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String strun = edtun.getText().toString();
                String strpass = edtpass.getText().toString();

                if (strFirebasekey.equals("firebaseupdate")){

                    ContactModel contact = new ContactModel();
                    contact.setConID(strid);
                    contact.setUserName(strun);
                    contact.setPassword(strpass);
                    myRef.child(strid).setValue(contact);
                    Intent intent = new Intent(UpdateActivity.this,Main2Activity.class);
                    intent.putExtra("FIREBASE_KEY","Firebase");
                    startActivity(intent);
                }else {
                    ContactModel contact = new ContactModel();
                    contact.setConID(strid);
                    contact.setUserName(strun);
                    contact.setPassword(strpass);
                    db.updateRecord(contact);
                    Intent i = new Intent(UpdateActivity.this, Main2Activity.class);
                    //  i.putExtra("FIREBASE_KEY","Firebase");
                    startActivity(i);
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (strFirebasekey.equals("firebaseupdate")){


                    myRef.child(strid).removeValue();
                    Intent i1 = new Intent(UpdateActivity.this,Main2Activity.class);
                    i1.putExtra("FIREBASE_KEY","Firebase");
                    startActivity(i1);

                }else {

                    ContactModel contact = new ContactModel();
                    contact.setConID(strid);
                    db.deleteRecord(contact);
                    Intent i2 = new Intent(UpdateActivity.this, Main2Activity.class);
                    // i.putExtra("FIREBASE_KEY","Firebase");
                    startActivity(i2);
                }
            }
        });
    }
}
