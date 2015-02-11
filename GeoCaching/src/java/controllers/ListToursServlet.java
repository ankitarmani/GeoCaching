/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import databaseinterface.ManageToursDatabaseMethods;
import models.Tour;
import models.Waypoint;
import utils.DatabaseUtils;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

/**
 *
 * @author Beatriz
 */
public class ListToursServlet extends HttpServlet {

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
    public final void processRequest(final HttpServletRequest request,
            final HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String responseContent = "";
        String coordinatesList = "";
        String startWayPoint = "";

        PrintWriter out = response.getWriter();

        EntityManager em = DatabaseUtils.startDBTransaction(emf, utx);

        ManageToursDatabaseMethods createTourLogic =
                new ManageToursDatabaseMethods(em);

        // This variable has the list of tours
        List<Tour> allTours = createTourLogic.getAllTours();
        String src = "";
        String distance = "";
        String imageRS = "";
        try {
            for (int i = 0; i < allTours.size(); i++) {

                List<Waypoint> coordinates = createTourLogic.
                        getAllWaypointsByTourId(allTours.get(i).getTourId());
                coordinatesList = "<table id='coord' style='width:100%;'>"
                        + "<td style='border:none;'>";

                for (int j = 0; j < coordinates.size(); j++) {
                    if (j == 0) {
                        src = "images/start-race-2.png";
                    } else if (j == coordinates.size() - 1) {
                        src = "images/cup.png";
                    } else {
                        src = "images/number_" + j + ".png";
                    }

                    coordinatesList += "<table><tr><td "
                            + "style='border:none;padding:0"
                            + " px;border-spacing:0 px; "
                            + "width:10px;'><img src='" + src
                            + "'/></td><td style='vertical-align: "
                            + "middle;border:none;width:300px;'>"
                            + coordinates.get(j).getWaypointName()
                            + "</td><td style='vertical-align: "
                            + "middle;border:none;width:50px;'>"
                            + coordinates.get(j).getWaypointLatitude()
                            + "</td><td style='vertical-align: "
                            + "middle;border:none;width:50px;'>"
                            + coordinates.get(j).getWaypointLongitude()
                            + "</td></tr></table>";
                }

                startWayPoint = coordinates.get(0).getWaypointLatitude()
                        + ";" + coordinates.get(0).getWaypointLongitude();

                // folder = System.getProperty("user.home")+ "/temp/";
                if (allTours.get(i).getsDistance() != null) {
                    distance = "Total distance:</br>"
                            + allTours.get(i).getsDistance() + " m";
                } else {
                    distance = " ";
                }
                if (allTours.get(i).getsDistance() != null) {
                    distance = "Total distance:</br>"
                            + allTours.get(i).getsDistance() + " m";
                } else {
                    distance = " ";
                }
                if (allTours.get(i).getsBadgeURL() != null) {
                    imageRS = allTours.get(i).getsBadgeURL();
                    imageRS = imageRS.substring(imageRS.
                            lastIndexOf(File.separator), imageRS.length());
                    coordinatesList += "</td><td "
                            + "style='middle;border:none;'>"
                            + "<img id='bageImage' src='uploads" + imageRS
                            + "' width='90'/></br></br>"
                            + distance + "</td></table>";

                } else {
                    imageRS = "images/nopicture.jpg";
                    coordinatesList += "</td><td "
                            + "style='middle;border:none;'>"
                            + "<img id='bageImage' src='" + imageRS
                            + "' width='90'/></br></br>"
                            + distance + "</td></table>";
                }

                responseContent += allTours.get(i).getTourId() + "|"
                        + "<a class='E' href='#'>"
                        + allTours.get(i).getTourName() + "</a>|"
                        + allTours.get(i).getTourDifficulty() + "|"
                        + allTours.get(i).getTourDescription() + "|"
                        + "<a class='E' href='editTour.jsp?tour="
                        + allTours.get(i).getTourId()
                        + "'>Edit</a>&nbsp&nbsp&nbsp<a class='D' "
                        + "href='DeleteTourServlet?tourdelete="
                        + allTours.get(i).getTourId() + "'>Delete</a>" + "|"
                        + coordinatesList + "|"
                        + startWayPoint + "|";
            }
            out.println(responseContent);

        } finally {
            out.close();
        }

        // Always need to end with this call.
        DatabaseUtils.endDBTransaction(utx, em);
    }

    /**
     * Build table.
     * @param tn .
     * @return table .
     * @throws ServletException .
     */
    public final String buildTable(final String tn) throws ServletException {

        //  HttpSession ToursNumber = request.getSession(true);
        //  <td class='servSHd'>#</td><td class='servSHd'>#</td>

        String htmlContent =
                "<table id='report' class='servicesT' cellspacing='0'>"
                + "<td class='servSHd'>Tour name</td><td class='servSHd'>"
                + "Difficulty</td>"
                + "<td class='servSHd'>Description</td> <td class='servSHd'> "
                + "</td></tr>";
        //10 seesion record count
        for (int i = 1; i < Integer.parseInt(tn) + 1; i++) {
            htmlContent += "<tr>"
                    + "<td id='name" + (i)
                    + "' class='servBodL'  style='color:orange; "
                    + "font-weight:bold;'><a class='S' href='#dialog' "
                    + "name='modal' title='Browse'></a></td>"
                    + "<td id='difficulty" + (i) + "' class='servBodL'>"
                    + (i + 2) + "</td>"
                    + "<td id='description" + (i) + "' class='servBodL'>"
                    + (i + 2 + 2) + "</td>"
                    + "<td id='rowcontrol" + (i)
                    + "' class='servBodL' style='text-align: center;'></td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td colspan='2' id='details" + (i) + "'"
                    + " style='text-align: left; width: 50%;'></td>"
                    + "<td colspan='2' id='map-canvas" + (i) + "'"
                    + "style='width: 50%;'>"
                    + "</td>"
                    + "</tr>";
        }
        htmlContent += "</table>";

        return htmlContent;
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
