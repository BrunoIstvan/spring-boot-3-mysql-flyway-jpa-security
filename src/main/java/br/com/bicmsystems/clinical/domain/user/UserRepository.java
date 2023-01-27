package br.com.bicmsystems.clinical.domain.user;

import br.com.bicmsystems.clinical.domain.user.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<UserModel, Long> {

    UserDetails findByLogin(String login);
}
