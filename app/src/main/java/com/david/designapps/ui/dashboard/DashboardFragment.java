package com.david.designapps.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.david.designapps.DTO.ProductoDTO;
import com.david.designapps.R;
import com.david.designapps.Services.ProductosService;
import com.david.designapps.Services.ServiceClient;
import com.david.designapps.databinding.FragmentDashboardBinding;
import com.david.designapps.utils.ProductosAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DashboardFragment extends Fragment {

    private ProductosAdapter productosAdapter;
    private ArrayList<HashMap<String, Object>> listaProductos;
    private ListView listaProductosView;

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        listaProductosView = root.findViewById(R.id.Lista_productos);

        ServiceClient serviceClient = new ServiceClient();
        Retrofit retrofit = serviceClient.BuildRetrofitClient();
        ProductosService productosService = retrofit.create(ProductosService.class);
        Call<List<ProductoDTO>> callProductos = productosService.GetListaProductos();

        callProductos.enqueue(new Callback<List<ProductoDTO>>() {
            @Override
            public void onResponse(Call<List<ProductoDTO>> call, Response<List<ProductoDTO>> response) {
                if (response.isSuccessful()) {
                    List<ProductoDTO> productos = response.body();
                    listaProductos = new ArrayList<>();

                    if (productos != null) {
                        for (ProductoDTO producto : productos) {
                            HashMap<String, Object> data = new HashMap<>();
                            data.put("NombreProducto", producto.getNombreProducto());
                            data.put("Precio", producto.getPrecio());
                            listaProductos.add(data);
                        }

                        productosAdapter = new ProductosAdapter(
                                root.getContext(),
                                listaProductos,
                                R.layout.item_list_productos,
                                new String[]{"NombreProducto", "Precio"},
                                new int[]{ R.id.txt_nombre_producto, R.id.txt_precio_producto }
                        );

                        listaProductosView.setAdapter(productosAdapter);
                        productosAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ProductoDTO>> call, Throwable t) {
                // Aquí puedes mostrar un Toast o registrar el error
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}