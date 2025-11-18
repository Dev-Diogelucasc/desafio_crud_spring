package com.devdioge.desafioCrud.repository;

import com.devdioge.desafioCrud.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

}
