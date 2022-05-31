package com.kreitek.PFBKreitekfy.Application.Service;

import com.kreitek.PFBKreitekfy.Application.Dto.AlbumDTO;
import com.kreitek.PFBKreitekfy.Application.Dto.AlbumDTO;
import com.kreitek.PFBKreitekfy.Application.Mapper.AlbumMapperImpl;
import com.kreitek.PFBKreitekfy.Application.Mapper.AlbumMapper;
import com.kreitek.PFBKreitekfy.Application.Service.Impl.AlbumServiceImpl;
import com.kreitek.PFBKreitekfy.Application.Service.Impl.AlbumServiceImpl;
import com.kreitek.PFBKreitekfy.Domain.Entity.Album;
import com.kreitek.PFBKreitekfy.Domain.Entity.Album;
import com.kreitek.PFBKreitekfy.Domain.Persistence.AlbumPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AlbumServiceImplTest {
    AlbumPersistence mockedAlbumPersistence;

    @BeforeEach
    void initialize() {
        mockedAlbumPersistence = mock(AlbumPersistence.class);
    }

    @Test
    void shouldReturnEmptyDTOListWhenRepositoryHasNoalbums() {
        Pageable pageable = PageRequest.of(0, 5);

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

        when(mockedAlbumPersistence.findAll(pageable, "")).thenReturn(new PageImpl<>(albums, pageable, 2));
        AlbumServiceImpl albumService = new AlbumServiceImpl(mockedAlbumPersistence, new AlbumMapperImpl());

        Page<AlbumDTO> albumDTOS = albumService.getAlbumsByCriteriaString(pageable, "");

        assertNotNull(albumDTOS);
        assertNotNull(albumDTOS.getContent());
        assertEquals(2, albumDTOS.getTotalElements());
        assertEquals(2, albumDTOS.getContent().size());
    }

    @Test
    void shouldReturnEmptyDTOalbumWhenRepositoryHasNotThatalbum() {
        when(mockedAlbumPersistence.getAlbumById(1L)).thenReturn(Optional.empty());
        AlbumServiceImpl albumService = new AlbumServiceImpl(mockedAlbumPersistence, new AlbumMapperImpl());

        Optional<AlbumDTO> albumDTO = albumService.getAlbumById(1L);

        assertNotNull(albumDTO);
        assertFalse(albumDTO.isPresent());
    }

    @Test
    void shouldReturnDTOalbumWhenRepositoryHasThatalbum() {
        when(mockedAlbumPersistence.getAlbumById(1L)).thenReturn(Optional.of(new Album()));
        AlbumServiceImpl albumService = new AlbumServiceImpl(mockedAlbumPersistence, new AlbumMapperImpl());

        Optional<AlbumDTO> albumDTO = albumService.getAlbumById(1L);

        assertNotNull(albumDTO);
        assertTrue(albumDTO.isPresent());
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

        when(mockedAlbumPersistence.saveItem(album)).thenReturn(albumUpdated);

        AlbumServiceImpl albumService = new AlbumServiceImpl(mockedAlbumPersistence, mockedAlbumMapper);

        AlbumDTO albumUpdatedDTO = albumService.saveItem(albumDTO);

        assertNotNull(albumUpdatedDTO);
    }
}