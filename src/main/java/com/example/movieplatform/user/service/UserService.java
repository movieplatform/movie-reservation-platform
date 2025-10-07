package com.example.movieplatform.user.service;

import com.example.movieplatform.common.exception.AdminCannotWithdrawException;
import com.example.movieplatform.common.exception.AlreadyWithdrawException;
import com.example.movieplatform.common.exception.EntityNotFoundException;
import com.example.movieplatform.user.Repository.UserRepository;
import com.example.movieplatform.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .sorted(Comparator
                        .comparing((User u) -> u.getRole() == User.Role.ADMIN ? 0 : 1)
                        .thenComparing(u -> u.getStatus() == User.Status.DELETED ? 1 : 0)
                )
                .toList();
    }

    public void withdrawUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(userId.toString()));

        if (user.getRole() == User.Role.ADMIN) {
            throw new AdminCannotWithdrawException();
        }

        if (user.getStatus() == User.Status.DELETED) {
            throw new AlreadyWithdrawException();
        }

        user.withdraw();
        userRepository.save(user);
    }
}
