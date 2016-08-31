package br.com.vlg.sistemaweb.dao;

import br.com.vlg.sistemaweb.model.entity.Funcionario;

public class FuncionarioDao extends Dao {

	public void salvar(Funcionario funcionario) {

		getEntityManager().merge(funcionario);

	}

}
