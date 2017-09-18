package hello;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByUserFullName(String userFullName);
    List<User> findByCity(String city);

    List<User> findByUserFullNameAndCity(String userFullName, String city);
    List<User> findByCityOrderByUserFullName(String city);
}