package com.RMSSpringBoot;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DelRepository extends JpaRepository<DeletedFriends, Long> {

}
