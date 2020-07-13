package spring.service;

import spring.entity.GoodAddress;

import java.util.List;
import java.util.Optional;

public interface GoodAddressService {
    Optional<GoodAddress> findById(Integer id);
    GoodAddress findByName(String addressName);
    GoodAddress addAddress(GoodAddress address);
}
