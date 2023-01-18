$.ajaxSetup({
    contentType:"application/x-www-form-urlencoded;charset=utf-8",
    complete:function(XMLHttpRequest,textStatus){
        //通过XMLHttpRequest取得响应结果
        var res = XMLHttpRequest.responseText;
        try{
            var jsonData = JSON.parse(res);
            if(jsonData.code == -1){
                //如果超时就处理 ，指定要跳转的页面(比如登陆页)
                alert(jsonData.msg);
                window.location.replace("/");
            }else if(jsonData.code == -999){
                //其他的异常情况,给个提示。
                alert(jsonData.msg);
            }else if(jsonData.code == 0){
                //正常情况就不统一处理了
            }else{
                alert(jsonData.msg);
            }
        }catch(e){
        }
    }
});



function addStudent(){
    var data = $("#formAddStudent").serialize();
    $("#addstudent").modal("hide");
    $("#sid").val("");
    $("#sname").val("");
    $("#spassword").val("");
    $("#gender").val("");

    $.ajax(
        {
            url: "/admin/insert/student",
            method: "post",
            data: data,
            success: function (re) {
                if(re.code==0){
                    alert("添加成功");
                }
                else{
                    alert(re.msg);
                }
                
                loadTable();

            },
            error: function () {
                loadTable();
                alert("出错了！！！");
            }

        }
    )
}



function addTeacher(){
    var data = $("#formAddTeacher").serialize();
    $("#addTeacher").modal("hide");
    $("#tname").val("");
    $("#tpassword").val("");
    $.ajax(
        {
            url: "/admin/insert/teacher",
            method: "post",
            data: data,
            success: function (re) {
                if(re.code==0){
                    alert("添加成功");
                }
            
                loadTable();

            },
            error: function () {
                loadTable();
                alert("出错了！！！");
            }

        }
    )
}

function addStudentCourse(){
    var data = $("#formAddStudentCourse").serialize();
    $("#addStudentCourse").modal("hide");
    $("#sid").val("");
    $("#tcid").val("");
    $.ajax(
        {
            url: "/admin/insert/studentcourses",
            method: "post",
            data: data,
            success: function (re) {
                if(re.code==0){
                    alert("添加成功");
                }
            
                loadTable();

            },
            error: function () {
                loadTable();
                alert("出错了！！！");
            }

        }
    )
}

function addTeacherCourse(){
    var data = $("#formAddTeacherCourse").serialize();
    $("#addTeacherCourse").modal("hide");
    $("#tid").val("");
    $("#cid").val("");
    $.ajax(
        {
            url: "/admin/insert/teachercourses",
            method: "post",
            data: data,
            success: function (re) {
                if(re.code==0){
                    alert("添加成功");
                }
            
                loadTable();

            },
            error: function () {
                loadTable();
                alert("出错了！！！");
            }

        }
    )
}



$("#insert").click(function() {

});


function loadTable() {

    $("#studentTable").bootstrapTable("destroy");
    $('#studentTable').bootstrapTable({

       
        url: "/admin/get/students",
        toolbar:"#toolbar",
        editable:true,
        clickEdit: true,
        showToggle: true,
        pagination: true,       //显示分页条
        showColumns: true,
        showPaginationSwitch: true,     //显示切换分页按钮
        showRefresh: true,      //显示刷新按钮
        striped: true,
        singleSelect: false,
        pageSize: 3,
        pageNumber: 1,
        sidePagination: "server",

        queryParams: function (params) {
            var paraObj = {
                size: params.limit,
                page: params.offset / params.limit,
                sname: $("#sname1").val(),
                sort: "sid",
                direct: "desc",
                gender: $("#gender1").val(),

            };
            return paraObj;
        },
        responseHandler: function (res) {

            if(res.code!=0){
                return{
                    "total":0,
                    "rows":null
                }
            }
            return{
                "total":res.data.total,
                "rows":res.data.rows
            }

        },


        columns: [{
            field: 'sid',
            title: '学号'
        },{
            field: 'sname',
            title: '学生姓名',
            cellStyle:function (value,row,index){
                return {css:{'background-color':'rgba(173,11,12,0.1)'}};
            },
            editable: {
                type: 'text',
                title: '用户名',
                validate: function (v) {
                    if (!v) return '用户名不能为空';
                }
            }
        },{
            field: 'spassword',
            title: '密码',
            cellStyle:function (value,row,index){
                return {css:{'background-color':'rgba(173,11,112,0.1)'}};
            },
            editable: {
                type: 'text',
                title: '密码',
                validate: function (v) {
                    if (!v) return '密码不能为空';

                }
            }
        },{
            field: 'gender',
            title: '性别',
            cellStyle:function (value,row,index){
                if(value =="男" ){
                    return {css:{'background-color':'rgba(173,11,223,0.1)'}}
                }
                if(value =="女"){
                    return {css:{'background-color':'rgba(173,11,12,0.1)'}}
                }
                return '';
            },
            editable: {
                type: 'select',
                title: '性别',
                source:[{value:"男",text:"男"},{value:"女",text:"女"},{value:"",text:"未知"}]
            }
            
        }],
        onEditableSave: function (field, row, oldValue, $el) {

             $.post("/admin/update/student",row,function(re){
                if(re.code==0){
                        alert("修改成功");
                    }
             });
        }
    })


}




