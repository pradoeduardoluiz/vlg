package br.com.vlg.sistemaweb.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;

import br.com.vlg.sistemaweb.util.EmailUtil;

@ManagedBean
public class ContactMB {

	private String fullName;
	private String phone;
	private String mail;
	private String message;

	public String enviar() {

		try {
			EmailUtil.sendMail(mail, "Contact", message);

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "E-mail",
							"Enviado com sucesso!!"));
			fullName = "";
			phone = "";
			mail = "";
			message = "";

		} catch (MessagingException e) {
			// TODO Auto-generated catch block

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "E-mail",
							"Houve um problema no envio!!"));

			e.printStackTrace();
		}

		return "";

	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
