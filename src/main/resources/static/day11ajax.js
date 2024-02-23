console.log("js실행")

let id = 9;
let content = "ajax테스트용";

function ajax1(){
    $.ajax({
        url : "/day11/ajax1",
        method : "get",
        success : (result ) => {console.log(result)},
        error: (error) => {console.log(error)}
    })
}

// 경로상의 매개변수
function ajax2(){
    $.ajax({
        url : `/day11/ajax2/${id}/${content}`,
        method : "get",
        success : (result ) => {console.log(result)},
        error: (error) => {console.log(error)}
    })
}

function ajax3(){
    $.ajax({
        url: `day11/ajax3?id=${id}&content=${content}`, method : "get",
        success : (r) => {console.log(r)}
    })
}

function ajax4(){
    $.ajax({
        url: `day11/ajax4`,
        method : "get",
        data:{id:id,content : content},
        success : (r) => {console.log(r)}
    })
}
// 쿼리스트링




function ajax5(){
    $.ajax({
        url: `day11/ajax5`,
        method : "post",
        data:{id:id,content : content},
        success : (r) => {console.log(r)}
    })
}

function ajax6(){
    $.ajax({
        url: `day11/ajax6`,
        method : "post",
        contentType: "application/json" ,
        data:JSON.stringify({id:id,content : content}),
        success : (r) => {
            console.log(r)
        }
    })
}