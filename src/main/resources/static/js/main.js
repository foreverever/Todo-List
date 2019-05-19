console.log("js 시작");

//생성
$(".create-todo-btn").click(createTodo);

function createTodo(e) {
    e.preventDefault();
    window.history.forward(1);
    console.log("call createTodo");
    var url = $(".create-todo").attr("action");

    var json = new Object();
    json.title = $("#title").val();
    json.contents = $("#contents").val();
    json.priority = $("input[name=priority]:checked").val();
    json.deadline = $("#deadline").val();

    console.log(url);

    $.ajax({
        type : "post",
        url : url,
        data : JSON.stringify(json),
        dataType : "json",
        contentType : "application/json",
        error : onError,
        success : function (data, status, jqXHR) {
            console.log(data);
            if(data) {
                alert("입력이 완료되었습니다.");
                location.href = jqXHR.getResponseHeader("location");    //응답 헤더가 가진 location으로 페이지 이동
            }
            else {
                alert("다시 입력 해주세요.");
            }
        }
    })
}

//수정
$(".update-todo").click(updateTodo);

function updateTodo(e) {
    e.preventDefault();
    console.log("call update");
    var url = $("#update-todo").attr("action");

    var json = new Object();
    json.title = $("#title").val();
    json.contents = $("#contents").val();
    json.priority = $("input[name=priority]:checked").val();
    json.deadline = $("#deadline").val();

    $.ajax({
        type : "put",
        url : url,
        data : JSON.stringify(json),
        contentType : "application/json",
        dataType : "json",
        error : onError,
        success : function(data, status, jqXHR) {
            if(data) {
                alert("수정이 완료되었습니다.");
                location.href = jqXHR.getResponseHeader("location");
            }
            else {
                alert("다시 입력해 주세요.");
            }
        }
    })
}

//완료
$(".complete-todo-btn").click(completeTodo);

function completeTodo(e) {
    e.preventDefault();
    console.log("call complete");
    var completeBtn = $(this);
    var url = completeBtn.parent().attr("action");

    $.ajax({
        type : "post",
        url : url,
        dataType : "json",
        error : onError,
        success : function(data, status, jqXHR) {
            console.log(data);
            var template;
            if(data.deadline) {
                var todoCompleteTemplate = $("#complete-todo-deadline-template").html();
                template = todoCompleteTemplate.format(data.title, data.priority, data.contents, data.deadline, data.formattedDeadline, data.id);
            }
            else {
                var todoCompleteTemplate = $("#complete-todo-template").html();
                template = todoCompleteTemplate.format(data.title, data.priority, data.contents, data.id);
            }
            $("#todo-body-"+data.id).html(template);
            $("#todo-body-"+data.id).on("click", ".delete-todo-btn[type='submit']", deleteTodo);
        }
    })
}

//삭제
$(".delete-todo-btn").click(deleteTodo);

function deleteTodo(e) {
    e.preventDefault();
    console.log("call delete");

    var deleteBtn = $(this);
    var url = deleteBtn.parent().attr("action");
    console.log(url);

    $.ajax({
        type : "delete",
        url : url,
        error : onError,
        success : function(data, status, jqXHR) {
            console.log(status);
            deleteBtn.closest(".one-todo-body").remove();
        }
    })
}




//에러 메세지
function onError(jqXHR, status, errorThrown) {
    console.log(jqXHR.responseText);    //json값 다 보여줌 (키,밸류 모두다) ex) {"message":"아이디 또는 비밀번호가 다릅니다."}
    console.log(jqXHR);
    alert(jqXHR.responseJSON.message);  //예외처리에서 받은 리턴값(responseEntity<ErrorMessage>)의 json객체의 키(message)값의 value를 가져온다.
}

//템플릿 추가
String.prototype.format = function() {
  var args = arguments;
       return this.replace(/{(\d+)}/g, function(match, number) {
    return typeof args[number] != 'undefined'
         ? args[number]
             : match
        ;
  });
};