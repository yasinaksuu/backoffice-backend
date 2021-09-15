package com.omniteam.backofisbackend.repository.productspecification;


import com.omniteam.backofisbackend.entity.Product;
import com.omniteam.backofisbackend.entity.ProductPrice;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.time.LocalDateTime;

public class ProductSpec {

    public static Specification<Product> getAllByFilter( String barcode,String productName, String description,Double minPrice,Double maxPrice, LocalDateTime startDate, LocalDateTime endDate) {
        return Specification
                .where(getProductByBarcode(barcode)
                        .and(getProductByProductName(productName))
                        .and(getProductByDescription(description))
                        .and(getProductByMinPrice(minPrice))
                        .and(getProductByMaxPrice(maxPrice))
                        .and(getProductByStartDate(startDate))
                        .and(getProductByEndDate(endDate)));


    }




    public static Specification<Product> getProductByProductName(String productName) {
        return (root, query, criteriaBuilder) -> {
            if(productName == null || productName.isEmpty()){
                return criteriaBuilder.conjunction();
            }
            Predicate likePredicate = criteriaBuilder.like(root.get("productName"), productName);

            return likePredicate;
        };
    }
    private static Specification<Product> getProductByDescription(String description) {
        return (root, query, criteriaBuilder) -> {
            if(description == null || description.isEmpty()){
                return criteriaBuilder.conjunction();
            }
            Predicate likePredicate = criteriaBuilder.like(root.get("description"), description);
            return likePredicate;
        };
    }
    public static Specification<Product> getProductByBarcode(String barcode) {
        return (root, query, criteriaBuilder) -> {
            if(barcode == null || barcode.isEmpty()){
                return criteriaBuilder.conjunction();
            }
            Predicate likePredicate = criteriaBuilder.like(root.get("barcode"), barcode);
            return likePredicate;
        };
    }


    private static Specification<Product> getProductByMinPrice(Double minPrice) {
        return (root, query, criteriaBuilder) -> {
        ListJoin<Product, ProductPrice> productPriceListJoin  = root.joinList("productPrices");


              if(minPrice==null){
                return criteriaBuilder.conjunction();
            }


            Predicate betweenPredicate = criteriaBuilder.greaterThanOrEqualTo(productPriceListJoin.get("actualPrice"),minPrice);


            return betweenPredicate;
        };
    }

    private static Specification<Product> getProductByMaxPrice(Double maxPrice) {
        return (root, query, criteriaBuilder) -> {
            ListJoin<Product, ProductPrice> productPriceListJoin  = root.joinList("productPrices");


            if(maxPrice==null){
                return criteriaBuilder.conjunction();
            }


            Predicate betweenPredicate = criteriaBuilder.lessThanOrEqualTo(productPriceListJoin.get("actualPrice"),maxPrice);


            return betweenPredicate;
        };
    }



    private static Specification<Product> getProductByStartDate(LocalDateTime startDate) {
        return (root, query, criteriaBuilder) -> {
            if(startDate==null ){
                return criteriaBuilder.conjunction();
            }
            Predicate betweenPredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("createdDate"),startDate);
            return betweenPredicate;
        };
    }


    private static Specification<Product> getProductByEndDate(LocalDateTime endDate) {
        return (root, query, criteriaBuilder) -> {
            if(endDate==null ){
                return criteriaBuilder.conjunction();
            }
            Predicate betweenPredicate = criteriaBuilder.lessThanOrEqualTo(root.get("createdDate"),endDate);
            return betweenPredicate;
        };
    }






}
