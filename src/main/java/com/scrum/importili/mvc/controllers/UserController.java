package com.scrum.importili.mvc.controllers;

import com.scrum.importili.payload.request.FrontalUser;
import com.scrum.importili.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    // Admin Methods :

    @GetMapping("/admin/users")
    //@PreAuthorize("hasRole('ADMIN')")
    public List<FrontalUser> getUsers() {
        return this.userService.getUsers();
    }

    @RequestMapping(value="/admin/users/{userId}",method = RequestMethod.GET)
    //@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public FrontalUser getUser(@PathVariable Long userId) {
        return this.userService.getUser(userId);
    }

    @PatchMapping(value="/admin/users/{userId}",consumes = MediaType.ALL_VALUE, produces = MediaType.ALL_VALUE)
    //@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void updateUser(@PathVariable Long userId, @RequestBody FrontalUser frontalUser) {
        this.userService.updateUser(userId, frontalUser);
    }

    @DeleteMapping("/admin/users/{userId}")
    //@PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@PathVariable Long userId) {
        this.userService.deleteUser(userId);
    }


}
