import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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


            String query = "INSERT INTO " + object.getClass().getName().toLowerCase() + "s ("+getFields(object)+
                    ") VALUES ("+ getQMarkers(object)+");";
            System.out.println(query);
            PreparedStatement statement = connection.prepareStatement(query);
            String[] valores =getValue(object,statement);
            int i =0;
            while(i<valores.length){
                statement.setString(i,valores[i]);
            }

            ResultSet rs= statement.executeQuery(query);


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
    private String getQMarkers(Object entity){
        StringBuffer sb= new StringBuffer();
            sb.append("?");
        for(int i=1; i<entity.getClass().getDeclaredFields().length;i++){
            sb.append(",?");
        }
        return sb.toString();
    }
    private String[] getValue(Object entity, PreparedStatement state){
        int i=0;
        String sField;
        Field[] l =entity.getClass().getDeclaredFields();
        String[] valores=null;
        try {
        while(i<l.length){
            sField= l[i].getName();
            Method m= entity.getClass().getDeclaredMethod(
                        "get"+sField.substring(0,1).toUpperCase()+sField.substring(1),null);
                        //añadir paràmetros donde el null ya que podria ser un problema para los setters.
            String res = (String)m.invoke(entity);
            System.out.println(sField);
            System.out.println(res);
            valores[i] = res;
            //state.setString(i,res);
            i++;
        }

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return valores;
    }
}
