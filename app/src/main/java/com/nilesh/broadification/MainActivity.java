package com.nilesh.broadification;

import static com.google.firebase.messaging.reporting.MessagingClientEvent.MessageType.TOPIC;
import static com.nilesh.broadification.models.Constants.TOPIC_ALL;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;
import com.nilesh.broadification.api.ApiUtilities;
import com.nilesh.broadification.models.NotificationData;
import com.nilesh.broadification.models.PushNOtification;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText title,message;
    Button sendBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        //spinner data and adapter
//        String[] arraySpinner = new String[]{
//                "General", "Staff"
//        };
//        Spinner spin1 = (Spinner) findViewById(R.id.topic_spin);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_spinner_item, arraySpinner);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spin1.setAdapter(adapter);
//


        title=(EditText) findViewById(R.id.edt_title);
        message=(EditText) findViewById(R.id.edt_message);
        sendBtn=(Button) findViewById(R.id.btn_send);
        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC_ALL);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titletxt= title.getText().toString();
                String msgtxt=message.getText().toString();

                if (!titletxt.isEmpty() && !msgtxt.isEmpty()){
                    PushNOtification notification = new PushNOtification(new NotificationData(titletxt,msgtxt),TOPIC_ALL);
                    sendNotification(notification);
                }
            }
        });

    }

    private void sendNotification(PushNOtification notification) {

        ApiUtilities.getClient().sendNotification(notification).enqueue(new Callback<PushNOtification>() {
            @Override
            public void onResponse(Call<PushNOtification> call, Response<PushNOtification> response) {
                if (response.isSuccessful()){
                    Toast.makeText(MainActivity.this,"success", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(MainActivity.this,"success", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<PushNOtification> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }
}