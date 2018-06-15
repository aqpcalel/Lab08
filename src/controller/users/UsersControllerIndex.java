package controller.users;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class UsersControllerIndex extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //Se usa para revisar si hay nua sesion activa
        HttpSession sesion= request.getSession();
        PersistenceManager pm = controller.PMF.get().getPersistenceManager();

        //Intenta hallar una sesion activa
        try{
            request.setAttribute("User",UsersControllerView.getUser(sesion.getAttribute("userID").toString()));
            request.setAttribute("UsersList",UsersControllerView.getAllUsers());
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/View/Users/index.jsp");
            dispatcher.forward(request,response);
        }
        //Si no la encuentra, redirige a la pagina inicial.
        catch (Exception e){
            e.printStackTrace();
            response.getWriter().println("<html><head><script>window.location.replace(\"../\")</script></head><body></bodyy></html>");
        }

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }
}