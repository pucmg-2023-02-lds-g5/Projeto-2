package com.projeto2.app.services;

import com.projeto2.app.models.Usuario;
import com.projeto2.app.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Usuario findById(Long id) {
        Optional<Usuario> usuario = this.userRepository.findById(id);
        return usuario.orElseThrow(() -> new RuntimeException("Usuario não entrado"));
    }

    public List<Usuario> findAll() {
        List<Usuario> usuarios = new ArrayList<>();
        usuarios = this.userRepository.findAll();
        return usuarios;
    }


    @Transactional
    public Usuario update(Usuario obj) {
        Usuario newObj = findById(obj.getId());
        newObj.setSenha(obj.getSenha());
        return this.userRepository.save(newObj);
    }

    public void delete(Long id) {
        findById(id);
        try {
            this.userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possivel excluir pois há entidades relacionadas");
        }

    }

}
