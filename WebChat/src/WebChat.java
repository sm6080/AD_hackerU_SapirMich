import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hackeru on 23.04.2017.
 */
public class WebChat extends javax.servlet.http.HttpServlet {

    List<Message> messagesList;
    Map<String,String> users;


    @Override
    public void init() throws ServletException {
        super.init();

        messagesList = new ArrayList<>();
        users=new HashMap<>();
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        String queryString = request.getQueryString();
        String userMessage;
        Map<String,String>qs=new HashMap<>();
        if(queryString != null){
            String[] keyValues = queryString.split("&");
            for(String keyValue : keyValues){
                String[] keyValuePair = keyValue.split("=");
                if(keyValuePair.length != 2)
                    continue;
                qs.put(keyValuePair[0], keyValuePair[1]);
            }
        }
        String action=qs.get("action");
        switch (action) {
            case "get":
                if (validParameters(qs)) {
                    String result = "";
                    String lastReadMessage = qs.get("lastMessage");
                    Integer last;
                    try {
                        last = Integer.valueOf(lastReadMessage);
                    } catch (Exception ex) {
                        return;
                    }
                    result = getMessages(last);
                    response.getWriter().write(result);
                }
                else
                    response.getWriter().write("no");
                break;
            case "send":
                if (validParameters(qs)) {
                    if ((userMessage = qs.get("message")) == null)
                        return;
                    String userName=qs.get("userName");
                    Message message=new Message(userName,userMessage);
                    messagesList.add(message);
                }
                break;
            case "sign_up":
                String userName = qs.get("userName");
                String password = qs.get("password");
                String value;
                if ((value = users.get(userName)) == null)
                    users.put(userName, password);
                else
                    response.getWriter().write("no");
                break;
            case "log_in":
                if (!validParameters(qs))
                    response.getWriter().write("no");
                else
                    response.getWriter().write("ok");
                break;
            default:
                return;
        }

    }
    private boolean validParameters(Map<String,String>  qs){
        String userName=qs.get("userName");
        String password=qs.get("password");
        String value;
        if ((value=users.get(userName))!=null)
            if (value.equals(password))
                return true;

        return false;
    }

    private String getMessages(int last){
        StringBuilder stringBuilder=new StringBuilder();
        String result;
        for (int i=last;i<messagesList.size();i++) {
            stringBuilder.append(messagesList.get(i).userName+"|"+messagesList.get(i).message+'&');
        }
        if(stringBuilder.length()>0)
            result=new String((stringBuilder.toString()).getBytes(),0,stringBuilder.length()-1);
        else
            result="";
        return result;

    }
}


class Message{
    String userName;
    String message;

    public Message(String userName, String message) {
        this.userName = userName;
        this.message = message;
    }
}

