package com.kreitek.PFBKreitekfy.Application.Service.AlbumServiceImpl;

import com.kreitek.PFBKreitekfy.Application.Dto.AlbumDTO;
import com.kreitek.PFBKreitekfy.Application.Mapper.AlbumMapper;
import com.kreitek.PFBKreitekfy.Application.Mapper.AlbumMapperImpl;
import com.kreitek.PFBKreitekfy.Application.Service.Impl.AlbumServiceImpl;
import com.kreitek.PFBKreitekfy.Domain.Entity.Album;
import com.kreitek.PFBKreitekfy.Domain.Persistence.AlbumPersistence;
import com.kreitek.PFBKreitekfy.Infrasctructure.Repository.AlbumRepository;
import com.kreitek.PFBKreitekfy.Infrasctructure.Repository.Impl.AlbumPersistenceImpl;
import com.kreitek.PFBKreitekfy.PfbKreitekfyApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {PfbKreitekfyApplication.class })
public class AlbumServiceImplGetAlbumsByCriteriaStringTest {
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

    @Test
    void shouldReturnEmptyDTOListWhenRepositoryHasNoalbums() {
        Pageable pageable = PageRequest.of(0, 5);

        AlbumPersistence mockedAlbumPersistence = mock(AlbumPersistence.class);
        when(mockedAlbumPersistence.findAll(pageable, "")).thenReturn(new PageImpl<>(new ArrayList<>(), pageable, 0));
        AlbumServiceImpl albumService = new AlbumServiceImpl(mockedAlbumPersistence, new AlbumMapperImpl());

        Page<AlbumDTO> albumDTOS = albumService.getAlbumsByCriteriaString(pageable, "");

        assertNotNull(albumDTOS);
        assertNotNull(albumDTOS.getContent());
        assertEquals(0, albumDTOS.getTotalElements());
        assertEquals(0, albumDTOS.getContent().size());
    }

    @Test
    void shouldReturnTwoDTOListWhenRepositoryHasTwoalbums() {
        Pageable pageable = PageRequest.of(0, 5);

        List<Album> albums = new ArrayList<>();
        albums.add(new Album());
        albums.add(new Album());

        AlbumPersistence mockedAlbumPersistence = mock(AlbumPersistence.class);
        when(mockedAlbumPersistence.findAll(pageable, "")).thenReturn(new PageImpl<>(albums, pageable, 2));
        AlbumServiceImpl albumService = new AlbumServiceImpl(mockedAlbumPersistence, new AlbumMapperImpl());

        Page<AlbumDTO> albumDTOS = albumService.getAlbumsByCriteriaString(pageable, "");

        assertNotNull(albumDTOS);
        assertNotNull(albumDTOS.getContent());
        assertEquals(2, albumDTOS.getTotalElements());
        assertEquals(2, albumDTOS.getContent().size());
    }
}
