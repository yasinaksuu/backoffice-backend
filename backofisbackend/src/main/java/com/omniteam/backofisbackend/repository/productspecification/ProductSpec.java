package com.omniteam.backofisbackend.repository.productspecification;

import com.omniteam.backofisbackend.entity.Order;
import com.omniteam.backofisbackend.entity.OrderDetail;
import com.omniteam.backofisbackend.entity.Product;
import com.omniteam.backofisbackend.entity.ProductPrice;
import org.hibernate.Metamodel;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;

public class ProductSpec {

    public static Specification<Product> getAllByFilter( String barcode,String productName, String description,Double minPrice,Double maxPrice, LocalDateTime startDate, LocalDateTime endDate) {
        return Specification
                .where(getProductByBarcode(barcode).and(getProductByProductName(productName)));

               // .or(getProductByDescription(description))
                //.or(getProductByDoubleRange(minPrice,maxPrice))
                //.or(getProductByDateTimeRange(startDate,endDate)
               // );
    }


/*
    private static Specification<Product> getProductByProductId(Integer productId) {
        return (root, query, criteriaBuilder) -> {
            if(productId == 0){
                return criteriaBuilder.conjunction();
            }
            Predicate equalPredicate = criteriaBuilder.equal(root.get("product"), productId);
            return equalPredicate;
        };
    }
*/

    public static Specification<Product> getProductByProductName(String productName) {
        return (root, query, criteriaBuilder) -> {
            if(productName == null || productName.isEmpty()){
                return criteriaBuilder.conjunction();
            }
            Predicate equalPredicate = criteriaBuilder.like(root.get("productName"), productName);

            return equalPredicate;
        };
    }
    private static Specification<Product> getProductByDescription(String description) {
        return (root, query, criteriaBuilder) -> {
            if(description == null || description.isEmpty()){
                return criteriaBuilder.conjunction();
            }
            Predicate equalPredicate = criteriaBuilder.equal(root.get("description"), description);
            return equalPredicate;
        };
    }
    public static Specification<Product> getProductByBarcode(String barcode) {
        return (root, query, criteriaBuilder) -> {
            if(barcode == null || barcode.isEmpty()){
                return criteriaBuilder.conjunction();
            }
            Predicate equalPredicate = criteriaBuilder.like(root.get("barcode"), barcode);
            return equalPredicate;
        };
    }


    private static Specification<Product> getProductByDoubleRange(Double minPrice, Double maxPrice) {
        return (root, query, criteriaBuilder) -> {
        // ListJoin<Product, ProductPrice> productPriceListJoin  = root.joinList("Product_.productPrices");

              if(minPrice==null && maxPrice ==null){
                return criteriaBuilder.conjunction();
            }
            Predicate betweenPredicate = criteriaBuilder.between(root.get("actual_price"),minPrice,maxPrice);


            return betweenPredicate;
        };
    }



    private static Specification<Product> getProductByDateTimeRange(LocalDateTime startDate, LocalDateTime endDate) {
        return (root, query, criteriaBuilder) -> {
            if(startDate==null && endDate ==null){
                return criteriaBuilder.conjunction();
            }
            Predicate betweenPredicate = criteriaBuilder.between(root.get("createdDate"),startDate,endDate);
            return betweenPredicate;
        };
    }






}
