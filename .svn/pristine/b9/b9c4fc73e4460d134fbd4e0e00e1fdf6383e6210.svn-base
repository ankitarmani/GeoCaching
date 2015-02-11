/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import databaseinterface.ManageToursDatabaseMethods;
import models.Question;
import models.Souvenir;
import models.Tour;
import models.Waypoint;
import utils.DatabaseUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@WebServlet(name = "QuestionServlet", urlPatterns = {"/QuestionServlet" })
public class QuestionServlet extends HttpServlet {

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
     * Constant.
     */
    private static final int ARG = 100;

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
    public final void processRequest(final HttpServletRequest request,
                                     final HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            EntityManager em = DatabaseUtils.startDBTransaction(emf, utx);
            ManageToursDatabaseMethods createTourLogic =
                    new ManageToursDatabaseMethods(em);

            HttpSession session = request.getSession(true);

            if (request.getParameter("getQuestions") != null) {
                String idTour = session.getAttribute("IDtour").toString();

                int idTourInt = Integer.parseInt(idTour);
                String waypointString = "";

                Tour tour = createTourLogic.getTourById(idTourInt);
                List<Souvenir> souvenirs =
                        createTourLogic.getAllSouvenirsByTourId(idTourInt);
                List<Waypoint> waypoints =
                        createTourLogic.getAllWaypointsByTourId(idTourInt);

                for (int i = 0; i < waypoints.size(); i++) {
                    int waypointID = waypoints.get(i).getWaypointId();
                    List<Question> waypointQuestions =
                            createTourLogic.getAllQuestionsByWaypointId(
                                            waypointID);
                    waypoints.get(i).setQuestions(waypointQuestions);
                }

                tour.setTourSouvenirs(souvenirs);
                tour.setTourWaypoints(waypoints);

                session.setAttribute("tour", tour);

                for (int i = 0; i < waypoints.size(); i++) {
                    waypointString += waypoints.get(i).getWaypointLatitude()
                            + "|" + waypoints.get(i).getWaypointLongitude()
                            + "|" + waypoints.get(i).getWaypointId()
                            + "|" + waypoints.get(i).getWaypointName()
                            + "|" + waypoints.get(i).getWaypointPosition()
                            + "|";

                    if (waypoints.get(i).getQuestions() == null) {
                            continue;
                        }

                    for (int j = 0; j
                            < waypoints.get(i).getQuestions().size(); j++) {

                        if (waypoints.get(i).getQuestions().get(
                                j).getQuestion().equals("")) {
                            continue;
                        }

                        String latLang = "lat";

                        if (waypoints.get(i).getQuestions().get(
                                j).getDigitPosition() > ARG) {
                            latLang = "lon";
                        }

                        int constantToGetLastNumbers = ARG;

                        String waypointID = ((Integer)
                                waypoints.get(i).getWaypointId()).toString();

                        String questionDigit = ((Integer) (waypoints.get(i).
                                getQuestions().get(j).getDigitPosition()
                                % constantToGetLastNumbers)).toString();

                        waypointString += waypointID
                                + latLang
                                + questionDigit
                                + ";"
                                + waypoints.get(i).getQuestions().get(
                                    j).getQuestion() + ";";
                    }
                    waypointString += ";|";
                }
                out.println(waypointString);
            } else if (request.getParameter("saveQuestions") != null) {

                Map<Integer, List<Question>> questionsMap =
                        new HashMap<Integer, List<Question>>();

                String[] questions =
                        request.getParameter("saveQuestions").split(";");
                for (String question : questions) {

                    String[] questionData = question.split("\\|");

                    int waypointID = Integer.parseInt(questionData[0]);
                    int position = Integer.parseInt(questionData[1]);

                    String questionText = "";

                    if (questionData.length > 2) {
                        questionText = questionData[2];
                    }

                    Question questionTour =
                             new Question(position, questionText, waypointID);

                    if (questionsMap.get(questionTour.getWaypointIdwaypoint())
                            == null) {
                        questionsMap.put(questionTour.getWaypointIdwaypoint(),
                                         new ArrayList<Question>());
                    }
                    questionsMap.get(
                            questionTour.getWaypointIdwaypoint()).add(
                                    questionTour);
                }

                Tour tour = (Tour) session.getAttribute("tour");

                List<Waypoint> waypoints = tour.getTourWaypoints();

                for (int i = 0; i < waypoints.size(); i++) {
                    waypoints.get(i).setQuestions(
                            questionsMap.get(waypoints.get(i).getWaypointId()));
                }

                createTourLogic.editTour(tour.getTourId(), tour);
                out.println("Questions are saved!");
            }
            // Always need to end with this call.
            DatabaseUtils.endDBTransaction(utx, em);
        } finally {
            out.close();
        }
    }

    /**
     * Build DIV.
     * @param waypoints .
     * @return div .
     * @throws ServletException .
     * @throws IOException .
     */
    public final String buildDIV(final String waypoints)
            throws ServletException, IOException {
        String htmlContent = "<div id='dialog-confirm' title='Question'></div><div id='dialog-message' title='Question'></div>";
        String src = "";
        for (int i = 1; i < Integer.parseInt(waypoints) + 1; i++) {
             if (i == 1) {
                        src = "images/start-race-2.png";
                    } else if (i == Integer.parseInt(waypoints)) {
                        src = "images/cup.png";
                    } else {
                        src = "images/number_" + (i - 1) + ".png";
                    }
            htmlContent += "<div style='border:1px solid #336699;text-align: "
                    + "center;'><table style='width:100%;text-align: center;'>"
                    + "<tr><td style='width:10%;text-align: right;'><img src='"
                    + src + "'/></td><td style='width:90%;text-align: center;'>"
                    + "<h3 id='waypointlabel" + i + "'></h3></td></tr>"
                    + "<tr ><td colspan='2' style='width:100%;text-align: "
                    + "center;'><div id='waypoint" + i + "' >" + i
                    + "</div></td></tr></table></div><br/>";
        }
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
