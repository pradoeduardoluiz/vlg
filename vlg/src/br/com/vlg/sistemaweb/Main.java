package br.com.vlg.sistemaweb;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.vlg.sistemaweb.model.entity.Endereco;
import br.com.vlg.sistemaweb.model.entity.Pessoa;

public class Main {

	final static Integer IDPESSOA = 1;

	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("vlg-pu");

		inserirPessoa(entityManagerFactory);
		alterarPessoa(entityManagerFactory);

		ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();

		pessoas = listarPessoas(entityManagerFactory);

		for (Pessoa pessoa : pessoas) {

			System.out.println("Código: " + pessoa.getId());
			System.out.println("Nome: " + pessoa.getNome() + "\n");

		}

		entityManagerFactory.close();

		// excluirPessoa(entityManagerFactory);

	}

	private static EntityManager criaEntityMananger(
			EntityManagerFactory entityManagerFactory) {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		return entityManager;
	}

	private static Pessoa buscarPessoa(
			EntityManagerFactory entityManagerFactory, Integer IDPESSOA) {

		EntityManager entity = criaEntityMananger(entityManagerFactory);

		begin(entity);

		Pessoa pessoaBusca = new Pessoa();

		pessoaBusca = entity.getReference(Pessoa.class, IDPESSOA);

		close(entity);

		return pessoaBusca;

	}

	@SuppressWarnings("unchecked")
	private static ArrayList<Pessoa> listarPessoas(
			EntityManagerFactory entityManagerFactory) {

		EntityManager entity = criaEntityMananger(entityManagerFactory);

		Query query = entity.createQuery("FROM pessoa");

		ArrayList<Pessoa> pessoas = (ArrayList<Pessoa>) query.getResultList();

		entity.clear();

		return pessoas;

	}

	private static void alterarPessoa(EntityManagerFactory entityManagerFactory) {
		// TODO Auto-generated method stub

		Pessoa pessoa = buscarPessoa(entityManagerFactory, IDPESSOA);

		System.out.println("Pessoa encontrada: " + pessoa.getNome());

		EntityManager entity = criaEntityMananger(entityManagerFactory);

		begin(entity);

		pessoa.setNome("Eder Soares");

		entity.merge(pessoa);

		commit(entity);
		close(entity);

		Pessoa pessoaAlterada = buscarPessoa(entityManagerFactory, IDPESSOA);

		System.out.println("Pessoa Alterada: " + pessoaAlterada.getNome());

	}

	private static void excluirPessoa(EntityManagerFactory entityManagerFactory) {
		// TODO Auto-generated method stub

		Pessoa pessoaExcluida = buscarPessoa(entityManagerFactory, IDPESSOA);

		EntityManager entity = criaEntityMananger(entityManagerFactory);

		begin(entity);

		entity.remove(pessoaExcluida);

		System.out.println("Exclui Pessoa...");

		commit(entity);
		close(entity);

	}

	private static void inserirPessoa(EntityManagerFactory entityManagerFactory) {

		EntityManager entity = criaEntityMananger(entityManagerFactory);

		begin(entity);

		Pessoa pessoa = new Pessoa();
		Endereco endereco = new Endereco();

		pessoa.setNome("Luiz Eduardo do Prado");
		endereco.setRua("Rua Ernani Castros do Santos");
		pessoa.setEndereco(endereco);

		entity.merge(pessoa);

		commit(entity);
		close(entity);

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
