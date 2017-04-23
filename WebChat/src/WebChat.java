import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hackeru on 23.04.2017.
 */
public class WebChat extends javax.servlet.http.HttpServlet {

    String message;
    String userMessage;

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String queryString=request.getQueryString();
        Map<String,String> qs=new HashMap<>();
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
           case "send":
               if ((userMessage=qs.get("message"))!=null)
                   message=userMessage;
               break;
           case "get":
               response.getWriter().write(message);
               break;
           default:
               return;
       }
    }
}
