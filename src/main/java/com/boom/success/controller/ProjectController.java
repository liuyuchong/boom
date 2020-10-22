package com.boom.success.controller;

import com.boom.success.bo.Project;
import com.boom.success.consts.GeneralCode;
import com.boom.success.consts.Result;
import com.boom.success.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/api/project", method = RequestMethod.POST)
    public Result<Boolean> addProject(@RequestBody Project project) {
        if (StringUtils.isEmpty(project.getName())) {
            return Result.fail(GeneralCode.Param_Error);
        }
        return Result.success(projectService.addProject(project.getName()));
    }

    @RequestMapping(value = "/api/project", method = RequestMethod.PUT)
    public Result<Boolean> update(@RequestBody Project project) {
        if (project.getId() == null || StringUtils.isEmpty(project.getName())) {
            return Result.fail(GeneralCode.Param_Error);
        }
        return Result.success(projectService.update(project));
    }

    @RequestMapping(value = "/api/project", method = RequestMethod.DELETE)
    public Result<Boolean> update(@RequestParam Long id) {
        return Result.success(projectService.delete(id));
    }

    @RequestMapping(value = "/api/projects", method = RequestMethod.GET)
    public Result<List<Project>> getProjects(){
        return Result.success(projectService.getProjects());
    }
}
