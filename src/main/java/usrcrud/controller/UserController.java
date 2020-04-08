package usrcrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import usrcrud.model.Role;
import usrcrud.model.User;
import usrcrud.service.RoleService;
import usrcrud.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Collections;
import java.util.Set;

@Controller
@RequestMapping("/")
public class UserController {

    private UserService userService;

    private RoleService roleService;

    @Autowired
    public void setUserService(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView login(String error, String logout, ModelAndView modelAndView) {
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView startPageAdmin() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject( "user", new User());
        modelAndView.addObject("users", this.userService.allUsers());
        modelAndView.addObject("roles", this.roleService.getRoles());
        modelAndView.setViewName("admin-usrs");
        return modelAndView;
    }

    @RequestMapping(value = {"/admin/add"}, method = RequestMethod.POST)
    public ModelAndView add(@ModelAttribute("user") User user, HttpServletRequest request) {
        Set<Role> roleSet = Collections.singleton(roleService.getRoleById(Long.valueOf(request.getParameter("role"))));
        user.setRoles(roleSet);
        userService.addUser(user);
        return new ModelAndView("redirect:/admin");
    }

    @RequestMapping(value = {"/admin/edit"}, method = RequestMethod.POST)
    public ModelAndView updatePost(@ModelAttribute("admin/user") User user, HttpServletRequest request) {
        Set<Role> roleSet = Collections.singleton(roleService.getRoleById(Long.valueOf(request.getParameter("role"))));
        user.setRoles(roleSet);
        userService.editUser(user);
        return new ModelAndView("redirect:/admin");
    }

    @RequestMapping(value = {"/admin/edit"}, method = RequestMethod.GET)
    public ModelAndView updateGet(HttpServletRequest request) {
        long employeeId = Long.parseLong(request.getParameter("id"));
        User user = userService.getUserById(employeeId);
        ModelAndView model = new ModelAndView("edit");
        model.addObject("user", user);
        return model;
    }

    @RequestMapping(value = {"/admin/delete"}, method = RequestMethod.POST)
    public ModelAndView deleteEmployee(HttpServletRequest request) {
        userService.deleteUser(Long.valueOf(request.getParameter("id")));
        return new ModelAndView("redirect:/admin");
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView helloPageUser(ModelAndView modelAndView, Principal principal) {
        String name = principal.getName();
        User user = userService.getUserByUserName(name);
        modelAndView.addObject(user);
        modelAndView.setViewName("the_user");
        return modelAndView;
    }

}
