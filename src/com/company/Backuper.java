package com.company;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Timer;
import java.util.TimerTask;

public class Backuper {

    private String from;
    private String to;
    private int timeout_ms;


    public Backuper(String from, String to, int timeout_ms) {
        this.from = from;
        this.to = to;
        this.timeout_ms = timeout_ms;
    }

    public void run() {
        Timer timerObj = new Timer();
        class r extends TimerTask {
            @Override
            public void run() {
                backupData(new File(from));
            }
        }

        timerObj.scheduleAtFixedRate(new r(), 0, timeout_ms);
    }


    private void backupData(File folder) {
        File[] fileNames = folder.listFiles();
        for (File file : fileNames){
            // if directory call the same method again
            if (file.isDirectory()) {
                backupData(file);
            } else {

                String inputFile = file.getAbsolutePath();
                String outputFile = to + inputFile.substring(inputFile.lastIndexOf('\\'), inputFile.length());
                System.out.println("Copying file: " + inputFile + " to " + outputFile);
                try {
                    Files.copy(Paths.get(inputFile), Paths.get(outputFile), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
