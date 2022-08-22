package com.tinqin.domain.data.repository;

import com.tinqin.domain.data.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

        Optional<Wallet> getWalletByCardNumberAndCvc(String cardNumber, String cvc);

}
