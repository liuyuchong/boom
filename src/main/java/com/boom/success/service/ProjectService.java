package com.boom.success.service;

import com.boom.success.bo.Project;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private Dao dao;

    public boolean addProject(String name) {
        Project project = dao.fetch(Project.class, Cnd.where("name", "=", name));
        if (project != null) {
            return false;
        }
        Project project1 = new Project();
        project1.setName(name);
        dao.insert(project1);
        return true;
    }

    public boolean update(Project project) {
        int count = dao.update(project);
        return count == 1;
    }

    public List<Project> getProjects() {
        return dao.query(Project.class,Cnd.NEW());
    }

    public boolean delete(Long id) {
        return dao.delete(Project.class, id)==1;
    }
}
