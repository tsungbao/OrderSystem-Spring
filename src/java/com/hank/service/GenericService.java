package com.hank.service;

import com.hank.web.action.GenericAction;

public abstract class GenericService {
   private GenericAction action;

    public GenericAction getAction() {
        return action;
    }

    public void setAction(GenericAction action) {
        this.action = action;
    }

}
