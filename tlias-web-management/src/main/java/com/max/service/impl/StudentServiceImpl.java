package com.max.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.max.dto.StudentDTO;
import com.max.mapper.ClazzMapper;
import com.max.mapper.StudentMapper;
import com.max.pojo.Clazz;
import com.max.pojo.PageResult;
import com.max.pojo.Student;
import com.max.pojo.StudentQueryParam;
import com.max.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public PageResult<StudentDTO> page(StudentQueryParam studentQueryParam) {

        PageHelper.startPage(studentQueryParam.getPage(), studentQueryParam.getPageSize());
        List<StudentDTO> studentList = studentMapper.list(studentQueryParam);

        for (StudentDTO studentDTO : studentList) {
            Clazz clazzName = clazzMapper.getById(studentDTO.getClazzId());
            Clazz clazz = clazzMapper.getById(studentDTO.getClazzId());
            if(clazz != null){
                studentDTO.setClazzName(clazz.getName());
            } else {
                studentDTO.setClazzName(null);
            }
        }

        Page<StudentDTO> p = (Page<StudentDTO>) studentList;

        return new PageResult<>(p.getTotal(), p.getResult());
    }

    @Override
    public void delete(List<Integer> ids) {

        studentMapper.deleteById(ids);
    }

    @Override
    public void save(Student student) {

        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());

        studentMapper.insert(student);
    }

    @Override
    public Student getInfo(Integer id) {

        return studentMapper.getById(id);
    }

    @Override
    public void update(Student student) {

        Student byId = studentMapper.getById(student.getId());

        if(byId == null){
            throw new RuntimeException("要修改的学员不存在");
        }

        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

    @Override
    public void violation(Integer id, Integer score) {

        Student student = studentMapper.getById(id);
        if (student == null) {
            throw new IllegalArgumentException("学员不存在");
        }
        studentMapper.countViolation(id, score);
    }

}
