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
            document.querySelector(".btitle").innerHTML = "제목 : "+r.btitle
            document.querySelector(".bcontent").innerHTML = "내용 : "+r.bcontent
            document.querySelector(".bcno").innerHTML = "카테고리 :"+r.bcno
            document.querySelector(".mno").innerHTML = "작성자 : "+r.mno
            document.querySelector(".bdate").innerHTML = "작성일 : "+r.bdate
            document.querySelector(".bview").innerHTML = "조회수 : "+r.bview
            document.querySelector(".bfile").innerHTML = "첨부파일 : "+r.bfile

        }
    })
}