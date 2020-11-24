package com.cworld.shareboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.cworld.shareboard.Server.RetroFitClient;
import com.cworld.shareboard.data.RecyclerClipboard;
import com.cworld.shareboard.data.RetroFitClipboard;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClipBoardActivity extends AppCompatActivity {
    RetroFitClipboard clipList;
    private ClipBoardAdapter adapter;

    private ClipboardClickListener clickListener = (content)-> {
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("SharedBoard", content.getBoard());
        clipboardManager.setPrimaryClip(clipData);
        Toast.makeText(this, content.getBoard() + "복사", Toast.LENGTH_SHORT).show();

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clip_board);

        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

        Button refresh = findViewById(R.id.activity_clip_board_refresh);
        Button load = findViewById(R.id.activity_clipboard_load);
        Button logout = findViewById(R.id.logout);

        SharedPreferences sharedPreferences = getSharedPreferences("sFile", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String token = sharedPreferences.getString("token","");
        Log.i("token", token);


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ClipBoardAdapter(clickListener);
        recyclerView.setAdapter(adapter);

        Call<RetroFitClipboard> start_request = RetroFitClient.getInstance().getApi().getClipboard(token);
        start_request.enqueue(new Callback<RetroFitClipboard>() {
            @Override
            public void onResponse(Call<RetroFitClipboard> call, Response<RetroFitClipboard> response) {
                Log.e("ClipBoard", String.valueOf(response.code()));
                clipList = response.body();

                Toast.makeText(ClipBoardActivity.this
                        , "클립보드 조회 " , Toast.LENGTH_SHORT).show();

                adapter.resetItem();

                for(int position = 0; position < clipList.result.size(); position++) {
                    addItem(clipList.result.get(position).getDeviceName(), clipList.result.get(position).getDeviceType(), clipList.result.get(position).getDate(), clipList.result.get(position).getBoard());
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<RetroFitClipboard> call, Throwable t) {
                Toast.makeText(ClipBoardActivity.this,
                        t.getMessage(), Toast.LENGTH_SHORT).show();
            }


        });

        refresh.setOnClickListener(v->{
            Call<RetroFitClipboard> request = RetroFitClient.getInstance().getApi().getClipboard(token);
            request.enqueue(new Callback<RetroFitClipboard>() {
                @Override
                public void onResponse(Call<RetroFitClipboard> call, Response<RetroFitClipboard> response) {
                    Log.e("ClipBoard", String.valueOf(response.code()));
                    clipList = response.body();
                    Toast.makeText(ClipBoardActivity.this
                            , "클립보드 조회 ", Toast.LENGTH_SHORT).show();

                    adapter.resetItem();

                    for(int position = 0; position < clipList.result.size(); position++) {
                        addItem(clipList.result.get(position).getDeviceName(), clipList.result.get(position).getDeviceType(), clipList.result.get(position).getDate(), clipList.result.get(position).getBoard());
                    }

                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<RetroFitClipboard> call, Throwable t) {
                    Toast.makeText(ClipBoardActivity.this,
                            t.getMessage(), Toast.LENGTH_SHORT).show();
                }


            });
        });

        load.setOnClickListener(v->{
            ClipData clipData = clipboardManager.getPrimaryClip();
            String clipText = clipData.getItemAt(0).getText().toString();
            Log.e("ClipData", clipText);



        });

        logout.setOnClickListener(v->{
            Intent intent = new Intent(ClipBoardActivity.this, LoginActivity.class);
            editor.remove("token");
            editor.commit();

            startActivity(intent);
            finish();
        });



    }

    public void addItem(String deviceName, String deviceType, String date, String board) {
        RecyclerClipboard item = new RecyclerClipboard();
        item.setDeviceName(deviceName);
        item.setDeviceType(deviceType);
        item.setDate(date);
        item.setBoard(board);

        adapter.addItem(item);
    }

}