function loadTable1() {

    $("#teacherTable").bootstrapTable("destroy");
    $('#teacherTable').bootstrapTable({
        url: "/admin/get/teachers",
        toolbar:"#toolbar",
        editable:true,
        clickEdit: true,
        showToggle: true,
        pagination: true,       //显示分页条
        showColumns: true,
        showPaginationSwitch: true,     //显示切换分页按钮
        showRefresh: true,      //显示刷新按钮
        striped: true,
        singleSelect: false,
        pageSize: 3,
        pageNumber: 1,
        sidePagination: "server",
        sidePagination: "server",

        queryParams: function (params) {
            var paraObj = {
                size: params.limit,
                page: params.offset / params.limit,
                tname: $("#tname1").val(),
                sort: "sid",
                direct: "desc",

            };
            return paraObj;
        },
        responseHandler: function (res) {

            if(res.code!=0){
                return{
                    "total":0,
                    "rows":null
                }
            }
            return{
                "total":res.data.total,
                "rows":res.data.rows
            }

        },


        columns: [{
            field: 'tid',
            title: '学号'
        },{
            field: 'tname',
            title: '教师姓名',
            cellStyle:function (value,row,index){
                return {css:{'background-color':'rgba(173,11,12,0.1)'}};
            },
            editable: {
                type: 'text',
                title: '用户名',
                validate: function (v) {
                    if (!v) return '用户名不能为空';
                }
            }
        },{
            field: 'tpassword',
            title: '密码',
            cellStyle:function (value,row,index){
                return {css:{'background-color':'rgba(173,11,112,0.1)'}};
            },
            editable: {
                type: 'text',
                title: '密码',
                validate: function (v) {
                    if (!v) return '密码不能为空';

                }
            }
        }],
        onEditableSave: function (field, row, oldValue, $el) {

            $.post("/admin/update/teacher",row,function(re){
               if(re.code==0){
                       alert("修改成功");
                   }
            });
       }
    })
}






function loadTable2() {

    $("#studentCourseTable").bootstrapTable("destroy");
    $('#studentCourseTable').bootstrapTable({
        url: "/admin/get/studentcourses",
        striped: true,
        pagination: true,
        singleSelect: false,
        pageSize: 3,
        pageNumber: 1,
        sidePagination: "server",

        queryParams: function (params) {
            var paraObj = {
                size: params.limit,
                page: params.offset / params.limit,
                tname: $("#tname").val(),
                sort: "sid",
                direct: "desc",
                cname: $("#cname").val(),
                sname: $("#sname").val(),

            };
            return paraObj;
        },
        responseHandler: function (res) {

            if(res.code!=0){
                return{
                    "total":0,
                    "rows":null
                }
            }
            return{
                "total":res.data.total,
                "rows":res.data.rows
            }

        },


        columns: [{
            field: 'sname',
            title: '学生姓名'
        },{
            field: 'tname',
            title: '教师姓名',
        },{
            field: 'cname',
            title: '课程名',
        },{
            field: 'score',
            title: '成绩',
        },{
            field: 'tcid',
            title: '授课号',
            cellStyle:function (value,row,index){
                return {css:{'background-color':'rgba(173,11,112,0.1)'}};
            },
            editable: {
                type: 'text',
                title: '授课号',
                validate: function (v) {
                    if (isNaN(v)) return '授课号必须是数字';
                }
            }
        }],
        onEditableSave: function (field, row, oldValue, $el) {

            $.post("/admin/update/studentcourses",row,function(re){
               if(re.code==0){
                       alert("修改成功");
                   }
            });
       }
    })


}



function loadTable3() {

    $("#teacherCourseTable").bootstrapTable("destroy");
    $('#teacherCourseTable').bootstrapTable({
        url: "/admin/get/teachercourses",
        striped: true,
        pagination: true,
        singleSelect: false,
        toolbar:"#toolbar", 
        clickEdit: true,
        showToggle: true,
        pagination: true,       //显示分页条
        showColumns: true,

        showRefresh: true,      //显示刷新按钮
        striped: true,

        singleSelect: false,
        pageSize: 3,
        pageNumber: 1,
        sidePagination: "server",

        queryParams: function (params) {
            var paraObj = {
                size: params.limit,
                page: params.offset / params.limit,
                tname: $("#tname").val(),
                sort: "sid",
                direct: "desc",
                cname: $("#cname").val(),

            };
            return paraObj;
        },
        responseHandler: function (res) {

            if(res.code!=0){
                return{
                    "total":0,
                    "rows":null
                }
            }
            return{
                "total":res.data.total,
                "rows":res.data.rows
            }

        },


        columns: [{
            field: 'tname',
            title: '教师姓名',
        },{
            field: 'cname',
            title: '课程名',
        },{
            field: 'cid',
            title: '课号',
            cellStyle:function (value,row,index){
                return {css:{'background-color':'rgba(173,11,112,0.1)'}};
            },
            editable: {
                type: 'text',
                title: '授课号',
                validate: function (v) {
                    if (isNaN(v)) return '课号必须是数字';
                }
            }
        },{
            field:'tcid',
            title: '操作',
            align: "center",
            width: "80px",
            formatter: function (field,row) {
                return ("<a herf='#' onclick='deleteTeacherCourse(" +field + ")'>删除</a>");
            }
        }],
        onEditableSave: function (field, row, oldValue, $el) {

            $.post("/admin/update/teachercourses",row,function(re){
               if(re.code==0){
                       alert("修改成功");
                   }
            });
       }
    })


}


function deleteTeacherCourse(tcid){


    $.ajax(
        {
            url: "/admin/delete/teachercourses",
            method: "delete",
            data: {tcid:tcid},
            success: function (rs) {
                if(rs.code==0){
                    alert("删除成功！！！");
                }

            },
            error: function () {
                alert("出错了！！！");
            }

        }
    )
}