package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private File file;
    PrintWriter writer;
    LocalDateTime timeStamp;
    public Logger(String fileName) {
        this.file = new File(fileName);
    }

    public void write(String message) {
        try {
            this.writer = new PrintWriter(new FileOutputStream(file.getAbsoluteFile(), true));
            LocalDateTime thisTime = timeStamp.now();
            DateTimeFormatter formattedTime = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a");
            String printTime = thisTime.format(formattedTime);
            this.writer.println(printTime + " " + message);
            this.writer.flush();
            this.writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
