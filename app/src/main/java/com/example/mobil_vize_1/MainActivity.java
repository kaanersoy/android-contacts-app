package com.example.mobil_vize_1;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.appcompat.widget.SearchView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mobil_vize_1.R.menu.menu;


public class MainActivity extends AppCompatActivity implements UsersAdapter.ClickedItem {
    Toolbar toolbar;
    FloatingActionButton btnEkle;
    RecyclerView kullaniciListe;
    UsersAdapter usersAdapter;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);
        this.getSupportActionBar().setTitle("");

        btnEkle = (FloatingActionButton) findViewById(R.id.btnEkle);
        kullaniciListe = (RecyclerView) findViewById(R.id.kullaniciListe);


        kullaniciListe.setLayoutManager(new LinearLayoutManager(this));
        kullaniciListe.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        usersAdapter = new UsersAdapter(this::ClickedUser);
        KullaniciGoster();
    }


    public void KullaniciGoster(){
        Call<List<UserResponse>> userList=ApiConnection.getUserService().getAllUsers();
        userList.enqueue(new Callback<List<UserResponse>>() {

            @Override
            public void onResponse(Call<List<UserResponse>> call, Response<List<UserResponse>> response) {
                if(response.isSuccessful()){
                    List<UserResponse> usersResponse = response.body();
                    usersAdapter.SetData(usersResponse);
                    kullaniciListe.setAdapter(usersAdapter);
                }
            }
            @Override
            public void onFailure(Call<List<UserResponse>> call, Throwable t) {
                Log.e("failure:",t.getLocalizedMessage());
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.search_view);

        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                usersAdapter.getFilter().filter(text);
                return true;
            }
        });
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.search_view){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void Goster(View view) {
        startActivity(new Intent(this, activity_ekle.class));
        finish();
    }

    @Override
    public void ClickedUser(UserResponse userResponse) {
        startActivity(new Intent(this, activity_kullanici_detay.class).putExtra("data", userResponse));
    }
}