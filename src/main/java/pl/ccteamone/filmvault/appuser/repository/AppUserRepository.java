package pl.ccteamone.filmvault.appuser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.ccteamone.filmvault.appuser.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
//    Optional<AppUser> findByUserName(String userName);
}
