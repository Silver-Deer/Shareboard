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

    List<RetroFitClipboard> clipList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clip_board);

        Button refresh = findViewById(R.id.activity_clip_board_refresh);

        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

        SharedPreferences sharedPreferences = getSharedPreferences("sFile", MODE_PRIVATE);
        String token = sharedPreferences.getString("token","");

        refresh.setOnClickListener(v->{
            Call<List<RetroFitClipboard>> request = RetroFitClient.getInstance().getApi().getClipboard(token);
            request.enqueue(new Callback<List<RetroFitClipboard>>() {
                @Override
                public void onResponse(Call<List<RetroFitClipboard>> call, Response<List<RetroFitClipboard>> response) {
                    clipList = response.body();

                    Toast.makeText(ClipBoardActivity.this
                            , "클립보드 조회", Toast.LENGTH_SHORT).show();

                    Log.e("ClipBoard", clipList.get(0).getBoard());
                }

                @Override
                public void onFailure(Call<List<RetroFitClipboard>> call, Throwable t) {
                    Toast.makeText(ClipBoardActivity.this,
                            t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });



    }
}