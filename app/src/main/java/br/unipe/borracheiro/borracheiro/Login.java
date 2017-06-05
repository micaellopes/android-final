package br.unipe.borracheiro.borracheiro;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.io.IOException;

import br.unipe.borracheiro.borracheiro.core.AuthController;
import br.unipe.borracheiro.borracheiro.core.Constants;
import br.unipe.borracheiro.borracheiro.model.LoginRequest;
import br.unipe.borracheiro.borracheiro.model.LoginResult;
import br.unipe.borracheiro.borracheiro.model.User;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Login extends AppCompatActivity {

    EditText etSenha;
    EditText etEmail;
    Button btnEntrar;
    private String responseJSON;
    Login login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        etSenha = (EditText) findViewById(R.id.et_senha);
        etEmail = (EditText) findViewById(R.id.et_email);
        btnEntrar = (Button) findViewById(R.id.btn_entrar);

//        btnEntrar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                new ExampleOkHttp().execute();
//            }
//        });
    }

    public String parseUserToJSON(){
        String email = etEmail.getText().toString();
        String password = etSenha.getText().toString();

        User usuario = new User();
        usuario.setEmail(email);
        usuario.setPassword(password);

        Gson gson = new Gson();
        String usuarioJSONString = gson.toJson(usuario);

        LoginRequest r = new LoginRequest();
        r.setJson(usuarioJSONString);

        usuarioJSONString = gson.toJson(r);

        return usuarioJSONString;
    }


    private class ExampleOkHttp extends AsyncTask<Void, Void, Response> {

        OkHttpClient client;
        Request request;

        public final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            client = new OkHttpClient();
            RequestBody body = RequestBody.create(JSON, parseUserToJSON());
            //URL exemplo: n recebe post requests
            request = new Request.Builder()
                    .url(Constants.URL_LOGIN)
                    .post(body)
                    .build();
        }
        @Override
        protected Response doInBackground(Void... params) {
            try {
                Response response = client.newCall(request).execute();
                return response;
            }catch (IOException e){
                return null;
            }
        }
        @Override
        protected void onPostExecute(Response response) {
            try {
                if (response.message().equals("true")){
                    responseJSON = AuthController.getInstance().loadAuthFromJSON(response.body().string());
                }
            }catch (IOException e){
                responseJSON = "OPS - Fail connection";
            }

            Toast.makeText(Login.this, responseJSON,Toast.LENGTH_LONG).show();
        }
    }

    public void entrarRoubando(View v){
        Intent intent = new Intent(Login.this, Home.class);
        startActivity(intent);
        finish();
    }

};