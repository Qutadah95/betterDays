package com.example.betterDays.Controller;

import com.example.betterDays.Entities.DoctorEntity;
import com.example.betterDays.Repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
@Controller
public class DoctorController {
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    PasswordEncoder encoder;
//@GetMapping("/doctor")
//    public String getDoctor(){
//    return "doctorpro";
//}
@GetMapping("/doctor")
    public String getDoctorAcc(){
    String password="0";
    DoctorEntity doctor=new DoctorEntity("k", encoder.encode("0"));
    doctorRepository.save(doctor);
    return "doctorpro";
}

}
