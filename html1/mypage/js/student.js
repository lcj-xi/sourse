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

    $("#studentTable").bootstrapTable("destroy");
    $('#studentTable').bootstrapTable({
        url: "/student/get/studentcourses",
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
                name: $("#sname").val(),
                sort: "id",
                direct: "desc",
                cname: $("#cname").val(),
                tname: $("#tname").val()

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
        }, {
            field: 'sname',
            title: '姓名'
        }, {
            field: 'tname',
            title: '教师'
        },{
            field: 'cname',
            title: '课程'
        },{
            field: 'score',
            title: '成绩'
        }]
    })

}


function loadTable1() {

    $("#courses").bootstrapTable("destroy");
    $('#courses').bootstrapTable({
        url: "/student/get/courses",
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
                name: $("#sname").val(),
                sort: "id",
                direct: "desc",
                cname: $("#cname").val(),
                tname: $("#tname").val()

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
        }, {
            field: 'tname',
            title: '教师'
        },{
            field: 'tcid',
            title: '操作',
            align: "center",
            width: "80px",
            formatter: function (filed) {
                return ("<a herf='#' onclick='getCourse(" + filed + ")'>选课</a>");
            }
        },{
            field: 'tcid',
            title: '操作',
            align: "center",
            width: "80px",
            formatter: function (filed) {
                return ("<a herf='#' onclick='deleteCourse(" + filed + ")'>退课</a>");
            }
        }],
        // rowStyle:function(row, index){
        //     if(1){
        //         return {
        //             css: {
        //               color: 'blue'
        //             }
        //           }
    
        //     }


        // }

    })

}



function rowStyle() {
   
  }

function getCourse(tcid) {
    $.ajax(
        {
            url: "/student/insert/course",
            method: "post",
            data: {tcid:tcid},
            success: function (rs) {
                if(rs.code==0){
                    alert("成功选课！！！");
                }

            },
            error: function () {
                alert("出错了！！！");
            }

        }
    )

}



function deleteCourse(tcid) {
    $.ajax(
        {
            url: "/student/delete/course",
            method: "delete",
            data: {tcid:tcid},
            success: function (rs) {
                if(rs.code==0){
                    alert("退课成功！！！");
                }

            },
            error: function () {
                alert("出错了！！！");
            }

        }
    )

}



