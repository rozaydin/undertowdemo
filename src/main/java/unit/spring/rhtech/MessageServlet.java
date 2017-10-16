package unit.spring.rhtech;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
// http://undertow-io.github.io/undertow-io-site/documentation/undertow-design-doc.html
@WebInitParam(name="admin", value="false") // undertow does not support annotation processing
public class MessageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println(getInitParameter("message"));

        // Setting up the content type of web page
        resp.setContentType("text/html");
        resp.getWriter().println("Servlet is alive ...");

    }
}
