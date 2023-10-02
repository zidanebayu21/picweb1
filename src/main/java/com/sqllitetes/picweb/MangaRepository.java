package com.sqllitetes.picweb;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MangaRepository extends JpaRepository<MangaEntity,Long> {

    @Query("SELECT m.nama,m.serial,m.halaman FROM MangaEntity m")
    List<Object[]> mangaList();

    @Query("SELECT m.picture FROM MangaEntity m WHERE m.id = 19")
    byte[] mangapictList();

    @Query("""
            SELECT 
                m.nama,
                m.serial,
                MAX(m.halaman) max_halaman,
                MIN(m.id) min_id
            FROM MangaEntity m
            GROUP BY m.nama
            ORDER BY m.id
            """)
    List<Object[]> mangaListgroup();
}
