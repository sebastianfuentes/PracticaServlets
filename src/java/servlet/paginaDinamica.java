/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author Alumno
 */
@WebServlet(name = "paginaDinamica", urlPatterns = {"/paginaDinamica"})
public class paginaDinamica extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException, SQLException {
        try (PrintWriter out = response.getWriter()){
        String registrado = request.getParameter("registrado");
        Connection conx;
        Statement stm = null;
            if(registrado != null){   
        try{
        Class.forName("com.mysql.jdbc.Driver");
        conx = DriverManager.getConnection("jdbc:mysql://localhost/cosasdejava","root","n0m3l0");
        stm = conx.createStatement();
        }
        catch(SQLException | ClassNotFoundException e){e.printStackTrace();}
        String nombre = request.getParameter("nombre");
        String paterno = request.getParameter("paterno");
        String materno = request.getParameter("materno");
        String correo = request.getParameter("correo");
        stm.executeQuery("call altapersona('"+nombre+"','"+paterno+"','"+materno+"','"+correo+"')");
                out.print("
                    <!DOCTYPE html>
                    <html lang="es">
                    <head>
                      <meta charset="utf-8"/>
                      <meta name="description" content="Pagina en servlet"/>
                      <meta name="viewport" content="width=device-width, minimum-scale=1 maximum-scale=1"/>
                      <title>Servlet Response</title>
                      <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700' rel='stylesheet' type='text/css'>
                      <link rel="stylesheet" type="text/css" href="normalize.css">
                      <link rel="stylesheet" type="text/css" href="estilos.css">
                    </head>
                    <body>
                      <header>
                      <h1><a href="#">Hola bienvenido al registro</a></h1>
                    </header>
                      <div id="contenido">
                      <form action="\paginaDinamica\?registrado=si" method="post" id="form">
                        <input type="text" placeholder="Nombre"/><br /><br />
                        <input type="text" placeholder="Apellido Paterno"/><br /><br />
                        <input type="text" placeholder="Apellido Materno"/><br /><br />
                        <input type="email" placeholder="Correo"/><br /><br />
                        <input type="submit"/>
                        <br />
                        <p>Ya estas registrado paps</p>
                      </form>
                      </div>
                    <footer>
                      <p>
                        <strong>Powered by Me</strong>
                      </p>
                      <p>
                          Hasta footer tiene :3
                      </p>
                    </footer>
                      </body>
                    </html>
                ");
            }}
            else
            {
                out.print("
                    <!DOCTYPE html>
                    <html lang="es">
                    <head>
                      <meta charset="utf-8"/>
                      <meta name="description" content="Pagina en servlet"/>
                      <meta name="viewport" content="width=device-width, minimum-scale=1 maximum-scale=1"/>
                      <title>Servlet Response</title>
                      <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700' rel='stylesheet' type='text/css'>
                      <link rel="stylesheet" type="text/css" href="normalize.css">
                      <link rel="stylesheet" type="text/css" href="estilos.css">
                    </head>
                    <body>
                      <header>
                      <h1><a href="#">Hola bienvenido al registro</a></h1>
                    </header>
                      <div id="contenido">
                      <form action="\paginaDinamica\?registrado=si" method="post" id="form">
                        <input type="text" placeholder="Nombre"/><br /><br />
                        <input type="text" placeholder="Apellido Paterno"/><br /><br />
                        <input type="text" placeholder="Apellido Materno"/><br /><br />
                        <input type="email" placeholder="Correo"/><br /><br />
                        <input type="submit"/>
                        <br />
                        <p>Aun no estas registrado paps</p>
                      </form>
                      </div>
                    <footer>
                      <p>
                        <strong>Powered by Me</strong>
                      </p>
                      <p>
                          Hasta footer tiene :3
                      </p>
                    </footer>
                      </body>
                    </html>
                ");
            }
            response.setContentType("text/html;charset=UTF-8");

        }
    

    // <editor-fold defaultstate="collapsed" desc="Metodos">
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {}
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {}
    }
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

