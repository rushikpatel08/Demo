package com.example.demo.Controller;

import com.example.demo.Entity.Mobile;
import com.example.demo.Service.mobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mobile")
//@CrossOrigin(origins = "http://34.204.75.205")
@CrossOrigin(origins = "http://34.204.75.205")
public class mobileController {


    @Autowired
    private mobileService MobileService;

    @GetMapping("/getallmobile")
    public List<Mobile> displaymobile()
    {
        return MobileService.getallmobile();

    }

    @PostMapping("/addmobile")
    public Mobile insertmobile(@RequestBody Mobile mobile)
    {
        return MobileService.addMobile(mobile);
    }

    @DeleteMapping("/deletemobile/{id}")
    public void deleteMobile(@PathVariable Long id)
    {
        MobileService.deletemobile(id);
    }

    @PutMapping("/updatemobile/{id}")
    public Mobile updatemobile(@PathVariable Long id,@RequestBody Mobile mobile)
    {
        return MobileService.updateMobile(id,mobile);
    }


}
