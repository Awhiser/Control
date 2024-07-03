package com.sisi.control.repository;

import com.sisi.control.model.project.Project;
import com.sisi.control.model.project.ProjectVo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProjectRepository extends AbastractRepository<Project> {

}
