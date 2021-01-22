package com.pluralsight.conferencedemo.repositories;

import com.pluralsight.conferencedemo.models.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpeakerJpaRepository extends JpaRepository<Speaker, Long> {
    List<Speaker> findByFirstNameAndLastName(String firstName, String lastName);
    List<Speaker> findByFirstNameOrLastName(String firstName, String lastName);

    List<Speaker> findBySpeakerPhotoNull();

    List<Speaker> findByCompanyIn(List<String> company);

    List<Speaker> findByCompanyNotIn(List<String> company);

    List<Speaker> findByCompanyIgnoreCase(String company);

    List<Speaker> findByLastNameOrderByFirstNameAsc(String lastName);

    List<Speaker> findByFirstNameOrderByLastNameDesc(String firstName);

    Speaker findFirstByFirstName(String firstName);
}
