package database; 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexao {

    private static int MAX_CONNECTIONS = 5;
    private static FabricaConexao instance;

    private static String URL = "jdbc:mysql://wagnerweinert.com.br:3306/";
    private static String BD = "tads24_cardoso";
    private static String USER = "tads24_cardoso";
    private static String PASSWORD = "tads24_cardoso";

    private Connection[] conexoes;

    private FabricaConexao() {

        conexoes = new Connection[MAX_CONNECTIONS];

    }

    public static FabricaConexao getInstance() {
        if (instance == null) {
            instance = new FabricaConexao();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException{
        for(int i = 0;i<MAX_CONNECTIONS;i++){
            if(conexoes[i]==null||conexoes[i].isClosed()){
                conexoes[i] = DriverManager.getConnection(URL+BD,
                    USER, PASSWORD );
                    return conexoes[i];
            }
        }
        throw new SQLException("maximo de conexoes abertas");
    }

}