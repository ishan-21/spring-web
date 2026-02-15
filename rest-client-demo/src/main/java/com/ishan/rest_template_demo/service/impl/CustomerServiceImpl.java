package com.ishan.rest_template_demo.service.impl;


import com.ishan.rest_template_demo.dto.CompanionDTO;
import com.ishan.rest_template_demo.dto.CustomerDTO;
import com.ishan.rest_template_demo.dto.CustomerDtoForSave;
import com.ishan.rest_template_demo.exception.CustomerNotFoundException;
import com.ishan.rest_template_demo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
    private final RestClient restClient;
    @Value("${target.base.url}")
    private String targetBaseUrl;
    @Value("${customer.not.found}")
    private String customerNotFound;
    @Value("${argument.name}")
    private String argumentName;

    // SAVE THROUGH POST
    public void saveCustomerUsingObject(CustomerDtoForSave customerDtoForSave) {
        try {
            restClient.post()
                    .uri(targetBaseUrl)
                    .body(customerDtoForSave)
                    .retrieve()
                    .toBodilessEntity();
        } catch (RestClientException exception) {
            handleException(exception);
        }
    }

    public void saveCustomerUsingEntity(CustomerDtoForSave customerDtoForSave) {
        try {
            restClient.post()
                    .uri(targetBaseUrl)
                    .body(customerDtoForSave)
                    .retrieve()
                    .toBodilessEntity();
        } catch (RestClientException exception) {
            handleException(exception);
        }
    }

    @Override
    public void saveCustomer(CustomerDtoForSave customerDtoForSave) {
        try {
            restClient.post()
                    .uri(targetBaseUrl)
                    .body(customerDtoForSave)
                    .retrieve()
                    .toBodilessEntity();
        } catch (RestClientException exception) {
            handleException(exception);
        }
    }

    // FIND THROUGH GET

    public CustomerDTO findCustomerUsingObject(int customerId) throws CustomerNotFoundException {
        try {
            String url = UriComponentsBuilder.fromUriString(targetBaseUrl)
                    .queryParam(argumentName, customerId)
                    .build()
                    .toUriString();
            CustomerDTO response = restClient.get()
                    .uri(url)
                    .retrieve()
                    .body(CustomerDTO.class);
            return response;
        } catch (RestClientException exception) {
            handleNotFoundException(exception, customerId);
            return null;
        }
    }

    public CustomerDTO findCustomerUsingEntity(int customerId) throws CustomerNotFoundException {
        try {
            String url = UriComponentsBuilder.fromUriString(targetBaseUrl)
                    .queryParam(argumentName, customerId)
                    .build()
                    .toUriString();
            ResponseEntity<CustomerDTO> responseEntity = restClient.get()
                    .uri(url)
                    .retrieve()
                    .toEntity(CustomerDTO.class);
            return responseEntity.getBody();
        } catch (RestClientException exception) {
            handleNotFoundException(exception, customerId);
            return null;
        }
    }

    @Override
    public CustomerDTO findCustomer(int customerId) throws CustomerNotFoundException {
        try {
            String url = UriComponentsBuilder.fromUriString(targetBaseUrl)
                    .queryParam(argumentName, customerId)
                    .build()
                    .toUriString();
            ResponseEntity<CustomerDTO> responseEntity = restClient.get()
                    .uri(url)
                    .retrieve()
                    .toEntity(CustomerDTO.class);
            return responseEntity.getBody();
        } catch (RestClientException exception) {
            handleNotFoundException(exception, customerId);
            return null;
        }
    }

    // update through PUT

    @Override
    public CustomerDTO updateCustomerDetails(CustomerDTO customerDTO) throws CustomerNotFoundException {
        try{
            ResponseEntity<CustomerDTO> responseEntity = restClient.put()
                    .uri(targetBaseUrl)
                    .body(customerDTO)
                    .retrieve()
                    .toEntity(CustomerDTO.class);
            return responseEntity.getBody();
        } catch (RestClientException exception) {
            handleException(exception);
            return null;
        }
    }

    // delete through DELETE

    public void deleteCustomerUsingObject(int customerId) throws CustomerNotFoundException {
        try {
            String url = UriComponentsBuilder.fromUriString(targetBaseUrl)
                    .queryParam("ownerId", customerId)
                    .build()
                    .toUriString();
            restClient.delete()
                    .uri(url)
                    .retrieve()
                    .toBodilessEntity();
        } catch (RestClientException exception) {
            handleNotFoundException(exception, customerId);
        }
    }


    @Override
    public void deleteCustomer(int customerId) throws CustomerNotFoundException {
        try {
            String url = UriComponentsBuilder.fromUriString(targetBaseUrl)
                    .queryParam("ownerId", customerId)
                    .build()
                    .toUriString();
            restClient.delete()
                    .uri(url)
                    .retrieve()
                    .toBodilessEntity();
        } catch (RestClientException exception) {
            handleNotFoundException(exception, customerId);
        }
    }

    // the below methods are used to handle exceptions

    private void handleNotFoundException(RestClientException exception, int customerId)
            throws CustomerNotFoundException {
        if (exception instanceof HttpClientErrorException.NotFound) {
            throw new CustomerNotFoundException(String.format(customerNotFound, customerId));
        } else {
            handleException(exception);
        }
    }

    private void handleException(RestClientException exception) {
        throw new RestClientException(extractErrorMessage(exception.getMessage()));
    }

    private String extractErrorMessage(String exceptionMessage) {
        Matcher matcher = Pattern.compile("\"message\":\"([^\"]+)\"").matcher(exceptionMessage);
        List<String> messages = new ArrayList<>();
        while (matcher.find()) {
            messages.add(matcher.group(1));
        }
        return messages.isEmpty() ? exceptionMessage : String.join("; ", messages);
    }

}
