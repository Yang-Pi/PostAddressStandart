package spring.repository;

import spring.entity.BadAddress;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BadAddressRepository extends CrudRepository<BadAddress, Integer> {
    Optional<BadAddress> findByBadName(String name);
}
