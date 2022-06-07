/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AccountRole;
import model.Parent;
import model.Teacher;

/**
 *
 * @author Admin
 */
public class AccountDAO {

    private Connection connection;
    private PreparedStatement ps;
    private ResultSet rs;

    public List<Parent> getAllParentInfor() {
        List<Parent> list = new ArrayList<>();
        try {
            String sql = "select * from parent";
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Parent t = new Parent(
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
                        rs.getString(11)
                );
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public AccountRole getAllAccount(String email, String password) {
        String sql = "select email, password, role from "
                + "(select email, password, role from Teacher "
                + "union "
                + "select email, password, role from Parent "
                + "union "
                + "select email, password, role from Staff) A "
                + "where A.email = ? and A.password = ?";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                AccountRole ac = new AccountRole(rs.getString(1), rs.getString(2), rs.getString(3));
                return ac;
            }
        } catch (Exception ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public AccountRole checkLoginTeacher(String email, String password) {
        String sql = "select email, password, role from Teacher where email =? and password = ?";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                AccountRole ac = new AccountRole(rs.getString(1), rs.getString(2), rs.getString(3));
                return ac;
            }
        } catch (Exception ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Teacher> getAllTeacherInfor() {
        List<Teacher> list = new ArrayList<>();
        try {
            String sql = "select * from teacher";
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Teacher t = new Teacher(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getBoolean(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10));
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public static void main(String[] args) {
        AccountDAO dao = new AccountDAO();
        AccountRole list = dao.getAllAccount("civilwar@gmail.com", "1234");
        System.out.println(list.getRole());
//        System.out.println(role);

    }
}
