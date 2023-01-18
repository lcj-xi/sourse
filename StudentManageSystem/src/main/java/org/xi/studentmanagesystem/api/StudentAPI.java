package org.xi.studentmanagesystem.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;
import org.xi.studentmanagesystem.entity.SC;
import org.xi.studentmanagesystem.entity.Student;
import org.xi.studentmanagesystem.entity.StudentCourseInfo;
import org.xi.studentmanagesystem.entity.TeacherCourseInfo;
import org.xi.studentmanagesystem.inerceptor.GlobalConst;
import org.xi.studentmanagesystem.service.CourseService;
import org.xi.studentmanagesystem.service.StudentService;
import org.xi.studentmanagesystem.utils.PageUtils;
import org.xi.studentmanagesystem.utils.RUtil;
import org.xi.studentmanagesystem.vo.R;

@RestController
@RequestMapping("/student")
public class StudentAPI {

    @Autowired
    StudentService studentService;

    @Autowired
    CourseService courseService;


    @GetMapping("login")
    R login(Integer  id, String password , HttpServletRequest httpServletRequest){
        Student student =studentService.getBySidAndSpassword(id, password);
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute(GlobalConst.STUDENT_SESSION_KEY.getMsg(), "1");
        session.setAttribute(GlobalConst.ID_SESSION_KEY.getMsg(),student.getSid());
        session.removeAttribute(GlobalConst.ADMIN_SESSION_KEY.getMsg());
        session.removeAttribute(GlobalConst.TEACHER_SESSION_KEY.getMsg());
        return RUtil.sucess(student);
    }

    @GetMapping("get/studentcourses")
    R getAllSC(@RequestParam(value = "page", defaultValue = "0") Integer page,
               @RequestParam(value = "size", defaultValue = "10") Integer size,
               StudentCourseInfo studentCourseInfo,
               String direct,
               HttpServletRequest httpServletRequest

    ){
        Integer sid =(Integer) httpServletRequest.getSession().getAttribute(GlobalConst.ID_SESSION_KEY.getMsg());
        Sort sort = null;
        studentCourseInfo.setSid(sid);
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

    @PostMapping("insert/course")
    R insertCourse(SC sc, HttpServletRequest httpServletRequest){

        Integer sid =(Integer) httpServletRequest.getSession().getAttribute(GlobalConst.ID_SESSION_KEY.getMsg());
        sc.setSid(sid);
        SC sc1 = courseService.insert(sc);
        return RUtil.sucess(sc1);
    }

    @DeleteMapping("delete/course")
    R deleteCourse(SC sc, HttpServletRequest httpServletRequest){

        Integer sid =(Integer) httpServletRequest.getSession().getAttribute(GlobalConst.ID_SESSION_KEY.getMsg());
        sc.setSid(sid);
        SC sc1 = courseService.delete(sc);
        return RUtil.sucess(sc1);
    }


    @GetMapping("get/courses")
    R getCourses(@RequestParam(value = "page", defaultValue = "0") Integer page,
                @RequestParam(value = "size", defaultValue = "10") Integer size,
                TeacherCourseInfo teacherCourseInfo
                ){
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
}
