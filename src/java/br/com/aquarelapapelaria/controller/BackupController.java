/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aquarelapapelaria.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Luis Henrique
 */
@ManagedBean
@RequestScoped
public class BackupController
{

    /**
     * Creates a new instance of BackupController
     */
    public BackupController()
    {
    }

    public String realizaBackup()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String data = sdf.format(new Date(System.currentTimeMillis()));

        String novonome = null;
        int numerodobackup = 0;
        File diretorio = new File("C:/Backup");
        File bck = new File("C:/Backup/Backup Aquarela " + data + ".sql");

        if (!diretorio.isDirectory())
        {
            new File("C:/Backup").mkdir();
        } else
        {
        }

        // Cria Arquivo de Backup  
        try
        {
            if (!bck.isFile())
            {

                String comando = "C:/Program Files/MySQL/MySQL Server 5.6/bin/mysqldump.exe";
                ProcessBuilder pb = new ProcessBuilder(comando, "--user=root", "--password=", "aquarelapapelaria", "--result-file=C:/Backup/Backup Aquarela " + data + ".sql");
                pb.start();
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!!", "Cópia de segurança < Backup Aquarela " + data + " > realizada com sucesso!"));
                return "backuptrue";

            } else
            {
                while (bck.isFile())
                {
                    numerodobackup++;

                    bck = new File("C:/Backup/Backup Aquarela " + data + "_" + numerodobackup + ".sql");
                    novonome = String.valueOf(bck);

                }

                String comando = "C:/Program Files/MySQL/MySQL Server 5.6/bin/mysqldump.exe";
                ProcessBuilder pb = new ProcessBuilder(comando, "--user=root", "--password=", "aquarelapapelaria", "--result-file=" + novonome);
                pb.start();
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!!", "Cópia de segurança Backup Aquarela " + data + " " + numerodobackup + " realizada com sucesso!"));
                return "backuptrue";

            }
        } catch (IOException ex)
        {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha!!", "Não foi possível realizar o backup!" + ex.getMessage()));
            return "backupfalse";
        }

    }

}
