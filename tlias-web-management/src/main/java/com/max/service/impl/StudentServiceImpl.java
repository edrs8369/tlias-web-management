package com.max.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.max.dto.StudentDTO;
import com.max.mapper.ClazzMapper;
import com.max.mapper.StudentMapper;
import com.max.pojo.Clazz;
import com.max.pojo.PageResult;
import com.max.pojo.StudentQueryParam;
import com.max.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            studentDTO.setClazzName(clazzName.getName());
        }

        Page<StudentDTO> p = (Page<StudentDTO>) studentList;

        return new PageResult<>(p.getTotal(), p.getResult());
    }

    @Override
    public void delete(List<Integer> ids) {

        studentMapper.deleteById(ids);
    }
}
