package br.com.example.springshop.repositories;

import br.com.example.springshop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    List<User> findAll();

    @Override
    <S extends User> S save(S s);

    List<User> findByNameContains(String name);
}
