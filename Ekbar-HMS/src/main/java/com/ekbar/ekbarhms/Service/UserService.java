package com.ekbar.ekbarhms.Service;

import com.ekbar.ekbarhms.Model.User;
import com.ekbar.ekbarhms.Request.updateAdminRequest;
import com.ekbar.ekbarhms.Response.AuthResponse;

import java.util.List;

public interface UserService {

    public AuthResponse createAdmin(User user) throws Exception;

    public void deleteAdmin(Long id);

    public List<User> getAllAdmin();

    public AuthResponse updateAdmin(updateAdminRequest req , Long id);




 }
