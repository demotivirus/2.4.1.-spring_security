package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Controller
//@RequestMapping("/")
public class UserController_v2 {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public UserController_v2(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView allUsers() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());
        modelAndView.addObject("users", userService.listUsers());
        modelAndView.addObject("roles", roleService.getRoles());
        modelAndView.setViewName("admin_info");
        return modelAndView;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView getUserPage(Authentication authentication, ModelAndView model) {
        List<User> user = new ArrayList<>();
        user.add(userService.findUserByName(authentication.getName()));
        model.addObject("user", user);
        model.setViewName("user_info");
        return model;
    }

    @GetMapping("/admin/addUser")
    public ModelAndView addUserGet() {
        ModelAndView mv = new ModelAndView("editUser");
        return mv;
    }

    @PostMapping("/admin/addUser")
    public ModelAndView addUserPost(@ModelAttribute("user") User user, HttpServletRequest request) {
        Set<Role> roles = user.getRoles();
        String roleUser = request.getParameter("user");
        String roleAdmin = request.getParameter("admin");
        if (roleUser != null) {
            user.setRoles(roles);
        }
        if (roleAdmin != null) {
            user.setRoles(roles);
        }
        ModelAndView mv = new ModelAndView();
        userService.add(user);
        return new ModelAndView("redirect:/admin");
    }

	@RequestMapping(value = "/admin/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editGet(@PathVariable("id") Long userId, ModelAndView modelAndView) {
		//modelAndView.setViewName("edit");
		modelAndView.setViewName("admin_info");
		//ModelAndView model = new ModelAndView("edit");
		User user = userService.getUserFromId(userId);
		modelAndView.addObject("user", user);
		List<Role> roles = roleService.getRoles();
		modelAndView.addObject("roles", roles);
		return modelAndView;
	}

    @RequestMapping(value = "/admin/editUser", method = RequestMethod.POST)
    public ModelAndView editPost(@ModelAttribute("admin/user") User user, @PathVariable Long role) {
        Set<Role> roleSet = Collections.singleton(roleService.getRoleById(role));
        user.setRoles(roleSet);
        userService.edit(user);
        return new ModelAndView("redirect:/admin");
    }

    @RequestMapping(value = "/admin/delete", method = RequestMethod.GET)
    public String deleteUser(@RequestParam("id") Long id) {
        userService.delete(userService.getUserFromId(id));
        return "redirect:/admin";
    }

}