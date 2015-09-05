/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aquarelapapelaria.controller;

import br.com.aquarelapapelaria.dao.UsuarioDAO;
import br.com.aquarelapapelaria.model.Usuario;
import br.com.aquarelapapelaria.util.AtivarCadastro;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Luis Henrique
 */
@ManagedBean
@RequestScoped
public class UsuarioController
{

    UsuarioDAO userDAO = new UsuarioDAO();
    private Usuario usuario;

    public UsuarioController()
    {
        usuario = new Usuario();
    }

    public Usuario getUsuario()
    {
        return usuario;
    }

    public String insereUsuario()
    {

        if (userDAO.inserirUsuario(usuario))
        {
            AtivarCadastro ativo = new AtivarCadastro();
            ativo.enviarEmailRec(getUsuario());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!!", "Usuario cadastrado com sucesso!\nUm e-mail foi enviado à < " + usuario.getEmail() + " > para ativação do cadastro. Verifique sua caixa de entrada!"));
            return "userregistersucess";
        } else
        {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO!!", "Falha ao cadastrar!"));
            return "userregisterfail";
        }
    }

    public String login()
    {
        boolean resultado = userDAO.login(usuario.getUsuario(), usuario.getMd5(usuario.getSenha()), usuario.isAtivo());
        
        if (resultado)
        {
            return "loginSucess";
        } else if (!usuario.isAtivo())
        {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!!", "Usuário cadastrado, porém não ativado. Verifique no seu email as instruções para ativar seu cadastro!"));
            return "loginWarn";
        } else
        {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha!!", "Usuario ou senha incorretos!"));
            return "loginFail";
        }

    }

}
