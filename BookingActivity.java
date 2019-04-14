package com.example.user.safetransitproject;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class BookingActivity extends AppCompatActivity {
    private static final String TAG = "booking";
    TextView orderdate,deliverydate;
    Button orderdatebutton,deliverydatebutton,placesOrder;
    EditText from,to,product,quantity;
    FirebaseFirestore db = FirebaseFirestore.getInstance();// Create a new user with a first and last name
    FirebaseUser user ;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        orderdate = findViewById(R.id.orderdate);
        deliverydate = findViewById(R.id.deliverydate);
        orderdatebutton = findViewById(R.id.orderdatebutton);
        deliverydatebutton = findViewById(R.id.deliverydatebutton);
        quantity = findViewById(R.id.quanity);
        product = findViewById(R.id.product);
        to = findViewById(R.id.to);
        from = findViewById(R.id.from);
        placesOrder = findViewById(R.id.placeorder);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        orderdatebutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(BookingActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.datepicker_dialog);
                dialog.show();
                DatePicker datePicker = dialog.findViewById(R.id.datePicker1);
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {

                    @Override
                    public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        Log.d("Date", "Year=" + year + " Month=" + (month + 1) + " day=" + dayOfMonth);
                        orderdate.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                        dialog.dismiss();
                    }
                });
            }
        });
        deliverydatebutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(BookingActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.datepicker_dialog);
                dialog.show();
                DatePicker datePicker = dialog.findViewById(R.id.datePicker1);
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {

                    @Override
                    public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        Log.d("Date", "Year=" + year + " Month=" + (month + 1) + " day=" + dayOfMonth);
                        deliverydate.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                        dialog.dismiss();
                    }
                });
            }
        });

        placesOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToFireStore();
            }
        });

    }
    public void addToFireStore(){
        progressBar.setVisibility(View.VISIBLE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        month++;
        Map<String, Object> order = new HashMap<>();
        order.put("from", from.getText().toString());
        order.put("to", to.getText().toString());
        order.put("product", product.getText().toString());
        order.put("quantity", quantity.getText().toString());
        order.put("pickupdt", orderdate.getText().toString());
        order.put("deliverydt", deliverydate.getText().toString());
        order.put("orderdt", day+"/"+month+"/"+year);
        order.put("USERid", user.getUid());

    // Add a new document with a generated ID
        db.collection("Orders")
                .add(order)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        addToUser(documentReference.getId());
                        progressBar.setVisibility(View.GONE);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                        Toast.makeText(BookingActivity.this, "error : "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                });

    }

    public void addToUser(String orderId){
        progressBar.setVisibility(View.VISIBLE);

        Map<String, Object> order = new HashMap<>();
        order.put("orderId", orderId);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int sec = calendar.get(Calendar.SECOND);
        int minute = calendar.get(Calendar.MINUTE);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        String time = hour+":"+minute+":"+sec+" "+day+"-"+month+"-"+year;
        db.collection("users").document(user.getUid()).collection("orders")
                .add(order)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(BookingActivity.this, "Order Placed\nOrder id: "+orderId, Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(BookingActivity.this, "error : "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        user = FirebaseAuth.getInstance().getCurrentUser();
    }
}
