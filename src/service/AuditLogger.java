package service;

import java.io.FileWriter;
import java.io.IOException;

public class AuditLogger implements AutoCloseable{
    private FileWriter writer;

    public AuditLogger(String filename){
        try {
            writer = new FileWriter("audit.log", true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void log(String message){
        try {
            writer.write(message);
            writer.write("\n");
        } catch (IOException e) {
            throw new RuntimeException("Unable to write audit log",e);
        }
    }

    @Override
    public void close(){
        try {
            writer.close();
        } catch (IOException e){
            throw new RuntimeException(e);
        }

    }
}
