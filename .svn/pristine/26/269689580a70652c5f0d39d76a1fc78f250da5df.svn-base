/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import databaseinterface.ManageToursDatabaseMethods;
import utils.DatabaseUtils;
import java.io.IOException;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

/**
 *
 * @author alex
 */
@WebServlet(name = "DeleteTourServlet", urlPatterns = {"/DeleteTourServlet" })
public class DeleteTourServlet extends HttpServlet {

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
     * Process Request.
     * @param request .
     * @param response .
     * @throws ServletException .
     * @throws IOException .
     */
    protected final void processRequest(final HttpServletRequest request,
            final HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager em = DatabaseUtils.startDBTransaction(emf, utx);
        ManageToursDatabaseMethods createTourLogic =
                new ManageToursDatabaseMethods(em);

        // This variable has the list of tours
        createTourLogic.deleteTourById(Integer.parseInt(
                request.getParameter("tourdelete").toString()));

        // Always need to end with this call.
        DatabaseUtils.endDBTransaction(utx, em);
        response.sendRedirect("ListTours.jsp");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods">
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
        processRequest(request, response);
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
