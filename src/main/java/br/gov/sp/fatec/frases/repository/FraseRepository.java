package br.gov.sp.fatec.frases.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.frases.entity.Frase;

public interface FraseRepository extends JpaRepository<Frase, Long>{

    public List<Frase> findByAutorNome(String nome);
    
}
