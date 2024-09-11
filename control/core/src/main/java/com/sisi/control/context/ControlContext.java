package com.sisi.control.context;

import com.sisi.control.utils.token.ControlToken;
import lombok.Data;

@Data
public class ControlContext {
    public ControlToken token;
    public String tenantId;

    public ControlContext(){

    }

    public ControlContext(String tenantId) {


        this.tenantId = tenantId;
    }


}
