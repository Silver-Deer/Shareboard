package com.cworld.shareboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cworld.shareboard.Server.RetroFitClient;
import com.cworld.shareboard.data.RetroFitAutoLogin;
import com.cworld.shareboard.data.RetroFitLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences("sFile",MODE_PRIVATE);

        String token = sharedPreferences.getString("token", "");

        if(!token.equals("")) {
            Log.e("token", token);

            Call<RetroFitAutoLogin> call = RetroFitClient.getInstance().getApi().autoLogin(token);
            call.enqueue(new Callback<RetroFitAutoLogin>() {
                @Override
                public void onResponse(Call<RetroFitAutoLogin> call, Response<RetroFitAutoLogin> response) {
                    RetroFitAutoLogin retroFitAutoLogin = response.body();
                    if(response.code() == 200) {
                        if(retroFitAutoLogin.getResult().equals("1")) {
                            Intent intent = new Intent(LoginActivity.this, ClipBoardActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }

                @Override
                public void onFailure(Call<RetroFitAutoLogin> call, Throwable t) {

                }
            });


        }

        setContentView(R.layout.activity_login);

        EditText id_et, pw_et;
        Button login;
        Button signin;
        id_et =findViewById(R.id.activity_login_id);
        pw_et =findViewById(R.id.activity_login_pw);

        login = findViewById(R.id.activity_login_button);

        signin = findViewById(R.id.activity_login_sign_in_button);
        //RetroFitApi retroFitApi = RetroFitClient.getApiService();

        login.setOnClickListener(v -> {
            String id = id_et.getText().toString();
            String pw = pw_et.getText().toString();

            if(id.equals("") && pw.equals(""))
                Toast.makeText(LoginActivity.this,
                        "아이디 또는 비밀번호가 공백입니다.", Toast.LENGTH_SHORT).show();
            else {
                RetroFitLogin retroFitLogin = new RetroFitLogin(id, pw);
                Call<RetroFitLogin> call = RetroFitClient.getInstance().getApi().loginRequest(retroFitLogin);
                call.enqueue(new Callback<RetroFitLogin>() {
                    @Override
                    public void onResponse(Call<RetroFitLogin> call, Response<RetroFitLogin> response) {
                        if(response.code() == 200) {
                            RetroFitLogin loginResponse = response.body();

                            if(loginResponse.getResult().equals("1")) {
                                Log.e("login", loginResponse.string());
                                SharedPreferences sharedPreferences = getSharedPreferences("sFile", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("token", loginResponse.getToken());
                                editor.commit();


                                Intent intent = new Intent(LoginActivity.this, ClipBoardActivity.class);
                                intent.putExtra("id", id);
                                intent.putExtra("pw", pw);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Toast.makeText(LoginActivity.this,
                                        "아이디 또는 비밀번호가 잘못되었습니다.", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else if (response.code() == 401) {
                            Toast.makeText(LoginActivity.this,
                                        "아이디 또는 비밀번호가 잘못되었습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<RetroFitLogin> call, Throwable t) {
                        Toast.makeText(LoginActivity.this,
                                t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }

        });

        signin.setOnClickListener(v-> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

    }
}