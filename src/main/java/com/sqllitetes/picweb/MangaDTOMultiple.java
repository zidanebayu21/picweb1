package com.sqllitetes.picweb;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class MangaDTOMultiple {

    private String nama;
    private String serial;
    private List<MultipartFile> filenya;

    public MangaDTOMultiple() {
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public List<MultipartFile> getFilenya() {
        return filenya;
    }

    public void setFilenya(List<MultipartFile> filenya) {
        this.filenya = filenya;
    }
}
