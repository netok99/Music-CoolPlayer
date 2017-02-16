package com.musiccoolplayer.core;

import android.content.Context;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AudiosManager {

    public static ArrayList<HashMap<String, String>> getPlayList(Context context) {
        ArrayList<HashMap<String, String>> audioList = new ArrayList<>();
        File home = new File(String.valueOf(context.getFilesDir()));
        if (home.listFiles(new FileExtensionFilter()).length > 0) {
            for (File file : home.listFiles(new FileExtensionFilter())) {
                HashMap<String, String> song = new HashMap<>();
                song.put("songTitle", file.getName().substring(0, (file.getName().length() - 4)));
                song.put("songPath", file.getPath());
                audioList.add(song);
            }
        }
        return audioList;
    }

    public static boolean audioExist(Context context, String path) {
        for (HashMap<String, String> audio : getPlayList(context)) {
            if (audio.get("songTitle").equals(path)) {
                return true;
            }
        }
        return false;
    }

    private static class FileExtensionFilter implements FilenameFilter {
        public boolean accept(File dir, String name) {
            return (name.endsWith(".mp3") || name.endsWith(".MP3"));
        }
    }

    public static boolean deleteAudio(Context context, String path) {
        if (audioExist(context, path)) {
            File file = new File(path);
            return file.delete();
        }
        return false;
    }

    public static void deleteAllAudio(Context context, List<String> audiosPaths) {
        for (String audiopath : audiosPaths) {
            deleteAudio(context, audiopath);
        }
    }

    public static String milliSecondsToTimer(long milliseconds) {
        String finalTimerString = "";
        String secondsString;
        int hours = (int) (milliseconds / (1000 * 60 * 60));
        int minutes = (int) (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);
        if (hours > 0) {
        }
        if (seconds < 10) {
            secondsString = "0" + seconds;
        } else {
            secondsString = "" + seconds;
        }
        finalTimerString = finalTimerString + minutes + ":" + secondsString;
        return finalTimerString;
    }
}