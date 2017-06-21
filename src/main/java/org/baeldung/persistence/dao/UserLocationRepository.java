package org.baeldung.persistence.dao;

import org.baeldung.persistence.model.User;
import org.baeldung.persistence.model.UserLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLocationRepository extends JpaRepository<UserLocation, Long> {
    UserLocation findByCountryAndUser(String country, User user);

}
