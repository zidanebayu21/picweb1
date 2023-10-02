package com.sqllitetes.picweb;

import jakarta.persistence.Column;
import org.springframework.web.multipart.MultipartFile;

public class MangaDTOInput {
    private Integer id;
    private String nama;
    private MultipartFile picture;
    private String serial;
    private Integer halaman;


    public MangaDTOInput() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public void setPicture(MultipartFile picture) {
        this.picture = picture;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Integer getHalaman() {
        return halaman;
    }

    public void setHalaman(Integer halaman) {
        this.halaman = halaman;
    }


}
