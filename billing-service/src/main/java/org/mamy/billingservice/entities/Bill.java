package org.mamy.billingservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mamy.billingservice.model.Customer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Bill {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date billingDate;
    private long customerId;
    @OneToMany(mappedBy = "bill", fetch = FetchType.LAZY)
    private List<ProductItem> productItems = new ArrayList<>();

    @Transient // Cet attribut n'est pas persistant
    private Customer customer;

    // Classe Builder
    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {
        private Long id;
        private Date billingDate;
        private long customerId;
        private List<ProductItem> productItems = new ArrayList<>();
        private Customer customer;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder billingDate(Date billingDate) {
            this.billingDate = billingDate;
            return this;
        }

        public Builder customerId(long customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder productItems(List<ProductItem> productItems) {
            this.productItems = productItems;
            return this;
        }

        public Builder customer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public Bill build() {
            Bill bill = new Bill();
            bill.id = this.id;
            bill.billingDate = this.billingDate;
            bill.customerId = this.customerId;
            bill.productItems = this.productItems;
            bill.customer = this.customer;
            return bill;
        }
    }
    // Getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Date getBillingDate() {
        return billingDate;
    }
    public void setBillingDate(Date billingDate) {
        this.billingDate = billingDate;
    }
    public long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }
    public List<ProductItem> getProductItems() {
        return productItems;
    }
    public void setProductItems(List<ProductItem> productItems) {
        this.productItems = productItems;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
