package com.cworld.shareboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.cworld.shareboard.Server.RetroFitClient;
import com.cworld.shareboard.data.RetroFitClipboard;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClipBoardActivity extends AppCompatActivity {

    RetroFitClipboard clipList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clip_board);

        Button refresh = findViewById(R.id.activity_clip_board_refresh);

        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

        SharedPreferences sharedPreferences = getSharedPreferences("sFile", MODE_PRIVATE);
        String token = sharedPreferences.getString("token","");
        Log.i("token", token);

        refresh.setOnClickListener(v->{
            Call<RetroFitClipboard> request = RetroFitClient.getInstance().getApi().getClipboard(token);
            request.enqueue(new Callback<RetroFitClipboard>() {
                @Override
                public void onResponse(Call<RetroFitClipboard> call, Response<RetroFitClipboard> response) {
                    Log.e("ClipBoard", String.valueOf(response.code()));
                    clipList = response.body();

                    Toast.makeText(ClipBoardActivity.this
                            , "클립보드 조회 " + clipList.result.size(), Toast.LENGTH_SHORT).show();

                    //Log.e("ClipBoard", clipList.get(0).getBoard());
                }

                @Override
                public void onFailure(Call<RetroFitClipboard> call, Throwable t) {
                    Toast.makeText(ClipBoardActivity.this,
                            t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });



    }
}