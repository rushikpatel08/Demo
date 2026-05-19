package com.example.demo.Repository;

import com.example.demo.Entity.Mobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface mobileRepository extends JpaRepository<Mobile, Long>{
}
