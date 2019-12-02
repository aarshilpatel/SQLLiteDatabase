package com.example.sqllitedatabase;

import android.content.Intent;
import android.service.autofill.UserData;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    ListView listView;
    Button butnAdd;
    private DatabaseReference myRef;
    ArrayList<ContactModel> contactModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        DatabaseHandlerClass db = new DatabaseHandlerClass(this);
        listView = (ListView)findViewById(R.id.list);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("User");

        Intent intent = getIntent();
        String strKey = intent.getStringExtra("FIREBASE_KEY");
        Log.e("Main__","key  "+strKey);
        contactModelArrayList = new ArrayList<ContactModel>();

        if (strKey.equals("Firebase")){

            if (myRef != null) {
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                            ContactModel contactModel = dataSnapshot1.getValue(ContactModel.class);
                            Log.e("Main2**", "size" + contactModel.getUserName());

                            contactModelArrayList.add(contactModel);


                        }
                        setdata(contactModelArrayList);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        }

        ArrayList<ContactModel> contacts = db.getAllRecords();
        for (int i = 0 ; i<contacts.size(); i++){
            Log.e("Main2","size"+contacts.get(i).getUserName());
            Toast.makeText(this, "size"+contacts.get(i).getUserName(), Toast.LENGTH_SHORT).show();
        }
        MyBaseAdapter myBaseAdapter = new MyBaseAdapter(this,contacts);
        listView.setAdapter(myBaseAdapter);
   }

    private void setdata(ArrayList<ContactModel> contactModelArrayList) {


            MyBaseAdapter myBaseAdapter = new MyBaseAdapter(Main2Activity.this, contactModelArrayList);
            listView.setAdapter(myBaseAdapter);
         }
}
