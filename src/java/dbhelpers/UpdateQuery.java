/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbhelpers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Players;


public class UpdateQuery {
    
    private Connection conn;
    
    public UpdateQuery() {
     Properties props = new Properties();  //MWC
        InputStream instr = getClass().getResourceAsStream("dbConn.properties");
        try {
            props.load(instr);
        } catch (IOException ex) {
            Logger.getLogger(UpdateQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            instr.close();
        } catch (IOException ex) {
            Logger.getLogger(UpdateQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String driver = props.getProperty("driver.name");
        String url = props.getProperty("server.name");
        String username = props.getProperty("user.name");
        String passwd = props.getProperty("user.password");
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UpdateQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn = DriverManager.getConnection(url, username, passwd);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    public void doUpdate (Players player) throws SQLException{
        
        String query = "UPDATE players SET playerName = ?, jerseyNumber = ?, age = ?, superbowlWins = ? WHERE playerID = ?";
     
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            ps.setString(1, player.getPlayerName());
            ps.setInt(2, player.getJerseyNumber());
            ps.setInt(3, player.getAge());
            ps.setInt(4, player.getSuperbowlWins());
            ps.setInt(5, player.getPlayerID());
            
            ps.executeUpdate();
    }
    
    }
