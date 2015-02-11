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
import java.util.ArrayList;
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
 * Servlet that processes create/edit commands for a tour.
 * @author Beatriz
 */
@WebServlet(name = "CreateTourServlet", urlPatterns = {"/CreateTourServlet" })
public class CreateTourServlet extends HttpServlet {

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
            HttpSession session = request.getSession(true);
            //Tour tour = new Tour();
            Tour tour = (Tour) session.getAttribute("tour");

            if (tour == null) {
                tour = new Tour();
            }

            if (request.getQueryString().contains("refresh")) {
                printListOfWaypoints(out, tour);
            } else if (request.getQueryString().contains("savefile")) {
               Object file = session.getAttribute("file");
            } else if (request.getQueryString().contains("newtour")) {
                tour = new Tour();
                printListOfWaypoints(out, tour);
            } else if (request.getQueryString().contains("addwaypoint")) {
                String latitude = extractLatitude(request, "addwaypoint");
                String longtitude = extractLongtitude(request, "addwaypoint");

                String name = request.getParameter("name");

                tour.addWaypoint(new Waypoint(-1, tour.getTourId(),
                        name, latitude, longtitude, tour.getWaypointCount()));
                printListOfWaypoints(out, tour);
            } else if (request.getQueryString().contains("changewaypoint")) {
                int i = Integer.parseInt(request.getParameter("index"));

                if (request.getParameter("coordinates") != null) {
                    String lat = extractLatitude(request, "coordinates");
                    String lon = extractLongtitude(request,
                            "coordinates");
                    tour.getWaypointByPosition(i).setWaypointLatitude(lat);
                    tour.getWaypointByPosition(i).setWaypointLongitude(lon);
                }
                if (request.getParameter("name") != null) {
                    String name = request.getParameter("name");
                    tour.getWaypointByPosition(i).setWaypointName(name);
                }
                printListOfWaypoints(out, tour);
            } else if (request.getQueryString().contains("deletewaypoint")) {

                String index = request.getParameter("index");
                int wayPointIndex = Integer.parseInt(index);

                tour.removeWaypoint(wayPointIndex);
                printListOfWaypoints(out, tour);
            } else if (request.getQueryString().contains("changeorder")) {

                String order = request.getParameter("changeorder");

                String[] stringNumbers = order.split(",");

                int[] numbers = new int[stringNumbers.length];

                for (int i = 0; i < stringNumbers.length; i++) {
                    numbers[i] = Integer.parseInt(stringNumbers[i]);
                }

                tour.setOrder(numbers);

                printListOfWaypoints(out, tour);
            } else if (request.getQueryString().contains("edittour")) {
                editTourToDatabase(request, tour);
                tour = new Tour();
                out.println("<br/><br/><div style="
                        + "'width:100%;text-align:center;'>"
                        + "<h2 style='color:green;font-size:25px;'>"
                        + "The tour is updated!</h2></div>");
            } else if (request.getQueryString().contains("addtour")) {
                addTourToDatabase(request, tour);
                tour = new Tour();
                out.println("<br/><br/><div style="
                        + "'width:100%;text-align:center;'>"
                        + "<h2 style='color:green;font-size:25px;'>"
                        + "The tour is saved!</h2></div>");
            }

