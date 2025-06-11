package com.profecarlos.tallerapirest.restapi.controller;

import com.profecarlos.tallerapirest.restapi.model.Usuario;
import com.profecarlos.tallerapirest.restapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/post")
    public ResponseEntity<Usuario> insertUser(@Valid @RequestBody Usuario user) {
        Usuario savedUser = userRepository.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Usuario>> getAllUsers() {
        List<Usuario> users = userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/get/{idget}")
    public ResponseEntity<Usuario> getUserById(@PathVariable("idget") Integer id) {
        return userRepository.findById(id)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/rol/{rol}")
    public ResponseEntity<List<Usuario>> getUsersByRol(@PathVariable String rol) {
        List<Usuario> users = userRepository.findByRol(rol);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("/put/{idput}")
    public ResponseEntity<Usuario> updateUser(@PathVariable("idput") Integer id, @Valid @RequestBody Usuario updatedUser) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setNombre(updatedUser.getNombre());
                    existingUser.setRol(updatedUser.getRol());
                    existingUser.setEmail(updatedUser.getEmail());
                    existingUser.setPassword(updatedUser.getPassword());
                    Usuario updated = userRepository.save(existingUser);
                    return new ResponseEntity<>(updated, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{iddelete}")
    public ResponseEntity<Void> deleteUser(@PathVariable("iddelete") Integer id) {
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.delete(user);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }
}
