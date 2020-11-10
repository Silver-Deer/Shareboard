package com.cworld.shareboard.data;

import java.util.List;

public class RetroFitClipboard {
    public List<ClipItem> result;

    class ClipItem {
        int boardId;
        String board;
        int deviceId;
        String date;
        String deviceName;
        String userId;
        String typeName;

        public int getBoardId() {
            return boardId;
        }

        public String getBoard() {
            return board;
        }

        public int getDeviceId() {
            return deviceId;
        }

        public String getDate() {
            return date;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public String getUserId() {
            return userId;
        }

        public String getTypeName() {
            return typeName;
        }
    }
}
