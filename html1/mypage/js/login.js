function findByNameAndPassword() {



    var data = $("#login").serialize();
    var user= $("#user").val();
    
    $.ajax(
        {
            url: "/"+user+"/login",
            data: data,
            type: "get",
            success: function (re) {
                if (re.code!=0) {//后端数据返回，验证是否有效登陆
                    alert("账户或密码错误！！！");
                    $("#password").val("");
                } else {
                    document.cookie="user="+user; 
                    document.cookie="name="+JSON.stringify(re.data);
                    window.location.href="/system.html";//携带cookie数据进行跳转
                }
            },
            error: function () {
                alert("出错了！！！");
            }
        }
    )
}
