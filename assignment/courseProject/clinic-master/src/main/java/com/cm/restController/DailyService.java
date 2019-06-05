package com.cm.restController;

import com.cm.dao.*;
import com.cm.model.*;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@RestController
public class DailyService {
    @PostMapping("/Edit")
    public void editUser(HttpServletRequest request,
                         HttpServletResponse response, @RequestParam("userID") String userIdS,@RequestParam("userName") String name,
                         @RequestParam("password")String password, @RequestParam("repassword")String repassword,
                         @RequestParam("nameAndSurname")String nameAndSurname, @RequestParam("address")String address,
                         @RequestParam("phoneNumber")String phoneNumber, @RequestParam("email")String email,
                         @RequestParam("numberOfIdCard")String numberOfIdCard, @RequestParam("bloodType")String bloodType,
                         @RequestParam("sex")String sex, @RequestParam("typeOfUsers")String typeOfUsers,
                         @RequestParam("nameOfJob")String nameOfJob)throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        int userId = Integer.parseInt(userIdS);
        User u = new User();
        u.setUserID(userId);
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

        if (password.equals(repassword)) {

            int status = UserDao.update(u);
            if (status > 0) {
                out.print("<h1 style='color: green;'>Updating saved successfully!</h1>");
                request.getRequestDispatcher("viewAdmin.jsp?page=1").include(
                        request, response);
//				response.sendRedirect("viewAdmin.jsp?page=1");
            } else {
                out.println("<h1 style='color: red;'>Sorry, unable to update user!</h1>");
                request.getRequestDispatcher("viewAdmin.jsp?page=1").include(
                        request, response);
            }

        } else {
            out.print("<h1 style='color: red;'>Sorry, your password and repassword is not same! Please repeat updating!</h1>");
            request.getRequestDispatcher("editUser.jsp?id=" + u.getUserID())
                    .include(request, response);
        }

