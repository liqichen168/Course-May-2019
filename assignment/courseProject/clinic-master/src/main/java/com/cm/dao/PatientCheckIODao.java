package com.cm.dao;

import com.cm.model.Hospital;
import com.cm.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientCheckIODao {
//    public int bedNum=5;
    public static List<User> getAllPatient(){
        List<User> pList=new ArrayList<User>();

        try {
            Connection con=ConnectionDao.getConnection();
            PreparedStatement ps=con.prepareStatement("select * FROM tblusers where TypeOfUsers='patient'");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                User user=new User();
                user.setUserID(rs.getInt(1));
                user.setUserName(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setNameAndSurname(rs.getString(4));
                user.setAddress(rs.getString(5));
                user.setPhoneNumber(rs.getString(6));
                user.setEmail(rs.getString(7));
                user.setNumberOfIDCard(rs.getString(8));
                user.setBloodType(rs.getString(9));
                user.setSex(rs.getString(10));
                user.setNameOfJob(rs.getString(11));
                user.setTypeOfUsers(rs.getString(12));
                pList.add(user);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pList;
    }
    public static boolean checkbed(int userid){
        boolean flag=true;
        try {
            Connection con=ConnectionDao.getConnection();
            PreparedStatement ps=con.prepareStatement("select * FROM hospital where userid=?");
            ps.setInt(1,userid);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                flag=true;
            }else
                flag=false;
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
    public static List<Hospital> getAllbed(){
        List<Hospital> hList=new ArrayList<Hospital>();
        try {
            Connection con=ConnectionDao.getConnection();
            PreparedStatement ps=con.prepareStatement("select * FROM hospital");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Hospital hp=new Hospital();
                hp.setUserID(rs.getInt("userID"));
                hp.setUsername(rs.getString("username"));
                hList.add(hp);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hList;
    }
    public static void addPatientBed(int id,String name){
        try {
            Connection con=ConnectionDao.getConnection();
            PreparedStatement ps=con.prepareStatement("insert into hospital (`userID`, `username`) VALUES (?, ?) ");
            ps.setInt(1,id);
            ps.setString(2,name);
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void checkout(int id){
        try {
            Connection con=ConnectionDao.getConnection();
            PreparedStatement ps=con.prepareStatement("delete from hospital where userid=?");
            ps.setInt(1,id);
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

//    public static int paitenctChechkIn(User user){
//        int changeline=0;
//        try {
//            Connection con = ConnectionDao.getConnection();
//            PreparedStatement ps = con
//                    .prepareStatement("insert into tblpatientCheck(UserID,UserName,Password,NameAndSurname,Address,PhoneNumber,Email,NumberOfIdCard,BloodType,Sex,NameOfJob,TypeOfUsers) values (?,?,?,?,?,?,?,?,?,?,?,?)");
//            ps.setInt(1, user.getUserID());
//            ps.setString(2, user.getUserName());
//            ps.setString(3, user.getPassword());
//            ps.setString(4, user.getNameAndSurname());
//            ps.setString(5, user.getAddress());
//            ps.setString(6, user.getPhoneNumber());
//            ps.setString(7, user.getEmail());
//            ps.setString(8, user.getNumberOfIDCard());
//            ps.setString(9, user.getBloodType());
//            ps.setString(10, user.getSex());
//            ps.setString(11, user.getNameOfJob());
//            ps.setString(12, user.getTypeOfUsers());
//            changeline=ps.executeUpdate();
//            ps.close();
//            con.close();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return  changeline;
//    }

    public static void main(String[] args){
        List<User> test=new ArrayList<User>();
        test=PatientCheckIODao.getAllPatient();
//        for(User u:test){
//            System.out.println(u.getUserID()+"\t"+u.getUserName()+"\t"+u.getAddress());
//        }
//        User user=new User();
//        user.setUserID(20);
//        user.setAddress("asdas");
//        user.setBloodType("O");
//        user.setEmail("asd@gmail.com");
//        user.setUserName("Patient2");
//        user.setNameAndSurname("asasasa");
//        user.setSex("male");
//        user.setNameOfJob("stu");
//        user.setNumberOfIDCard("12312");
//        user.setPassword("asdczx");
//        user.setTypeOfUsers("patient");
//        user.setPhoneNumber("121314141");
//        PatientCheckIODao.paitenctChechkIn(user);
    }

}
