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
import model.Staff;

/**
 *
 * @author Admin
 */
public class StaffDAO extends DBContext {

    private Connection connection;
    private PreparedStatement st;
    private ResultSet rs;

    public List<Staff> getAll() {
        String sql = "select * from Staff";
        List<Staff> list = new ArrayList<>();
        try {
            connection = new DBContext().getConnection();
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
//                System.out.println("1");
                Staff s = new Staff();
                s.setStaff_id(rs.getInt("staff_id"));
                s.setFirst_name(rs.getString("first_name"));
                s.setLast_name(rs.getString("last_name"));
                s.setGender(rs.getBoolean("gender"));
                s.setEmail(rs.getString("email"));
                s.setPassword(rs.getString("password"));
                s.setDob(rs.getString("dob"));
                s.setPhone(rs.getString("phone_number"));
                s.setAddress(rs.getString("address"));
                s.setImg(rs.getString("img"));
                s.setRole(rs.getString("role"));
                list.add(s);
            }
        } catch (Exception e) {

        }
        return list;
    }

    public void insertStaff(Staff s) {
        String sql = "insert into Staff\n"
                + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            connection = new DBContext().getConnection();
            st = connection.prepareStatement(sql);
            st.setString(1, s.getFirst_name());
            st.setString(2, s.getLast_name());
            st.setString(3, s.getPhone());
            st.setString(4, s.getAddress());
            st.setString(5, s.getPassword());
            st.setBoolean(6, s.isGender());
            st.setString(7, s.getEmail());
            st.setString(8, s.getDob());
            st.setString(9, s.getImg());
            st.setString(10, s.getRole());
            rs = st.executeQuery();
        } catch (Exception e) {

        }
    }

    public void updateStaff(Staff s) {
        String sql = "update staff\n"
                + "set first_name = ?, last_name = ?, phone_number = ?, address = ?, password = ?,\n"
                + "gender = ?, email = ?, dob = ?, img = ?, role = ?\n"
                + "where staff_id = ?";
        try {
            connection = new DBContext().getConnection();
            st = connection.prepareStatement(sql);
            st.setString(1, s.getFirst_name());
            st.setString(2, s.getLast_name());
            st.setString(3, s.getPhone());
            st.setString(4, s.getAddress());
            st.setString(5, s.getPassword());
            st.setBoolean(6, s.isGender());
            st.setString(7, s.getEmail());
            st.setString(8, s.getDob());
            st.setString(9, s.getImg());
            st.setString(10, s.getRole());
            st.setInt(11, s.getStaff_id());
            rs = st.executeQuery();
        } catch (Exception e) {

        }
    }

    public void deleteStaff(int id) {
        String sql = "delete staff \n"
                + "where staff_id = ?";
        try {
            connection = new DBContext().getConnection();
            st = connection.prepareStatement(sql);

            st.setInt(1, id);
            rs = st.executeQuery();
        } catch (Exception e) {

        }
    }

    public Staff searchStaffByMail(String mail, String pass) {
        String sql = "select * from Staff where email = ? and password = ?";
        try {
            connection = new DBContext().getConnection();
            st = connection.prepareStatement(sql);
            st.setString(1, mail);
            st.setString(2, pass);
            rs = st.executeQuery();
            while (rs.next()) {
//                System.out.println("1");
                Staff s = new Staff();
                s.setStaff_id(rs.getInt("staff_id"));
                s.setFirst_name(rs.getString("first_name"));
                s.setLast_name(rs.getString("last_name"));
                s.setGender(rs.getBoolean("gender"));
                s.setEmail(rs.getString("email"));
                s.setPassword(rs.getString("password"));
                s.setDob(rs.getString("dob"));
                s.setPhone(rs.getString("phone_number"));
                s.setAddress(rs.getString("address"));
                s.setImg(rs.getString("img"));
                s.setRole(rs.getString("role"));
                return s;
            }
        } catch (Exception e) {

        }
        return null;
    }

    public static void main(String[] args) {
        StaffDAO sd = new StaffDAO();
        List<Staff> list = sd.getAll();
//        for (Staff s : list) {
//            System.out.println(s.getFirst_name());
//        }
//        System.out.println(list.get(0).getDob());
//        sd.insertStaff(new Staff(1, "Đm", "Khôi", true, "dmkhoi@gmail.com", "1234", "10/20/2002", "0948484", "Ha Noi", null, "admin"));
        sd.updateStaff(new Staff(2, "Đm", "Khôi", true, "dmkhoi@gmail.com", "1234", "10/20/2002", "0948484", "Ha Noi", null, "admin"));
//        sd.deleteStaff(3);
        Staff s = sd.searchStaffByMail("dmkhoi@gmail.com", "1234");
        System.out.println(s.getFirst_name() + s.getLast_name());
    }
}