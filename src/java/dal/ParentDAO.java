/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Parent;

/**
 *
 * @author win
 */
public class ParentDAO extends DBContext {

    private Connection connection;
    private PreparedStatement st;
    private ResultSet rs;

    public ArrayList<Parent> getAll() {
        ArrayList<Parent> parents = new ArrayList<>();
        try {
            String sql = "SELECT [parent_id]\n"
                    + "      ,[first_name]\n"
                    + "      ,[last_name]\n"
                    + "      ,[gender]\n"
                    + "      ,[email]\n"
                    + "      ,[password]\n"
                    + "      ,[dob]\n"
                    + "      ,[phone_number]\n"
                    + "      ,[address]\n"
                    + "      ,[img]\n"
                    + "      ,[role]\n"
                    + "  FROM [Parent]";
            connection = new DBContext().getConnection();
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Parent p = new Parent();
                p.setParent_id(rs.getInt("parent_id"));
                p.setFirst_name(rs.getString("first_name"));
                p.setLast_name(rs.getString("last_name"));
                p.setGender(rs.getBoolean("gender"));
                p.setEmail(rs.getString("email"));

                p.setPassword(rs.getString("password"));
                p.setDob(rs.getString("dob"));
                p.setPhone(rs.getString("phone_number"));
                p.setAddress(rs.getString("address"));
                p.setImg(rs.getString("img"));
                p.setImg(rs.getString("role"));
                parents.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ParentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ParentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return parents;
    }

    public void insertParent(Parent parent) {
        try {
            String sql = "INSERT INTO [Parent]\n"
                    + "           ([first_name]\n"
                    + "           ,[last_name]\n"
                    + "           ,[gender]\n"
                    + "           ,[email]\n"
                    + "           ,[password]\n"
                    + "           ,[dob]\n"
                    + "           ,[phone_number]\n"
                    + "           ,[address]\n"
                    + "           ,[img]\n"
                    + "           ,[role])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?,?,?,?,?,?)";
            connection = new DBContext().getConnection();
            st = connection.prepareStatement(sql);
            st.setString(1, parent.getFirst_name());
            st.setString(2, parent.getLast_name());
            st.setBoolean(3, parent.isGender());
            st.setString(4, parent.getEmail());
            st.setString(5, parent.getPassword());
            st.setString(6, parent.getDob());
            st.setString(7, parent.getPhone());
            st.setString(8, parent.getAddress());
            st.setString(9, parent.getImg());
            st.setString(10, parent.getRole());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ParentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ParentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateParent(Parent parent) {
        try {
            String sql = "UPDATE [Parent]\n"
                    + "   SET [first_name] = ?\n"
                    + "      ,[last_name] = ?\n"
                    + "      ,[gender] = ?\n"
                    + "      ,[email] = ?\n"
                    + "      ,[password] = ?\n"
                    + "      ,[dob] = ?\n"
                    + "      ,[phone_number] = ?\n"
                    + "      ,[address] = ?\n"
                    + "      ,[img] = ?\n"
                    + "      ,[role] = ?\n"
                    + " WHERE parent_id = ?";
            connection = new DBContext().getConnection();
            st = connection.prepareStatement(sql);
            st.setInt(11, parent.getParent_id());
            st.setString(1, parent.getFirst_name());
            st.setString(2, parent.getLast_name());
            st.setBoolean(3, parent.isGender());
            st.setString(4, parent.getEmail());
            st.setString(5, parent.getPassword());
            st.setString(6, parent.getDob());
            st.setString(7, parent.getPhone());
            st.setString(8, parent.getAddress());
            st.setString(9, parent.getImg());
            st.setString(10, parent.getRole());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ParentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ParentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteParent(int id) {
        try {
            String sql = "DELETE FROM [Parent]\n"
                    + "      WHERE parent_id = ?";
            connection = new DBContext().getConnection();
            st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ParentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ParentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Parent getParentByMail(String mail, String password) {
        String sql = "select * from parent where email = ? and password = ?";
        try {
            connection = new DBContext().getConnection();
            st = connection.prepareStatement(sql);
            st.setString(1, mail);
            st.setString(2, password);
            rs = st.executeQuery();
            while (rs.next()) {
                Parent p = new Parent();
                p.setParent_id(rs.getInt("parent_id"));
                p.setFirst_name(rs.getString("first_name"));
                p.setLast_name(rs.getString("last_name"));
                p.setGender(rs.getBoolean("gender"));
                p.setEmail(rs.getString("email"));

                p.setPassword(rs.getString("password"));
                p.setDob(rs.getString("dob"));
                p.setPhone(rs.getString("phone_number"));
                p.setAddress(rs.getString("address"));
                p.setImg(rs.getString("img"));
                p.setImg(rs.getString("role"));
                return p;
            }
        } catch (Exception e) {

        }
        return null;
    }

    public static void main(String[] args) {
        ParentDAO pd = new ParentDAO();
//        List<Parent> list = pd.getAll();
//        for (Parent p : list) {
//            System.out.println(p.getFirst_name());
//        }
//        pd.deleteParent(6);
//        Parent p = new Parent(1, "Tuấn", "Trần", true, "tuan@gmail.com", "1234", "10/20/2002", "094718444", "Hà Nội", null, "parent");
//        pd.updateParent(p);
        Parent p = pd.getParentByMail("tuan@gmail.com", "1234");
        System.out.println(p.toString());
    }
}