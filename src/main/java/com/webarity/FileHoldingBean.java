package com.webarity;

import java.util.List;
import java.util.Map;

import com.webarity.fileUpload.UploadedFile;

public class FileHoldingBean {

    private List<UploadedFile> files;

    public List<UploadedFile> getFiles() {
        return files;
    }
    public void setFiles(List<UploadedFile> files) {
        this.files = files;
    }

    public void submit() {
        System.out.println("here be files");
        files.forEach(file -> System.out.println(String.format("Received: %s, size is: %d", file.getFileName(), file.getData().length)));
    }
}