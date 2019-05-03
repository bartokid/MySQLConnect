import java.lang.reflect.Field;
import java.lang.reflect.Method;
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
            // INSERT INTO Usuarios (id, name, ) VALUES (?,?,?)
            // INSERT INTO Usuarios (id, name, ) VALUES ("PAu", "dksk2, "sds!")

            String query = "INSERT INTO " + object.getClass().getName().toLowerCase() + "s ("+getFields(object)+
                    ") VALUES ("+ getValues(object)+");";
            System.out.println(query);
            PreparedStatement statement = connection.prepareStatement(query);


            String sField; // ·"nombre"

            "get"+sField.substring(0,1).toUpperCase()+sField.substring(1);



            Method m = object.getClass().getDeclaredMethod("getNombre", null);

            String res = (String)m.invoke(object);

            statement.setString(i, res);


            ResultSet rs= statement.executeQuery(query);
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

    private String getFields(Object entity) {

        StringBuffer sb= new StringBuffer();
        Field[] l =entity.getClass().getDeclaredFields();

        sb.append(l[0].getName());
        for(int i =1 ; i<l.length;i++){
            sb.append("," + l[i].getName());
        }

        return sb.toString();
    }
    private String getValues(Object entity){
        StringBuffer sb= new StringBuffer();
            sb.append("?");
        for(int i=1; i<entity.getClass().getDeclaredFields().length;i++){
            sb.append(",?");
        }
        return sb.toString();
    }
}
