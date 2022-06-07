package cz.muni.fi.pb162.hw03.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author Matus Jakab
 */
public class CsvWriter {
    private FileWriter myWriter;
    private String fileName;

    /**
     *
     * @param fileName of a file to create
     * @param charset used in a file
     * @throws IOException if something went wrong
     */
    public CsvWriter(String fileName, Charset charset) throws IOException {
        this.fileName = fileName;
        this.myWriter = new FileWriter(fileName, charset);
    }

    /**
     *
     * @param line to write
     * @throws IOException if something went wrong
     */
    public void write(String line) throws IOException{
        myWriter.write(line);
    }

    /**
     * closes file, deletes it if it's empty
     * @param written true if the file is not empty, otherwise false
     * @throws IOException if something went wrong
     */
    public void close(boolean written) throws IOException{
        myWriter.close();
        if (!written){
            Files.delete(Path.of(fileName));
        }
    }
}
