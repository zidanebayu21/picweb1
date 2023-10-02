package com.sqllitetes.picweb;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import java.util.List;

public interface MangaService {
    void saveDto(MangaDTOInput dtofake, Blob blob);

    void saveDto(MangaDTOInput dtofake, byte[] bytenya);

    List<MangaEntity> findAll();

    MultipartFile selectonepict(int i);

    List<MangaEntity> findAll2();
}
