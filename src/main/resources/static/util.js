function count_feedback(state) {
    // 发送后台
    $.get("/os/manage/feedback/countState", {
        state: state
    }).done(function (data) {
        if (data.code == 1) {
            // 结果显示
            var notice = new PNotify({
                title: '反馈总数',
                text: '查询到状态为（' + state + '）的反馈共有'+data.data+'条记录。',
                type: 'success',
                styling: 'bootstrap3',
                buttons: {
                    closer: false,
                    sticker: false
                }
            });
            notice.get().click(function () {
                notice.remove();
            });
        } else {
            // 结果显示
            var notice = new PNotify({
                title: '查询失败',
                text: '原因: ' + data.message,
                type: 'warning',
                styling: 'bootstrap3',
                buttons: {
                    closer: false,
                    sticker: false
                }
            });
            notice.get().click(function () {
                notice.remove();
            });
        }

    }).fail(function (data) {
        error_notify("查询失败", "服务器内部错误。")
    });

}