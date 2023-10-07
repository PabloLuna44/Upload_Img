package com.uploads.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uploads.entity.Usuario;

public interface IUsuarioDAO extends JpaRepository<Usuario, Long>{

}
