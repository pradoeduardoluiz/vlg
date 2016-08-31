package br.com.vlg.sistemaweb.model.entity;

import br.com.vlg.sistemaweb.dao.FuncionarioDao;

public class FuncionarioBO {

	private FuncionarioDao dao;

	public FuncionarioBO() {

		dao = new FuncionarioDao();

	}

	public void salvar(Funcionario funcionario) {

		dao.salvar(funcionario);

	}

	public Usuario buscarPorId() {
		return null;

	}

}
