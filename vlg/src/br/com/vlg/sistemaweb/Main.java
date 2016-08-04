package br.com.vlg.sistemaweb;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.vlg.sistemaweb.model.entity.Endereco;
import br.com.vlg.sistemaweb.model.entity.Pessoa;

public class Main {

	private static Pessoa p;

	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("vlg-pu");

		EntityManager entity = entityManagerFactory.createEntityManager();


	}

	private static EntityManager criaEntityMananger(EntityManagerFactory entityManagerFactory) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		return entityManager;
	}

	private static void buscarPessoa(EntityManager entity) {

		Pessoa pessoaBusca = new Pessoa();

		pessoaBusca = entity.getReference(Pessoa.class, p.getId());

		System.out.println("Pessoa buscada: " + pessoaBusca.getNome());

	}

	private static void listarPessoas(EntityManager entity) {
		// TODO Auto-generated method stub

		Query query = entity.createQuery("FROM pessoa");

		List<Pessoa> pessoas = query.getResultList();

		for (Pessoa pessoa : pessoas) {

			System.out.println("Codigo: " + pessoa.getId() + " Nome: " + pessoa.getNome());

		}

	}

	private static void alterarPessoa(EntityManager entity) {
		// TODO Auto-generated method stub

		Pessoa pessoa = new Pessoa();

		pessoa = entity.getReference(Pessoa.class, p.getId());

		System.out.println("Pessoa encontrada: " + pessoa.getNome());

		pessoa.setNome("Eder Soares");

		entity.merge(pessoa);

		Pessoa pessoaAlterada = entity.getReference(Pessoa.class, p.getId());

		System.out.println("Pessoa Alterada: " + pessoaAlterada.getNome());

	}

	private static void excluirPessoa(EntityManager entity) {
		// TODO Auto-generated method stub

		Pessoa pessoaExcluida = new Pessoa();

		pessoaExcluida = entity.getReference(Pessoa.class, p.getId());

		entity.remove(pessoaExcluida);

		System.out.println("Exclui Pessoa...");

	}

	private static void inserirPessoa(EntityManager entity) {

		Pessoa pessoa = new Pessoa();
		Endereco endereco = new Endereco();

		pessoa.setNome("Luiz Eduardo do Prado");
		endereco.setRua("Rua Ernani Castros do Santos");
		pessoa.setEndereco(endereco);

		p = entity.merge(pessoa);

	}

	private static void close(EntityManager entity) {
		entity.close();
	}

	private static void commit(EntityManager entity) {
		entity.getTransaction().commit();
	}

	private static void begin(EntityManager entity) {
		entity.getTransaction().begin();
	}

}
