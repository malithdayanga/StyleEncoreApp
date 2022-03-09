package com.app.shopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class rateActivity extends AppCompatActivity {


    private Button submitFeedbackBtn;
    private EditText InputNameR, InputFeedbackR, InputStarR;
    private ProgressDialog loadingBarR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);

        submitFeedbackBtn = (Button) findViewById(R.id.submitFeedback);
        InputNameR = (EditText) findViewById(R.id.test123);
        InputStarR = (EditText) findViewById(R.id.test456);
        InputFeedbackR = (EditText) findViewById(R.id.test789);
        loadingBarR = new ProgressDialog(this);
        submitFeedbackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RateAppR();
            }
        });

    }
    private void RateAppR(){
        String nameR = InputNameR.getText().toString();
        String StarR = InputStarR.getText().toString();
        String feedbackR = InputFeedbackR.getText().toString();
        if (TextUtils.isEmpty(nameR))
        {
            Toast.makeText(this, "fill it", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(StarR))
        {
            Toast.makeText(this, "fill it", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(feedbackR))
        {
            Toast.makeText(this, "fill it", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBarR.setTitle("Rate app");
            loadingBarR.setMessage("Posting feedback...");
            loadingBarR.setCanceledOnTouchOutside(false);
            loadingBarR.show();

            ValidateNameR(nameR, StarR, feedbackR);
        }

    }

    private void ValidateNameR(final String nameR, final String StarR,final String feedbackR) {
        final DatabaseReference reff;
        reff = FirebaseDatabase.getInstance().getReference();
        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!(dataSnapshot.child("Rate").child(nameR).exists())){
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("nameR", nameR);
                    userdataMap.put("feedbackR", feedbackR);
                    userdataMap.put("StarR", StarR);
                    reff.child("Rate").child(nameR).updateChildren(userdataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful())
                            {
                                Toast.makeText(rateActivity.this, "done.", Toast.LENGTH_SHORT).show();
                                loadingBarR.dismiss();
                                Intent intent = new Intent(rateActivity.this, com.app.shopping.HomeActivity.class);
                                startActivity(intent);
                            }
                            else
                            {
                                loadingBarR.dismiss();
                                Toast.makeText(rateActivity.this, "err", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });



                }/*
                
                
                To display Toast Msg :  enable this
                
                
                else {
                    Toast.makeText(rateActivity.this, "Dear " + nameR + " you have added some feedback....", Toast.LENGTH_SHORT).show();
                    loadingBarR.dismiss();
                    Toast.makeText(rateActivity.this, "Please try again using another account.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(rateActivity.this, com.app.shopping.HomeActivity.class);
                    startActivity(intent);
                }
            }
                
                
                
                
                
                */
                else {
                    Toast.makeText(rateActivity.this, "Dear " + nameR + " you have added some feedback....", Toast.LENGTH_SHORT).show();
                    loadingBarR.dismiss();
                    Toast.makeText(rateActivity.this, "Please try again using another account.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(rateActivity.this, com.app.shopping.HomeActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}



