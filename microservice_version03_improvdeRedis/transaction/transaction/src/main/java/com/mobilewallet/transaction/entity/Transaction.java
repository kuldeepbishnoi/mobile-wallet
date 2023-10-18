package com.mobilewallet.transaction.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "transaction")
public class Transaction {
    
	@Id
    @Column(name = "id")
    private String id;

    @Column(name = "amount")
    @NotNull
    @Min(1) 
    private Integer amount;

    @Column(name = "from_wallet_id")
    private String  fromWalletId;

    @NotNull
    @Column(name = "to_wallet_id")
    private String toWalletId;

    // @Column(name = "date_time")
    // private LocalDateTime dateTime;
    
    @Column(name = "description")
	private String description;

    @Column(name = "status")
    private String status;
}
