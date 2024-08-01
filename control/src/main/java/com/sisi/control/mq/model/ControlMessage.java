package com.sisi.control.mq.model;

import com.sisi.control.context.ControlContext;
import com.sisi.control.mq.MQType;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class ControlMessage implements Serializable {
    public MQType mqType;
    public Map<String,Object> params;
    public ControlContext context;
}
