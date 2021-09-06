package br.com.example.springshop.repositories;

import br.com.example.springshop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    List<User> findAll();

    User findByEmail(String email);

    @Override
    <S extends User> S save(S s);

    List<User> findByUsernameContains(String username);

    Optional<User> findByUsername(String username);
}
