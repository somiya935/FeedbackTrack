package com.cognizant.training.feedbacktrack.repository;


import com.cognizant.training.feedbacktrack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
