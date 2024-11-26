package com.Hotel.Reservention.Hotel.Reservention.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Hotel.Reservention.Hotel.Reservention.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
}
