package com.kreitek.PFBKreitekfy.Domain.Entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "canciones")
public class Cancion {

    @Id
    private Long id;
}
