package com.example.betterDays.Controller;
import com.example.betterDays.Entities.DoctorEntity;
import com.example.betterDays.Entities.DoctorWaiting;
import com.example.betterDays.Entities.Patient;
import com.example.betterDays.Entities.Story;
import com.example.betterDays.Repositories.DoctorRepository;
import com.example.betterDays.Repositories.DoctorWaitingRepsitory;
import com.example.betterDays.Repositories.PatientRepository;
import com.example.betterDays.Repositories.StoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import java.security.Principal;
import java.util.List;

@Controller
public class PatientController {
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    StoryRepo storyRepo;
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    DoctorWaitingRepsitory doctorWaitingRepsitory;


    @PostMapping("/testResult")
    public String getSolution(Principal principal, @RequestParam int submit, Model model) throws Exception {
        Patient patient = patientRepository.findByUsername(principal.getName());
//        Patient patient1 = patientRepository.findById(16).get();

        String firstName=patient.getFirstName();
        String lastName=patient.getLastName();
        String userName=patient.getUserName();
        String nickName=patient.getNickName();
        String email=patient.getEmail();
        String password=patient.getPassword();
        int age=patient.getAge();
//        patientRepository.save(patient);
        System.out.println(submit);
        System.out.println(submit);
        System.out.println(patient.getId());
//int newid =patient.getId();
        if (submit < 3) {
            patient.setTester("Initiation");
            patientRepository.save(patient);
//            patientRepository.delete(patient);
//            Patient patient0=new Patient(firstName,lastName,userName,nickName,email,password,age,"Initiation");
//            patientRepository.save(patient0);
//            System.out.println(patient.getTestResult());
//            PostgreSQLJDBC.main(new String[] {"Initiation"});
            return "index";
        } else if (submit == 3) {
            patient.setTester("Experimentation stage");
            patientRepository.save(patient);

//            patientRepository.delete(patient);
//            Patient patient0=new Patient(firstName,lastName,userName,nickName,email,password,age,"Experimentation stage");
//            patientRepository.save(patient0);
//            System.out.println(patient.getTestResult());
//            PostgreSQLJDBC.main(new String[] {"Experimentation stage"});
            return "level3";
        } else if (submit >= 4 && submit < 7) {
            patient.setTester("Regular Usage");
            patientRepository.save(patient);

//            patientRepository.delete(patient);
//            Patient patient0=new Patient(firstName,lastName,userName,nickName,email,password,age,"Regular Usage");
//            patientRepository.save(patient0);
//            System.out.println(patient.getTestResult());
//            PostgreSQLJDBC.main(new String[] {"Regular Usage"});
            return "level2";
        } else if (submit >= 7 && submit < 10) {
            patient.setTester("Risky Usage");
            patientRepository.save(patient);

//            patientRepository.delete(patient);
//            Patient patient0=new Patient(firstName,lastName,userName,nickName,email,password,age,"Risky Usage");
//            patientRepository.save(patient0);
//            System.out.println(patient.getTestResult());
//            PostgreSQLJDBC.main(new String[] {"Risky Usage"});
            return "level1";
        } else {
//            patient.addr("Crisis/Treatment");
                        patient.setTester("Crisis/Treatment");
            patientRepository.save(patient);


//            patientRepository.delete(patient);
//            Patient patient0=new Patient(firstName,lastName,userName,nickName,email,password,age,"Crisis/Treatment");
//            patientRepository.save(patient0);
//            System.out.println(patient.getTestResult());
//            PostgreSQLJDBC.main(new String[] {"Crisis/Treatment"});
            return "level1";
        }
    }

    @GetMapping("/test")
    public String getTest() {
        return "test";
    }
    @GetMapping("/patientProfile")
    public String getPatientProfile(Principal principal, Model model) {
        Patient patient = patientRepository.findByUsername(principal.getName());
        DoctorEntity doctor = doctorRepository.findByUsername(principal.getName());
        if(patient!=null && (patient.getAuthority().contains("role_patient"))){
            model.addAttribute("patient", patient);
            return "profile";
        }else if (doctor!=null){
            model.addAttribute("patient", doctor);
            return "doctorpro";
        }else {
            model.addAttribute("patient", patient);

            return "adnimpro";
        }
    }
//@GetMapping("/patientProfile")
//public String getPatientProfile(Principal principal, Model model) {
//    Patient patient = patientRepository.findByUsername(principal.getName());
////    DoctorEntity doctor = doctorRepository.findByUsername(principal.getName());
//
//    System.out.println(patient.getAuthority().contains("role_patient"));
//    if(patient!=null && patient.getAuthority().contains("role_patient")) {
//        model.addAttribute("patient", patient);
//        return "profile";
//
//    }else if(patient!=null && patient.getAuthority().contains("role_admin")){
//        model.addAttribute("patient", patient);
//        return "adnimpro";
//    }else {
////        model.addAttribute("patient", doctor);
//        return "doctorpro";
//    }
//}
    @PostMapping("/updateProfile")
    public String updateProfile(@RequestParam String firstName,
                                @RequestParam String lastName,
                                @RequestParam String nickName,
                                @RequestParam String userName,
                                @RequestParam String email,
                                @RequestParam String password,
                                @RequestParam int age, Principal principal,
                                Model model) {
        Patient patientToUpdate = patientRepository.findByUsername(principal.getName());
        patientToUpdate.setFirstName(firstName);
        patientToUpdate.setLastName(lastName);
        patientToUpdate.setAge(age);
        patientToUpdate.setEmail(email);
        patientToUpdate.setPassword(encoder.encode(password));
        patientToUpdate.setUserName(userName);
        patientToUpdate.setNickName(nickName);
        patientRepository.save(patientToUpdate);
        model.addAttribute("patient", patientToUpdate);
        return "profile";
    }
    @PostMapping("/addDoctorToBooking/{id}")
    public RedirectView profile(@PathVariable int id, Model m, Principal principal){
        DoctorEntity doctor = doctorRepository.findById(id).get();
        Patient patient = patientRepository.findByUsername(principal.getName());
        patient.setDoctorEntity(doctor);
        doctor.addPatient(patient);
        patientRepository.save(patient);
        doctorRepository.save(doctor);
        return new RedirectView("/calender");

    }
    @PostMapping("/addstory")
    public RedirectView addStory(@RequestParam String body, @RequestParam String title  , Principal p, Model model){
        Patient patient=patientRepository.findByUsername(p.getName());
        Story newStory=new Story(body,title);
        patient.addStory(newStory);
        patientRepository.save(patient);
        storyRepo.save(newStory);


        model.addAttribute("patients",patientRepository.findAll());

        return new RedirectView("/stories");
    }




}
