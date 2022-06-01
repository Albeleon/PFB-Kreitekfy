package com.kreitek.PFBKreitekfy.IntegrationTests.Application.Service.AlbumServiceImpl;

import com.kreitek.PFBKreitekfy.Application.Dto.AlbumDTO;
import com.kreitek.PFBKreitekfy.Application.Mapper.AlbumMapper;
import com.kreitek.PFBKreitekfy.Application.Service.Impl.AlbumServiceImpl;
import com.kreitek.PFBKreitekfy.Infrasctructure.Repository.AlbumRepository;
import com.kreitek.PFBKreitekfy.Infrasctructure.Repository.Impl.AlbumPersistenceImpl;
import com.kreitek.PFBKreitekfy.PfbKreitekfyApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {PfbKreitekfyApplication.class })
public class AlbumServiceImplDeleteAlbumByIdIntegrationTest {
    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private AlbumMapper albumMapper;

    @Sql({"/test_data.sql"})
    @Sql(scripts = "/test_clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    void ShouldDeleteAlbumWhenDeleteItem() {
        AlbumServiceImpl albumService = new AlbumServiceImpl(new AlbumPersistenceImpl(albumRepository), albumMapper);

        albumService.deleteAlbumById(99991L);
        Optional<AlbumDTO> albumDTODeleted = albumService.getAlbumById(99991L);

        assertNotNull(albumDTODeleted);
        assertFalse(albumDTODeleted.isPresent());
    }
}
