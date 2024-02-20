console.log("안녕")
doRead();
//1. 저장메소드 : 실행조건 : 등록 버튼 킄ㄹ릭시 매개변수 x 리턴x
function doCreate(){
    console.log("등록")
}
//2. 전체 호출 메소드 : 실행조건 : 페이지 열릴떄,변화가있을떄 매개변수x , 리턴x
function doRead(){
    console.log("출력")
}
//3. 수정 메소드 : 실행 조건: 수정버튼 클릿기 매개변수 : 수정할 식별키bno 리턴x
function doUpdate(bno){
    console.log("수정"+bno)
}
// 4. 삭제 메소드 : 실행조건 :삭제버튼 클릭시  매개변수 : 삭제할 식별키bno 리턴x
function doDelete(bno){
    console.log("삭제"+bno)
}
