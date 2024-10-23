/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.edu.fesa.MedQuery.repositories;

import br.edu.fesa.MedQuery.model.UserModel;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hugok
 */
@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {
    UserDetails findByEmail(String email);
}
