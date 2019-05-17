console.log("js 시작");

//수정 폼
//$(".update-todo-btn").click(updateForm);

function updateForm(e) {
    e.preventDefault();
    console.log("call updateForm");
    var btn = $(this);
    var url = btn.parent().attr("action");
    console.log("url : " + url);

    $.ajax({
        type: "get",
        url : url,
        dataType : "json",
        error : onError,
        success : function(data, status, jqXHR) {
            console.log(status);
            console.log(data);
            var todoUpdateTemplate = $("#write-update-todo-template").html();
            var template = todoUpdateTemplate.format(data.title, data.contents);
            $("#todo-body-"+data.id).html(template);
            $("#template-update-btn").click(update);
        }
    })
}

//수정
function update(e){
    e.preventDefault();
    console.log("call update");

}


//삭제
$(".delete-todo-btn").click(deleteTodo);

function deleteTodo(e) {
    e.preventDefault();
    console.log("call delete");

    var deleteBtn = $(this);
    var url = deleteBtn.parent().attr("action");

    $.ajax({
        type : "delete",
        url : url,
        error : onError,
        success : function(data, status, jqXHR) {
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

Handlebars.registerHelper('ifCond', function(v1, v2, options) {
  if(v1 === v2) {
    return options.fn(this);
  }
  return options.inverse(this);
});