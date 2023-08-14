package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.config.ApplicationContextProvider;
import web.model.User;
import web.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("/")
    public String printUsers(ModelMap modelMap, @RequestParam(required = false) final Long id) {
        UserService userService = ApplicationContextProvider.getApplicationContext().getBean(UserService.class);
        modelMap.addAttribute("users", userService.getUserList());
        if (id == null) {
            modelMap.addAttribute("user", new User());
        } else {
            modelMap.addAttribute("user", userService.getUserById(id));
        }
        return "index";
    }

    @GetMapping("/deleteUser")
    public String deleteEmployee(@RequestParam Long userId) {
        userService.deleteUser(userId);
        return "redirect:/";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute User user) {
        if (user.getId() == null) {
            userService.addUser(user);
        } else {
            userService.updateUser(user);
        }
        return "redirect:/";
    }
}
