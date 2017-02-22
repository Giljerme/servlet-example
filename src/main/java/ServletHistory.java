import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Ivan on 22.02.2017.
 */
@WebServlet(name = "HistoryServlet", urlPatterns = "/history")
public class ServletHistory extends HttpServlet {
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
        out.write("<title>CALCULATOR</title>");
        out.write("</head>");
        out.write("<body>");

        List<String> history  = (List<String>) req.getSession().getAttribute("history");

        out.write("<h2>You session ID: " + req.getSession().getId());
        for (String s : history) {
            out.write("<br>" + s);
        }
        out.write("</body>");
        out.write("</html>");
        out.close();
    }
}
