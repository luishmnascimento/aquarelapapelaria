package br.com.aquarelapapelaria.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luis Henrique
 */
public class Dados
{

    private final String driver = "com.mysql.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost/aquarelapapelaria";
    private final String user = "root";
    private final String pass = "";
    public Connection conn;

    public Connection conectar()
    {
        if (conn == null)
        {
            try
            {
                Class.forName(driver);
                conn = DriverManager.getConnection(url, user, pass);
            } catch (SQLException | ClassNotFoundException ex)
            {
                System.err.println(ex.getMessage());
                return null;
            }
        }
        return conn;
    }

    public void desconectar()
    {
        try
        {
            conn.close();
        } catch (SQLException ex)
        {
            Logger.getLogger(Dados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
