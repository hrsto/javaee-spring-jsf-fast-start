package com.webarity.fileUpload;

public class UploadedFile {

    private String fileName;
    private byte[] data;

    public UploadedFile(String fileName, byte[] data) {
        this.fileName = fileName;
        this.data = data;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @return the data
     */
    public byte[] getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(byte[] data) {
        this.data = data;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}