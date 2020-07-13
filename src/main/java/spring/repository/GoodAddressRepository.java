package spring.repository;

import org.springframework.stereotype.Repository;
import spring.entity.GoodAddress;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@Repository
public interface GoodAddressRepository extends CrudRepository<GoodAddress, Integer> {
    Optional<GoodAddress> findByName(String name);
}
