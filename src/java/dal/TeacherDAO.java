/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Kinder_Class;
import model.Parent;
import model.Teacher;

/**
 *
 * @author Admin
 */
public class TeacherDAO {

    private Connection connection;
    private PreparedStatement ps;
    private ResultSet rs;
    
    

    public List<Teacher> getAllTeacherInfor() {
        List<Teacher> list = new ArrayList<>();
        try {
            String sql = "select * from teacher";
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Teacher t = new Teacher(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getBoolean(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11));
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public static void main(String[] args) {
        TeacherDAO dao = new TeacherDAO();
        List<Teacher> list = dao.getAllTeacherInfor();
        for (Teacher teacher : list) {
            System.out.println(teacher.toString());
        }
    }
}
