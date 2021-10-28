package br.gov.sp.fatec.frases;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashSet;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.frases.entity.Autorizacao;
import br.gov.sp.fatec.frases.entity.Frase;
import br.gov.sp.fatec.frases.entity.Usuario;
import br.gov.sp.fatec.frases.repository.AutorizacaoRepository;
import br.gov.sp.fatec.frases.repository.FraseRepository;
import br.gov.sp.fatec.frases.repository.UsuarioRepository;
import br.gov.sp.fatec.frases.service.SegurancaService;

@SpringBootTest
@Transactional
@Rollback
class FrasesApplicationTests {

	@Autowired
	private UsuarioRepository usuarioRepo;

	@Autowired
	private AutorizacaoRepository autorizacaoRepo;

	@Autowired
	private FraseRepository fraseRepo;

	@Autowired
	private SegurancaService segurancaService;

	@Test
	void contextLoads() {
	}

	@Test
	void findByNomeTest() {
		Usuario usuario = new Usuario();
		usuario.setNome("Teste");
		usuario.setEmail("teste@email.com");
		usuario.setSenha("123");
		usuarioRepo.save(usuario);

		assertNotNull(usuarioRepo.findByNome("Teste"));
	}

	@Test
	void findByNomeContainsOrEmailContainsTest() {
		Usuario usuario = new Usuario();
		usuario.setNome("Teste");
		usuario.setEmail("teste@email.com");
		usuario.setSenha("123");
		usuarioRepo.save(usuario);

		assertFalse(usuarioRepo.findByNomeContainsOrEmailContains("est", "lalala").isEmpty());
	}

	@Test
	void findByAutorizacoesNomeTest() {
		Autorizacao autorizacao = new Autorizacao();
		autorizacao.setNome("Teste");
		autorizacaoRepo.save(autorizacao);

		Usuario usuario = new Usuario();
		usuario.setNome("Teste");
		usuario.setEmail("teste@email.com");
		usuario.setSenha("123");
		usuario.setAutorizacoes(new HashSet<Autorizacao>());
		usuario.getAutorizacoes().add(autorizacao);
		usuarioRepo.save(usuario);

		assertFalse(usuarioRepo.findByAutorizacoesNome("Teste").isEmpty());
	}


	@Test
	void findByUsuariosNomeTest() {
		Autorizacao autorizacao = new Autorizacao();
		autorizacao.setNome("Teste");
		autorizacaoRepo.save(autorizacao);

		Usuario usuario = new Usuario();
		usuario.setNome("Teste");
		usuario.setEmail("teste@email.com");
		usuario.setSenha("123");
		usuario.setAutorizacoes(new HashSet<Autorizacao>());
		usuario.getAutorizacoes().add(autorizacao);
		usuarioRepo.save(usuario);

		assertFalse(autorizacaoRepo.findByUsuariosNome("Teste").isEmpty());
	}

	@Test
	void findByAutorNomeTest() {
		Usuario autor = new Usuario();
		autor.setNome("Teste");
		autor.setEmail("teste@email.com");
		autor.setSenha("123");
		usuarioRepo.save(autor);
		
		Frase frase = new Frase();
		frase.setTitulo("Teste");
		frase.setConteudo("Teste");
		frase.setDataHora(new Date());
		frase.setAutor(autor);
		fraseRepo.save(frase);

		assertFalse(fraseRepo.findByAutorNome("Teste").isEmpty());

	}

	@Test
	void findByFrasesTituloTest() {
		Usuario autor = new Usuario();
		autor.setNome("Teste");
		autor.setEmail("teste@email.com");
		autor.setSenha("123");
		usuarioRepo.save(autor);
		
		Frase frase = new Frase();
		frase.setTitulo("Teste");
		frase.setConteudo("Teste");
		frase.setDataHora(new Date());
		frase.setAutor(autor);
		fraseRepo.save(frase);

		assertFalse(usuarioRepo.findByFrasesTitulo("Teste").isEmpty());

	}

	@Test
	void novoUsuarioTest() {
		segurancaService.novoUsuario("Teste", "teste@emailcom", "123", "ROLE_TESTE");

		assertNotNull(usuarioRepo.findByNome("Teste"));
	}

}
