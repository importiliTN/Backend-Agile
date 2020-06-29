package com.scrum.importili.mvc.controllers;

import com.scrum.importili.mvc.model.User;
import com.scrum.importili.payload.request.FrontalUser;
import com.scrum.importili.payload.request.LoginRequest;
import com.scrum.importili.payload.response.JwtResponse;
import com.scrum.importili.repositories.UserRepository;
import com.scrum.importili.security.JwtUtils;
import com.scrum.importili.security.UserDetailsImpl;
import com.scrum.importili.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getUsername());
        if(user == null)
            return ResponseEntity.ok("User not found / not active");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String role = userDetails.getAuthority().getAuthority();

        JwtResponse response = new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                role);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value="/signup",method= RequestMethod.POST,consumes = MediaType.ALL_VALUE, produces = MediaType.ALL_VALUE)
    @ResponseBody
    public void createUser(@RequestBody FrontalUser frontalUser) {
        System.out.println(frontalUser.getEmail() + ' '+ frontalUser.getPassword());
        this.userService.createUser(frontalUser);
    }

}