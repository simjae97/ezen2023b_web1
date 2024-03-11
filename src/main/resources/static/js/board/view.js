//* 경로(url)상의 쿼리스트링(매개변수) 호출하기.
    // 1. new URL(location.href) : 현재 페이지의 경로객체 호출
console.log(new URL(location.href))
    // 2. 경로상의 변수들
console.log(new URL(location.href).searchParams)
    // 3. 변수들에서 특정 매개변수 호출
console.log(new URL(location.href).searchParams.get("bno"))

let bno = new URL(location.href).searchParams.get("bno")
onView();
//1.게시물 개별 조회
function onView(){
    console.log("onview")
    $.ajax({
        url: "/board/view.do?bno="+bno,
        method : "get",
        success : (r) => {
            console.log(r)
            document.querySelector(".btitle").innerHTML = r.btitle
            document.querySelector(".bcontent").innerHTML = r.bcontent
            document.querySelector(".bcno").innerHTML = r.bcno
            document.querySelector(".mid").innerHTML = r.mid;
            document.querySelector(".bdate").innerHTML = r.bdate
            document.querySelector(".bview").innerHTML = r.bview
            if(r.bfile){
                document.querySelector('.bfile').innerHTML = `<a href="/board/file/download?bfile=${ r.bfile }"> ${ r.bfile } </a>`;
            }
            //삭제 수정버튼 활성화 ( 해당 보고있는 크라이언트가 작성자의아이디와 동일하면
            $.ajax({
                url:"/member/login/check",
                method: "get",
                success: (loginid) =>{
                    console.log(loginid)
                    console.log(r.mid)
                    if(loginid == r.mid){
                    document.querySelector(".btnBox").innerHTML = `
                    <button type="button" class="boardBtn" onclick="location.href='/board'">목록보기</button>
                    <button type="button" class="boardBtn" onclick="onDelete(${r.bno})">삭제 </button>
                    <button type="button" class="boardBtn" onclick="location.href='/board/update?bno=${r.bno}'"> 수정 </button>`
                    }
                }
            })
        }
    })
}

function onDelete(bno){
    $.ajax({
        url : "/board/delete.do",method:"delete", data: {bno : bno},
        success: (r) => {
            if(r){
                alert("삭제완료");
                location.href="/board/"
            }
            else{
                alert("삭제실패");
            }
        }
    })
}