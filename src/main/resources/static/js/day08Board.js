console.log("안녕")
doRead();
//1. 저장메소드 : 실행조건 : 등록 버튼 킄ㄹ릭시 매개변수 x 리턴x
function doCreate(){
    // 1. 입력받은 데이터 가져오기
    let bcontent = document.querySelector("#bcontent").value;
    let bwriter = document.querySelector("#bwriter").value;
    let bpassword = document.querySelector("#bpassword").value;
    console.log(bcontent+""+bwriter+""+bpassword)
    // 1-2 유효성 검사

    //2객체화
    let info = {bcontent: bcontent, bpassword:bpassword, bwriter:bwriter}
    console.log(info);


    //3.controller 전송후 응답

    $.ajax({
        url: "/board/create",
        method: "post",
        data : info,
        //4. 결과
        success : function (result){
            if(result){alert("글쓰기 성공"); doRead()}
            else{alert("글쓰기 실패")}
        }
    })
}
//2. 전체 호출 메소드 : 실행조건 : 페이지 열릴떄,변화가있을떄 매개변수x , 리턴x
function doRead(){
    $.ajax({
        url: "/board/read",
        method : "get",
        success : function (result){
            console.log(result)
            //1어디에
            let tbody = document.querySelector('table tbody')
            //2무엇을
            let html = ""

            for(let i=0; i<result.length; i++){
                html += `<tr>
                            <td> ${result[i].bno} </td><td> ${result[i].bcontent} </td><td> ${result[i].bwriter} </td>
                            <td>
                                <button onclick="doDelete(${result[i].bno})">삭제</button>
                                <button onclick="doUpdate(${result[i].bno})">수정</button>
                            </td>
                        </tr>`
            }
            console.log(html)
            //3출력
            tbody.innerHTML = html;
        }
    })
}
//3. 수정 메소드 : 실행 조건: 수정버튼 클릿기 매개변수 : 수정할 식별키bno 리턴x
function doUpdate(bno){
    let bcontent = prompt("수정할 내용 :");
    let bpassword = prompt("비밀번호 :");
    $.ajax({
        url:"/board/update",
        method: "POST",
        data : {bno: bno , bcontent: bcontent , bpassword:bpassword},
        success : function (result){
            if(result){
                alert("수정완료")
                doRead()
            }
            else{
                alert("수정실패")
            }
        }
    })
}
// 4. 삭제 메소드 : 실행조건 :삭제버튼 클릭시  매개변수 : 삭제할 식별키bno 리턴x
function doDelete(bno){
    let bpassword = prompt("삭제할 내용 :");
    $.ajax({
        url:`/board/delete/${bno}/${bpassword}`,
        method: "POST",
        success : function (result){
            if(result){
                alert("삭제완료")
                doRead();
            }
            else{
                alert("삭제실패 ")
            }
        }
    })
}
//function doDelete( bno ){
//    console.log( "doDelete()" + bno  );
//
//    let bpassword = prompt('게시물 비밀번호 ');
//
//    // ----------- AJAX ------------- //
//    $.ajax({
//       url : `/board/delete/${bno}/${bpassword}`,
//       method : 'Post',
//       success : function ( result ){
//            if( result ){ alert('삭제성공'); doRead(); }
//            else{ alert('삭제실패'); }
//       }
//    })
//    // ------------------------------ //
// }