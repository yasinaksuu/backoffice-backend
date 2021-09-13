package com.omniteam.backofisbackend.service.implementation;


import com.omniteam.backofisbackend.dto.PagedDataWrapper;
import com.omniteam.backofisbackend.dto.customer.CustomerAddDto;
import com.omniteam.backofisbackend.dto.product.ProductGetAllDto;
import com.omniteam.backofisbackend.dto.product.ProductGetAllRequest;
import com.omniteam.backofisbackend.dto.product.ProductSaveRequestDTO;
import com.omniteam.backofisbackend.entity.*;
import com.omniteam.backofisbackend.repository.*;
import com.omniteam.backofisbackend.service.ProductService;
import com.omniteam.backofisbackend.shared.constant.ResultMessage;
import com.omniteam.backofisbackend.shared.mapper.ProductMapper;
import com.omniteam.backofisbackend.shared.result.DataResult;
import com.omniteam.backofisbackend.shared.result.Result;
import com.omniteam.backofisbackend.shared.result.SuccessDataResult;
import com.omniteam.backofisbackend.shared.result.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired(required = false)
    private ProductMapper productMapper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AttributeTermRepository  attributeTermRepository;

    @Autowired
    private ProductImageRepository productImageRepository;

    @Autowired
    private ProductAttributeTermRepository productAttributeTermRepository;



    @Transactional
    public void  saveProductToDB(MultipartFile file ,String productName,String description,Integer unitsInStock,String barcode,Integer categoryId,List<Integer> attributeId) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        ProductImage productImage = new ProductImage();
        productImage.setImage(file.getBytes());
        productImage.setProductImageName(fileName);

         Product product = new Product();

         Category category = categoryRepository.getById(categoryId);
         List<AttributeTerm> attributeTerms = attributeTermRepository.findAllById(attributeId);
         List<ProductAttributeTerm> productAttributeTerms = attributeTerms.stream()
                 .map(x->new ProductAttributeTerm(
                         0,product,x.getAttribute(),x
                 )).collect(Collectors.toList());



         product.setProductName(productName);
         product.setUnitsInStock(unitsInStock);
         product.setDescription(description);
         product.setBarcode(barcode);
         product.setCategory(category);
         product.setProductAttributeTerms(productAttributeTerms);



        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("//download")
                .path(fileName)
                .toUriString();

        productImage.setFilePath(url);




        productRepository.save(product);

        productImage.setProduct(product);
        productImageRepository.save(productImage);
        productAttributeTermRepository.saveAll(productAttributeTerms);


    }



    @Override
    public DataResult<PagedDataWrapper<ProductGetAllDto>> getAll(int page, int size, String searchKey) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products =
                this.productRepository.findProductsByProductNameContainingOrDescriptionContaining(
                        searchKey,
                        searchKey,
                        pageable);

        List<ProductGetAllDto> productGetAllDtoList = products.getNumberOfElements() == 0
                ? Collections.emptyList()
                : this.productMapper.toProductGetAllDtoList(products.getContent());


        PagedDataWrapper<ProductGetAllDto> pagedDataWrapper = new PagedDataWrapper<>(
                productGetAllDtoList,
                products.getNumber(),
                products.getSize(),
                products.getTotalElements(),
                products.getTotalPages(),
                products.isLast()
        );

        return new SuccessDataResult<>(pagedDataWrapper);
    }



}
