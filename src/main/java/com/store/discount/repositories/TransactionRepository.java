package com.store.discount.repositories;

import com.store.discount.models.Purchase;
import com.store.discount.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

    @Query(value="SELECT " +
            "transactions.id as transactionId, customer_id as customerId, type as customerType, first_purchase as firstPurchase, product_id as productId, category, price " +
            "FROM transactions, customers, products WHERE transactions.customer_id = customers.id AND transactions.product_id = products.id " +
            "AND customer_id = :customerId and transactions.id= :transactionId",
            nativeQuery = true)
    List<Purchase> queryBy(@Param("transactionId") long transactionId,  @Param("customerId") long customerId);

}