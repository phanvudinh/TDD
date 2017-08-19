package com.axonactive.kiss.TDD.repository;

import com.axonactive.kiss.TDD.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

}
