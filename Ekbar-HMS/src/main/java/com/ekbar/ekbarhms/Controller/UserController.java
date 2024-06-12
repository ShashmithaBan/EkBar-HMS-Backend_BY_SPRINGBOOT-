package com.ekbar.ekbarhms.Controller;

import com.ekbar.ekbarhms.Model.User;
import com.ekbar.ekbarhms.Request.updateAdminRequest;
import com.ekbar.ekbarhms.Response.AuthResponse;
import com.ekbar.ekbarhms.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public AuthResponse registerUser(@RequestBody User user) {
        try {
            return userService.createAdmin(user);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteAdmin(id);
        return "User with ID " + id + " deleted successfully";
    }

    @GetMapping("")
    public List<User> getAllUsers() {
        return userService.getAllAdmin();
    }

    @PutMapping("/{id}/update")
    public AuthResponse updateUser(@RequestBody updateAdminRequest request, @PathVariable Long id) {
        try {
            return userService.updateAdmin(request, id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
