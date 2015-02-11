/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import databaseinterface.UserManagementMethods;
import models.User;
import utils.DatabaseUtils;
import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;

/**
 *
 * @author Beatriz
 */
public class Login extends HttpServlet {

    /**
     * This is to access the Model Classes (DB).
     */
    @PersistenceUnit
    private EntityManagerFactory emf;
    /**
     * This is to access the Model Classes (DB).
     */
    @Resource
    private UserTransaction utx;

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected final void processRequest(final HttpServletRequest request,
            final HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /*
             * TODO output your page here. You may use following sample code.
             */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet login at " + request.getContextPath()
                    + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold desc="HttpServlet methods">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected final void doGet(final HttpServletRequest request,
            final HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected final void doPost(final HttpServletRequest request,
            final HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        HttpSession session = request.getSession(true);

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        EntityManager em = DatabaseUtils.startDBTransaction(emf, utx);
        UserManagementMethods authenticationLogic =
                new UserManagementMethods(em);

        int userId = authenticationLogic.authenticate(user);

        DatabaseUtils.endDBTransaction(utx, em);

        if (userId != -1) {
            session.setAttribute("userid", userId);
            session.setAttribute("username", username);

            String referer = request.getHeader("Referer");

            if (referer == null || referer.contains("login.jsp")) {
                response.sendRedirect("index.jsp");
            } else {
                response.sendRedirect(referer);
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public final String getServletInfo() {
        return "Short description";
    } // </editor-fold>
}