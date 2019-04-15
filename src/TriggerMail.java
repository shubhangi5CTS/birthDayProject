import java.util.ArrayList;

public class TriggerMail {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        ArrayList<ArrayList<String>> detailList=ExcelReader.getDetails();
        EmailGreetings.triggerEmail(detailList);


    }

}
