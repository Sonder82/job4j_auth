package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.model.Address;

import java.util.Optional;


public interface AddressRepository extends CrudRepository<Address, Integer> {

    Optional<Address> findById(int id);
}
