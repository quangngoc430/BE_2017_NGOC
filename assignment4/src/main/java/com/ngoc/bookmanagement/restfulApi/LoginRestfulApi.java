package com.ngoc.bookmanagement.restfulApi;

import com.ngoc.bookmanagement.model.User;
import com.ngoc.bookmanagement.repository.UserRepository;
import com.ngoc.bookmanagement.service.PasswordEncryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class LoginRestfulApi {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncryption passwordEncryption;


    @PostMapping(value = "/api/login", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> loginPost(@RequestBody @Valid User user,
                                       BindingResult bindingResult,
                                       HttpServletRequest request){
        Message message = new Message();

        if(bindingResult.hasErrors())
        {
            message.setMessage(bindingResult.getAllErrors().toString());
            return new ResponseEntity<Message>(message, HttpStatus.BAD_REQUEST);
        }

        String encryptingPassword = passwordEncryption.encryptPassword(user.getPassword());

        if (!userRepository.existsByEmailAndPassword(user.getEmail(), encryptingPassword)){
            message.setMessage("Email is not exist");
            return new ResponseEntity<Message>(message, HttpStatus.BAD_REQUEST);
        }

        request.getSession().setAttribute("userLogin", userRepository.findByEmail(user.getEmail()));

        System.out.println(((User) request.getSession().getAttribute("userLogin")).toString());
        message.setMessage("Login successfully");
        return new ResponseEntity<Message>(message, HttpStatus.OK);
    }

    class Message{
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Message() {
        }

        Message(String message) {
            this.message = message;
        }
    }

}