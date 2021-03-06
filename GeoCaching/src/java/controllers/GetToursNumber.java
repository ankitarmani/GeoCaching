/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import databaseinterface.ManageToursDatabaseMethods;
import utils.DatabaseUtils;
import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;

/**
 *
 * @author alex
 */
@WebServlet(name = "GetToursNumber", urlPatterns = {"/GetToursNumber" })
public class GetToursNumber extends HttpServlet {

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
     * Process request.
     * @param request .
     * @param response .
     * @throws ServletException .
     * @throws IOException .
     */
    protected final void processRequest(
            final HttpServletRequest request,
            final HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(true);
        EntityManager em = DatabaseUtils.startDBTransaction(emf, utx);
        ManageToursDatabaseMethods createTourLogic =
                new ManageToursDatabaseMethods(em);

        if (request.getParameter("object").contains("tour")) {
            int allTours = createTourLogic.getAllTours().size();
            session.setAttribute("ToursNumber", allTours + "");
            DatabaseUtils.endDBTransaction(utx, em);
        } else {
            if (request.getParameter("object").contains("waypoint")) {
                HttpSession session1 = request.getSession(true);
                String idTour = session1.getAttribute("IDtour").toString();
                int allWaypoints = createTourLogic.getAllWaypointsByTourId(
                        Integer.parseInt(idTour)).size();
                session.setAttribute("WaypointsNumber", allWaypoints + "");
                DatabaseUtils.endDBTransaction(utx, em);
            }
        }
        // This variable has the list of tours
        // Always need to end with this call.
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
