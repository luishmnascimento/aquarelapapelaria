package br.com.aquarelapapelaria.model;

import java.security.MessageDigest;

/**
 *
 * @author Luis Henrique
 */
public class Usuario
{

    private int idUsuario;
    private String usuario;
    private String email;
    private String senha;
    private boolean ativo;

    public Usuario()
    {
    }

    public Usuario(int idUsuario, String usuario, String email, String senha, boolean ativo)
    {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.email = email;
        this.senha = senha;
        this.ativo = ativo;
    }

    public int getIdUsuario()
    {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario)
    {
        this.idUsuario = idUsuario;
    }

    public String getUsuario()
    {
        return usuario;
    }

    public void setUsuario(String usuario)
    {
        this.usuario = usuario;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getSenha()
    {
        return senha;
    }

    public void setSenha(String senha)
    {
        this.senha = senha;
    }

    public boolean isAtivo()
    {
        return ativo;
    }

    public void setAtivo(boolean ativo)
    {
        this.ativo = ativo;
    }
    
    public String getMd5(String message)
    {
        String digest = null;
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(message.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder(2 * hash.length);
            for (byte b : hash)
            {
                sb.append(String.format("%02x", b & 0xff));
            }
            digest = sb.toString();
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return digest;
    }

}
