package br.com.vlg.sistemaweb.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.com.vlg.sistemaweb.model.entity.Funcionario;
import br.com.vlg.sistemaweb.model.entity.FuncionarioBO;
import br.com.vlg.sistemaweb.model.entity.Usuario;

@ManagedBean
public class FuncionarioMB {

	private Funcionario funcionario;
	private FuncionarioBO funcionarioBO;
	private List<Funcionario> funcionarios;

	@PostConstruct
	public void iniciar() {
		funcionario = new Funcionario();
		funcionarioBO = new FuncionarioBO();
	}

	public void salvar() {

		funcionarioBO.salvar(funcionario);

	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

}
