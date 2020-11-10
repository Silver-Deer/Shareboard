package com.cworld.shareboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cworld.shareboard.Server.RetroFitApi;
import com.cworld.shareboard.Server.RetroFitClient;
import com.cworld.shareboard.data.RetroFitRegister;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText id_et;
        EditText pw_et;
        EditText pw_et_re;

        Button register;

        id_et = findViewById(R.id.activity_register_id);
        pw_et = findViewById(R.id.activity_register_pw);
        pw_et_re = findViewById(R.id.activity_register_pw_re);

        register = findViewById(R.id.activity_register_button);

        //RetroFitApi retroFitApi = RetroFitClient.getApiService();

        register.setOnClickListener(v-> {
            String id = id_et.getText().toString();
            String pw = pw_et.getText().toString();
            String pw_re = pw_et_re.getText().toString();

            if(id.equals("") && pw.equals(""))
                Toast.makeText(RegisterActivity.this,
                        "아이디 또는 비밀번호가 공백입니다.", Toast.LENGTH_SHORT).show();
            else if(!pw.equals(pw_re))
                Toast.makeText(RegisterActivity.this,
                        "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show();
            else {
                RetroFitRegister retroFitRegister = new RetroFitRegister(id, pw);
                Call<RetroFitRegister> call = RetroFitClient.getInstance().getApi().registerRequest(retroFitRegister);
                call.enqueue(new Callback<RetroFitRegister>() {
                    @Override
                    public void onResponse(Call<RetroFitRegister> call, Response<RetroFitRegister> response) {
                        if (response.code() == 200) {
                            RetroFitRegister retroFitRegister = response.body();
                            if (retroFitRegister.getResult().equals("1")) {
                                Log.e("Register", "회원가입 성공");
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                intent.putExtra("id", id);
                                intent.putExtra("pw", pw);
                                finish();
                            }
                        }
                        Log.e("Register", String.valueOf(response.code()));
                    }
                    @Override
                    public void onFailure(Call<RetroFitRegister> call, Throwable t) {
                        Toast.makeText(RegisterActivity.this,
                                t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }


        });

    }
}