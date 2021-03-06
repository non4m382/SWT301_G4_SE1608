/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.teacher;

import dal.AttendanceDAO;
import dal.StudentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.AccountRole;
import model.Attendence;
import model.Kinder_Class;
import model.Kindergartner;
import model.Teacher;

/**
 *
 * @author Windows 10 TIMT
 */
public class attendanceServlet extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet attendanceServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet attendanceServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        AccountRole acc = (AccountRole) session.getAttribute("account");
        Kinder_Class kc = (Kinder_Class) session.getAttribute("kinder_class");
        try ( PrintWriter out = response.getWriter()) {
            if (acc != null) {
                StudentDAO stuDAO = new StudentDAO();
                int classID = kc.getClass_id();
                out.println(classID);
                List<Kindergartner> listStu = stuDAO.getKidsByClass(classID);
                request.setAttribute("listStudent", listStu);
                request.getRequestDispatcher("teacher/checkin.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Do you want to create an account?");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Kinder_Class kinder_class = (Kinder_Class) session.getAttribute("kinder_class");
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        String action = request.getParameter("action");
        try ( PrintWriter out = response.getWriter()) {
            StudentDAO studao = new StudentDAO();
            AttendanceDAO attdao = new AttendanceDAO();
            List<Kindergartner> list = studao.getKidsByClass(kinder_class.getClass_id());
            List<Attendence> present_kids = new ArrayList<>();
            String date = java.time.LocalDate.now().toString();
            for (int i = 0; i < list.size(); i++) {
                Integer check = Integer.parseInt(request.getParameter("checkAttendence" + list.get(i).getKinder_id()));
                if (action.equalsIgnoreCase("check_in")) {
                    Attendence attendance = null;
                    if (check == 1) {
                        attendance = new Attendence(list.get(i).getKinder_id(), date, 1, teacher.getTeacher_id());
                    } else if (check == 0) {
                        attendance = new Attendence(list.get(i).getKinder_id(), date, 0, teacher.getTeacher_id());
                    }
                    if (attdao.checkAttendance(attendance) != null) {
                        attdao.updateAttendanceInfor(attendance);
                    } else {
                        attdao.insertAttendanceInfor(attendance);
                    }
                    present_kids.add(attendance);
                }
            }
            session.setAttribute("present_kids", present_kids);
            response.sendRedirect("attendance");
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
