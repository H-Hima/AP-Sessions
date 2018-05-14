package DataBase;

import javax.xml.transform.sax.SAXSource;
import java.sql.*;

public class DataBase {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String url      = "jdbc:mysql://localhost/ap_db";   //database specific url.
        String user     = "root";
        String password = "";
        ProgramInstance instance = new ProgramInstance(url, user, password);
        instance.runSampleCode();
    }
}
