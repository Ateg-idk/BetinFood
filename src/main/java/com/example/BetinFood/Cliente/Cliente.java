package com.example.BetinFood.Cliente;

import java.sql.Date;


import com.example.BetinFood.Usuario.Usuario;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


import lombok.Data;

@Entity
@Data
@Table(name="cliente")
public class Cliente {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id_cliente;
    private String nombres;
    private String apellidos;
    private String correo;
    private String telefono;
     private String estado;
    private String genero;
    private int documentoIdentidad;
    private String direccion;

    @ManyToOne()
    @JoinColumn(name="id_usuario")
    private Usuario usuario;

}