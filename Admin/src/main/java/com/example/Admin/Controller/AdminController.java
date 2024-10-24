package com.example.Admin.Controller;

import com.example.Admin.Model.Admin;
import com.example.Admin.Service.AdminService;
import com.example.Admin.Service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private SendEmailService sendEmailService;
    public static long id=0;

    @GetMapping("/login")
    public String loginPage(@ModelAttribute("student") Admin admin)
    {
        return "login";
    }

    @RequestMapping("/logout-success")
    public String logoutpage()
    {
        id=0;
        return "logout";
    }


    @GetMapping("/index")
    public String ViewhomePage(Model model)
    {
        model.addAttribute("listStudents",adminService.getAllStudents());
        model.addAttribute("student",adminService.getStudentById(id));
        return "index";
    }
    @GetMapping("/admin")
    public String ViewAdminPage(Model model)
    {
        model.addAttribute("listStudents",adminService.getAllStudents());
        model.addAttribute("student",adminService.getStudentById(id));
        return "index";
    }
    @GetMapping("/profile")
    public String profile(Model model)
    {
        model.addAttribute("student",adminService.getStudentById(id));
        return "profile";
    }

    @GetMapping("/contact")
    public String contact(Model model)
    {
        return "contact";
    }

    @GetMapping("/learner")
    public String ViewlearnerPage(Model model)
    {
        model.addAttribute("student",adminService.getStudentById(id));
        return "learner";
    }

    @GetMapping("/mentor")
    public String ViewMentorPage(Model model)
    {
        model.addAttribute("student",adminService.getStudentById(id));
        return "mentor";
    }

    @GetMapping("/")
    public String Viewhomepage(Model model)
    {
        if(id==0)
        {
            return "redirect:/login";
        }
        Admin admin;
        admin=adminService.getStudentById(id);
        if(admin.getRole().equals("ADMIN"))
        {
            return "redirect:/admin";
        }
        else if (admin.getRole().equals("LEARNER"))
        {
            return "redirect:/learner";
        }
        else if(admin.getRole().equals("MENTOR"))
        {
            return "redirect:/mentor";
        }
        return "login";
    }

    @GetMapping("/register")
    public String registerStudent(Model model)
    {
        Admin admin= new Admin();
        model.addAttribute("student",admin);
        return "registration";
    }
    @PostMapping("/saveStudent1")
    public String saveStudent(@ModelAttribute("student") Admin admin)
    {
        adminService.saveEmployee(admin);
        return "redirect:/";
    }


    @PostMapping("/saveStudent")
    public String saveEmployee(@ModelAttribute("student") Admin admin)
    {
        adminService.saveEmployee(admin);
        return "redirect:/index";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model)
    {
        Admin admin= adminService.getStudentById(id);
        model.addAttribute("student",admin);
        return "update_student";
    }

    @GetMapping("/deleteStudent/{id}")
    public String deleteEmployee(@PathVariable(value = "id") long id)
    {
        //call delete Employee Method
        this.adminService.deleteStudentById(id);
        return "redirect:/index";
    }

    @GetMapping("/forgot")
    public String forgotPassword(@ModelAttribute("student") Admin admin)
    {
        return "forgot";
    }

    @GetMapping("/forgotsubmit")
    public String forgotPasswordsubmit(@ModelAttribute("student") Admin admin)
    {
        Admin admin1=adminService.getStudentByEmail(admin.getEmail());
        String password= adminService.generateCommonTextPassword();
        admin1.setPassword(password);
        if (admin1!=null)
        {
            adminService.updateStudent(admin1);
            sendEmailService.sendEmail(admin.getEmail(),"Your new Password Is:: "+ password ,"Password Reset For ASV's Website");
        }
        return "redirect:/login";
    }

}

/*
@GetMapping("/showNewStudentForm")
    public String saveStudent(Model model)
    {
        Admin admin= new Admin();
        model.addAttribute("student",admin);
        return "new_Student";
    }
*/