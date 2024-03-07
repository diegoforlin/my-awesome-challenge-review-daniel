package com.challengescrd.challenge.service;

import com.challengescrd.challenge.entities.User;
import com.challengescrd.challenge.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class); //Adicionando log para melhorar o debug
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User saveUser(User user) { // É melhor a classe de serviço retornar o objeto em si e ele ser encapsulado na ResponseEntity na controller
        user.getAddresses().forEach(address -> address.setUser(user)); // Salvar em todos os endereços o usuário ao invés de somente o primeiro
        User newUser = userRepository.save(user); // não precisa da chamada ao adressRepository pois já está salvando em cascata
        LOGGER.info("Novo usuário {} salvo no banco de dados!", newUser.getId());

        return newUser;
    }
    public List<User> fetchAllUsers() {
        return userRepository.findAll();
    }
    public User fetchUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }
    public Boolean deleteUser(Long id) {
        LOGGER.info("Deletando usuário com id '{}'", id); //Adicionar logs para facilitar o debug
        userRepository.deleteById(id);
        //pode fazer uma validação se o usuário foi de fato excluído:
        return userRepository.findById(id).isEmpty();
    }
    public User updateUser(Long id, User updatedUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
//        existingUser.setName(updatedUser.getName()); Dessa forma você subscreve o que já estava lá sem nenhuma validação
//        existingUser.setCellphone(updatedUser.getCellphone());
//        existingUser.setAddresses(updatedUser.getAddresses()); poderia fazer dessa forma:
        //Atualizar nome:
        if(updatedUser.getName() !=null && !updatedUser.getName().isBlank()) existingUser.setName(updatedUser.getName());
        //Atualiar telefone
        if(updatedUser.getCellphone() !=null && !updatedUser.getCellphone().isBlank()) existingUser.setCellphone(updatedUser.getCellphone());
        //Atualizar endereços
        if(updatedUser.getAddresses() !=null && !updatedUser.getAddresses().isEmpty()) existingUser.setAddresses(updatedUser.getAddresses());


        return userRepository.save(existingUser);
    }
}