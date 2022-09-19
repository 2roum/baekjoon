import java.util.*;
class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        if (play_time.equals(adv_time))
            return "00:00:00";
        int playTime = timeToSecond(play_time);
        int advTime = timeToSecond(adv_time);
        int[] log_start = new int[logs.length];
        long[] total = new long[playTime + 1];
        int[] log_end = new int[logs.length];
        for (int i = 0 ; i < logs.length ; i++){
            String[] s = logs[i].split("-");
            log_start[i] = timeToSecond(s[0]);
            log_end[i] = timeToSecond(s[1]);
            total[log_start[i]] += 1;
            total[log_end[i]] -= 1;
        }
        for (int i = 1; i < playTime ; i++){
            total[i] += total[i-1];
        }
        for (int i = 1; i < playTime ; i++){
            total[i] += total[i-1];
        }
        long max = 0;
        int start = 0;
        for (int i = 0; i < playTime ; i++){
            if (i < advTime){
                if (max < total[i]){
                    max = total[i];
                    start = 0;
                }
            }
            else {
                long length = total[i] - total[i-advTime];
                if (max < length){
                    max = length;
                    start = i-advTime+1;
                }
            }
        }
        return secToTime(start);
    }
    public int timeToSecond(String log){
        String[] hms = log.split(":");
        int hour = Integer.parseInt(hms[0]);
        int minute = Integer.parseInt(hms[1]);
        int second = Integer.parseInt(hms[2]);
        return (hour*3600 + minute*60 + second);
    }
    public String secToTime(int ans){
        int hour = ans / 3600;
        int minute = (ans % 3600) / 60;
        int sec = (ans % 60);
        StringBuilder sb = new StringBuilder();
        if (hour < 10){
            sb.append("0" + hour);
        }
        else
            sb.append(hour);
        sb.append(":");
        if (minute < 10)
            sb.append("0" +minute);
        else
            sb.append(minute);
        sb.append(":");
        if (sec < 10)
            sb.append("0" + sec);
        else
            sb.append(sec);
        return sb.toString();
    }
}
