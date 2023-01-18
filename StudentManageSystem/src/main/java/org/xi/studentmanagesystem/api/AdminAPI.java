package org.xi.studentmanagesystem.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;
import org.xi.studentmanagesystem.entity.*;
import org.xi.studentmanagesystem.inerceptor.GlobalConst;
import org.xi.studentmanagesystem.service.AdminService;
import org.xi.studentmanagesystem.service.CourseService;
import org.xi.studentmanagesystem.service.StudentService;
import org.xi.studentmanagesystem.service.TeacherService;
import org.xi.studentmanagesystem.utils.PageUtils;
import org.xi.studentmanagesystem.utils.RUtil;
import org.xi.studentmanagesystem.vo.R;

@RestController
@RequestMapping("/admin")
public class AdminAPI {
    @Autowired
    AdminService adminService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    StudentService studentService;

    @Autowired
    CourseService courseService;




    @GetMapping("/login")
    R login(Integer  id, String password , HttpServletRequest httpServletRequest){
        Admin admin = adminService.getByAidAndApassword(id, password);
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute(GlobalConst.ADMIN_SESSION_KEY.getMsg(), "1");
        session.setAttribute(GlobalConst.ID_SESSION_KEY.getMsg(),admin.getAid());
        session.removeAttribute(GlobalConst.STUDENT_SESSION_KEY.getMsg());
        session.removeAttribute(GlobalConst.TEACHER_SESSION_KEY.getMsg());
        return RUtil.sucess(admin);
    }

    @GetMapping("get/teachers")
    R getTeachers(@RequestParam(value = "page", defaultValue = "0") Integer page,
                 @RequestParam(value = "size", defaultValue = "10") Integer size,
                 Teacher teacher,
                 String direct
    ){
        Sort sort = null;
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().
                withMatcher("tname",ExampleMatcher.GenericPropertyMatchers.contains()).
                withMatcher("tpassword",ExampleMatcher.GenericPropertyMatchers.exact()).
                withMatcher("tid",ExampleMatcher.GenericPropertyMatchers.exact()).
                withMatcher("did",ExampleMatcher.GenericPropertyMatchers.exact());

        Example<Teacher> example=Example.of(teacher,exampleMatcher);
        if(direct==null || direct.equals("")){
            sort = Sort.by(Sort.Direction.DESC, "tid");
        }
        else if(direct.equals("DESC")){
            sort = Sort.by(Sort.Direction.DESC, "tid");
        }
        else {
            sort = Sort.by(Sort.Direction.ASC, "tid");
        }
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Teacher> teachers = teacherService.getAll(example,pageable);
        PageUtils pageUntils = new PageUtils(teachers.getContent(), teachers.getTotalElements());
        return RUtil.sucess(pageUntils);
    }

    @PostMapping("insert/teacher")
    R insertTeacher(Teacher teacher){
        Teacher teacher1 = teacherService.insert(teacher);
        return RUtil.sucess(teacher1);
    }

    @PostMapping("update/teacher")
    R updateTeacher(Teacher teacher){
        Teacher teacher1 = teacherService.update(teacher);
        return RUtil.sucess(teacher1);
    }

    @DeleteMapping("delete/teacher")
    R deleteTeacher(Teacher teacher){
        Teacher teacher1 = teacherService.delete(teacher);
        return RUtil.sucess(teacher1);
    }
    @GetMapping("get/teachercourses")
    R getAllTeacherCourse(@RequestParam(value = "page", defaultValue = "0") Integer page,
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

    @PostMapping("insert/teachercourses")
    R insertTeacherCourse(TC tc){
        TC tc1 = courseService.insert(tc);
        return RUtil.sucess(tc1);
    }

    @PostMapping("update/teachercourses")
    R updateTeacherCourse(TC tc){
        TC tc1 = courseService.update(tc);
        return RUtil.sucess(tc1);
    }

    @DeleteMapping("delete/teachercourses")
    R deleteTeacherCourse(TC tc){
        TC tc1 = courseService.delete(tc);
        return RUtil.sucess(tc1);
    }




    @GetMapping("get/students")
    R getStudents(@RequestParam(value = "page", defaultValue = "0") Integer page,
                 @RequestParam(value = "size", defaultValue = "10") Integer size,
                 Student student,
                 String direct
    ){
        Sort sort = null;
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().
                withMatcher("sname",ExampleMatcher.GenericPropertyMatchers.contains()).
                withMatcher("spassword",ExampleMatcher.GenericPropertyMatchers.exact()).
                withMatcher("gender",ExampleMatcher.GenericPropertyMatchers.contains()).
                withMatcher("sid",ExampleMatcher.GenericPropertyMatchers.exact()).
                withMatcher("pid",ExampleMatcher.GenericPropertyMatchers.exact());

        Example<Student> example=Example.of(student,exampleMatcher);
        if(direct==null || direct.equals("")){
            sort = Sort.by(Sort.Direction.DESC, "sid");
        }
        else if(direct.equals("DESC")){
            sort = Sort.by(Sort.Direction.DESC, "sid");
        }
        else {
            sort = Sort.by(Sort.Direction.ASC, "sid");
        }
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Student> students = studentService.getAll(example,pageable);
        PageUtils pageUntils = new PageUtils(students.getContent(), students.getTotalElements());
        return RUtil.sucess(pageUntils);
    }

    @PostMapping("insert/student")
    R insertStudent(Student student){
        Student student1 = studentService.insert(student);
        return RUtil.sucess(student1);
    }

    @PostMapping("update/student")
    R updateStudent(Student student){
        Student student1 = studentService.update(student);
        return RUtil.sucess(student1);
    }

    @DeleteMapping("delete/student")
    R deleteStudent(Student student){
        Student student1 = studentService.delete(student);
        return RUtil.sucess(student1);
    }


    @GetMapping("get/studentcourses")
    R getAllSC(@RequestParam(value = "page", defaultValue = "0") Integer page,
               @RequestParam(value = "size", defaultValue = "10") Integer size,
               StudentCourseInfo studentCourseInfo,
               String direct,
               HttpServletRequest httpServletRequest

    ){
        Sort sort=null;
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

    @PostMapping("insert/studentcourses")
    R insertStudentCourse(SC sc){
        SC sc1 = courseService.insert(sc);
        return RUtil.sucess(sc1);
    }

    @PostMapping("update/studentcourses")
    R updateStudentCourse(SC sc){
        SC sc1 = courseService.update(sc);
        return RUtil.sucess(sc1);
    }

    @DeleteMapping("delete/studentcourses")
    R deleteStudentCourse(SC sc){
        SC sc1 = courseService.delete(sc);
        return RUtil.sucess(sc1);
    }







}
