package com.kairosds.cursospb2.kafkaconsumer.pelicula;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pelicula implements Serializable {

    private String titulo;
    private String autor;
    private Double duracionMinutos;
}