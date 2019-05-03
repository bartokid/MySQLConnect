import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import  java.sql.SQLException;
public class MySQLConnect {

    Connection connection = null;



    public MySQLConnect(){

    }

    public  Connection connect() {

        boolean state=false;
        String SQLURL="jdbc:mysql://localhost:3306/usuariosbbdd?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String admin="root";
        String password="admin";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(SQLURL, admin, password);
            if(connection==null){
                state=true;
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public void insert(Object object ){
        try {
            Statement statement = connection.createStatement();
            ResultSet rs= statement.executeQuery("INSERT INTO " + object.getClass().getName() + "s VALUES ("+
                    getFields(object)+")");
            String fields = getFields(object);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean disconect(Connection connection){
        boolean state=false;
        try {
            connection.close();
            state=true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return state;
    }

    public String getFields(Object entity) {
        String fields=null;
        Field[] l =entity.getClass().getFields();

        for (Field f : l) {
            fields = fields + f.getName() +",";
            System.out.println(f.getName());
            System.out.println("hola");
        }

        return fields;
    }

}
