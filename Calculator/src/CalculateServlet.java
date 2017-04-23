import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by hackeru on 20.04.2017.
 */
public class CalculateServlet extends javax.servlet.http.HttpServlet {

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String query=request.getQueryString();
        Map<String,String> qs=new HashMap<>();
        if (query!=null){
            String[] keyValues=query.split("&");
            for (String keyValue:keyValues   ) {
                String[] keyValuePair = keyValue.split("=");
                if (keyValuePair.length != 2)
                    continue;
                qs.put(keyValuePair[0],keyValuePair[1]);
            }
        }
        String action=qs.get("action");
        if (action==null)
            return;
        String operand1String=qs.get("operand1");
        String operand2String=qs.get("operand2");
        if (operand1String==null||operand2String==null)
            return;
        int operand1;
        int operand2;
        try {
            operand1 = Integer.valueOf(operand1String);
            operand2 = Integer.valueOf(operand2String);
        }catch (Exception ex){
            return;
        }
        String result;
        switch (action) {
            case "add":
                result = String.valueOf(operand1 + operand2);
                break;
            case "substract":
                result = String.valueOf(operand1 - operand2);
                break;
            case "multiply":
                result = String.valueOf(operand1 * operand2);
                break;
            case "divide":
                if (operand2 == 0) {
                    response.getWriter().write("Division by zero ");
                    return;
                }
                result = String.valueOf(operand1 / operand2);
                break;
            default:
                break;
        }
        response.getWriter().write("");

    }
}
