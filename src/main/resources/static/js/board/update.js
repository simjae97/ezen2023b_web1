
let bno = new URL(location.href).searchParams.get("bno")

onView();
function onView(){
    console.log("onview")
    $.ajax({
        url: "/board/view.do?bno="+bno,
        method : "get",
        success : (r) => {
            console.log(r)

            document.querySelector(".btitle").value = r.btitle
            document.querySelector(".bcontent").innerHTML = r.bcontent
            document.querySelector(".bcno").value = r.bcno
            document.querySelector(".bfile").innerHTML = r.bfile
            let option = {
                lang: 'ko-KR', //한글패치
                height: 500 //에디터 세로크기
            }
            $('#summernote').summernote(option);
            //삭제 수정버튼 활성화 ( 해당 보고있는 크라이언트가 작성자의아이디와 동일하면

        }
    })
}

function onUpdate(){
    //1.폼 가져온다
    let boardUpdateForm = document.querySelector(".boardUpdateForm");
    //2.폼 객체화
    let boardUpdateFormData = new FormData(boardUpdateForm)
    //폼객체에 데이터 추가(완전중요)
    boardUpdateFormData.set("bno",bno);
    $.ajax({
        url : "/board/update.do",method:"put",
        data:boardUpdateFormData,
        contentType : false, processData : false,
        success : (r) => {
            if(r){
                alert("수정성공");
                location.href="/board/view?bno="+bno}
            else{
                alert("실패");
            }
        }
    })
}
