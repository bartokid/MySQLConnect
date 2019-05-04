import java.sql.Connection;

public class testInsert {
    public static void main(String[] args){

        Usuario usr = new Usuario("usr","pass",129,true,100,0);

        MySQLConnect myCon = new MySQLConnect();
        Connection con = myCon.connect();
        myCon.insert(usr);
        myCon.disconect(con);

    }
}
