package com.cm.newController;

import com.cm.dao.AdminDao;
import com.cm.dao.LoginDao;
import com.cm.dao.UserDao;
import com.cm.model.Admin;
import com.cm.model.User;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;



@Controller
public class LogInOut {

//    @GetMapping("/hello")
//    public String test(){
//        return "hello";
//    }
//    HttpServletRequest request;
//    HttpServletResponse response;
//    HttpSession session = request.getSession();
    /*
    /log in method
     */
    @RequestMapping(value ="/Login",method= RequestMethod.POST)
    public ModelAndView checkLogin(@RequestParam("name") String name,@RequestParam("password") String password,
                                   HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        ModelAndView mnv=null;
        HttpSession session = request.getSession();
        String n=name;
        String p=password;
        User user = UserDao.getUserByNameAndPassword(n, p);
        Admin admin = AdminDao.getAdminByNameAndPassword(n, p);
        PrintWriter out = response.getWriter();

        if ((n.equals(admin.getUserName()) && p.equals(admin.getPassword()) || AdminDao.validateAdmin(
                n, p))) {
//            mnv=new ModelAndView("viewAdmin");
//            mnv.addObject("name",n);
//            mnv.addObject("admin",admin);
            session.setAttribute("name", n);
            session.setAttribute("admin", admin);
            response.sendRedirect("viewAdmin.jsp?page=1");

        } else if (UserDao.validatePatient(n, p)) {
//            mnv=new ModelAndView("doctorAppointment");
//            mnv.addObject("name",n);
//            mnv.addObject("user",user);
            session.setAttribute("name", n);
            session.setAttribute("user", user);
            // Da bi snimili tekuceg korisnika koji se loguje u tabelu
            LoginDao.save(user);
            response.sendRedirect("doctorAppointment.jsp");
        } else if (UserDao.validateMedic(n, p) && user.getNameOfJob().equals("doctor")) {
//            mnv=new ModelAndView("takeMedication");
//            mnv.addObject("name",n);
//            mnv.addObject("user",user);
            session.setAttribute("name", n);
            session.setAttribute("user", user);
            LoginDao.save(user);
            response.sendRedirect("takeMedication.jsp");
        } else if (UserDao.validateMedic(n, p) && !user.getNameOfJob().equals("doctor")) {
//            mnv=new ModelAndView("medicalStaffTools");
//            mnv.addObject("name",n);
//            mnv.addObject("user",user);
            session.setAttribute("name", n);
            session.setAttribute("user", user);
            LoginDao.save(user);
            response.sendRedirect("medicalStaffTools.jsp");
        }
        else {
            out.print("<p style='text-align: center;color: red;'>Sorry username or password error! If You are not registered please register now!</p>");
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.include(request, response);
        }

        return mnv;

    }
    /*
    /log out method
     */
    @GetMapping("/Logout")
    public void logout(HttpServletRequest request,
                       HttpServletResponse response)throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String name=(String)session.getAttribute("name");


        if (name != null) {

//            out.print("<h2 style='text-align: center;color: green;'>" + name
//                    + ", you are successfully logged out!</h2>");


            String success=name+" log out successfully";
            session.setAttribute("success",success);
            response.sendRedirect("index.jsp");
//            session.invalidate();
//            mnv=new ModelAndView("index");
//            return mnv;
//            request.getRequestDispatcher("index.jsp")
//                    .include(request, response);
        } else {
//            out.print("<h2 style='text-align: center;color: red;'>Please login first!</h2>");
            String error=" log in first";
            session.setAttribute("error",error);
            response.sendRedirect("index.jsp");
//            request.getRequestDispatcher("index.jsp")
//                    .include(request, response);
        }

        out.close();
    }

    @GetMapping("/LogoutAdmin")
    public void adminLogOut(HttpServletRequest request,HttpServletResponse response) throws IOException{
        HttpSession session=request.getSession();
        String name=(String) session.getAttribute("name");

        if (name != null) {
            String success=name+" log out successfully";
            session.setAttribute("success",success);
            response.sendRedirect("index.jsp");


        } else {
            String error=" log in first";
            session.setAttribute("error",error);
            response.sendRedirect("index.jsp");

        }

    }


    @PostMapping("/Administration")
    public void administration(HttpServletRequest request,
                               HttpServletResponse response)throws IOException,ServletException {
        PrintWriter out = response.getWriter();

        String n = request.getParameter("name");
        String p = request.getParameter("password");

        Admin admin = AdminDao.getAdminByNameAndPassword(n, p);

        HttpSession session = request.getSession();

        if ((n.equals("admin") && p.equals("admin"))
                || AdminDao.validateAdmin(n, p)) {
            session.setAttribute("name", n);
            session.setAttribute("admin", admin);
            response.sendRedirect("viewAdmin.jsp?page=1");
        } else if (!AdminDao.validateAdmin(n, p)) {
            out.print("<p style='color: red;text-align: center;'>Sorry admin NOT exist, please register Administration user!</p>");
            request.getRequestDispatcher("addAdmin.jsp").include(request,
                    response);
        } else {
            out.print("<p style='color: red;text-align: center;'>Sorry, username or password error! Please repeat entry!</p>");
            request.getRequestDispatcher("adminLogin.jsp").include(request,
                    response);
        }
        out.close();
    }

}
