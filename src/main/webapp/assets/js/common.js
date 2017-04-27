var common = {
    init: function () {
        $("#searchSubmit").click(function () {
            var search = $("#search").val();
            if (search == '') {
                alert('提示：请输入小说名称或作者名字！');
                return;
            }

            $.submit({
                url: "search",
                data: {search: search}
            });

        });
    }

};



$.submit = function (opts) {
    opts = opts || {};
    opts.url = opts.url || '';
    var $form = $('<form>').appendTo('body'), data = {};
    var attr = {
        action: opts.url.indexOf('/') == 0 ? (SYS.ctx + opts.url) : (SYS.path + opts.url),
        method: opts.type || opts.method || 'POST',
        target: opts.target
    };
    if (opts.target) {
        attr.target = opts.target;
    }
    $form.attr(attr);
    if (opts.data) {
        data = SysUtils.j2d(opts.data);
    }
    for (var key in data) {
        $form.append($('<input>').attr({
            type: 'hidden',
            name: key,
            value: data[key]
        }));
    }
    $form.submit();
};


var SysUtils = {
    j2d: function (value, preKey, isMap) {
        var data = {}, key, val;
        if (preKey) {
            preKey = preKey + (isMap ? '[_KEY_]' : '._KEY_');
        } else {
            preKey = '_KEY_';
        }
        for (var ckey in value) {
            val = value[ckey];
            key = preKey.replace('_KEY_', ckey);
            if ($.isPlainObject(val)) {
                $.extend(data, this.j2d(val, key, ckey.indexOf('Map', ckey.length - 3) !== -1));
            } else if ($.isArray(val)) {
                $.extend(data, this.a2d(val, key));
            } else {
                data[key] = val;
            }
        }
        return data;
    }
};

$(function () {
    common.init();
});