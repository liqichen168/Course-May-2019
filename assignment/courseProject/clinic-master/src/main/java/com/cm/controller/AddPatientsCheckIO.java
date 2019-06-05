package com.cm.controller;

import com.cm.dao.PatientCheckIODao;
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

@WebServlet("/AddPatientsCheckIO")

public class AddPatientsCheckIO extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddPatientsCheckIO() {
        super();
    }
    protected void doPost(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.setContentType("text/html");//解决文字乱码问题
        PrintWriter out = response.getWriter();//这个方法直接输出数据，不需要刷新页面
        HttpSession session = request.getSession(false);

        List<User> patientList= PatientCheckIODao.getAllPatient();


        int totalNum=5;//you can set up total number here.



        int positionNum=0;

        List<Hospital> hList=PatientCheckIODao.getAllbed();
        positionNum=totalNum-hList.size();
        for(User u : patientList){
            if(PatientCheckIODao.checkbed(u.getUserID())){
                u.setFlag(true);
            }else
                u.setFlag(false);

        }

        if(positionNum<=0){
            session.setAttribute("full",true);
        }else
            session.setAttribute("full",false);
        session.setAttribute("positionNum",positionNum);
        session.setAttribute("userList",patientList);
        session.setAttribute("bedList",hList);

        response.sendRedirect("patientCheckIO.jsp");

//        if(request.getAttribute("userid")==null){
//            response.sendRedirect("patientCheckIO.jsp");
//        }else{
//            HttpSession session2 = request.getSession(false);
//            int userid=Integer.parseInt(request.getParameter("userid"));
//            System.out.println(userid);
//            session2.setAttribute("userid",userid);
//            response.sendRedirect("patientCheckIO.jsp");
//        }



    }


}
