package com.sqllitetes.picweb;

import jakarta.persistence.*;

import java.util.Arrays;

@Entity
@Table(name = "Manga")
public class MangaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Nama")
    private String nama;

    @Column(name = "Picture", columnDefinition = "BLOB")
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] picture;

    @Column(name = "Serial")
    private String serial;

    @Column(name = "Halaman")
    private Integer halaman;

    // Constructor, getters, and setters

    public MangaEntity() {
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

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
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

    @Override
    public String toString() {
        return "MangaEntity{" +
                "id=" + id +
                ", nama='" + nama + '\'' +
                ", serial='" + serial + '\'' +
                ", halaman=" + halaman +
                '}';
    }
}