package com.example.demo.Service;

import com.example.demo.Entity.Mobile;
import com.example.demo.Repository.mobileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class mobileService {


    @Autowired
    private mobileRepository MobileRepository;

    public List<Mobile> getallmobile()
    {
        return MobileRepository.findAll();
    }

    public Mobile addMobile(Mobile mobile)
    {
        return MobileRepository.save(mobile);
    }

    public void deletemobile(Long id)
    {
        MobileRepository.deleteById(id);
    }

    public Mobile updateMobile(Long id,Mobile newMobile)
    {
        Mobile existingMobile=MobileRepository.findById(id).orElseThrow(()->new RuntimeException("Mobile Not Found"));
        existingMobile.setBrand(newMobile.getBrand());
        existingMobile.setModel(newMobile.getModel());
        existingMobile.setPrice(newMobile.getPrice());
        return MobileRepository.save(existingMobile);
    }

}
