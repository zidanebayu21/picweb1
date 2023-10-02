package com.sqllitetes.picweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class MangaServiceIpml implements MangaService {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private MangaRepository mangaRepository;

    @Override
    public void saveDto(MangaDTOInput dtofake, Blob file) {
        MangaEntity2 entity = new MangaEntity2();
        entity.setNama(dtofake.getNama());
        entity.setHalaman(dtofake.getHalaman());
        entity.setSerial(dtofake.getSerial());
        entity.setPicture(file);
        //mangaRepository.save(entity);
    }
    @Override
    public void saveDto(MangaDTOInput dtofake,byte[] file) {
        MangaEntity entity = new MangaEntity();
        entity.setNama(dtofake.getNama());
        entity.setHalaman(dtofake.getHalaman());
        entity.setSerial(dtofake.getSerial());
        entity.setPicture(file);
        mangaRepository.save(entity);
    }

    @Override
    public List<MangaEntity> findAll() {
        var query = mangaRepository.mangaList();
        //var testnya =(String) query.get(0)[0];

        var query2 = mangapict(15);
        var ok = query.get(0);
        List<MangaEntity> hasil = new ArrayList<>();
        int i=0;
        for (var item:query
             ) {
            var manganya = new MangaEntity();
            manganya.setNama((String) query.get(i)[0]);
            manganya.setSerial((String) query.get(i)[1]);
            manganya.setHalaman((Integer) query.get(i)[2]);

            hasil.add(manganya);
            i++;
        }
        var akhirnya = hasil;
        return akhirnya;
    }
    @Override
    public List<MangaEntity> findAll2() {
        var query = mangaRepository.mangaListgroup();

        List<MangaEntity> hasil = new ArrayList<>();
        int i=0;
        for (var item:query
        ) {
            var manganya = new MangaEntity();
            manganya.setNama((String) query.get(i)[0]);
            manganya.setSerial((String) query.get(i)[1]);
            manganya.setHalaman((Integer) query.get(i)[2]);
            manganya.setId((Integer) query.get(i)[3]);
            hasil.add(manganya);
            i++;
        }
        var akhirnya = hasil;
        return akhirnya;
    }


    @Override
    public MultipartFile selectonepict(int i) {
        byte[] gambarnya = mangapict(i);

        if (gambarnya != null) {
            MultipartFile multipartFile = new ByteArrayMultipartFile(gambarnya, "filename.jpg", "image/jpeg");
            return multipartFile;
        } else {
            return null;
        }
    }

    private byte[] mangapict (int mangaId){
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT picture FROM manga WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, mangaId); // Ganti mangaId dengan ID manga yang ingin Anda baca

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        byte[] blobData = resultSet.getBytes("picture");
                        return blobData;
                    } else {
                        System.out.println("Manga dengan ID tersebut tidak ditemukan.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
