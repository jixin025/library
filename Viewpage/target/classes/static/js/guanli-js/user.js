function xiugai(id) {
    $.get("./updatauser/" + id, function (data) {
        dialog({
            title: '修改用户信息',
            content: data
        }).width(460).show();
    });
}

function delet(id) {
    $.get("./deleteuser/" + id, function (result) {
        if (result == 1) {
            dialog({
                title: '删除成功',
                content: '删除成功',
                cancel: false,
                quickClose: true,// 点击空白处快速关闭
                onclose: function () {
                    self.location=document.referrer;
                }
            }).width(460).show();
        } else {
            dialog({
                title: '删除失败',
                content: '删除失败，该用户可能已不存在，请您刷新重试。',
                quickClose: true,// 点击空白处快速关闭
                cancel: false
            }).width(460).show();
        }
    });
}

function GetUrlParam(paraName) {
    var url = document.location.toString();
    var arrObj = url.split("?");

    if (arrObj.length > 1) {
        var arrPara = arrObj[1].split("&");
        var arr;

        for (var i = 0; i < arrPara.length; i++) {
            arr = arrPara[i].split("=");

            if (arr != null && arr[0] == paraName) {
                return arr[1];
            }
        }
        return "";
    }
    else {
        return "";
    }
}

var Num = GetUrlParam("pageNum");
function firstPage() {
    Num = 1;
    window.location.href = "http://localhost:1411/view/guanlilistuser?pageNum=" + Num;
}

function prevPage() {
    Num = Num - 1;
    if (Num < 1) {
        alert("已经是第一页");
    } else {
        window.location.href = "http://localhost:1411/view/guanlilistuser?pageNum=" + Num;
    }
}

function pnextPage() {

    var tab = document.getElementById("tbody");
    var rows = tab.rows.length;
    if (Num<1){
        Num=1;
    }
    if (rows>=1){
        Num=parseInt(Num)+1;
        window.location.href = "http://localhost:1411/view/guanlilistuser?pageNum=" + Num;
    }else {
        alert("这是最后一页");
    }

}