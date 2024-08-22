package com.eastflag.nnc.testkmj.user;

import com.eastflag.nnc.testkmj.useraccount.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * User Table 접근 인터페이스
 */
public interface UserRepository1 extends JpaRepository<User1, Integer> {
    Optional<User1> findByUserAccount(UserAccount userAccount);
    Optional<User1> findByTelNum(String telNum);
}