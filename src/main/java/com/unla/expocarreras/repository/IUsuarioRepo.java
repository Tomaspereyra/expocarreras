package com.unla.expocarreras.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unla.expocarreras.model.Usuario;

public interface IUsuarioRepo extends JpaRepository<Usuario,Integer>  {

	
}
