import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ExcelReader {

    public static ArrayList<ArrayList<String>> getDetails()
    {

        ArrayList<ArrayList<String>> detailList=new ArrayList<ArrayList<String>>();

        Connection con=null;
        Statement stmnt=null;
        String filename=".\\BirthDayData";

        String pattern="dd/MM";
        SimpleDateFormat format=new SimpleDateFormat(pattern);
        Date date=new Date();
        ResultSet rs=null;

        try
        {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

            String conUrl="jdbc:odbc:DRIVER={Microsoft Excel Driver (*.xls, *.xlsx, *.xlsm, *.xlsb)};DBQ="
                    + filename + ".xlsx;DriverID=22;readonly=false";

            con=DriverManager.getConnection(conUrl);
            stmnt=con.createStatement();
            String query="select Name,Birthdate,EmailID from[Sheet1$] where Birthdate='"
                    + format.format(date) + "'";

            //String query="select Name,Birthdate,EmailID from[Sheet1$]";
            //rs.refreshRow();
            //rs.beforeFirst();
            rs=stmnt.executeQuery(query);

            //System.out.println("Found the following urls:");



            while(rs.next())
            {

                ArrayList<String> indetailList= new ArrayList<String>();
                indetailList.add(rs.getString("Name"));
                indetailList.add(rs.getString("Birthdate"));
                indetailList.add(rs.getString("EmailID"));

                detailList.add(indetailList);

                System.out.println("Indetails in arraylist"+indetailList);
            }


            System.out.println("details in arraylist"+detailList);

        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            try {

                //rs.refreshRow();
                //rs.deleteRow();
                rs.close();
                stmnt.close();
                con.close();

            }

            catch (SQLException e)
            {

                e.printStackTrace();
            }
        }

        return detailList;

    }

}
