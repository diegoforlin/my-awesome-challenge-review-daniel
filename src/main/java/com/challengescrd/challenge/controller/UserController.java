package com.challengescrd.challenge.controller;

import com.challengescrd.challenge.entities.User;
import com.challengescrd.challenge.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @Operation(summary = "Cadastro de usuário")
    @Transactional
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping
    @Operation(summary = "Ficha de usuários")
    @Transactional
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.fetchAllUsers());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Encontre usuário por ID")
    @Transactional
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.fetchUserById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{userId}")
    @Operation(summary = "Atualização de usuário")
    @Transactional
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User user) {
        User updatedUser = userService.updateUser(userId, user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminação de usuário por ID")
    @Transactional
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        Boolean deletedUser = userService.deleteUser(id);
        String response = (deletedUser?"Sucesso":"Falha") + " ao excluir usuário";
        return ResponseEntity.ok(response);
    }
}
