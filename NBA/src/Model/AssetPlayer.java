/**
 * 
 */
package Model;

import java.sql.*;

import Utilities.StringUtility;

/**
 * Esta classe terá os atributos e ações para manipulação da tabela AssetPlayer
 * @author Willian
 * 
 */
public class AssetPlayer {
	
	protected Integer idContract = null;
	protected String startC = null;
	protected String endC = null;
	protected String salary = null;
	protected Integer idPlayer = null;
	protected Integer idFranchise = null;
	
	public AssetPlayer(){
		
		DBManager.connect();
	}
	
	public AssetPlayer(Integer idContract, String startC, String endC, String salary, Integer idPlayer, Integer idFranchise){
		this.idContract = idContract;
		this.startC = StringUtility.safeString(startC);
		this.endC = StringUtility.safeString(endC);
		this.salary = StringUtility.safeString(salary);
		this.idPlayer = idPlayer;
		this.idFranchise = idFranchise;
		
		DBManager.connect();
	}
	
	public void insert() throws SQLException{
		try {
			DBManager.inserRecord("AssetPlayer", getColums(), toString());
			System.out.println ("DBManager: Insert Success");
		}
		catch (java.sql.SQLIntegrityConstraintViolationException e) {
		    System.out.println ("DBManager: Caught java.sql.SQLIntegrityConstraintViolationException") ;
		}  
		
	}
	
	public void remove(){
		
	}
	
	public void update(){
		
	}
	
	public void setAssetPlayer(){
		
	}
	
	//Queries
	
	public ResultSet getAll() throws SQLException{
		return DBManager.executeQuery("select * from AssetPlayer");
	}
	
	
	
	
	
	public String toString(){
		return  idContract.toString() +
				", TO_DATE('"+ startC + "','MM/DD/YYYY')"+
				", TO_DATE('"+ endC + "','MM/DD/YYYY'), " +
				salary +", " + 
				idPlayer.toString() +", " + 
				idFranchise.toString();
				
	}
	
	public String getColums(){
		return "idContract, startC, endC, salary, idPlayer, idFranchise";
	}
	
	
	

}
