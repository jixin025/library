function regist() {
    $.get("./regist", function (data) {
        dialog({
            title: '注册用户',
            content: data,
        }).width(460).show();
    });
}