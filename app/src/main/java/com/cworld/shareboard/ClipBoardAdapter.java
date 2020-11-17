package com.cworld.shareboard;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cworld.shareboard.data.RecyclerClipboard;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static android.content.Context.CLIPBOARD_SERVICE;

public class ClipBoardAdapter extends RecyclerView.Adapter<ClipBoardAdapter.ItemViewHolder> {

    private ArrayList<RecyclerClipboard> listData = new ArrayList<>();
    ClipBoardActivity clipBoardActivity;

    private ClipboardClickListener listener;

    public ClipBoardAdapter(ClipboardClickListener listener) {
        this.listener = listener;
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.clipboard_item, parent, false);
        return new ItemViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        RecyclerClipboard recyclerClipboard = listData.get(position);
        holder.deviceName.setText(recyclerClipboard.getDeviceName());
        holder.deviceType.setText(recyclerClipboard.getDeviceType());
        holder.boardDate.setText(recyclerClipboard.getDate());
        holder.board.setText(recyclerClipboard.getBoard());

        holder.itemView.setOnClickListener((v)-> {
            listener.onClickListener(recyclerClipboard);
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    void addItem(RecyclerClipboard data) {
        listData.add(data);
    }

    void resetItem() {
        listData.clear();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView deviceName;
        private TextView deviceType;
        private TextView boardDate;
        private TextView board;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            deviceName = itemView.findViewById(R.id.device_name);
            deviceType = itemView.findViewById(R.id.device_type);
            boardDate = itemView.findViewById(R.id.board_date);
            board = itemView.findViewById(R.id.board);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    int pos = getAdapterPosition();
//                    if(pos != RecyclerView.NO_POSITION);
//                    Log.e("ShareBoard",listData.get(pos).getBoard());
//
//                    clipBoardActivity.clipboardManager = (ClipboardManager)clipBoardActivity.getSystemService(CLIPBOARD_SERVICE);
//                    ClipData clipData = ClipData.newPlainText("ShareBoard", board.getText());
//
//                    clipBoardActivity.clipboardManager.setPrimaryClip(clipData);
//
//                    Toast.makeText(clipBoardActivity, board.getText() + " 복사!", Toast.LENGTH_SHORT).show();
//
//                }
//            });
        }
    }

}
