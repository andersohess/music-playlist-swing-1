/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbs.java.mps.controller;

import br.com.dbs.java.mps.model.util.JPAUtil;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author daniel
 */
public class RelatorioController {
    
    public void gerarRelatorio(String nomePlaylist) throws SQLException {
        
        try {
            JasperCompileManager.compileReportToFile("relatorio-playlists.jrxml");
            Map<String, Object> params = new HashMap<>();
            params.put("NOME_PLAYLIST", nomePlaylist);
            Connection conn = JPAUtil.getConnection();
            
            JasperPrint jasperPrint = JasperFillManager.fillReport("relatorio-playlists.jasper", params, conn);
                JasperViewer.viewReport(jasperPrint,false);
                    } catch (JRException ex) {
            Logger.getLogger(RelatorioController.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    
}
