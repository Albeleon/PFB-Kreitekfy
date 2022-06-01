package com.kreitek.PFBKreitekfy.UnitaryTests.Application.Service.AlbumServiceImpl;

import com.kreitek.PFBKreitekfy.Application.Dto.AlbumDTO;
import com.kreitek.PFBKreitekfy.Application.Mapper.AlbumMapper;
import com.kreitek.PFBKreitekfy.Application.Service.Impl.AlbumServiceImpl;
import com.kreitek.PFBKreitekfy.Domain.Entity.Album;
import com.kreitek.PFBKreitekfy.Domain.Persistence.AlbumPersistence;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AlbumServiceImpltSaveItemUnitaryTest {

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
