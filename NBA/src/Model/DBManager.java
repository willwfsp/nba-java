package Model;

/**
 * A Classe DBManager é a classe responsável por gerenciar as requisições no banco Oracle.
 * SELECTs, INSERTs, UPDATES serão feitos por esta classe.
 * 
 * Como pode ser visto esta classe usa o padrão Singleton. O motivo é que crie-se apenas uma instancia compartilhada
 * desta classe para que apenas uma conexão com com o banco seja aberta durante o uso da aplicação
 * 
 * Modo de Uso:
 * 
 * 	DBManager db = DBManager.getInstance();
 * 
 * 
 * @author Willian
 * 
 */


import java.sql.*;

public class DBManager {
	
	private static DBManager instance = null;
	private static Connection connection = null;
	private static Statement statement = null;
	
	
	/**
	 * Configurações iniciais para o gerenciamento do Banco
	 */
	public DBManager (){
		try {
			
			setConnection("oracle","grad.icmc.usp.br","15215","orcl", "w8066190", "w8066190");
			
			setStatement(connection.createStatement());
			
			System.out.println("DBManager: Oracle DB conectado\n");
		
		}catch(Exception exc){
			exc.printStackTrace();
		}
		
	}
	
	
	/**
	 * Inicializa a instancia compartilhada
	 * @return
	 */
	public static synchronized DBManager connect(){
		if (instance == null){
			instance = new DBManager();
		}
		
		return instance;
	}

	
	
	/**
	 * Altera as configurações padrões da conexão
	 * Os parâmetros são algumas das opções que são necessárias para a abertura de uma conexão.
	 * @throws SQLException 
	 */
	public static void setConnection(String sgbd, String host, String port, String sid, String username, String password) throws SQLException {
		String connectionOptions = "jdbc:"+sgbd+":thin:@"+host+":"+port+":"+sid;

		setConnection(DriverManager.getConnection(connectionOptions, username, password));
	}
	
	
	/**
	 * Executa uma consulta 
	 * @param sql 	String com a especificação SQL para a consulta.
	 * @throws SQLException 
	 */
	public static ResultSet executeQuery(String query) throws SQLException{

		return statement.executeQuery(query);
	}
	
	
	/**
	 * Realiza um INSERT simples 
	 * @param table 	A tabela a receber o insert.
	 * @param keys		As colunas ordenadas da tabela.
	 * @param values 	Os valores ordenados de acordo com keys
	 * @throws SQLException 
	 */
	public static int inserRecord(String table, String keys, String values) throws SQLException{
		
		
		setStatement();
		
		String sql = "INSERT INTO "+table+ " ("+keys+") VALUES ("+values+")";
        System.out.println("DBManager: " + sql);
		
		return statement.executeUpdate(sql);
	}
	

	//Checkers
	/**
	 * @return Se a conexão existe
	 */
	public static Boolean isConnected() {
		if(connection != null){
			return true;
		}
		
		return false;
	}

	
	
	
	// Getters and Setters
	public static Connection getConnection() {
		return connection;
	}

	public static void setConnection(Connection connection) {
		DBManager.connection = connection;
	}

	public static Statement getStatement() {
		return statement;
	}

	public static void setStatement(Statement statement) {
		DBManager.statement = statement;
	}	
	
	public static void setStatement() throws SQLException {
		statement = connection.createStatement();
	}
}
