package vsb.vis.cz.moviezzz.controllers;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vsb.vis.cz.moviezzz.mappers.CustomerMapper;
import vsb.vis.cz.moviezzz.models.LoginModel;

@RestController
@RequestMapping("/login")
@CrossOrigin( origins = "*")
public class LoginController {

    private CustomerMapper customerMapper;

    @Autowired
    public void setCustomerMapper(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Long login(@RequestBody LoginModel loginModel) throws NotFoundException {
        return customerMapper.authenticateUser(loginModel);
    }


}
