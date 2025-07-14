package com.david.designapps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.david.designapps.DTO.UsuarioLoginDTO;
import com.david.designapps.Services.ServiceClient;
import com.david.designapps.Services.UsuarioService;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Login extends AppCompatActivity {

    private TextInputLayout txtUsuario;
    private TextInputLayout txtPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login2);

        txtUsuario =(TextInputLayout) findViewById(R.id.txtUsuario);
        txtPassword =(TextInputLayout) findViewById(R.id.txtPassword);
        btnLogin =(Button) findViewById(R.id.btnIniciarSesion);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtUsuario.getEditText().toString().trim().isEmpty()){
                    Toast.makeText(Login.this, "Usuario obligatorio", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(txtPassword.getEditText().toString().trim().isEmpty()){
                    Toast.makeText(Login.this, "contraseña obligatoria", Toast.LENGTH_SHORT).show();
                return;
                }

                ServiceClient client = new ServiceClient();
                Retrofit retrofit = client.BuildRetrofitClient();
                UsuarioService service = retrofit.create(UsuarioService.class);

                UsuarioLoginDTO usuarioDTO = new UsuarioLoginDTO();
                usuarioDTO.setUsuario(txtUsuario.getEditText().getText().toString());
                usuarioDTO.setClave(txtPassword.getEditText().getText().toString());

                Call<UsuarioLoginDTO> usuarioCall = service.Login(usuarioDTO);

                usuarioCall.enqueue(new Callback<UsuarioLoginDTO>() {
                    @Override
                    public void onResponse(Call<UsuarioLoginDTO> call, Response<UsuarioLoginDTO> response) {
                        if (response.isSuccessful()) {
                            UsuarioLoginDTO responseBody = response.body();

                            if(responseBody == null){
                                Toast.makeText(Login.this, "Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (responseBody.getUsuario() == null) {
                                Toast.makeText(Login.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            if (responseBody.getUsuario() != null) {
                                Toast.makeText(Login.this, "Bienvenido " + responseBody.getUsuario(), Toast.LENGTH_SHORT).show();

                                Intent inicio = new Intent(Login.this, MainActivity.class);
                                startActivity(inicio);
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<UsuarioLoginDTO> call, Throwable t) {
                        Toast.makeText(Login.this, "OCURRIÓ UN ERROR EN EL SERVIDOR", Toast.LENGTH_SHORT).show();
                        return;
                    }

                });




                //GitHubService service = retrofit.create(GitHubService.class);
            }
        });
    }
}