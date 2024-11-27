
package br.edu.fesa.MedQuery.repositories;

import br.edu.fesa.MedQuery.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hugok
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    //UserDetails findByEmail(String email);
    //User findByNome(String nome);
}
