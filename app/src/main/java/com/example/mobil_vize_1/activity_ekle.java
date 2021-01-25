package com.example.mobil_vize_1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_ekle extends AppCompatActivity {

    EditText kullanici_txt, ad_txt, soyad_txt, email_txt;
    Button btnKayitOl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekle);

        kullanici_txt = findViewById(R.id.kullanici_txt);
        ad_txt = findViewById(R.id.ad_txt);
        soyad_txt = findViewById(R.id.soyad_txt);
        email_txt = findViewById(R.id.email_txt);
        btnKayitOl = findViewById(R.id.btnKayitOl);

        btnKayitOl.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                SaveUser(createRequest());
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public UserRequest createRequest(){
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername(kullanici_txt.getText().toString());
        userRequest.setFirst_name(ad_txt.getText().toString());
        userRequest.setLast_name(soyad_txt.getText().toString());
        userRequest.setEmail(email_txt.getText().toString());

        return userRequest;
    }

    public void SaveUser(UserRequest userRequest){
        Call<UserResponse> userResponseCall = ApiConnection.getUserService().saveUsers(userRequest);
        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(response.isSuccessful()){
                    Toast.makeText(activity_ekle.this, "Kullanıcı Kaydedildi.", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(activity_ekle.this, MainActivity.class));
                }else{
                    Toast.makeText(activity_ekle.this, "Fail olduk brom ", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(activity_ekle.this, "Fail olduk brom " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

}