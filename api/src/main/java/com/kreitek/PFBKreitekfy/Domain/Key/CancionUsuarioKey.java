package com.kreitek.PFBKreitekfy.Domain.Key;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CancionUsuarioKey implements Serializable {

    private static final long serialVersionUID = -7537839466101594569L;

    @Column(name = "cancion_id")
    private Long cancionId;

    @Column(name = "usuario_id")
    private Long usuarioId;


    public CancionUsuarioKey() {
    }

    public Long getCancionId() {
        return cancionId;
    }

    public void setCancionId(Long cancionId) {
        this.cancionId = cancionId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CancionUsuarioKey that = (CancionUsuarioKey) o;
        return cancionId.equals(that.cancionId) && usuarioId.equals(that.usuarioId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cancionId, usuarioId);
    }
}
