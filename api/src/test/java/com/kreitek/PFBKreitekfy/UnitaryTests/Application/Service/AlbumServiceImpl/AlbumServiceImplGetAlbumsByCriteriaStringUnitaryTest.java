package com.kreitek.PFBKreitekfy.UnitaryTests.Application.Service.AlbumServiceImpl;

import com.kreitek.PFBKreitekfy.Application.Dto.AlbumDTO;
import com.kreitek.PFBKreitekfy.Application.Mapper.AlbumMapperImpl;
import com.kreitek.PFBKreitekfy.Application.Service.Impl.AlbumServiceImpl;
import com.kreitek.PFBKreitekfy.Domain.Entity.Album;
import com.kreitek.PFBKreitekfy.Domain.Persistence.AlbumPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AlbumServiceImplGetAlbumsByCriteriaStringUnitaryTest {

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
