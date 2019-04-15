import java.util.ArrayList;

public class EmailGreetings {


    public static void triggerEmail(ArrayList<ArrayList<String>> detailList)
    {
        int emailCnt=detailList.size();
        for(int i=0;i<emailCnt; i++ )
        {
            String name=detailList.get(i).get(0);
            String to=detailList.get(i).get(2);
            Email user=new Email(name,to);
            user.email();


        }
    }

}