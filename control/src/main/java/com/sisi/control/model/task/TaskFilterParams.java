package com.sisi.control.model.task;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TaskFilterParams {
    public List<String> types = new ArrayList<>();
}
