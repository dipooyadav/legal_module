package com.nscs.SBMaster.repository;

import com.nscs.SBMaster.Entity.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialsRepository extends JpaRepository<Credentials, Integer> {
}
