/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import databaseinterface.ManageToursDatabaseMethods;
import models.Souvenir;
import models.Tour;
import models.Waypoint;
import utils.DatabaseUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet(name = "LoadTour", urlPatterns = {"/LoadTour" })
public class LoadTour extends HttpServlet {

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
        EntityManager em = DatabaseUtils.startDBTransaction(emf, utx);

        ManageToursDatabaseMethods createTourLogic =
                new ManageToursDatabaseMethods(em);
        try {
            HttpSession session = request.getSession(true);

            String idTour = session.getAttribute("IDtour").toString();

            int idTourInt = Integer.parseInt(idTour);

            Tour showTour = createTourLogic.getTourById(idTourInt);
            String coordinatesList = "";
            List<Waypoint> coordinates =
                    createTourLogic.getAllWaypointsByTourId(idTourInt);
            List<Souvenir> souvenirs =
                    createTourLogic.getAllSouvenirsByTourId(idTourInt);
            showTour.setTourWaypoints(coordinates);
            showTour.setTourSouvenirs(souvenirs);
            session.setAttribute("tour", showTour);


            for (int i = 0; i < coordinates.size(); i++) {
                coordinatesList +=
                        coordinates.get(i).getWaypointId() + ";"
                        + coordinates.get(i).getWaypointName() + ";"
                        + coordinates.get(i).getWaypointLatitude() + ";"
                        + coordinates.get(i).getWaypointLongitude() + ";";
            }

            String souvenirList = "";
            for (int i = 0; i < souvenirs.size(); i++) {
                souvenirList = souvenirList
                        + ";"
                        + souvenirs.get(i).getSouvenirURL();
            }
            String souvenirDownloads = "";
            for (int i = 0; i < souvenirs.size(); i++) {
                souvenirDownloads = souvenirDownloads
                        + ";"
                        + souvenirs.get(i).getDownloads();
            }
            out.println(showTour.getTourId()
                    + "|" + showTour.getTourName()
                    + "|" + showTour.getTourDifficulty()
                    + "|" + showTour.getTourDescription()
                    + "|" + coordinatesList
                    + "|" + showTour.getsDistance()
                    + "|" + (souvenirs.size())
                    + "|" + souvenirList
                    + "|" + souvenirDownloads
                    + "|" + showTour.getsBadgeURL());


        } finally {
            out.close();
            DatabaseUtils.endDBTransaction(utx, em);
        }
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
     * @return a String containing servlet description
     */
    @Override
    public final String getServletInfo() {
        return "Short description";
    } // </editor-fold>
}
