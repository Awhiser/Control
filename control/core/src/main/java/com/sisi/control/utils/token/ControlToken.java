package com.sisi.control.utils.token;

import lombok.Data;

@Data
public class ControlToken {
    public String tenantId;
    public String userId;
    public long timeStamp;

}
