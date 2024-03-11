
//------------------페이지 과녉 객체-------------------

let pageObject = {
    page : 1,
    pageBoardSize : 5,
    bcno : 0 ,       //현재 카테고리
    key : "b.btitle",
    keyword : ""
}

let currentDate = new Date();

// 연도, 월, 일을 얻기
let year = currentDate.getFullYear(); // 연도
let month = currentDate.getMonth() + 1; // 월 (0부터 시작하므로 +1 해야 함)
let day = currentDate.getDate(); // 일
month = month>10 ? month : "0"+month;
day = day>10 ? day : "0"+day;
let nowday = year+"-"+month+"-"+day
//1. 전체 출력용 : 함수 : 매개변수 x :반환 x 언제 실행할건지 : 페이지 열릴때(js)

doViewList(1);


function doViewList(page){

    pageObject.page =page; //매개변수로 들어온 페이지를 현재 페이지에 대입

    $.ajax({
        url: "/board/do",
        method: "get",
        data: pageObject,
        success : (r) => {
            console.log(r)
            let boardTableBody = document.querySelector("#boardTableBody")
            let html = ""
            r.list.forEach(board => {
            console.log(board.bdate.split(" ")[0])
            console.log(nowday)
                html += `        <tr>
                                     <th style="width:5%";>${board.bno}</th>
                                     <td style="width:60%"><a href = "/board/view?bno=${board.bno}" >${board.btitle} </a></td>
                                     <td style="width:15%"><img src="/img/${board.mimg}" style="width:20px; border-radius:50%"/>
                                      ${board.mid} </td>
                                     <td style="width:15%">${board.bdate.split(" ")[0]==nowday?board.bdate.split(" ")[1]:board.bdate}</td>
                                     <td style="width:5%">${board.bview}</td>
                                 </tr>`
            })
            boardTableBody.innerHTML = html
            let pagination = document.querySelector(".pagination");
            let pagehtml = "";
            pagehtml += `            <li class="page-item">
                                         <a class="page-link" onclick="doViewList(${page-1 < 1 ?1 : page-1})" >이전</a>
                                     </li>`
            for(let i = r.startbtn; i <=r.endbtn; i++){
                pagehtml +=  `<li class="page-item">
                                  <a class="page-link ${ i == page ? 'active' : '' }" onclick="doViewList(${i})"> ${i}</a>
                             </li>`
            }
            pagehtml += `            <li class="page-item">
                                          <a class="page-link" onclick="doViewList(${page+1 > r.totalpage ? r.totalpage : page+1})" >다음</a>
                                     </li>`
            pagination.innerHTML = pagehtml;
            // 부가 출력

            document.querySelector(".totalPage").innerHTML = "전체 페이지 수 :"+r.totalpage;
            document.querySelector(".totalBoardSize").innerHTML= "총 게시물 수 :"+r.totalBoardSize;
        }
    })

    return;
}

function onPageBoardSize(object){
    console.log(object); console.log(object.value);
    pageObject.pageBoardSize = object.value
    doViewList(1)

}

function onBcno(bcno){
    // bcno : 카테고리 식별번호 [0: 없다 -> 전체보기 ] [1:1번카테고리만 ,2:2번카테고리만]
    pageObject.bcno = bcno;
    //검색 제거
    pageObject.key = "b.btitle";
    pageObject.keyword = "";
    document.querySelector(".key").value = "b.btitle";
    document.querySelector(".keyword").value = "";
    let categoryBtns = document.querySelectorAll(".catebtn > button");
    console.log(categoryBtns)
    for (let i = 0; i<categoryBtns.length; i++){
        categoryBtns[i].classList.remove("usecate");
    }
    categoryBtns[bcno].classList.add("usecate");
    doViewList(1);
}

function onSearch(){
    let key = document.querySelector(".key").value;
    let keyword = document.querySelector(".keyword").value;
    pageObject.key = key;
    pageObject.keyword = keyword;

    doViewList(1);
}