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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@SpringBootTest(classes = {PfbKreitekfyApplication.class })
public class AlbumServiceImplGetAlbumByIdIntegrationTest {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private AlbumMapper albumMapper;

    @Sql({"/test_data.sql"})
    @Sql(scripts = "/test_clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    void ShouldReturnAlbumWhenFindOneAlbum() {
        AlbumServiceImpl albumService = new AlbumServiceImpl(new AlbumPersistenceImpl(albumRepository), albumMapper);
        Optional<AlbumDTO> albumDTO = albumService.getAlbumById(99991L);

        assertNotNull(albumDTO);
        assertTrue(albumDTO.isPresent());
        assertEquals(99991, albumDTO.get().getId());
        assertEquals("_-_album_-_", albumDTO.get().getNombre());
    }

    @Sql({"/test_data.sql"})
    @Sql(scripts = "/test_clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    void ShouldReturnNoAlbumWhenFindOneAlbumThatIsNot() {
        AlbumServiceImpl albumService = new AlbumServiceImpl(new AlbumPersistenceImpl(albumRepository), albumMapper);
        Optional<AlbumDTO> albumDTO = albumService.getAlbumById(99990L);

        assertNotNull(albumDTO);
        assertFalse(albumDTO.isPresent());
    }
}
