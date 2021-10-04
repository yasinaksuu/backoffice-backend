package com.omniteam.backofisbackend.service.implementation;

import com.omniteam.backofisbackend.dto.customer.CustomerAddContactsDto;
import com.omniteam.backofisbackend.dto.customer.CustomerUpdateContactsDto;
import com.omniteam.backofisbackend.dto.customercontact.CustomerContactDto;
import com.omniteam.backofisbackend.dto.customercontact.CustomerContactUpdateDto;
import com.omniteam.backofisbackend.entity.Customer;
import com.omniteam.backofisbackend.entity.CustomerContact;
import com.omniteam.backofisbackend.enums.EnumLogIslemTipi;
import com.omniteam.backofisbackend.repository.CustomerContactRepository;
import com.omniteam.backofisbackend.repository.CustomerRepository;
import com.omniteam.backofisbackend.service.CustomerContactService;
import com.omniteam.backofisbackend.shared.constant.ResultMessage;
import com.omniteam.backofisbackend.shared.mapper.CustomerContactMapper;
import com.omniteam.backofisbackend.shared.result.DataResult;
import com.omniteam.backofisbackend.shared.result.Result;
import com.omniteam.backofisbackend.shared.result.SuccessDataResult;
import com.omniteam.backofisbackend.shared.result.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerContactServiceImpl implements CustomerContactService {
    @Autowired
    private SecurityVerificationServiceImpl securityVerificationService;

    @Autowired
    private  LogServiceImpl logService;

    private final CustomerContactRepository customerContactRepository;
    private final CustomerContactMapper customerContactMapper;
    private final CustomerRepository customerRepository;
    @Autowired
    public CustomerContactServiceImpl(CustomerContactRepository customerContactRepository, CustomerContactMapper customerContactMapper, CustomerRepository customerRepository) {
        this.customerContactRepository = customerContactRepository;
        this.customerContactMapper = customerContactMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public DataResult<List<CustomerContactDto>> getByCustomerId(int customerId) {
        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        List<CustomerContact> customerContacts = this.customerContactRepository.findCustomerContactsByCustomer(customer);
        List<CustomerContactDto> customerContactDtoList = this.customerContactMapper.customerContactDtoList(customerContacts);
        logService.loglama(EnumLogIslemTipi.CustomerGetContacts,securityVerificationService.inquireLoggedInUser());

        return new SuccessDataResult<>(customerContactDtoList);
    }

    @Override
    public Result add(CustomerAddContactsDto customerAddContactsDto) {
        List<CustomerContact> customerContactList =
                this.customerContactMapper.toCustomerContactList(customerAddContactsDto.getCustomerContactAddDtoList());
        Optional<Customer> customerOptional = this.customerRepository.findById(customerAddContactsDto.getCustomerId());

        if (customerOptional.isPresent()){
            Customer customer = customerOptional.get();
            customerContactList.forEach(customerContact -> {
                customerContact.setCustomer(customer);
                if(customerContact.getCity().getCityId() == null){
                    customerContact.setCity(null);
                    customerContact.setCountry(null);
                    customerContact.setDistrict(null);
                }
            });
        }

        this.customerContactRepository.saveAll(customerContactList);
        logService.loglama(EnumLogIslemTipi.CustomerAddContacts,securityVerificationService.inquireLoggedInUser());

        return new SuccessResult("contacts added");
    }

    @Override
    @Transactional
    public Result update(CustomerUpdateContactsDto customerUpdateContactsDto) {
        List<CustomerContactUpdateDto> customerContactUpdateDtoList= customerUpdateContactsDto.getCustomerContactUpdateDtoList();
        customerContactUpdateDtoList.forEach(customerContactUpdateDto -> {
            CustomerContact customerContactToUpdate = this.customerContactRepository.getById(customerContactUpdateDto.getCustomerContactId());
            this.customerContactMapper.update(customerContactToUpdate,customerContactUpdateDto);
            if(customerContactUpdateDto.getCityId()==null || customerContactUpdateDto.getCityId()==0){
                customerContactToUpdate.setCity(null);
            }
            if (customerContactUpdateDto.getCountryId()==null || customerContactUpdateDto.getCountryId()==0){
                customerContactToUpdate.setCountry(null);
            }
            if (customerContactUpdateDto.getDistrictId()==null || customerContactUpdateDto.getDistrictId()==0){
                customerContactToUpdate.setDistrict(null);
            }
            this.customerContactRepository.save(customerContactToUpdate);
        });
        logService.loglama(EnumLogIslemTipi.CustomerUpdateContacts,securityVerificationService.inquireLoggedInUser());

        return new SuccessResult(ResultMessage.CUSTOMER_CONTACT_UPDATED);
    }
}
