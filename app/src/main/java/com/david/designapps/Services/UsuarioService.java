package com.david.designapps.Services;


import com.david.designapps.DTO.UsuarioLoginDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UsuarioService {



        @POST("Usuario/Login")
        Call<UsuarioLoginDTO> Login(@Body UsuarioLoginDTO usuario);

}
