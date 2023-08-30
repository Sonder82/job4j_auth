package ru.job4j.service;

import ru.job4j.model.Address;

import java.util.Optional;

public interface AddressService {

    Optional<Address> findById(int id);
}
