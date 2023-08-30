package ru.job4j.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.Address;
import ru.job4j.repository.AddressRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleAddressService implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public Optional<Address> findById(int id) {
        return addressRepository.findById(id);
    }
}
