package com.scrum.importili.services;

import com.scrum.importili.exceptions.ResourceNotFoundException;
import com.scrum.importili.mvc.model.ERole;
import com.scrum.importili.mvc.model.Role;
import com.scrum.importili.mvc.model.User;
import com.scrum.importili.payload.request.FrontalUser;
import com.scrum.importili.repositories.RoleRepository;
import com.scrum.importili.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;


    public List<FrontalUser> getUsers() {
        List<User> users = this.userRepository.findAll();
        List<FrontalUser> frontalUsers = new ArrayList<>();
        User user;
        for(int i=0; i<users.size(); i++)
        {
            user = users.get(i);
            frontalUsers.add(new FrontalUser(
                    user.getId(),
                    user.getEmail(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getCin(),
                    user.getPhoneNumber(),
                    user.getRole().getName().name(),
                    "",
                    user.isActive()
            ));
        }
        return frontalUsers;
    }

    public void createUser(@RequestBody FrontalUser frontalUser) {
        if (userRepository.existsByEmail(frontalUser.getEmail()))
            throw new ResourceNotFoundException("User found with email "+frontalUser.getEmail());
        else {
            User user = new User();
            user.setEmail(frontalUser.getEmail());
            user.setPassword(encoder.encode(frontalUser.getPassword()));
            user.setPhoneNumber(frontalUser.getPhoneNumber());
            user.setCin(frontalUser.getCin());
            user.setFirstName(frontalUser.getFirstName());
            user.setLastName(frontalUser.getLastName());
            user.setActive(frontalUser.isActive());
            String roleStr = frontalUser.getRole();
            Role role;

            if (roleStr.equals("")) {
                role = roleRepository.findByName(ERole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            } else {
                if (roleStr.equals("ROLE_ADMIN"))
                    role = roleRepository.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                else role = roleRepository.findByName(ERole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            }
            user.setRole(role);
            userRepository.save(user);
        }
    }

    public FrontalUser getUser(Long userId) {
        if(!userRepository.existsById(userId))
            throw new ResourceNotFoundException("User not found with id " + userId);
        else {
            User user = userRepository.findById(userId).get();
            return new FrontalUser(
                    user.getId(),
                    user.getEmail(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getCin(),
                    user.getPhoneNumber(),
                    user.getRole().getName().name(),
                    "",
                    user.isActive()
            );
        }
    }


    public void updateUser(Long userId, @RequestBody FrontalUser frontalUser) {
        if (!userRepository.existsByEmail(frontalUser.getEmail()))
            throw new ResourceNotFoundException("User not found with id " + userId);
        else {
            User user = new User();
            user.setId(userId);
            user.setEmail(frontalUser.getEmail());
            user.setPassword(encoder.encode(frontalUser.getPassword()));
            user.setPhoneNumber(frontalUser.getPhoneNumber());
            user.setCin(frontalUser.getCin());
            user.setFirstName(frontalUser.getFirstName());
            user.setLastName(frontalUser.getLastName());
            user.setActive(frontalUser.isActive());
            String roleStr = frontalUser.getRole();
            Role role;

            if (roleStr.equals("")) {
                role = roleRepository.findByName(ERole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            } else {
                if (roleStr.equals("ROLE_ADMIN"))
                    role = roleRepository.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                else role = roleRepository.findByName(ERole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            }
            user.setRole(role);
            userRepository.save(user);
        }
    }

    public void deleteUser( Long userId) {
        if(!userRepository.existsById(userId))
            throw new ResourceNotFoundException("User not found with id " + userId);
        else userRepository.deleteById(userId);
    }
}
