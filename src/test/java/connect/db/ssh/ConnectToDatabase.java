package connect.db.ssh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Date;
import java.util.Properties;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import modules.ExcelData;

public class ConnectToDatabase {
	
	
	private static void doSshTunnel(String strSshUser, String strSshPassword, String strSshHost, int nSshPort,
            String strRemoteSHost, int nLocalPort, int nRemotePort) throws JSchException {
        final JSch jsch = new JSch();
        Session session = jsch.getSession(strSshUser, strSshHost, 22);
        session.setPassword(strSshPassword);

        final Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);

        session.connect();
        session.setPortForwardingL(nLocalPort, strRemoteSHost, nRemotePort);
    }

    public static void main(String[] args) {
        try {
        	ExcelData excel_data= new ExcelData();
            String strSshUser = "genia"; // SSH loging username
            String strSshPassword = "mooredna"; // SSH login password
            String strSshHost = "10.21.53.12"; // hostname or ip or SSH server
                                                         
            int nSshPort = 22; // remote SSH host port number
            String strRemoteHost = "10.21.53.12"; // hostname or
                                                              // ip of
                                                                    // your
                                                                    // database
                                                                    // server
            int nLocalPort = 3366; // local port number use to bind SSH tunnel
            int nRemotePort = 3306; // remote port number of your database
            String strDbUser = excel_data.getDbUsername(); // database loging username
            String encyptedPassword = excel_data.getDbPassword(); // database login password
            String strDbPassword;
    		byte[] decryptedPwd = Base64.getDecoder().decode(encyptedPassword);
    		strDbPassword = new String(decryptedPwd);
    		
            ConnectToDatabase.doSshTunnel(strSshUser, strSshPassword, strSshHost, nSshPort, strRemoteHost, nLocalPort,nRemotePort);

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:" + nLocalPort, strDbUser,strDbPassword);
            ConnectToDatabase.readDatabase(con);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }
    private static void readDatabase(Connection con) throws SQLException{
    	String query= "select * from cumulus.exp_run limit 2";
    	java.sql.Statement stmt =con.createStatement();
 		ResultSet rs= stmt.executeQuery(query);			
 		while (rs.next()){
    		int exp_run_id = rs.getInt(1);								        
            String run_name = rs.getString(2);	
            int analysis_count = rs.getInt(3);
            String run_group = rs.getString(4);		                               
            Date create_date = new Date(rs.getString(5));					                               
            Date last_modified_date = new Date(rs.getString(6));					                               
            Date permanent_delete_date = new Date(rs.getString(7));					                               
            Date expiration_date = new Date(rs.getString(8));					                               
            String status = rs.getString(9);
            float size = rs.getFloat(10);					                               
            String roche_id = rs.getString(11);					                               
            String last_action = rs.getString(12);	
            int application_id = rs.getInt(13);	
            
System.out.println(exp_run_id);
    }	

    }
}
