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



function loadTable() {

    $("#teacherCourseTable").bootstrapTable("destroy");
    $('#teacherCourseTable').bootstrapTable({
        url: "/teacher/get/teachercourses",
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
                sort: "id",
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
            field: 'cid',
            title: '课号'
        },{
            field: 'cname',
            title: '课程'
        }]
    })

}



function loadTable1() {

    $("#teacherStudentTable").bootstrapTable("destroy");
    $('#teacherStudentTable').bootstrapTable({
        url: "/teacher/get/students",
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
                sname: $("#sname").val(),
                sort: "id",
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
            field: 'sid',
            title: '学号'
        },{
            field: 'sname',
            title: '学生姓名'
        },{
            field: 'cname',
            title: '课程'
        },{
            field: 'score',
            title: '分数',
            cellStyle:function (value,row,index){
                if(value <60 ){
                    return {css:{'background-color':'rgba(173,11,223,0.1)'}}
                }
                if(value >60){
                    return {css:{'background-color':'rgba(173,11,12,0.1)'}}
                }
                return '';
            },
            editable: {
                type: 'text',
                title: '成绩',
                validate: function (v) {
                    if (isNaN(v)) return '成绩必须是数字';
                    if (v<0  || v >100) return '成绩无效，成绩应在[0,100]';
                }
            }

        }],
        onEditableSave: function (field, row, oldValue, $el) {

            $.post("/teacher/update/score",row,function(re){
               if(re.code==0){
                       alert("修改成功");
                   }
            });
       }
          

    })


}


