
$(function () {
  $('[data-toggle="tooltip"]').tooltip()
});

$(function () {
  $('[data-toggle="popover"]').popover()
});

function changeTab(obj) {
    $(".subTab li a").each(function() {
        var id = $(this).attr("id");
        $(this).removeClass("active");
        $("#" + id + "Area").css("display", "none");
    });

    var tabId = $(obj).attr("id");
    $("#" + tabId).addClass("active");
    $("#" + tabId + "Area").css("display", "block");
}

function checkAndRemoveClass(obj, className) {
    if ($(obj).hasClass(className)) {
        $(obj).removeClass(className);
    }
}

function setInputCheckedByValue() {
    $(":input").each(function() {
        var obj = $(this);
        var type = obj.attr('type');

        if (type == "radio") {
            var val = obj.val();
            var dataInputValue = obj.attr("data-input-value");
            if(val == dataInputValue) {
                obj.attr("checked", "checked");
            }
        }
        if (type == "date" || type == "text") {
            var dataInputValue = obj.attr("data-input-value");
            obj.val(dataInputValue);
        }
    });
}

function changeBgByActiveValue(targetObject, deleteClassName, addClassName, activeValue) {
    $(targetObject).each(function() {
        var nowValue = $(this).text();
        if (nowValue != activeValue) {
            return;
        }
        checkAndRemoveClass($(this), deleteClassName);
        $(this).addClass(addClassName);
    });
}