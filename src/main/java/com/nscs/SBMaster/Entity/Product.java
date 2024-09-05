package com.nscs.SBMaster.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="epm_products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description ;
    private String model ;
    private int  partners_id ;
    private String product_owner_mail ;
    private String ActiveYN ;
    private String product_owner_name ;
    private int  product_owner_id ;


}
