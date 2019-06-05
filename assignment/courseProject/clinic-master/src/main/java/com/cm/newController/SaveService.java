package com.cm.newController;

import com.cm.dao.*;
import com.cm.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class SaveService {
    @RequestMapping(value="/SaveAdmin",method = RequestMethod.POST)
    public void saveAdmin(HttpServletRequest request,
                                  HttpServletResponse response, @RequestParam("nameAndSurname") String nameAndSurname,
                                  @RequestParam("userName") String name, @RequestParam("password") String password,
                                  @RequestParam("repassword")String repassword) throws ServletException,IOException {
        HttpSession session=request.getSession();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
//        ModelAndView mnv=null;
        if (password.equals(repassword) && !AdminDao.validateAdminExist(name)) {

            Admin a = new Admin();
            a.setNameAndSurname(nameAndSurname);
            a.setUserName(name);
            a.setPassword(password);

            int status = AdminDao.save(a);
            if (status > 0) {
                 response.sendRedirect("login.html");
            } else {
                session.setAttribute("error","cannot save records");
                response.sendRedirect("index.jsp");
            }

        } else {
            out.print("<p style='color: red;text-align: center;'>Sorry, your password and repassword is not same or Admin already exist! Please repeat registration or exit application!</p>");

            request.getRequestDispatcher("addAdmin.html").include(request,
                    response);
//            response.sendRedirect("addAdmin.html");
        }

        out.close();
    }

    @GetMapping("/SaveDoctorAppointment")
    public void saveDoctorAppointment(HttpServletRequest request,
                                      HttpServletResponse response,@RequestParam("nameOfDoctor")String nameOfDoctor,
                                      @RequestParam("dateOfScheduling")String dateOfScheduling) throws ServletException, IOException{
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (user == null) {
            out.print("<p style='text-align: center;color: red;'>Sorry, you must login first!</p>");
            request.getRequestDispatcher("login.html").include(request,
                    response);
            // response.sendRedirect("login.html");
        }

        MedicalDepartment department = (MedicalDepartment) session
                .getAttribute("medicalDepartment");

        String splitPattern = " ";
        String splitPattern02 = "-";
        String[] dateOfSchedulingForm = dateOfScheduling.split(splitPattern);
        String string01 = dateOfSchedulingForm[0];
        String[] dateOfSchedulingForm02 = string01.split(splitPattern02);

        String dateOfSchedulingConcat = dateOfSchedulingForm02[0] + "-"
                + dateOfSchedulingForm02[1] + "-" + dateOfSchedulingForm02[2]
                + " " + dateOfSchedulingForm[1];

        User medicalStaffUser = UserDao.getUserByNameAndSurname(nameOfDoctor);
        MedicalStaff medicalStaff = MedicalStaffDao
                .getMedicalStaffByUserIDAndMedicalDepartmentID(
                        medicalStaffUser.getUserID(),
                        department.getMedicalDepartmentID());

        DateOfScheduling dateOfSchedulingObject = DateOfSchedulingDao
                .getDateOfSchedulingObjectByDateOfSchedulingAndMedicalDepartmentID(
                        dateOfSchedulingConcat,
                        department.getMedicalDepartmentID());


        int statusUpdateScheduledTo1 = DateOfSchedulingDao
                .updateScheduledTo1(dateOfSchedulingObject
                        .getDateOfSchedulingID());
        int statusSaveDoctorAppointment = DoctorAppointmentDao.save(
                user.getUserID(), medicalStaff.getMedicalStaffID(),
                dateOfSchedulingObject.getDateOfSchedulingID());

        if (statusUpdateScheduledTo1 > 0 && statusSaveDoctorAppointment > 0) {
//			out.print("<p style='color: green;'>Doctor Appointment saved successfully!</p>");
//			request.getRequestDispatcher("doctorAppointmentSuccess.jsp")
//					.include(request, response);
            session.setAttribute("user", user);
            session.setAttribute("department", department);
            session.setAttribute("dateOfScheduling", dateOfSchedulingObject);
            session.setAttribute("doctor", medicalStaffUser);
//			request.getRequestDispatcher("doctorAppointmentSuccess.jsp").include(
//					request, response);
            response.sendRedirect("doctorAppointmentSuccess.jsp");
        } else {
//            out.println("<p style='color: red;'>Sorry, unable to save Doctor Appointment! Please try again!</p>");
            session.setAttribute("user", user);
            session.setAttribute("error","cannot save appointment!");
            response.sendRedirect("doctorAppointment.jsp");
        }

    }

    @PostMapping("/SaveUser")
    public void saveUser(HttpServletRequest request,
                         HttpServletResponse response,@RequestParam("userName") String name,
                         @RequestParam("password")String password,@RequestParam("repassword")String repassword,
                         @RequestParam("nameAndSurname")String nameAndSurname,@RequestParam("address")String address,
                         @RequestParam("phoneNumber")String phoneNumber,@RequestParam("email")String email,
                         @RequestParam("numberOfIdCard")String numberOfIdCard,@RequestParam("bloodType")String bloodType,
                         @RequestParam("sex")String sex, @RequestParam("typeOfUsers")String typeOfUsers,
                         @RequestParam("medicalDepartment")String medicalDepartment,@RequestParam("nameOfJob")String nameOfJob
                         )
    throws ServletException,IOException{
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

//        String medicalDepartment = request.getParameter("medicalDepartment");
//        String nameOfJob = request.getParameter("nameOfJob");
        if (UserDao.validateByUserNameExist(name)
                && typeOfUsers.equals("patient")) {
            out.println("<p style='color: red;text-align: center;'>User name for patient exist, please choice another User name for registration!</p>");
            request.getRequestDispatcher("addUserPatient.html").include(
                    request, response);
        } else if (UserDao.validateByUserNameExist(name)
                && typeOfUsers.equals("medic")) {
            out.println("<p style='color: red;text-align: center;'>User name for medic exist, please choice another User name for registration!</p>");
            request.getRequestDispatcher("addUserMedic.html").include(request,
                    response);
        } else if (password.equals(repassword)) {

            // Kreiranje Usera
            User u = new User();
            u.setUserName(name);
            u.setPassword(password);
            u.setNameAndSurname(nameAndSurname);
            u.setAddress(address);
            u.setPhoneNumber(phoneNumber);
            u.setEmail(email);
            u.setNumberOfIDCard(numberOfIdCard);
            u.setBloodType(bloodType);
            u.setSex(sex);
            u.setNameOfJob(nameOfJob);
            u.setTypeOfUsers(typeOfUsers);

            int status = UserDao.save(u);

            // Izvlacenje informacije iz baze o Medical Departmentu
            MedicalDepartment department = MedicalDeparmentDao
                    .getMedicalDepartmentObjectByName(medicalDepartment);

            // Izvlacenje informacija iz baze o Useru
            User newUser = UserDao.getUserByNameAndPassword(name, password);

            // Kreiranje Medical Staffa
            MedicalStaff staff = new MedicalStaff();
            staff.setUser(newUser);
            staff.setMedicalDepartment(department);

            int statusForMedicalStaff = 0;

            if (typeOfUsers.equals("medic")) {
                statusForMedicalStaff = MedicalStaffDao.save(staff);
            }

            if (status > 0) {
                switch (typeOfUsers) {
                    case "patient":
                        out.print("<p style='color: green;text-align: center;'>Registration for patient saved successfully! Please continue with login!</p>");
                        request.getRequestDispatcher("login.html").include(request,
                                response);
                        break;
                    case "medic":
                        if (statusForMedicalStaff > 0) {

                            out.print("<p style='color: green;text-align: center;'>Registration for medic saved successfully! Please continue with login!</p>");
                            request.getRequestDispatcher("login.html").include(
                                    request, response);
                        } else {
                            out.println("<p style='color: red;text-align: center;'>Sorry, unable to save record! Please check your database connection!</p>");
                            request.getRequestDispatcher("index.jsp").include(
                                    request, response);
                        }
                        break;
                    default:
                        out.println("<p style='color: red;text-align: center;'>Registration saved successfully! Please continue with login!</p>");
                        request.getRequestDispatcher("index.jsp").include(request,
                                response);
                        break;
                }
            } else {
                out.println("<p style='color: red;text-align: center;'>Sorry, unable to save record! Please check your database connection!</p>");
                request.getRequestDispatcher("index.jsp").include(request,
                        response);
            }

        } else {
            switch (typeOfUsers) {
                case "patient":
                    out.print("<p style='color: red;text-align: center;'>Sorry, your password and repassword is not same! Please repeat the registration!</p>");
                    request.getRequestDispatcher("addUserPatient.html").include(
                            request, response);
                    break;
                case "medic":
                    out.print("<p style='color: red;text-align: center;'>Sorry, your password and repassword is not same! Please repeat the registration!</p>");
                    request.getRequestDispatcher("addUserMedic.jsp").include(
                            request, response);
                    break;
                default:
                    out.println("<p style='color: red;text-align: center;'>Sorry, your password and repassword is not same! Please repeat the registration!</p>");
                    request.getRequestDispatcher("index.jsp").include(request,
                            response);
                    break;
            }
        }

        out.close();

    }

    @PostMapping("/SaveUserAdmin")
    public void saveUserAdmin(HttpServletRequest request,
                         HttpServletResponse response,@RequestParam("userName") String name,
                         @RequestParam("password")String password,@RequestParam("repassword")String repassword,
                         @RequestParam("nameAndSurname")String nameAndSurname,@RequestParam("address")String address,
                         @RequestParam("phoneNumber")String phoneNumber,@RequestParam("email")String email,
                         @RequestParam("numberOfIdCard")String numberOfIdCard,@RequestParam("bloodType")String bloodType,
                         @RequestParam("sex")String sex, @RequestParam("typeOfUsers")String typeOfUsers,
                         @RequestParam("medicalDepartment")String medicalDepartment,@RequestParam("nameOfJob")String nameOfJob
    )
            throws ServletException,IOException{
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        if (UserDao.validateByUserNameExist(name)
                && typeOfUsers.equals("patient")) {
            out.println("<p style='color: red;text-align: center;'>User name for patient exist, please choice another User name for registration!</p>");
            request.getRequestDispatcher("addUserPatient.html").include(
                    request, response);
        } else if (UserDao.validateByUserNameExist(name)
                && typeOfUsers.equals("medic")) {
            out.println("<p style='color: red;text-align: center;'>User name for medic exist, please choice another User name for registration!</p>");
            request.getRequestDispatcher("addUserMedic.html").include(request,
                    response);
        } else if (password.equals(repassword)) {

            User u = new User();
            u.setUserName(name);
            u.setPassword(password);
            u.setNameAndSurname(nameAndSurname);
            u.setAddress(address);
            u.setPhoneNumber(phoneNumber);
            u.setEmail(email);
            u.setNumberOfIDCard(numberOfIdCard);
            u.setBloodType(bloodType);
            u.setSex(sex);
            u.setNameOfJob(nameOfJob);
            u.setTypeOfUsers(typeOfUsers);

            int status = UserDao.save(u);
            if (status > 0) {
                out.print("<p style='color: lightblue;'>Registration saved successfully!</p>");
//				request.getRequestDispatcher("viewAdmin.jsp?page=1").include(
//						request, response);
                response.sendRedirect("viewAdmin.jsp?page=1");
            } else {
                out.println("<p style='color: red;'>Sorry, unable to save record!</p>");
                request.getRequestDispatcher("viewAdmin.jsp?page=1").include(
                        request, response);
            }

        } else {
            out.print("<p style='color: red;'>Sorry, your password and repassword is not same! Please repeat registration!</p>");
            request.getRequestDispatcher("addUserAdmin.html").include(request,
                    response);
        }

        out.close();

    }

}
