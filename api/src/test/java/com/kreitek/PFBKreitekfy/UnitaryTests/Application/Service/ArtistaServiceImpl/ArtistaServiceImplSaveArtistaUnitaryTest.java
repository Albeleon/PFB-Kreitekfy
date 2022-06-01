package com.kreitek.PFBKreitekfy.UnitaryTests.Application.Service.ArtistaServiceImpl;

import com.kreitek.PFBKreitekfy.Application.Dto.ArtistaDTO;
import com.kreitek.PFBKreitekfy.Application.Mapper.ArtistaMapper;
import com.kreitek.PFBKreitekfy.Application.Service.Impl.ArtistaServiceImpl;
import com.kreitek.PFBKreitekfy.Domain.Entity.Artista;
import com.kreitek.PFBKreitekfy.Domain.Persistence.ArtistaPersistence;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ArtistaServiceImplSaveArtistaUnitaryTest {
    @Test
    void shouldReturnDTOArtistaWhenRepositorySavesArtista() {

        ArtistaDTO artistaDTO = new ArtistaDTO();
        ArtistaDTO artistaDTOUpdated = new ArtistaDTO();

        Artista artista = new Artista();
        Artista artistaUpdated = new Artista();

        ArtistaMapper mockedArtistaMapper = mock(ArtistaMapper.class);
        when(mockedArtistaMapper.toEntity(artistaDTO)).thenReturn(artista);
        when(mockedArtistaMapper.toDto(artistaUpdated)).thenReturn(artistaDTOUpdated);

        ArtistaPersistence mockedArtistaPersistence = mock(ArtistaPersistence.class);
        when(mockedArtistaPersistence.saveArtista(artista)).thenReturn(artistaUpdated);

        ArtistaServiceImpl artistaService = new ArtistaServiceImpl(mockedArtistaPersistence, mockedArtistaMapper);

        ArtistaDTO artistaUpdatedDTO = artistaService.saveArtista(artistaDTO);

        assertNotNull(artistaUpdatedDTO);
    }
}
