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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

@SpringBootTest(classes = {PfbKreitekfyApplication.class })
public class AlbumServiceImplGetAlbumsByCriteriaStringIntegrationTest {
    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private AlbumMapper albumMapper;

    @Sql({"/test_data.sql"})
    @Sql(scripts = "/test_clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    void ShouldReturnAlbumWhenFindAllAlbumes() {
        AlbumServiceImpl albumService = new AlbumServiceImpl(new AlbumPersistenceImpl(albumRepository), albumMapper);
        Pageable pageable = PageRequest.of(0, 5);
        Page<AlbumDTO> albumDTO = albumService.getAlbumsByCriteriaString(pageable, "");

        assertNotNull(albumDTO);
        assertEquals(1, albumDTO.getContent().size());
        assertEquals(99991, albumDTO.getContent().get(0).getId());
        assertEquals("_-_album_-_", albumDTO.getContent().get(0).getNombre());
    }
}