            session.setAttribute("tour", tour);


        } finally {
            out.close();
        }
    }

    /**
     * Sets values corresponding values to the database.
     * @param request Request that contains new tour parameters.
     * @param tour Tour to edit in the database.
     * @throws ServletException .
     */
    private void editTourToDatabase(final HttpServletRequest request, Tour tour)
            throws ServletException {
        // Always need to start with these two instructions.
        HttpSession session = request.getSession(true);
        String idTour = session.getAttribute("IDtour").toString();
        int idTourInt = Integer.parseInt(idTour);

        if (idTour != null && !"".equals(idTour)) {
            int id = idTourInt;
            tour.setTourId(id);
            tour = setValuesToTour(request, session, tour);

            EntityManager em = DatabaseUtils.startDBTransaction(emf, utx);
            ManageToursDatabaseMethods createTourLogic =
                    new ManageToursDatabaseMethods(em);

            createTourLogic.editTour(idTourInt, tour);

            // Always need to end with this call.
            DatabaseUtils.endDBTransaction(utx, em);
        }
    }

    /**
     * Add a new to to the database.
     * @param request Request that contains new tour parameters.
     * @param tour Tour to add to the database.
     * @throws ServletException .
     */
    private void addTourToDatabase(final HttpServletRequest request, Tour tour)
            throws ServletException {

        HttpSession session = request.getSession(true);
        tour = setValuesToTour(request, session, tour);

        EntityManager em = DatabaseUtils.startDBTransaction(emf, utx);
        ManageToursDatabaseMethods createTourLogic;
        createTourLogic = new ManageToursDatabaseMethods(em);

        int nTourId = createTourLogic.insertTour(tour);
        HttpSession session1 = request.getSession(true);
        session1.setAttribute("IDtour", nTourId);

        // Always need to end with this call.
        DatabaseUtils.endDBTransaction(utx, em);
    }

    /**
     * Set values from the request attributes to the tour.
     * @param request contains attributes for the tour.
     * @param session contains data related with the current tour.
     * @param tour gets the values from request.
     * @return .
     */
    private Tour setValuesToTour(final HttpServletRequest request,
            final HttpSession session, final Tour tour) {
        String level = request.getParameter("level");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String sdistance = request.getParameter("distance");
        Float distance = Float.parseFloat(sdistance);
        String badgeUrl = (String) session.getAttribute("bageLocation");

        List<String> souvenirsLocations =
                (List<String>) session.getAttribute("souvenirsLocations");
        List<Integer> souvenirsDownloads =
                (List<Integer>) session.getAttribute("souvenirsDownloads");

        List<Souvenir> souvenirs = new ArrayList<Souvenir>();

        if (souvenirsLocations != null) {
            for (int i = 0; i < souvenirsLocations.size(); i++) {
                Souvenir souvenir = null;
                if (souvenirsLocations.get(i) == null
                    && i < tour.getTourSouvenirs().size()) {
                    souvenir = tour.getTourSouvenirs().get(i);
                } else if (souvenirsLocations.get(i) != null) {

                    souvenir = new Souvenir(-1, tour.getTourId(),
                            souvenirsLocations.get(i),
                            souvenirsDownloads.get(i));

                    if (i < tour.getTourSouvenirs().size()) {
                        tour.addRemovedSouvernirs(
                                tour.getTourSouvenirs().get(i));
                    }
                }
                if (souvenir != null) {
                    souvenirs.add(souvenir);
                }
            }

            tour.setTourSouvenirs(souvenirs);
        }

        tour.setTourDifficulty(level);
        tour.setTourName(name);
        tour.setTourDescription(description);
        tour.setsDistance(distance);

        if (badgeUrl != null) {
            tour.setsBadgeURL(badgeUrl);
        }

        tour.setnUserId(
                Integer.parseInt(session.getAttribute("userid").toString()));

        return tour;
    }

    /**
     * Generates html code for a list of way points forms.
     * @param out PrintWriter to print the list.
     * @param tour contains list of way points to print.
     */
    private void printListOfWaypoints(final PrintWriter out, final Tour tour) {
        for (int i = 0; i < tour.getWaypointCount(); i++) {
            String imageName;
            if (i == 0) {
                imageName = "images/start-race-2.png";
            } else if (i == tour.getWaypointCount() - 1) {
                imageName = "images/cup.png";
            } else {
                imageName = "images/number_" + i + ".png";
            }

            String line = "<li style=\"margin: 0\""
                    + " class=\"ui-state-default\" "
                    + " id=\"waypoint"
                    + Integer.toString(i)
                    + "\"> "
                    + " <span class=\"ui-icon ui-icon-arrowthick-2-n-s\">"
                    + "</span>"
                    + "<td><img src='"
                    + imageName
                    + "' style=\"height:100%\"/></td>   "
                    + tour.getWaypointByPosition(i) + "</li>";
            out.println(line);
        }
    }

    /**
     * Extracts longitude from the request string.
     * @param request contains longitude.
     * @param parameter parameter to get from the request (addTour or editTour).
     * @return returns the extracted longitude.
     */
    private String extractLongtitude(final HttpServletRequest request,
            final String parameter) {
        String coordinates = request.getParameter(parameter);

        int rightBracket = coordinates.indexOf(")");
        int comma = coordinates.indexOf(",");

        String longtitude = coordinates.substring(comma + 2, rightBracket);
        return longtitude;
    }

    /**
     * Extracts longitude from the request string.
     * @param request contains Latitude.
     * @param parameter parameter to get from the request (addTour or editTour).
     * @return returns the extracted latitude.
     */
    private String extractLatitude(final HttpServletRequest request,
            final String parameter) {
        String coordinates = request.getParameter(parameter);

        int leftBracket = coordinates.indexOf("(");
        int comma = coordinates.indexOf(",");

        String latitude = coordinates.substring(leftBracket + 1, comma - 1);
        return latitude;
    }

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
    }
}
