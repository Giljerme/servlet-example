import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

/**
 * Created by Ivan on 22.02.2017.
 */
@WebServlet(name = "UserServlet", urlPatterns = "/users")
public class ServletUsers extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        out.write("<html>");
        out.write("<head>");
        out.write("<title>users</title>");
        out.write("</head>");
        out.write("<body>");

        Set<String> users = (Set<String>) req.getSession().getServletContext().getAttribute("userList");;

        for (String s : users) {
            out.write("<br>" + s);
        }
        out.write("</body>");
        out.write("</html>");
        out.close();
    }
}
