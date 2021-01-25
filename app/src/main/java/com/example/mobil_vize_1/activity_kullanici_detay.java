package com.example.mobil_vize_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class activity_kullanici_detay extends AppCompatActivity {
    TextView tvDetayKullaniciAdi, tvDetayEmail, tvDetayKatilan;
    UserResponse userResponse;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kullanici_detay);


        tvDetayKullaniciAdi = findViewById(R.id.tvDetayKullaniciAdi);
        tvDetayEmail = findViewById(R.id.tvDetayEmail);
        tvDetayKatilan = findViewById(R.id.tvDetayKatilan);

        Intent intent = getIntent();
        if(intent.getExtras() != null){
            userResponse = (UserResponse) intent.getSerializableExtra("data");

            String username = userResponse.getUsername();
            String useremail = userResponse.getEmail();
            String userjoined = userResponse.getDate_joined();

            tvDetayKullaniciAdi.setText(username);
            tvDetayEmail.setText(useremail);
            tvDetayKatilan.setText(userjoined);
        }
    }
}