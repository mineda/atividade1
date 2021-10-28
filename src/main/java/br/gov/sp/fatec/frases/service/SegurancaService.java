package br.gov.sp.fatec.frases.service;

import java.util.List;

import br.gov.sp.fatec.frases.entity.Usuario;

public interface SegurancaService {

    public Usuario novoUsuario(String nome, String email, String senha, String autorizacao);

    public List<Usuario> buscarTodosUsuarios();
    
}
