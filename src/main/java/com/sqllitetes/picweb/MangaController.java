package com.sqllitetes.picweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.sql.DataSource;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/manga")
public class MangaController {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private MangaService mangaService;
    @GetMapping("/register")
    public String registerForm(Model model){
        MangaDTOInput dto = new MangaDTOInput();
        model.addAttribute("dto",dto);
        var dtomulti = new MangaDTOMultiple();
        model.addAttribute("dtomulti",dtomulti);
        return "manga/register";
    }
    //BufferedImage a = ImageIO.read(new ByteArrayInputStream(m.getBytes()));
    @PostMapping("/save")
    public String saveForm(@ModelAttribute(value = "dto") MangaDTOInput dto) throws IOException {
        var dtofake = new MangaDTOInput();
        dtofake.setNama("ada_pagge1");
        dtofake.setHalaman(1);
        dtofake.setSerial(dto.getSerial());
        dtofake.setPicture(dto.getPicture());

        MultipartFile m = dto.getPicture();

//            try (Connection connection = dataSource.getConnection()) {
//                String sql = "INSERT INTO manga (picture) VALUES (?)";
//                try (PreparedStatement statement = connection.prepareStatement(sql)) {
//                    statement.setBytes(1, m.getBytes());
//                    statement.executeUpdate();
//                }
//            }

        Blob pictureBlob = null;

//        try (Connection connection = dataSource.getConnection()) {
//            pictureBlob = connection.createBlob();
//            pictureBlob.setBytes(1, m.getBytes());
//        } catch (SQLException | IOException e) {
//            throw new RuntimeException("Failed to convert MultipartFile to Blob", e);
//        }

        mangaService.saveDto(dto,m.getBytes());
        return "redirect:/manga/register";
    }

    @GetMapping("/home")
    public String home(Model model){
        List<MangaEntity> dto = new ArrayList<>();
        dto = mangaService.findAll2();
        model.addAttribute("dto",dto);

//        MultipartFile pictdumb = mangaService.selectonepict(15);
//        model.addAttribute("pictdumb",pictdumb);


        model.addAttribute("imageUrl", "gambar/15");
        return "manga/home";
    }


    @GetMapping("/gambar/{id}")
    public ResponseEntity<byte[]> tampilkanGambar(@PathVariable("id") int id) throws IOException {
        byte[] gambarnya = mangaService.selectonepict(id).getBytes();

        // Tentukan tipe konten gambar yang sesuai
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // Ganti dengan tipe konten yang sesuai

        return new ResponseEntity<>(gambarnya, headers, HttpStatus.OK);
    }


    @PostMapping("/multiple")
    public String handleMultipleMangaUpload(@ModelAttribute(value = "dtomulti") MangaDTOMultiple dto) throws IOException {
        List<MangaDTOInput> manga = new ArrayList<>();
        var data = dto.getFilenya();
        int i=1;
        for (var item : data) {
            var isi = new MangaDTOInput();
            isi.setNama(dto.getNama());
            isi.setHalaman(i);
            isi.setSerial(item.getOriginalFilename());
            isi.setPicture(item);
            manga.add(isi);
            i++;
        }
        var a = i;
        for (var item : manga) {
            mangaService.saveDto(item,item.getPicture().getBytes());
        }
        var c = a;
        return "redirect:/manga/register";
    }

    @GetMapping("/tampilan/{id}/{halaman}")
    public String tampilan(@PathVariable("id") int id, @PathVariable("halaman") int halaman, Model model) {
        // Lakukan operasi yang diperlukan dengan nilai id dan halaman
        // ...
        int halawal = id;
        int halakhir = id + halaman-1;
        model.addAttribute("halawal", halawal);
        model.addAttribute("halakhir", halakhir);
        return "manga/tampilan";
    }
}
