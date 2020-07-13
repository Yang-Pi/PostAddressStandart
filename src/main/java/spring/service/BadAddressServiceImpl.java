package spring.service;

import org.springframework.stereotype.Service;
import spring.entity.BadAddress;
import org.springframework.beans.factory.annotation.Autowired;
import spring.repository.BadAddressRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BadAddressServiceImpl implements BadAddressService {
    @Autowired
    private BadAddressRepository repository;

    @Override
    public Optional<BadAddress> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public BadAddress findByName(String addressName) {
        Optional<BadAddress> badAddress = repository.findByBadName(addressName);
        if (badAddress.isPresent()) {
            return badAddress.get();
        }

        return null;
    }

    @Override
    public BadAddress addAddress(BadAddress address) {
        return repository.save(address);
    }
}
