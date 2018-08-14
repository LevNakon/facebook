package com.ua.lev_neko.utils;

import com.ua.lev_neko.models.Customer;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CustomerValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(Customer.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Customer customer = (Customer) o;
        if (customer.getUsername().length() <3){
            errors.rejectValue("username","message.length.error");
        }
    }
}
