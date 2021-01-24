function toggle_fixed_type(value, event) {
    if(event.currentTarget.checked) {
        console.log("checked");
        add_fixed_type(value, event);
    } else {
        delete_fixed_type(value, event);
        console.log("unchecked");
    }
}

$(document).ready(function () {
    // 커스텀 확장자 추가
    $("#submit-add").click(function (event) {
        event.preventDefault();
        console.log($("#type-string").val());
        add_custom_type($("#type-string").val(), event);
    });

    // 고정확장자 추가/삭제 토글
    $(".fixed-type").click(function (event) {
        event.preventDefault();
        console.log(event);
        toggle_fixed_type(event.currentTarget.value, event);
    });

    initCheckbox();
});

function initCheckbox() {
    $(".fixed-type").each(function (index, el) {
        console.log(el);
        if(fixedTypeList.match(el.value) != null) {
            el.checked = true;
        }
    });
}

function add_custom_type(fileType, event) {
    var eventTarget = $(event.target);
    var addRequest = {}
    addRequest["fileTypeString"] = fileType;
    eventTarget.prop("disabled", true);
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/main/createtype",
        data: JSON.stringify(addRequest),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            console.log("SUCCESS : ", data);
            var originHtml = $('#type-container').html();
            originHtml = originHtml + "<div id='custom-type-" + data.id + "' style='display: inline-block'>" + data.fileTypeString + "<button onclick='delete_custom_type(" + data.fileTypeString + ")'>X</button></div>";
            $('#type-container').html(originHtml);
            eventTarget.prop("disabled", false);
            $("#type-string").val("");
        },
        error: function (e) {
            console.log("ERROR : ", e);
            eventTarget.prop("disabled", false);
            alert("저장에 실패했습니다.\r\n"+e.responseText);
        }
    });
}

function add_fixed_type(fileType, event) {
    var eventTarget = $(event.target);
    var addRequest = {}
    addRequest["fileTypeString"] = fileType;
    eventTarget.prop("disabled", true);
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/main/createtype",
        data: JSON.stringify(addRequest),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            console.log("SUCCESS : ", data);
            eventTarget.prop("disabled", false);
            eventTarget.prop("checked", true);
        },
        error: function (e) {
            console.log("ERROR : ", e);
            eventTarget.prop("disabled", false);
            eventTarget.prop("checked", false);
            alert("저장에 실패했습니다.\r\n"+e.responseText);
        }
    });
}

function delete_fixed_type(fileType, event) {
    var eventTarget = $(event.target);
    var addRequest = {}
    addRequest["fileTypeString"] = fileType;
    addRequest["isDeleted"] = true;
    eventTarget.prop("disabled", true);
    $.ajax({
        type: "DELETE",
        contentType: "application/json",
        url: "/api/main/deletetype",
        data: JSON.stringify(addRequest),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            console.log("SUCCESS : ", data);
            eventTarget.prop("disabled", false);
            eventTarget.prop("checked", false);
        },
        error: function (e) {
            console.log("ERROR : ", e);
            eventTarget.prop("disabled", false);
            eventTarget.prop("checked", true);
            alert("삭제에 실패했습니다.\r\n"+e.responseText);
        }
    });
}

function delete_custom_type(fileType) {
    var addRequest = {}
    addRequest["fileTypeString"] = fileType;
    $.ajax({
        type: "DELETE",
        contentType: "application/json",
        url: "/api/main/deletetype",
        data: JSON.stringify(addRequest),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            console.log("SUCCESS : ", data);
            $("#custom-type-" + data.id).remove();
        },
        error: function (e) {
            console.log("ERROR : ", e);
            alert("삭제에 실패했습니다.\r\n"+e.responseText);
        }
    });
}