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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Teacher_Record;

/**
 *
 * @author win
 */
public class TeacherRecordDAO extends DBContext{
     private Connection connection;
    private PreparedStatement st;
    private ResultSet rs;

    public ArrayList<Teacher_Record> getAllRecord() {
        ArrayList<Teacher_Record> records = new ArrayList<>();
        try {
            String sql = "";
            connection = new DBContext().getConnection();
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Teacher_Record tr = new Teacher_Record();

//                t.setActivity_id(rs.getInt("activity_id"));
//                t.setAct_description(rs.getString("act_description"));
//                t.setAct_name(rs.getString("act_name"));
                records.add(tr);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ActivityDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ActivityDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return records;
    }
    
}
