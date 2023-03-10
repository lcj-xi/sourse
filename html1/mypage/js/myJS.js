
// $(function (){
//     $.ajax(
//         {
//             url:'/webapi/student/list',
//
//         }
//     ).done(function (rs){
//         var length=rs.length;
//         var html="";
//         var sname=$("#sname").val();
//         for(var i=0;i<length;i++){
//
//             var item=rs[i]
//             if(item.name==sname || sname=="" || sname==null){
//                 html+="<tr>"+
//                     "<td>"+item.id+"</td>"+
//                     "<td>"+item.name+"</td>"+
//                     "<td>"+item.age+"</td>"+
//                     "<td>"+item.gender+"</td>"+
//                     "</tr>"
//             }
//         }
//         $("#studentID").html(html);
//     })
// })
//

// function loadTable() {
//     $.ajax({
//         url: "../webapi/student/list",
//         type: "get",
//         success: function (rs) {
//             var length = rs.length;
//             var html = "";
//             for (var i = 0; i < length; i++) {
//
//                 var item = rs[i]
//                 html += "<tr>" +
//                     "<td>" + item.id + "</td>" +
//                     "<td>" + item.name + "</td>" +
//                     "<td>" + item.age + "</td>" +
//                     "<td>" + item.gender + "</td>" +
//                     "<td><a herf='#' onclick='edit(" + item.id + ")'>修改</a></td>" +
//                     "<td><a herf='#' onclick='deleteStudent(" + item.id + ")'>删除</a></td>" +
//                     "</tr>"
//
//             }
//             $("#studentID").html(html);
//
//         },
//         error: function () {
//             alert("???");
//         }
//
//     })
// }

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
                window.location.replace("/mypage/login.html");
            }else if(jsonData.code == -999){
                //其他的异常情况,给个提示。
                alert(jsonData.msg);
            }else{
                //正常情况就不统一处理了
            }
        }catch(e){
        }
    }
});







function loadTable() {




    $("#studentTable").bootstrapTable("destroy");
    $('#studentTable').bootstrapTable({
        url: "/webapi/student/getByPage",
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
                name: $("#sname").val(),
                sort: "id",
                direct: "desc"

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
            field: 'id',
            title: '学号'
        }, {
            field: 'name',
            title: '姓名'
        }, {
            field: 'gender',
            title: '性别'
        }, {
            field: 'age',
            title: '年龄'
        }, {
            field: 'score',
            title: '成绩'
        }, {
            field: 'id',
            title: '操作',
            align: "center",
            width: "80px",
            formatter: function (filed) {
                return ("<a herf='#' onclick='edit(" + filed + ")'>修改</a>");
            }
        }, {
            field: 'id',
            title: '操作',
            align: "center",
            width: "80px",
            formatter: function (filed) {
                return ("<a herf='#' onclick='deleteStudent(" + filed + ")'>删除</a>");
            }
        }]
    })




}

$(function () {
    loadTable();
})

function search() {


    loadTable();
}

function findByName() {
    var sname = $("#sname").val();


    $.ajax(
        {
            url: "../webapi/student/findbyname",
            data: {name: sname},
            type: "get",
            success: function (rs) {
                var length = rs.length;
                var html = "";
                if (length == 0) {
                    html += "<tr>"
                        + "<td> 未查询到相关该学生信息。</td>"
                        + "</tr>"
                } else {
                    for (var i = 0; i < length; i++) {

                        var item = rs[i]
                        if (item.name == sname || sname == "" || sname == null) {
                            html += "<tr>" +
                                "<td>" + item.id + "</td>" +
                                "<td>" + item.name + "</td>" +
                                "<td>" + item.age + "</td>" +
                                "<td>" + item.gender + "</td>" +
                                "<td><a herf='#' onclick='edit(" + item.id + ")'>修改</a></td>" +
                                "<td><a herf='#' onclick='deleteStudent(" + item.id + ")'>删除</a></td>" +
                                "</tr>"
                        }
                    }
                }
                $("#studentID").html(html);
            },
            error: function () {
                alert("???")

            }
        }
    )
}

function initAdd() {

    $("#id").val("");
    $("#name").val("");
    $("#age").val("");
    $("#gender").val("");
    $("#password").val("");
    $("#score").val("");
    $("#operation").html("新增");
    $("#operation").removeAttr("onclick");
    $("#operation").attr("onclick", "addStudent()");
}

function addStudent() {
    var data = $("#formAddStudent").serialize();

    $.ajax(
        {
            url: "../webapi/student/insert",
            method: "post",
            data: data,
            success: function () {

                loadTable();

                $("#addstudent").modal("hide");
            },
            error: function () {
                loadTable();
                $("#addstudent").modal("hide");
                alert("出错了！！！");
            }

        }
    )

}

function edit(id) {

    $.ajax(
        {
            url: "../webapi/student/findById",
            method: "get",
            data: {
                id: id
            },
            success: function (re) {
                $("#addstudent").modal("show");
                $("#id").val(re.id);
                $("#name").val(re.name);
                $("#age").val(re.age);
                $("#gender").val(re.gender);
                $("#score").val(re.score);
                $("#password").val("");
                var attr = $("#operation").html("保存");
                $("#operation").removeAttr("onclick");
                $("#operation").attr("onclick", "updateStudent()");

                var s = 1;
            },
            error: function () {
                alert("出错了！！！");
            }
        }
    )

}

function updateStudent() {
    var data = $("#formAddStudent").serialize();

    $.ajax(
        {
            url: "../webapi/student/update",
            method: "put",
            data: data,
            success: function () {
                loadTable();
                $("#addstudent").modal("hide");
            },
            error: function () {
                loadTable();
                $("#addstudent").modal("hide");
                alert("出错了！！！");
            }

        }
    )

}

function deleteStudent(id) {

    if (confirm("注意：将要s删除该学生信息！！！")) {

        $.ajax(
            {
                url: "../webapi/student/delete",
                method: "delete",
                data: {
                    id: id
                },
                success: function () {
                    loadTable();
                },
                error: function () {
                    loadTable();
                    alert("出错了！！！");
                }

            }
        )

    }


}
