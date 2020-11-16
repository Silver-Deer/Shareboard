package com.cworld.shareboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cworld.shareboard.data.RecyclerClipboard;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ClipBoardAdapter extends RecyclerView.Adapter<ClipBoardAdapter.ItemViewHolder> {

    private ArrayList<RecyclerClipboard> listData = new ArrayList<>();


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.clipboard_item, parent, false);
        return new ItemViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.onBind(listData.get(position));
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    void addItem(RecyclerClipboard data) {
        listData.add(data);
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
        }

        void onBind(RecyclerClipboard data) {
            deviceName.setText(data.getDeviceName());
            deviceType.setText(data.getDeviceType());
            boardDate.setText(data.getDate());
            board.setText(data.getBoard());
        }
    }

}
