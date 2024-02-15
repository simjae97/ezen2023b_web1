console.log("todo.js실행");
doGet();// js실행시 최초로 한번 실행
//1등록
function doPost(){
    console.log("doPost")
    // 1. HTML에서 입력받은 값 가져오기
    let content = document.querySelector("#content").value;
    let deadline = document.querySelector("#deadline").value;

    // 2. 객체화
    let info= {
       content : content,
       deadline : deadline
    }
    // 3. 컨트롤에게 요청 / 응 답
    $.ajax({
        url : "/todo/post.do",
        method:"post",
        data : info,
        success : function(result){
            console.log(result)
            if(result == true){
                //화면 갱신    // 4.   출력
                doGet();
            }
        }
    })
}
//2출력
function doGet(){
    //- 스프링(자바)와 통신(주고받고)
    // JQUERY AJAX
    //$.ajax({})
    /*
        HTTP method : post, get , put ,delete 등등
        $.ajax({
            method: "http method" 통신방법,
            url : spring controller url/ 통신 대상 식별,
            data:"HTTP request value / 통신 요청으로 보낼 데이터
            success : HTTP response function / 통신응답 함수
        })
    */
//    $.ajax({
//        method:"mapping 방법"
//        url: "spring controller mapping 주소"
//        data: "보낼 데이터"
//        success : function result("받을 데이터"){}
//    })
    $.ajax({
        url: "/todo/get.do",
        method: "get",
        success : function result(resultValue){
        console.log(resultValue)
        // 통신 응답 결과를 html형식으로 출력 해주기
        //1.어디에
        let tbody =document.querySelector('table tbody')
        // 2. 무엇을
        let html = ""
        for(let i = 0 ; i<resultValue.length; i++){
            html += `<tr>
                        <th>${resultValue[i].id}</th>
                        <th>${resultValue[i].content}</th>
                        <th>${resultValue[i].deadline}</th>
                        <th>${resultValue[i].state}</th>
                    </tr>`
        }
        tbody.innerHTML = html;
        }
    })
}
//3 수정
function doPut(){

}
//4 삭제
function doDelete(){

}