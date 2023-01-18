function initHtml(){
    var user=document.cookie.split(";")[0].split("=")[1]; 

    var info=JSON.parse(document.cookie.split(";")[1].split("=")[1]); 
    if(user=="student"){
        $("#adminMenu").css('display','none'); 
        $("#teacherMenu").css('display','none'); 
        $("#user").html(info.sname); 
        $("#mainContent").attr("src","mypage/student/studentCourse.html")
        

    }
    else if(user=="teacher"){
        $("#adminMenu").css('display','none'); 
        $("#studentMenu").css('display','none'); 
        $("#user").html(info.tname); 
        $("#mainContent").attr("src","mypage/teacher/teacherCourse.html")

    }  
    else if(user=="admin"){  
        $("#studentMenu").css('display','none'); 
        $("#teacherMenu").css('display','none'); 
        $("#user").html(info.aname); 
        $("#mainContent").attr("src","mypage/admin/student.html")
    }
    else{
        $("#adminMenu").css('display','none'); 
        $("#studentMenu").css('display','none'); 
        $("#teacherMenu").css('display','none'); 
    }
}