import java.sql.*;

import Model.AssetPlayer;
import Model.DBManager;

 
/**
 * 
 * @author www.luv2code.com
 *
 */
public class Main {
 
    public static void main(String[] args) throws SQLException {
    	
    	//Conectando com banco
    	
 
        ResultSet myRs = null;
        
        try {
        	
        	DBManager.connect();
        	
            AssetPlayer aPlayer = new AssetPlayer(8,"05/07/2015","05/06/2015","240000",366,2);   
            
            aPlayer.insert();
//            myRs = aPlayer.getAll();
//             
//            // 4. Process the result set
//            while (myRs.next()) {
//                System.out.println(myRs.getString("idContract") + ", \t" 
//                                + myRs.getString("salary"));
//            }
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        finally {
            if (myRs != null) {
                myRs.close();
            }
             
            if (DBManager.isConnected()) {
            	DBManager.getConnection().close();
            }
        }
    }
 
}
