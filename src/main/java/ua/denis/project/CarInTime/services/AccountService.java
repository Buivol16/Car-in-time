package ua.denis.project.CarInTime.services;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.denis.project.CarInTime.model.User;
import ua.denis.project.CarInTime.repositories.UserRepository;


@Service
public class AccountService {

  @Autowired UserRepository userRepository;

  @Autowired HashingService hashingService;

  @SneakyThrows
  public User checkUser(User s) {
    User user = getUserByEmail(s.getEmail());
    String hashedPassword = hashingService.getHashedString(s.getPassword());
    if (user != null && hashedPassword.equals(user.getPassword())) return user;
    else return null;
  }

  private User getUserByEmail(String email) {
    User user;
    try{
      user = userRepository.findByEmail(email).get(0);
    }catch (Exception e){
      user = null;
    }
    return user;
  }
}
