package org.xi.studentmanagesystem.api;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import org.xi.studentmanagesystem.entity.*;
import org.xi.studentmanagesystem.exception.REnum;
import org.xi.studentmanagesystem.exception.RException;
import org.xi.studentmanagesystem.inerceptor.GlobalConst;
import org.xi.studentmanagesystem.service.CourseService;
import org.xi.studentmanagesystem.service.TeacherService;
import org.xi.studentmanagesystem.utils.PageUtils;
import org.xi.studentmanagesystem.utils.RUtil;
import org.xi.studentmanagesystem.vo.R;

@RestController
@RequestMapping("/teacher")
public class TeacherAPI {
    @Autowired
    TeacherService teacherService;

    @Autowired
    CourseService courseService;

    @GetMapping ("/login")
    R login(Integer  id, String password , HttpServletRequest httpServletRequest){
        Teacher teacher = teacherService.getByTidAndTpassword(id, password);
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute(GlobalConst.TEACHER_SESSION_KEY.getMsg(), "1");
        session.setAttribute(GlobalConst.ID_SESSION_KEY.getMsg(),teacher.getTid());
        session.removeAttribute(GlobalConst.STUDENT_SESSION_KEY.getMsg());
        session.removeAttribute(GlobalConst.ADMIN_SESSION_KEY.getMsg());
        return RUtil.sucess(teacher);
    }

    @GetMapping("get/teachercourses")
    R getAllTeacherCourse(@RequestParam(value = "page", defaultValue = "0") Integer page,
                          @RequestParam(value = "size", defaultValue = "10") Integer size,
                          TeacherCourseInfo teacherCourseInfo,
                          HttpServletRequest httpServletRequest
    ){
        Integer tid=(Integer)httpServletRequest.getSession().getAttribute(GlobalConst.ID_SESSION_KEY.getMsg());
        teacherCourseInfo.setTid(tid);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().
                withMatcher("tname",ExampleMatcher.GenericPropertyMatchers.contains()).
                withMatcher("cname",ExampleMatcher.GenericPropertyMatchers.contains()).
                withMatcher("cid",ExampleMatcher.GenericPropertyMatchers.exact()).
                withMatcher("tid",ExampleMatcher.GenericPropertyMatchers.exact());
        Example<TeacherCourseInfo> example=Example.of(teacherCourseInfo,exampleMatcher);
        Pageable pageable = PageRequest.of(page, size);
        Page<TeacherCourseInfo> students = courseService.getAllTC(example,pageable);
        PageUtils pageUntils = new PageUtils(students.getContent(), students.getTotalElements());
        return RUtil.sucess(pageUntils);
    }


    @GetMapping("get/students")
    R getAllSC(@RequestParam(value = "page", defaultValue = "0") Integer page,
               @RequestParam(value = "size", defaultValue = "10") Integer size,
               StudentCourseInfo studentCourseInfo,
               String direct,
               HttpServletRequest httpServletRequest

    ){
        Sort sort = null;
        Integer tid =(Integer) httpServletRequest.getSession().getAttribute(GlobalConst.ID_SESSION_KEY.getMsg());
        studentCourseInfo.setTid(tid);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().
                withMatcher("sname",ExampleMatcher.GenericPropertyMatchers.contains()).
                withMatcher("tname",ExampleMatcher.GenericPropertyMatchers.contains()).
                withMatcher("cname",ExampleMatcher.GenericPropertyMatchers.contains()).
                withMatcher("sid",ExampleMatcher.GenericPropertyMatchers.exact()).
                withMatcher("tcid",ExampleMatcher.GenericPropertyMatchers.exact()).
                withMatcher("tid",ExampleMatcher.GenericPropertyMatchers.exact());

        Example<StudentCourseInfo> example=Example.of(studentCourseInfo,exampleMatcher);
        if(direct==null || direct.equals("")){
            sort = Sort.by(Sort.Direction.DESC, "score");
        }
        else if(direct.equals("DESC")){
            sort = Sort.by(Sort.Direction.DESC, "score");
        }
        else {
            sort = Sort.by(Sort.Direction.ASC, "score");
        }
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<StudentCourseInfo> students = courseService.getAllSC(example,pageable);
        PageUtils pageUntils = new PageUtils(students.getContent(), students.getTotalElements());
        return RUtil.sucess(pageUntils);
    }

    @PostMapping("update/score")
    R updateScore(SC sc,HttpServletRequest httpServletRequest){
        Integer tid =(Integer) httpServletRequest.getSession().getAttribute(GlobalConst.ID_SESSION_KEY.getMsg());
        SC sc1=null;
        try{
            TC tc = courseService.getByTcid(sc.getTcid());
            if(tc.getTid().equals(tid)){
                 sc1= courseService.update(sc);
            }
            else{
                throw new RException(REnum.SESSION_ERROR);
            }

        }
        catch (Exception exception){
            throw exception;
        }
        return RUtil.sucess(sc1);
    }

}
