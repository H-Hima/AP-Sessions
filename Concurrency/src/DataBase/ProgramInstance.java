package DataBase;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringReader;
import java.sql.*;

public class ProgramInstance {
    Connection connection;
    String url;
    String user;
    String password;

    ProgramInstance(String url, String user, String password) throws ClassNotFoundException, SQLException {
        this.url=url;
        this.user=user;
        this.password=password;

        initialize();
    }

    public void initialize() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
    }

    public void runSampleCode() throws SQLException, IOException {
        connection = getConnection();
//        connection.getMetaData().supportsResultSetConcurrency(1,ResultSet.CONCUR_READ_ONLY);
//        connection.getMetaData().supportsResultSetType(1);

        Statement query = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_UPDATABLE,
                ResultSet.CLOSE_CURSORS_AT_COMMIT);

        ResultSet result = query.executeQuery("SELECT course_name,units,grade,name,last_name from ( (course_human join humans on id=human_id) join courses on courses.courseid=course_human.course_id)");
        while(result.next()) {
            result.getMetaData().getColumnType(5);

            System.out.println(result.getInt("grade"));
            System.out.println(result.getString("course_name"));
            System.out.println(result.getString("name"));
            System.out.println(result.getString("last_name"));
        }

        if(true) {
            return;
        }

        ObjectInputStream os=null;
        byte[] aa;
        int len=os.read(aa);
        byte[] a=new byte[len];

        try {
            PreparedStatement statement=connection.prepareStatement("Insert into saves (game_id,value) Values (:id,:val)");
            byte[] byteArray=null;
            statement.setBytes(2,byteArray);
            statement.setString(1,"sdfsdfs");
            query.execute("INSERT INTO humans\n" +
                    "(ID,Name,Last_Name,Email) VALUES(19,'A','B','c@d.com')");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            PreparedStatement insert = getInsertCourseStatement();
            insert.setLong(1, 102);
            insert.setString(2, "Mathematics");
            insert.setInt(3, 4);
            insert.execute();
            insert.setLong(1, 101);
            insert.setString(2, "AP");
            insert.setInt(3, 4);
            insert.execute();
        }catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("=================");

        result = query.executeQuery("SELECT courseid,course_name,units from courses");

        while (result.next()) {
            try {
                System.out.println(result.getString(1));
                System.out.println(result.getString(2));
                result.updateInt("courseid", result.getInt("courseid") * -1);
                result.updateRow();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            //System.out.println(result.getString(3));
            //System.out.println(result.getString(4));
            System.out.println("===========================");
        }

        try {
            result.moveToInsertRow();
            result.updateInt(1, 1011);
            result.updateString(2, "Physic" + "_2");
            result.updateInt(3, 4);
            result.insertRow();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        connection.close();
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }

    public PreparedStatement getUpadteStatement() throws SQLException {
        connection = getConnection();
        return connection.prepareStatement("Update humans set  email = ? where name = ? and last_name = ?");
    }

    public PreparedStatement getSelectStatement() throws SQLException {
        connection = getConnection();
        return connection.prepareStatement("Select ID ,course_id, grade, unit from ((humans join courses_human on ID=human_ID) join course_id on courseID=course_id), where name = ? and last_name = ?",ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_UPDATABLE,
                ResultSet.CLOSE_CURSORS_AT_COMMIT);
    }

    public PreparedStatement getInsertGradeStatement() throws SQLException {
        connection = getConnection();
        return connection.prepareStatement("Insert into course_human (human_id,course_id,grade) VALUES(?,?,?)");
    }

    public PreparedStatement getInsertCourseStatement() throws SQLException {
        connection = getConnection();
        return connection.prepareStatement("Insert into courses (courseID,course_name,units) VALUES (?,?,?)");
    }

    public PreparedStatement getInsertHumanStatement() throws SQLException {
        connection = getConnection();
        return connection.prepareStatement("Insert into humans (ID,name,last_name,email) VALUES(?,?,?,?)");
    }

    public PreparedStatement getDeleteStatement() throws SQLException {
        connection = getConnection();
        return connection.prepareStatement("Delete from course_human where human_id=? and course_id=?");
    }
}
