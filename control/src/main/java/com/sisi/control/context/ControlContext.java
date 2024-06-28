package com.sisi.control.context;

import com.sisi.control.utils.token.ControlToken;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
