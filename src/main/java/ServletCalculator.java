import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by Ivan on 22.02.2017.
 */
public class ServletCalculator extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        List<String> history;
        ServletContext context;
        Set<String> users;
        if(req.getSession().isNew()) {
            history = new ArrayList<>();
            req.getSession().setAttribute("history", new ArrayList<String>());
        } else {
            history = (List<String>) req.getSession().getAttribute("history");
        }
        context = req.getSession().getServletContext();
        if (hasElement(context.getAttributeNames())) {
            users = (Set<String>) context.getAttribute("userList");
        }
        else {
            users = new HashSet<>();
        }
        users.add(req.getSession().getId());
        context.setAttribute("userList", users);

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        out.write("<html>");
        out.write("<head>");
        out.write("<title>CALCULATOR</title>");
        out.write("</head>");
        out.write("<body>");

        double a1 = Double.parseDouble(req.getParameter("a1"));
        double a2 = Double.parseDouble(req.getParameter("a2"));
        String type = req.getParameter("typeAction");
        double result;
        switch (type) {
            case ("add"): {
                result = a1 + a2;
                type = " + ";
                break;
            }
            case ("subtract"): {
                result = a1 - a2;
                type = " - ";
                break;
            }
            case ("divide"): {
                result = a1 / a2;
                type = " / ";
                break;
            }
            default: {
                result = a1 * a2;
                type = " * ";
            }
        }
        Story item = new Story(a1, a2, type, result);
        history.add(item.toString());
        req.getSession().setAttribute("history", history);
        out.write(item.toString() + "<br>");
        out.write("<a href=\"history\">User history</a><br>");
        out.write("<a href=\"users\">User list</a><br>");
        out.write("</body>");
        out.write("</html>");
        out.close();

    }

    private boolean hasElement(Enumeration attributeNames) {
        while (attributeNames.hasMoreElements()) {
            if (attributeNames.nextElement().equals("userList")) {
                return true;
            }
        }

        return false;
     }
}
