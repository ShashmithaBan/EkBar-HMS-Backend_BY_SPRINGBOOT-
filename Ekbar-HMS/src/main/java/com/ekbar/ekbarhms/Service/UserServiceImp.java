package com.ekbar.ekbarhms.Service;

import com.ekbar.ekbarhms.Config.JwtProvider;
import com.ekbar.ekbarhms.Model.User;
import com.ekbar.ekbarhms.Repo.UserRepo;
import com.ekbar.ekbarhms.Request.updateAdminRequest;
import com.ekbar.ekbarhms.Response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public AuthResponse createAdmin(User user) throws Exception {
        User isEmailExist = userRepository.findByEmail(user.getEmail());

        if (isEmailExist != null) {
            throw new Exception("The provided email already exists. Try with another account.");
        }

        User createUser = new User();
        createUser.setEmail(user.getEmail());
        createUser.setName(user.getName());
        createUser.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(createUser);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Registration successful");
        return authResponse;
    }

    @Override
    public void deleteAdmin(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    @Override
    public List<User> getAllAdmin() {
        return userRepository.findAll();
    }

    @Override
    public AuthResponse updateAdmin(updateAdminRequest req, Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User updateUser = user.get();
            updateUser.setName(req.getName());
            if (req.getPassword() != null && !req.getPassword().isEmpty()) {
                updateUser.setPassword(passwordEncoder.encode(req.getPassword()));
            }
            userRepository.save(updateUser);

            AuthResponse authResponse = new AuthResponse();
            authResponse.setMessage("Update successful");
            return authResponse;
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }


}
