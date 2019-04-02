
//var uname = [[${uname}]];

function getdate() {
    var date = new Date();
    date.setMonth(date.getMonth()+1);
    return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
}

function xujie(id) {
    $.get("./guanlixu/" + id,function (result) {
        if (result == 1) {
            dialog({
                title: '续借成功',
                content: '您续借成功，归还时间为'+getdate(),
                cancel: false,
                ok:function () {
                    location.reload()
                }
            }).width(460).show();
        } else {
            dialog({
                title: '续借失败',
                content: '续借失败，您可能已经超过归还时间或者馆中藏书不足。若有疑问请联系管理员！',
                quickClose: true,// 点击空白处快速关闭
                cancel: false
            }).width(460).show();
        }
    });
}

function guihuan(id) {
    $.get("./guanlihuan/" + id,function (result) {
        if (result == 1) {
            dialog({
                title: '归还成功',
                content: '您归还成功',
                cancel: false,
                ok:function () {
                    location.reload()
                }
            }).width(460).show();
        } else {
            dialog({
                title: '归还失败',
                content: '归还失败，您可能已经超过归还时间.若有疑问请联系管理员！',
                quickClose: true,// 点击空白处快速关闭
                cancel: false
            }).width(460).show();
        }
    });
}