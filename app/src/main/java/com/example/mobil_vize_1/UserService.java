package com.example.mobil_vize_1;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {

    @POST("users/")
    Call<UserResponse> saveUsers(@Body UserRequest userRequest);

    @GET("users/")
    Call<List<UserResponse>>  getAllUsers();

}