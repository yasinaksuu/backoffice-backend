package com.omniteam.backofisbackend.service.implementation;


import com.omniteam.backofisbackend.dto.PagedDataWrapper;
import com.omniteam.backofisbackend.dto.category.CategoryDTO;
import com.omniteam.backofisbackend.dto.customer.CustomerUpdateDto;
import com.omniteam.backofisbackend.dto.order.OrderDto;
import com.omniteam.backofisbackend.dto.product.ProductDto;
import com.omniteam.backofisbackend.dto.product.ProductGetAllDto;
import com.omniteam.backofisbackend.dto.product.ProductUpdateDTO;
import com.omniteam.backofisbackend.entity.*;
import com.omniteam.backofisbackend.repository.*;
import com.omniteam.backofisbackend.repository.productspecification.ProductSpec;
import com.omniteam.backofisbackend.requests.ProductGetAllRequest;
import com.omniteam.backofisbackend.service.ProductService;
import com.omniteam.backofisbackend.shared.constant.ResultMessage;
import com.omniteam.backofisbackend.shared.mapper.ProductMapper;
import com.omniteam.backofisbackend.shared.result.DataResult;
import com.omniteam.backofisbackend.shared.result.Result;
import com.omniteam.backofisbackend.shared.result.SuccessDataResult;
import com.omniteam.backofisbackend.shared.result.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
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

    @Autowired
    private ProductPriceRepository productPriceRepository;



    @Transactional
    public Result  saveProductToDB(MultipartFile file ,String productName,String description,Integer unitsInStock,String barcode,Integer categoryId,List<Integer> attributeId,Double actualPrice,String shortDescription) throws IOException {
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
         product.setShortDescription(shortDescription);


          ProductPrice productPrice= new ProductPrice();
          productPrice.setActualPrice(actualPrice);


        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("//download")
                .path(fileName)
                .toUriString();

        productImage.setFilePath(url);


        productRepository.save(product);

        productImage.setProduct(product);
        productPrice.setProduct(product);
        productImageRepository.save(productImage);
        productAttributeTermRepository.saveAll(productAttributeTerms);
        productPriceRepository.save(productPrice);
        return new SuccessResult(ResultMessage.PRODUCT_SAVE);
    }



    @Override
    public DataResult<PagedDataWrapper<ProductDto>> getAll(ProductGetAllRequest productGetAllRequest) {
        Pageable pageable = PageRequest.of(productGetAllRequest.getPage(),productGetAllRequest.getSize());

        Page<Product> productPage =
                productRepository.findAll(
                        ProductSpec.getAllByFilter(
                                productGetAllRequest.getBarcode(),
                                productGetAllRequest.getProductName(),
                                productGetAllRequest.getDescription(),
                                productGetAllRequest.getMinPrice(),
                                productGetAllRequest.getMaxPrice(),
                                productGetAllRequest.getStartDate(),
                                productGetAllRequest.getEndDate()
                        ),pageable);



        List<ProductDto> productDtoList  = productMapper.mapToDTOs(productPage.getContent());

        PagedDataWrapper<ProductDto> pagedDataWrapper = new PagedDataWrapper<>(
                productDtoList,
                productPage.getNumber(),
                productPage.getSize(),
                productPage.getTotalElements(),
                productPage.getTotalPages(),
                productPage.isLast()
        );


        return new SuccessDataResult<>(pagedDataWrapper);

    }

    public DataResult<ProductDto> getById(Integer productId){
        Product product = productRepository.getById(productId);
        ProductDto productDto = productMapper.mapToDTO(product);
        return new SuccessDataResult<>(productDto);
    }


    public Result productUpdate(ProductUpdateDTO productUpdateDTO) {
        Product productToUpdate = productRepository.getById(productUpdateDTO.getProductId());
        if (Objects.nonNull(productToUpdate)) {
            if (productUpdateDTO.getProductName()!=null) {
                productToUpdate.setProductName(productUpdateDTO.getProductName());
            }

            if (productUpdateDTO.getDescription()!=null){
                productToUpdate.setDescription(productUpdateDTO.getDescription());
            }

            if (productUpdateDTO.getShortDescription()!=null){
                productToUpdate.setShortDescription(productUpdateDTO.getShortDescription());
            }

            if (productUpdateDTO.getUnitsInStock()!=null){
                productToUpdate.setUnitsInStock(productUpdateDTO.getUnitsInStock());
            }

            if (productUpdateDTO.getUnitsInStock()!=null){
                productToUpdate.setUnitsInStock(productUpdateDTO.getUnitsInStock());
            }

            if (productUpdateDTO.getActualPrice()!=null){
                ProductPrice productPrice = productPriceRepository.findByProduct_ProductId(productUpdateDTO.getProductId());
                productPrice.setActualPrice(productUpdateDTO.getActualPrice());
                productPriceRepository.save(productPrice);
            }

            if (productUpdateDTO.getCategoryId()!=null){
                Category category = categoryRepository.getById(productUpdateDTO.getCategoryId());
                productToUpdate.setCategory(category);

            }
            if (productUpdateDTO.getAttributeTermId()!=null){
                AttributeTerm attributeTerm =attributeTermRepository.getById(productUpdateDTO.getAttributeTermId());
                ProductAttributeTerm productAttributeTerm = productAttributeTermRepository.findByProduct_ProductId(productUpdateDTO.getProductId());
                productAttributeTerm.setAttributeTerm(attributeTerm);
                productAttributeTerm.setAttribute(attributeTerm.getAttribute());
                productAttributeTermRepository.save(productAttributeTerm);
            }
        }

        productRepository.save(productToUpdate);
         return new SuccessResult(ResultMessage.PRODUCT_UPDATED);
    }


}
