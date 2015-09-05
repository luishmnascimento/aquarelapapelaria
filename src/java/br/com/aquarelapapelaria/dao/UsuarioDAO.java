package br.com.aquarelapapelaria.dao;

import br.com.aquarelapapelaria.model.Usuario;
import br.com.aquarelapapelaria.util.Dados;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luis Henrique
 */
public class UsuarioDAO
{

    private Dados bd;

    public UsuarioDAO()
    {
        bd = new Dados();
    }

    public boolean inserirUsuario(Usuario usuario)
    {
        bd.conectar();
        try
        {
            String sql = "INSERT INTO usuarios(user_usuario, user_email, user_senha) VALUES(?,?,?)";
            PreparedStatement ps;
            ps = bd.conn.prepareStatement(sql);
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getMd5(usuario.getSenha()));
            ps.execute();

            return true;
        } catch (SQLException ex)
        {
            ex.getMessage();
            return false;
        } finally
        {
            bd.desconectar();
        }
    }

    public boolean login(String usuario, String senha, boolean ativo)
    {
        bd.conectar();
        try
        {
            String sql = "SELECT USER_USUARIO, USER_SENHA, ATIVO FROM USUARIOS WHERE USER_USUARIO = ? AND USER_SENHA = ? AND ATIVO = 1";
            PreparedStatement ps = bd.conn.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, senha);
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                return true;
            }

        } catch (SQLException ex)
        {
            ex.getMessage();
        } finally
        {
            bd.desconectar();
        }
        return false;
    }

    
    
}
