package it.epicode.Pratica_S7_L5_Esame_Settimanale_6.repository;

import it.epicode.Pratica_S7_L5_Esame_Settimanale_6.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    public Optional<User> findByUsername(String username);
}