        out.close();

    }


    @GetMapping("/Delete")
    public void deleteUser(HttpServletRequest request,
                           HttpServletResponse response,@RequestParam("id") int sid)throws ServletException,IOException{

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        int status = UserDao.delete(sid);
        if (status > 0) {
            out.print("<p style='color: lightblue;'>User deleted successfully!</p>");
            request.getRequestDispatcher("viewAdmin.jsp?page=1").include(
                    request, response);
            // response.sendRedirect("viewAdmin.jsp?page=1");
        } else {
            out.println("<p style='color: red;'>Sorry, unable to delete user!</p>");
            request.getRequestDispatcher("viewAdmin.jsp?page=1").include(
                    request, response);
        }

    }

    @GetMapping("/TakeMedicationPatient")
    public void takeMedicationPation(HttpServletRequest request,
                                     HttpServletResponse response)throws IOException,ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        if (user == null) {
            out.print("<p style='text-align: center;color: red;'>Sorry, you must login first!</p>");
            request.getRequestDispatcher("login.html").include(request,
                    response);
        }

        MedicalStaff medicalStaff = (MedicalStaff) session
                .getAttribute("medicalStaff");

        String patientIDString = request.getParameter("patientID");
        int patientID = Integer.parseInt(patientIDString);

        String pharmacyIDString = request.getParameter("pharmacyID");
        int pharmacyID = Integer.parseInt(pharmacyIDString);

        String quantityString = request.getParameter("quantity");
        int quantityInt = Integer.parseInt(quantityString);
        int quantity = quantityInt;

        // Podaci o pacijent smesteni o objekat patientObject zbog sesije
        User patientObject = UserDao.getUserById(patientID);

        // izvlacimo objekat sa podacima iz tabele Pharmacy
        Pharmacy pharmacyObject = PharmacyDao.getPharmacyById(pharmacyID);

        // izvlacimo kolicinu lekova za objekat pharmacyObject
        int quantityOnCondition = pharmacyObject.getQuantityOnCondition();

        // Nova kolicina lekova nakon sto smo izdali lekove pacijentu
        int newQuantity;
        newQuantity = quantityOnCondition - quantity;

        // Upisujemo novo stanje u tabelu tblPharmacy
        int statusQuantity = PharmacyDao.updateQuantityOnCondition(newQuantity,
                pharmacyID);

        // Novi podaci iz tabele Pharmacy, zbog sesije
        Pharmacy pharmacyObjectNew = PharmacyDao.getPharmacyById(pharmacyID);

        // Snimamo u tabelu tblTakeMedication UserID, MedicalStaffID, PharmacyID
        int statusTakeMedication = TakeMedicationDao.save(patientID,
                medicalStaff.getMedicalStaffID(), pharmacyID);

        // Iz tabele tblDoctorAppointment izvlacimo objekat
        // doctorAppointmentObject na osnovu UserID i MedicalStaffID
        DoctorAppointment doctorAppointmentObject = DoctorAppointmentDao
                .getDoctorAppointmentObjectByUserIDAndMedicalStaffID(patientID,
                        medicalStaff.getMedicalStaffID());

        // Iz tabele tblTakeMedications izvlacimo podatke, zbog sesije
        TakeMedication takeMedicationObject = TakeMedicationDao
                .getTakeMedicationObjectByUserIDAndMedicalStaffID(patientID,
                        medicalStaff.getMedicalStaffID());

        // U tabeli tblDoctorAppointment menjamo atribut u koloni Examined sa 0
        // na 1 da obelezimo da je za tog pacijenta obavljen pregled.
        // To radimo na nacin da prosledimo objekat iz tabele
        // tblDoctorAppointments za koji zelimo da izvrsimo promenu.
        int statusExaminedTo1 = DoctorAppointmentDao
                .updateExaminedTo1(doctorAppointmentObject
                        .getDoctorAppointmentID());

        // Postavljamo sesije koje prenosimo na sledecu stranicu

        if (statusTakeMedication > 0 && statusExaminedTo1 > 0
                && statusQuantity > 0) {
            session.setAttribute("user", user);
            session.setAttribute("medicalStaff", medicalStaff);
            session.setAttribute("patientObject", patientObject);
            session.setAttribute("pharmacyObject", pharmacyObject);
            session.setAttribute("pharmacyObjectNew", pharmacyObjectNew);
            session.setAttribute("doctorAppointmentObject",
                    doctorAppointmentObject);
            session.setAttribute("takeMedicationObject", takeMedicationObject);
            response.sendRedirect("takeMedicationWithPatient.jsp");
        } else {
            out.println("<p style='color: red;'>Sorry, unable to save record, please try again!</p>");
            request.getRequestDispatcher("takeMedication.jsp").include(request,
                    response);
        }
        out.close();
    }

    @GetMapping("/CheckDepartment")
    public void checkDepartment(HttpServletRequest request,
                                HttpServletResponse response,
                                @RequestParam("user") User user,@RequestParam("medicalDepartment")String department)
    throws ServletException,IOException{

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        boolean status = DateOfSchedulingDao
                .validateDateOfSchedulingByMedicalDepartmentExist(department);
        if (status) {
            session.setAttribute("user", user);
            session.setAttribute("medicalDepartment", department);
            out.print("<p style='color: green;text-align: center;'>There are vacant terms at the chosen medical department, please choose the desired term and the name of the doctor you want to schedule a review!</p>");
            request.getRequestDispatcher("doctorAppointment02.jsp").include(
                    request, response);
            // response.sendRedirect("viewAdmin.jsp?page=1");
        } else {
            out.println("<p style='color: red;text-align: center;'>Sorry, there are no free terms at the chosen medical department!</p>");
            request.getRequestDispatcher("doctorAppointment.jsp").include(
                    request, response);
        }
    }

    @GetMapping("/AddPatientsCheckIO")
    public void addPatientsCheckIO(HttpServletRequest request,
                                   HttpServletResponse response)throws IOException{
        response.setContentType("text/html");//解决文字乱码问题
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
    }

    @PostMapping("/hospitalBedcheck")
    public void hospitalBedCheck(HttpServletRequest request,
                                 HttpServletResponse response,@RequestParam("positionNum")int positionNum,@RequestParam("userid") int userid,
                                 @RequestParam("checkout")String flag)throws IOException{
        response.setContentType("text/html");//解决文字乱码问题
        HttpSession session = request.getSession(false);

        if(!flag.equals("true")){
//            int userid=Integer.parseInt(request.getParameter("userid"));
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
        }else {
//            int id = Integer.parseInt(request.getParameter("checkoutid"));
            PatientCheckIODao.checkout(userid);
            List<User> patientList = PatientCheckIODao.getAllPatient();
            positionNum++;
            for (User u : patientList) {
                if (PatientCheckIODao.checkbed(u.getUserID())) {
                    u.setFlag(true);
                } else
                    u.setFlag(false);
                if (u.getUserID() ==userid) {
                    u.setFlag(false);
                }
            }
            if (positionNum <= 0) {
                session.setAttribute("full", true);
            } else
                session.setAttribute("full", false);
            session.setAttribute("userList", patientList);
            session.setAttribute("positionNum", positionNum);
            List<Hospital> hList = PatientCheckIODao.getAllbed();
            session.setAttribute("bedList", hList);
            response.sendRedirect("patientCheckIO.jsp");
        }
    }

}
