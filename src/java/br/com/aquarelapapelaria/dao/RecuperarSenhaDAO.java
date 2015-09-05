package br.com.aquarelapapelaria.dao;

import br.com.aquarelapapelaria.util.Dados;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Luis Henrique
 */
public class RecuperarSenhaDAO
{
    private Dados bd;

    public RecuperarSenhaDAO()
    {
        bd = new Dados();
    }
    
    public boolean verificarEmailExistente(String email)
    {
        bd.conectar();
        try
        {
            String sql = "SELECT user_email FROM usuarios WHERE user_email = ?";
            PreparedStatement ps = bd.conn.prepareStatement(sql);
            ps.setString(1, email);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                return true;
            }
            
        } 
        catch (SQLException ex)
        {
            System.out.println("erro" + ex.getMessage());
        }
        finally
        {
            bd.desconectar();
        }
        
        return false;
    }
    
    public boolean senhaRecuperada(int id)
    {
        
        
        return false;
    }
    
}
