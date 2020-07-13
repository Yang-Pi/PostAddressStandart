package spring.service;

import spring.entity.BadAddress;

import java.util.List;
import java.util.Optional;

public interface BadAddressService {
    Optional<BadAddress> findById(Integer id);
    BadAddress findByName(String addressName);
    BadAddress addAddress(BadAddress address);
}
