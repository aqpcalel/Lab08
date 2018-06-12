package controller.users;

import model.Role;
import model.User;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@SuppressWarnings("serial")
public class UsersControllerAdd extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Comprueba si lo que queremos hacer es iniciar sesion, o simplemente crear un usuario
        boolean isLogIn;

        PersistenceManager pm = controller.PMF.get().getPersistenceManager();

        //El ID del usuario. Este id se obtiene de la direccion de correo -> en richard@gmail.com el ID es richard
        String userID;

        try {
            //El parametro logIn se añade unicamente al iniciar sesion, mas no al crear un nuevo usario.
            isLogIn = request.getParameter("logIn").equals("logIn");
        } catch (NullPointerException e){
            //Si lo que se quiere es crear un usuario, y no iniciar sesion.
            isLogIn = false;
        }

        //Si se quiere iniciar sesion
        if (isLogIn){
            //Obtiene el ID del Usuario que va iniciar sesion.
            userID = request.getParameter("userID");

            //Busca si ya existe una sesion iniciada
            HttpSession misesion= request.getSession(true);

            //Si no existe la sesion, la crea usando el ID del usuario
            if (!sesionExist(misesion)){
                System.out.println("No existe sesión. Se creará.");

                misesion = request.getSession(true);
                misesion.setAttribute("userID",userID);
                //La sesion perdurara sin actividad durante 6 minutos
                misesion.setMaxInactiveInterval(360);
            }
            //Si la sesion existe, continua.
            else {
                System.out.println("Sesion: " + misesion.getId() + "\nUserID: " + misesion.getAttribute("userID"));
            }

        }
        //Si lo que se quiere hacer es crear un Usuario
        else {
            String userEmail;
            userID = "";
        }

        crearUsuario(userID,request,pm);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Redirige a doPost
        doPost(request, response);
    }

    //Revisa si un usuario existe: id -> ID del usuario (ejm. en richard@gmail.com el ID es richard)
    private boolean userExists(String id, PersistenceManager pm){
        try{
            //Intenta buscar en el DataStore un usuario con el ID respectivo.
            User usr = pm.getObjectById(User.class, id);

            //Si lo encuentra lo imprime en consola y devuelve true (el usuario si existe)
            System.out.println(usr);
            return true;
        } catch (JDOObjectNotFoundException exc){
            //Si no lo encuentra, se lanza una Excepción, se captura, y se devuelve false (el usuario no existe)
            return false;
        }
    }

    //Comprueba si existe una sesion: sesion -> Objeto HttpSesion que contiene la sesion actual
    private boolean sesionExist(HttpSession sesion){
        try{
            //Intenta buscar el atributo userID dentro de la sesion
            boolean a = sesion.getAttribute("userID").toString().isEmpty();
            //Si lo encuentra, la sesion si existe
            return true;
        } catch (NullPointerException e){
            //Si no, la sesion no existe
            return false;
        }
    }

    private void crearUsuario(String userID, HttpServletRequest request, PersistenceManager pm){

        //Revisa si el usuario con su ID ya tiene un objeto User Persistente almacenado.
        if (userExists(userID, pm)){
            System.out.println("User exists!!!!");
        }
        //Si no existe, crea el objeto de tipo User con los datos que se obtienen del request, y lo hace Persistente.
        else {
            String userName = request.getParameter("userName");
            String userImg = request.getParameter("userImg");
            String userRole = request.getParameter("userRole");
            String email = userID + "@gmail.com";

            //El new Role es provisional, hasta que termine la implementacion del CRUD de Role.
            User user = new User(userID, userName, userImg, email, new Role(userRole));

            try{
                pm.makePersistent(user);
                System.out.println("Usuario creado con exito.");
            } finally {
                pm.close();
            }

        }
    }

}
