package com.kreitek.PFBKreitekfy.Application.Dto;

import java.io.Serializable;
import java.util.Objects;

public class CancionUsuarioDTO implements Serializable {

        private Long cancionId;
        private Long usuarioId;
        private Long reproducciones;
        private Long valoracion;


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

        public Long getReproducciones() {
                return reproducciones;
        }

        public void setReproducciones(Long reproducciones) {
                this.reproducciones = reproducciones;
        }

        public Long getValoracion() {
                return valoracion;
        }

        public void setValoracion(Long valoracion) {
                this.valoracion = valoracion;
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                CancionUsuarioDTO that = (CancionUsuarioDTO) o;
                return cancionId.equals(that.cancionId) && usuarioId.equals(that.usuarioId) && reproducciones.equals(that.reproducciones) && valoracion.equals(that.valoracion);
        }

        @Override
        public int hashCode() {
                return Objects.hash(cancionId, usuarioId, reproducciones, valoracion);
        }

        @Override
        public String toString() {
                return "CancionUsuarioDTO{" +
                        "cancionId=" + cancionId +
                        ", usuarioId=" + usuarioId +
                        ", reproducciones=" + reproducciones +
                        ", valoracion=" + valoracion +
                        '}';
        }
}
