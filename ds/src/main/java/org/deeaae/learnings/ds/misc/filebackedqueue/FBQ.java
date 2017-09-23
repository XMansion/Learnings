package org.deeaae.learnings.ds.misc.filebackedqueue;

import java.io.*;
import java.nio.file.*;
import java.util.LinkedList;
import java.util.Queue;

public class FBQ<T> implements MyQueue<T> {

    // TODO: file operation to append to file
    // TODO: file operation to read data
    // TODO: Marker for the position upto read in file
    // TODO:
    final static long READ_BUFFER_SIZE=10;

    final static long WRITE_BUFFER_SIZE=10;

    long readPosInFile=-1;
    boolean isDataInFile = false;
    long readOffset = 0;
    long totalCount = 0;

    Queue<T> readBuffer = new LinkedList<>();
    Queue<T> writeBuffer = new LinkedList<>();

    public FBQ() {
        try {
            new FileOutputStream("fbq.dat", false).close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    T getData(){
        if(readBuffer.size()!=0) {
            readOffset++;
            return readBuffer.remove();
        }else if(isDataInFile == false) {
            // TODO: should apply lock in this operation
            return writeBuffer.remove();
        }else {
            readDataFromFile();
            return readBuffer.remove();
        }
    }

    void check(int n) {
        if(writeBuffer.size()>(WRITE_BUFFER_SIZE+n) || readBuffer.size()>(READ_BUFFER_SIZE + n)){
            throw new RuntimeException("Size limit exceeded");
        }
    }



    void readDataFromFile(){
        if(!readBuffer.isEmpty()==true){
            return;
        }
        Path path = Paths.get("fbq.dat");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            long count = 0;
            int countInThisBatch=0;
            while(countInThisBatch<READ_BUFFER_SIZE) {
                String line = reader.readLine();
                if(line==null){
                    isDataInFile=false;
                    break;
                }
                count++;
                if(count>totalCount) {
                    if (line.trim().isEmpty()) {
                        continue;
                    }
                    readBuffer.add((T) line);
                    totalCount++;
                    countInThisBatch++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    void writeDataToFile(){
        //Path path = Paths.get("/home/anunay/fbq.dat");
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("fbq.dat",true), "utf-8"))) {
            writeBuffer.stream().forEach(entry->{
                try {
                    isDataInFile=true;
                    writer.write(entry.toString());
                    writer.write("\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            writeBuffer.clear();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    void pushData(T data){
        writeBuffer.add(data);
        if(writeBuffer.size()>WRITE_BUFFER_SIZE) {
            writeDataToFile();
        }
    }

    @Override
    public T peek() {
        return null;
    }

    @Override
    public T get() {
        check(2);
        return getData();
    }

    @Override
    public void push(T data) {
        check(2);
        pushData(data);
    }
}
