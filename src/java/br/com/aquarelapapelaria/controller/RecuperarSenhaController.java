/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aquarelapapelaria.controller;

import br.com.aquarelapapelaria.dao.RecuperarSenhaDAO;
import br.com.aquarelapapelaria.model.Usuario;
import br.com.aquarelapapelaria.util.Email;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Luis Henrique
 */
@ManagedBean
@SessionScoped
public class RecuperarSenhaController
{

    private Usuario usuario;

    public RecuperarSenhaController()
    {
        usuario = new Usuario();
    }

    public Usuario getUsuario()
    {
        return usuario;
    }

    public String recuperaSenha()
    {
        RecuperarSenhaDAO dao = new RecuperarSenhaDAO();
        Email email = new Email();
        
        boolean resultado = dao.verificarEmailExistente(usuario.getEmail());
        if (resultado)
        {
            email.enviarEmailRec(usuario.getEmail());

            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!!", "Foi enviado um e-mail para < " + usuario.getEmail() + " > contendo seus dados cadastrais, verifique sua caixa de entrada!"));
            return "recuperatrue";
        } else
        {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "ERRO!!", "E-mail não cadastrado no sistema. Por favor tente novamente!"));
            return "recuperafalse";
        }
    }

}
