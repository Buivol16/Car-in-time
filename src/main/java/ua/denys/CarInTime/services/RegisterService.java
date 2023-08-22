package ua.denys.CarInTime.services;


import org.springframework.stereotype.Service;
import ua.denys.CarInTime.model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RegisterService {

    public boolean checkUserToRegister(User user){
        if (isEmail(user.getEmail()) && !user.getPassword().isBlank()) return true;
        return false;
    }

    private boolean isEmail(String email){
        var result = false;
        if (email == null) return result;
        Pattern regex = Pattern.compile(".+@.+[.].+");
        Matcher matcher = regex.matcher(email);
        if (matcher.find()) result = true;
        return result;
    }
}
