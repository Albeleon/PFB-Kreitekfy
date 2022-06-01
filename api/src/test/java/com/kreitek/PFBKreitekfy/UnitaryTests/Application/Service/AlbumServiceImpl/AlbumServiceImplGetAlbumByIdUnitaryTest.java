package com.kreitek.PFBKreitekfy.UnitaryTests.Application.Service.AlbumServiceImpl;

import com.kreitek.PFBKreitekfy.Application.Dto.AlbumDTO;
import com.kreitek.PFBKreitekfy.Application.Mapper.AlbumMapperImpl;
import com.kreitek.PFBKreitekfy.Application.Service.Impl.AlbumServiceImpl;
import com.kreitek.PFBKreitekfy.Domain.Entity.Album;
import com.kreitek.PFBKreitekfy.Domain.Persistence.AlbumPersistence;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AlbumServiceImplGetAlbumByIdUnitaryTest {

    @Test
    void shouldReturnEmptyDTOalbumWhenRepositoryHasNotThatalbum() {
        AlbumPersistence mockedAlbumPersistence = mock(AlbumPersistence.class);
        when(mockedAlbumPersistence.getAlbumById(1L)).thenReturn(Optional.empty());
        AlbumServiceImpl albumService = new AlbumServiceImpl(mockedAlbumPersistence, new AlbumMapperImpl());

        Optional<AlbumDTO> albumDTO = albumService.getAlbumById(1L);

        assertNotNull(albumDTO);
        assertFalse(albumDTO.isPresent());
    }

    @Test
    void shouldReturnDTOAlbumWhenRepositoryHasThatAlbum() {
        AlbumPersistence mockedAlbumPersistence = mock(AlbumPersistence.class);
        when(mockedAlbumPersistence.getAlbumById(1L)).thenReturn(Optional.of(new Album()));
        AlbumServiceImpl albumService = new AlbumServiceImpl(mockedAlbumPersistence, new AlbumMapperImpl());

        Optional<AlbumDTO> albumDTO = albumService.getAlbumById(1L);

        assertNotNull(albumDTO);
        assertTrue(albumDTO.isPresent());
    }
}
