/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDAO;
import dal.ClassDAO;
import dal.ParentDAO;
import dal.StaffDAO;
import dal.TeacherDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.AccountRole;
import model.Kinder_Class;

import model.Parent;
import model.Staff;
import model.Teacher;

/**
 *
 * @author Admin
 */
public class LoginServlet extends HttpServlet {

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
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
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
        String action = request.getParameter("action");
        if (action != null) {
            HttpSession session = request.getSession(true);
            session.removeAttribute("account");
            session.removeAttribute("teacher");
            session.removeAttribute("kinder_class");
            session.removeAttribute("present_kids");

            response.sendRedirect("login");
        } else {
            request.getRequestDispatcher("login.jsp").forward(request, response);
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
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        HttpSession session = request.getSession(true);

        AccountDAO d = new AccountDAO();

        AccountRole acc = d.getAllAccount(email, password);
        try ( PrintWriter out = response.getWriter()) {
//            out.println(accs);
            if (acc == null) {
                out.print("nope");
                request.setAttribute("loginError", "Incorrect password");
//            response.sendRedirect("login.jsp");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                switch (acc.getRole()) {
                    case "parent":
                        ParentDAO pd = new ParentDAO();
                        Parent p = pd.getParentByMail(email, password);
                        session.setAttribute("parent", p);
                        request.getRequestDispatcher("parent.jsp").forward(request, response);
                        break;
                    case "teacher":
                        ClassDAO classDao = new ClassDAO();
                        TeacherDAO teacherDao = new TeacherDAO();
                        List<Teacher> list = teacherDao.getAllTeacherInfor();
                        for (Teacher teacher : list) {
                            if (teacher.getEmail().equals(email) && teacher.getPassword().equals(password)) {
                                Kinder_Class kc = classDao.getTeacherClass(teacher.getTeacher_id());
                                session.setAttribute("teacher", teacher);
                                session.setAttribute("kinder_class", kc);
                            }
                        }
                        session.setAttribute("account", acc);
                        response.sendRedirect("loadteacherhome");
                        break;
                    case "admin":
                        StaffDAO sd = new StaffDAO();
                        Staff s = sd.searchStaffByMail(email, password);

                        session.setMaxInactiveInterval(30 * 60);
                        session.setAttribute("staff", s);
//                    response.sendRedirect("adminpage");
//                    request.getRequestDispatcher("staff/adminpage.jsp").forward(request, response);
                        response.sendRedirect("staff/adminpage.jsp");
                }

//                if (acc != null && acc.getRole().equals("teacher")) {
//                    ClassDAO classDao = new ClassDAO();
//                    TeacherDAO teacherDao = new TeacherDAO();
//                    List<Teacher> list = teacherDao.getAllTeacherInfor();
//                    for (Teacher teacher : list) {
//                        if (teacher.getEmail().equals(email) && teacher.getPassword().equals(password)) {
//                            Kinder_Class kc = classDao.getTeacherClass(teacher.getTeacher_id());
//                            session.setAttribute("teacher", teacher);
//                            session.setAttribute("kinder_class", kc);
//                        }
//                    }
//                    session.setAttribute("account", acc);
//                    response.sendRedirect("loadteacherhome");
//                } else {
//                    request.setAttribute("error", "Account do not exist");
//                    request.getRequestDispatcher("login.jsp").forward(request, response);
//                }
            }

        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
