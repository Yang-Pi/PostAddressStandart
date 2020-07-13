package spring.service;

import org.springframework.stereotype.Service;
import spring.entity.BadAddress;
import spring.entity.GoodAddress;
import org.springframework.beans.factory.annotation.Autowired;
import spring.repository.GoodAddressRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GoodAddressServiceImpl implements GoodAddressService {
    @Autowired
    private GoodAddressRepository repository;

    @Override
    public Optional<GoodAddress> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public GoodAddress findByName(String addressName) {
        Optional<GoodAddress> goodAddress = repository.findByName(addressName);
        if (goodAddress.isPresent()) {
            return goodAddress.get();
        }
        return null;
    }

    @Override
    public GoodAddress addAddress(GoodAddress address) {
        return repository.save(address);
    }
}
