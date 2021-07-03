package com.mkayacdev.account.model

import org.hibernate.annotations.GenericGenerator
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Transaction (
        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID",strategy = "UUIDGenerator")
        val id: String?,
        val transactionType: TransactionType? = TransactionType.INITIAL,
        val amount: BigDecimal?,
        val transactionDate: LocalDateTime?,

        @ManyToOne(fetch = FetchType.LAZY, optional = false,cascade = [CascadeType.ALL])
        @JoinColumn(name = "accoint_id",nullable = false)
        val account: Account

    )
{
        constructor(customer: Customer, balance: BigDecimal, creationDate: LocalDateTime) : this(
                customer = customer,
                balance = balance,
                creationDate = creationDate
        )


        override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (javaClass != other?.javaClass) return false

                other as Transaction

                if (id != other.id) return false
                if (transactionType != other.transactionType) return false
                if (amount != other.amount) return false
                if (transactionDate != other.transactionDate) return false
                if (account != other.account) return false

                return true
        }

        override fun hashCode(): Int {
                var result = id?.hashCode() ?: 0
                result = 31 * result + (transactionType?.hashCode() ?: 0)
                result = 31 * result + (amount?.hashCode() ?: 0)
                result = 31 * result + (transactionDate?.hashCode() ?: 0)
                return result
        }
}



enum class  TransactionType{
        INITIAL,TRANSFER
    }
