package ua.denys.CarInTime.services;

import com.google.common.hash.Hashing;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class HashingService {

    public String getHashedString(String... str) throws NoSuchAlgorithmException {
        StringBuilder stringBuilder = new StringBuilder();
        for (var string : str){
            stringBuilder.append(string);
        }
        return Hashing.sha512().hashBytes(stringBuilder.toString().getBytes()).toString();
    }
}
