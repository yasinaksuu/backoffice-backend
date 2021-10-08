package com.omniteam.backofisbackend.service.implementation;

import com.omniteam.backofisbackend.dto.category.CategoryDTO;
import com.omniteam.backofisbackend.dto.product.ProductAttributeTermDTO;
import com.omniteam.backofisbackend.dto.product.ProductDto;
import com.omniteam.backofisbackend.dto.product.ProductPriceDTO;
import com.omniteam.backofisbackend.dto.product.ProductSaveRequestDTO;
import com.omniteam.backofisbackend.entity.*;
import com.omniteam.backofisbackend.repository.ProductAttributeTermRepository;
import com.omniteam.backofisbackend.repository.ProductImageRepository;
import com.omniteam.backofisbackend.repository.ProductPriceRepository;
import com.omniteam.backofisbackend.repository.ProductRepository;
import com.omniteam.backofisbackend.service.ProductService;
import com.omniteam.backofisbackend.shared.constant.ResultMessage;
import com.omniteam.backofisbackend.shared.mapper.CategoryMapper;
import com.omniteam.backofisbackend.shared.mapper.ProductMapper;
import com.omniteam.backofisbackend.shared.result.DataResult;
import com.omniteam.backofisbackend.shared.result.Result;
import com.omniteam.backofisbackend.shared.result.SuccessResult;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockReset;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Spy
     private ProductMapper productMapper = Mappers.getMapper(ProductMapper.class); ;

    @Mock
    private ProductPriceRepository productPriceRepository;

    @Mock
    private ProductAttributeTermRepository productAttributeTermRepository;

    @Mock
    private ProductImageRepository productImageRepository;

    @Test
    @DisplayName(value = "Must Find Product by Product ID")
   public void getById() {
        Product product = new Product();
        product.setProductId(11);

        Mockito.when(productRepository.getById(Mockito.any())).thenReturn(product);

        ProductDto productDto = new ProductDto();
        productDto.setProductId(1);

        Mockito.when(productMapper.mapToDTO(Mockito.any(Product.class))).thenReturn(productDto);

        DataResult<ProductDto> serviceResult = productService.getById(11);

        Assertions.assertEquals(serviceResult.getData(),productDto);
    }

    @Test
   public void when_productSaveRequestDTO_Expect_saveProductDB() {

        ProductSaveRequestDTO productSaveRequestDTO =  new ProductSaveRequestDTO();
        List<ProductPriceDTO> productPriceDTOs = new ArrayList<>();
        List<ProductAttributeTermDTO> productAttributeTermDTOS = new ArrayList<>();
        ProductAttributeTermDTO productAttributeTermDTO = new ProductAttributeTermDTO();
        ProductPriceDTO productPriceDTO = new ProductPriceDTO();

        productPriceDTO.setProductPriceId(175);
        productPriceDTO.setActualPrice(123.123);
        productPriceDTO.setDiscountedPrice(123.123);
        productPriceDTO.setIsActive(true);
        productPriceDTO.setCreatedDate(LocalDateTime.now());
        productPriceDTOs.add(productPriceDTO);

        productAttributeTermDTO.setProductId(100);
        productAttributeTermDTO.setAttributeId(2);
        productAttributeTermDTO.setAttributeTermId(2);
        productAttributeTermDTOS.add(productAttributeTermDTO);


        productSaveRequestDTO.setProductPriceDTOS(productPriceDTOs);
        productSaveRequestDTO.setProductId(100);
        productSaveRequestDTO.setProductName("aaaaaaa");
        productSaveRequestDTO.setBarcode("22222");
        productSaveRequestDTO.setCategoryId(4);
        productSaveRequestDTO.setDescription("123123");
        productSaveRequestDTO.setShortDescription("123");
        productSaveRequestDTO.setUnitsInStock(21231);
        productSaveRequestDTO.setProductAttributeTermDTOS(productAttributeTermDTOS);


        Product product = productMapper.mapToEntity(productSaveRequestDTO);
        product.setProductId(100);
        Mockito.when(productRepository.save(Mockito.any())).thenReturn(product);

        List<ProductAttributeTerm> productAttributeTerms =productMapper.mapToProductAttributeTerm(productSaveRequestDTO.getProductAttributeTermDTOS());
        productAttributeTerms.stream().forEach(item->item.setProduct(product));
        Mockito.when(productAttributeTermRepository.saveAll(Mockito.anyList())).thenReturn(productAttributeTerms);


        List<ProductPrice> productPrice = productMapper.mapToEntities(productSaveRequestDTO.getProductPriceDTOS());
        Mockito.when(productPriceRepository.saveAll(Mockito.anyList())).thenReturn(productPrice);


        Integer servisResult = productService.saveProductToDB(productSaveRequestDTO);

       Assertions.assertEquals(servisResult,product.getProductId());

    }

     @Test
    public void Image_Expect_saveProductImageDB() throws IOException {
      Product product = new Product();
      product.setProductId(84);
      Mockito.when(productRepository.getById(Mockito.any())).thenReturn(product);
         List<ProductImage> productImages =new ArrayList<>();
         Mockito.when(productImageRepository.findAllByProduct_ProductId(Mockito.eq(84))).thenReturn(productImages);

         if (productImages!=null){
             Mockito.when(productImageRepository.deleteAllByProduct_ProductId(Mockito.eq(84))).thenReturn(productImages);
         }

        // MockMultipartFile mockMultipartFile = new MockMultipartFile();
         MockMultipartFile file
                 = new MockMultipartFile(
                 "file",
                 "hello.txt",
                 MediaType.TEXT_PLAIN_VALUE,
                 "Hello, World!".getBytes()
         );

         String fileName = StringUtils.cleanPath(file.getOriginalFilename());

         ProductImage productImage = new ProductImage();

         productImage.setImage(Base64.getEncoder().encodeToString(file.getBytes()));

         productImage.setProductImageName(fileName);

        // String url ="//downloadhello.txt";
         String url =new MockHttpServletRequestBuilder(HttpMethod.POST,"//downloadhello.txt").contextPath().pathInfo("//download").pathInfo(fileName).toString();

         productImage.setFilePath(url);
         productImage.setProduct(product);

         Mockito.when(productImageRepository.save(Mockito.any(ProductImage.class))).thenReturn(productImage);

         String message = ResultMessage.PRODUCT_IMAGE_SAVE;
         String serviceResult = productService.saveProductImageDB(file,product.getProductId()).getMessage();

         Assertions.assertEquals(serviceResult,message);
     }

}