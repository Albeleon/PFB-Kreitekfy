package com.kreitek.PFBKreitekfy.Application.Service.AlbumServiceImpl;

import com.kreitek.PFBKreitekfy.Application.Dto.AlbumDTO;
import com.kreitek.PFBKreitekfy.Application.Mapper.AlbumMapper;
import com.kreitek.PFBKreitekfy.Application.Service.Impl.AlbumServiceImpl;
import com.kreitek.PFBKreitekfy.Domain.Entity.Album;
import com.kreitek.PFBKreitekfy.Domain.Persistence.AlbumPersistence;
import com.kreitek.PFBKreitekfy.Infrasctructure.Repository.AlbumRepository;
import com.kreitek.PFBKreitekfy.Infrasctructure.Repository.Impl.AlbumPersistenceImpl;
import com.kreitek.PFBKreitekfy.PfbKreitekfyApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {PfbKreitekfyApplication.class })
public class AlbumServiceImpltSaveItemTest {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private AlbumMapper albumMapper;

    @Sql({"/test_data.sql"})
    @Sql(scripts = "/test_clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    void ShouldInsertAlbumWhenSaveItem() {
        AlbumServiceImpl albumService = new AlbumServiceImpl(new AlbumPersistenceImpl(albumRepository), albumMapper);

        AlbumDTO albumDTO = new AlbumDTO();
        albumDTO.setNombre("Nuevo album");
        AlbumDTO albumDTOInserted = albumService.saveItem(albumDTO);

        assertNotNull(albumDTOInserted);
        assertEquals("Nuevo album", albumDTOInserted.getNombre());
    }

    @Sql({"/test_data.sql"})
    @Sql(scripts = "/test_clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    void ShouldModifyAlbumWhenSaveItem() {
        AlbumServiceImpl albumService = new AlbumServiceImpl(new AlbumPersistenceImpl(albumRepository), albumMapper);

        AlbumDTO albumDTO = new AlbumDTO();
        albumDTO.setId(99991L);
        albumDTO.setNombre("Album modificado");
        AlbumDTO albumDTOModified = albumService.saveItem(albumDTO);

        assertNotNull(albumDTOModified);
        assertEquals("Album modificado", albumDTOModified.getNombre());
    }

    @Test
    void shouldReturnDTOAlbumWhenRepositorySavesAlbum() {

        AlbumDTO albumDTO = new AlbumDTO();
        AlbumDTO albumDTOUpdated = new AlbumDTO();

        Album album = new Album();
        Album albumUpdated = new Album();

        AlbumMapper mockedAlbumMapper = mock(AlbumMapper.class);
        when(mockedAlbumMapper.toEntity(albumDTO)).thenReturn(album);
        when(mockedAlbumMapper.toDto(albumUpdated)).thenReturn(albumDTOUpdated);

        AlbumPersistence mockedAlbumPersistence = mock(AlbumPersistence.class);
        when(mockedAlbumPersistence.saveItem(album)).thenReturn(albumUpdated);

        AlbumServiceImpl albumService = new AlbumServiceImpl(mockedAlbumPersistence, mockedAlbumMapper);

        AlbumDTO albumUpdatedDTO = albumService.saveItem(albumDTO);

        assertNotNull(albumUpdatedDTO);
    }
}
