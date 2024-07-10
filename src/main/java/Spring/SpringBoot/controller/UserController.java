package Spring.SpringBoot.controller;

import Spring.SpringBoot.model.User;
import Spring.SpringBoot.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String index(ModelMap model,
                        @ModelAttribute("user") User user) {
        model.addAttribute("users", userService.readAllUsers());
        return "index";
    }

    @GetMapping("/create")
    public String createForm(@ModelAttribute User user) {
        return "create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "create";
        }
        userService.createUser(user);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String updateForm(ModelMap model,
                             @RequestParam("id") int id) {
        model.addAttribute("user", userService.readUserById(id));
        return "update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult,
                         @RequestParam("id") int id,
                         @RequestParam("age") int age,
                         @RequestParam("surname") String surname) {
        if (bindingResult.hasErrors()) {
            return "update";
        }

        user.setAge(age);
        user.setSurname(surname);
        userService.updateUser(id, user);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}
