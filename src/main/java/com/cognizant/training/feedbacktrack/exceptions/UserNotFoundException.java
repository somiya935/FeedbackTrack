package com.cognizant.training.feedbacktrack.exceptions;

import com.cognizant.training.feedbacktrack.model.User;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String msg){
        super(msg);
    }
}
