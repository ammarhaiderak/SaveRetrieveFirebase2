package com.example.ammar.saveretrievefirebase;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.fingerprint.FingerprintManager;
import android.media.Image;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.multidots.fingerprintauth.AuthErrorCodes;
import com.multidots.fingerprintauth.FingerPrintAuthCallback;
import com.multidots.fingerprintauth.FingerPrintAuthHelper;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements FingerPrintAuthCallback{
    ImageView imageView;
    ArrayList<Item> items;
    DatabaseReference myRef;
    MyAP myap;
    ListView lv;
    FingerPrintAuthHelper mFingerPrintAuthHelper;
    ValueEventListener mValueEventListener;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**********************************/
        //FingerPrint//

        //Link to FingerPrint Library
        // =>https://github.com/multidots/android-fingerprint-authentication

        mFingerPrintAuthHelper=FingerPrintAuthHelper.getHelper(this,this);

        /**********************************/

        items=new ArrayList<Item>();
        myRef= FirebaseDatabase.getInstance().getReference("items");
        lv=findViewById(R.id.lv);
        myap=new MyAP(getApplicationContext(),items);
        lv.setAdapter(myap);

        mValueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                items.clear();

                for(DataSnapshot x:dataSnapshot.getChildren()){

                    Item val=x.getValue(Item.class);
                    items.add(val);

                }

                myap.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };


    }


    @Override
    protected void onResume() {
        super.onResume();
        //start finger print authentication
        mFingerPrintAuthHelper.startAuth();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mFingerPrintAuthHelper.stopAuth();
    }



    public void insertData(View v){     //make database + populate
                                        //run this once and then comment this code;

        Toast.makeText(this,"Insert Clicked",Toast.LENGTH_SHORT).show();

        //Items Table
        Item a=new Item("Polo1","1","3","3","3","https://drive.google.com/open?id=1KyHJFxr29cRk4iaYrWRLVzziKvxmAVsh");
        Item b=new Item("Polo2", "1", "3", "3", "3", "https://drive.google.com/open?id=1vC6R5YXaJdd_5-LDM5zQV-asGKmPEsMZ");
        Item c=new Item("Polo3","1","3","3","3","https://drive.google.com/open?id=1SXZ92vnQwB6ZirgEy4onvV8wgR2wckZU");
        Item d=new Item("Polo4","1","3","3","3","https://drive.google.com/open?id=1j8EOBpemUtKFpYOJ6njNcaZ9Gm9thZbp");
        Item e=new Item("Polo5","1","3","3","3","https://drive.google.com/open?id=1UBgA5RT2xlJIxBhk3er7BLZ3Vp0EtHFL");
        Item f=new Item("Polo6","1","3","3","3","https://drive.google.com/open?id=1bAfiYHIJc6_4M6Ankfm30IoZRn9bOTr-");
        Item g=new Item("Polo7","1","3","3","3","https://drive.google.com/open?id=1UmmEMuE3ss1MfEK5XkGVvcizlbhZvgT8");
        Item h=new Item("Polo8","1","3","3","3","https://drive.google.com/open?id=1RJcOrRpahrMpC-iiavsYwr_MBNbR-G5I");
        Item i=new Item("Polo9","1","3","3","3","https://drive.google.com/open?id=1thYGUBaqCmQMzfT1iwCBZedrJqhrJnN7");
        Item j=new Item("Polo10","1","3","3","3","https://drive.google.com/open?id=1YM78P3wNOSPz2N-sMjMPyaFPJZsOf_Bq");

        String x=myRef.push().getKey();
        myRef.child(x).setValue(a);
        x=myRef.push().getKey();
        myRef.child(x).setValue(b);
        x=myRef.push().getKey();
        myRef.child(x).setValue(c);
        x=myRef.push().getKey();
        myRef.child(x).setValue(d);
        x=myRef.push().getKey();
        myRef.child(x).setValue(e);
        x=myRef.push().getKey();
        myRef.child(x).setValue(f);
        x=myRef.push().getKey();
        myRef.child(x).setValue(g);
        x=myRef.push().getKey();
        myRef.child(x).setValue(h);
        x=myRef.push().getKey();
        myRef.child(x).setValue(i);
        x=myRef.push().getKey();
        myRef.child(x).setValue(j);


        //*****************************************//
        // making new User and Addition to table
        DatabaseReference userTableRef=FirebaseDatabase.getInstance().getReference("users");

        User u1=new User();
        u1.useremail="g2bdesigns@gmail.com";
        u1.password="ammar1234";
        u1.address="Firebase";
        u1.phno="03030303";

        String autogeneratedKey=userTableRef.push().getKey();

        userTableRef.child(autogeneratedKey).setValue(u1);

        //user addition ends here

        //******************************************//
        //carts table same implementation goes for whishlist
        DatabaseReference cartTableRef=FirebaseDatabase.getInstance().getReference("carts");

        Cart cc=new Cart();

        cc.itemstobuy=items;
        cc.u=u1;

        autogeneratedKey=cartTableRef.push().getKey();

        cartTableRef.child(autogeneratedKey).setValue(c);
        //carts addtion ends here


        //********************************************//
        //Suggestion
        DatabaseReference suggestionTableRef=FirebaseDatabase.getInstance().getReference("suggestions");

        Suggestion obj=new Suggestion();
        obj.suggestion="You app is lit!";
        obj.u=u1;

        autogeneratedKey=suggestionTableRef.push().getKey();
        suggestionTableRef.child(autogeneratedKey).setValue(obj);
        //Suggestion ends here




    }


    public void retrieveObjects(View v){

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                Item value;
                String s="";
                for(DataSnapshot x:dataSnapshot.getChildren()){

                    value=x.getValue(Item.class);
                    s+=value.toString()+"\n";
                    items.add(value);

                }

                //tv.setText(s);


                myap.notifyDataSetChanged();


               /*
                Item value = dataSnapshot.getValue(Item.class);
                tv.setText(value.getItemid());
*/
                //               Log.d("abc",value.getItemid());

                //Log.d("abc", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("abc", "Failed to read value.", error.toException());
            }
        });

    }

    public void querydata(View v){


       //for query
        // Select * from items where itemname="Polo1"

        Query q=FirebaseDatabase.getInstance().getReference("items")
                .orderByChild("itemname")
                .equalTo("Polo1");

        //for query
        // select * from items where iteml<3
        /*
        Query q=FirebaseDatabase.getInstance().getReference("items")
                .orderByChild("itemname")
                .endAt(29);
        */

        q.addListenerForSingleValueEvent(mValueEventListener);      //runquery



    }


    /******CALLBACK OF FINGERPRINTHELPERCLASS**************/
    @Override
    public void onNoFingerPrintHardwareFound() {
        //Device does not have finger print scanner.

    }

    @Override
    public void onNoFingerPrintRegistered() {
        //There are no finger prints registered on this device.
    }

    @Override
    public void onBelowMarshmallow() {
        //Device running below API 23 version of android that does not support finger print authentication.
    }

    @Override
    public void onAuthSuccess(FingerprintManager.CryptoObject cryptoObject) {
        Toast.makeText(this,"Authentication sucessful.",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAuthFailed(int errorCode, String errorMessage) {
        switch (errorCode) {    //Parse the error code for recoverable/non recoverable error.
            case AuthErrorCodes.CANNOT_RECOGNIZE_ERROR:
                //Cannot recognize the fingerprint scanned.
                break;
            case AuthErrorCodes.NON_RECOVERABLE_ERROR:
                //This is not recoverable error. Try other options for user authentication. like pin, password.
                break;
            case AuthErrorCodes.RECOVERABLE_ERROR:
                //Any recoverable error. Display message to the user.
                break;
        }
    }




}



