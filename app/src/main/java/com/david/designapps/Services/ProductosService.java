package com.david.designapps.Services;

import com.david.designapps.DTO.ProductoDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductosService {
    @GET("Producto")
    Call<List<ProductoDTO>>GetListaProductos();
}
