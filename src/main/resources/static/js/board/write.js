//썸머노트 실행
$(document).ready(function() {
    //섬머노트 옵션
    let option = {
        lang : "ko-KR" , //한글패치
        height : 500 //에디터 세로크기
    }
  $('#summernote').summernote(option);
});

console.log("안녕")
function onWrite(){
    console.log("onWrite()")
    let boardWriteForm = document.querySelector(".boardWriteForm");
    let boardWriteFormData = new FormData(boardWriteForm);

    $.ajax({
        url : "/board/write.do",
        method : "post",
        data : boardWriteFormData,
        contentType : false,
        processData : false,
        success: (r) => {
            if(r == 0){
                alert("작성실패")
            }
            else if (r == -1){
                alert("작성실패")
            }
            else if(r== -2){
                alert("작성실패")
            }
            else if(r >= 1){
                alert("작성성공")
                location.href="/board/view?bno="+r;
            }
        }

    })
}