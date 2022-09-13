package springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springboot.model.User;
import springboot.service.RoleService;
import springboot.service.UserService;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getListUsers());
        return "admin";
    }

    @GetMapping("/add")
    public String saveUser(Model model) {
        model.addAttribute("user", new User());
        return "add";
    }

    @PostMapping("/add")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Long id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("listRoles", roleService.getAllRoles());
        model.addAttribute("user", user);
        return "update";
    }

    @PatchMapping("/update")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam("listRoles") ArrayList<Integer> roles) {
        user.setRoles(roleService.findByIdRoles(roles));
        userService.updateUser(user);
        return "redirect:/";
    }
}
