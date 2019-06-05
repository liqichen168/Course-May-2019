package com.cm.controller;

import com.cm.dao.PatientCheckIODao;
import com.cm.dao.UserDao;
import com.cm.model.Hospital;
import com.cm.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
//@WebServlet("/hospitalBedcheck")
public class hospitalBedcheck extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public hospitalBedcheck() {
        super();
    }
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.setContentType("text/html");//解决文字乱码问题

        HttpSession session = request.getSession(false);

        String flag=request.getParameter("checkout");
        int positionNum=Integer.parseInt(request.getParameter("positionNum"));
        if(!flag.equals("true")){
            int userid=Integer.parseInt(request.getParameter("userid"));
            User user= UserDao.getUserById(userid);
            PatientCheckIODao.addPatientBed(user.getUserID(),user.getUserName());
            positionNum--;
            List<User> patientList= PatientCheckIODao.getAllPatient();
            for(User u : patientList){
                if(PatientCheckIODao.checkbed(u.getUserID())){
                    u.setFlag(true);
                }else
                    u.setFlag(false);
                if(u==user){
                    u.setFlag(true);
                }
            }
            if(positionNum<=0){
                session.setAttribute("full",true);
            }else
                session.setAttribute("full",false);
            session.setAttribute("userList",patientList);
            session.setAttribute("positionNum",positionNum);
            List<Hospital> hList=PatientCheckIODao.getAllbed();
            session.setAttribute("bedList",hList);
            response.sendRedirect("patientCheckIO.jsp");
        }else{
            int id=Integer.parseInt(request.getParameter("userid"));
            PatientCheckIODao.checkout(id);
            List<User> patientList= PatientCheckIODao.getAllPatient();
            positionNum++;
            for(User u:patientList){
                if(PatientCheckIODao.checkbed(u.getUserID())){
                    u.setFlag(true);
                }else
                    u.setFlag(false);
                if(u.getUserID()==id){
                    u.setFlag(false);
                }
            }
            if(positionNum<=0){
                session.setAttribute("full",true);
            }else
                session.setAttribute("full",false);
            session.setAttribute("userList",patientList);
            session.setAttribute("positionNum",positionNum);
            List<Hospital> hList=PatientCheckIODao.getAllbed();
            session.setAttribute("bedList",hList);
            response.sendRedirect("patientCheckIO.jsp");

        }

//       }



    }

}
