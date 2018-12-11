package com.webarity;

import java.io.File;
import java.util.Map;

public class FileHoldingBean {

    private Map<String, File> files;

    public Map<String, File> getFiles() {
        return files;
    }
    public void setFiles(Map<String, File> files) {
        this.files = files;
    }

    public Object submit() {
        System.out.println("here be files");
        return null;
    }
}