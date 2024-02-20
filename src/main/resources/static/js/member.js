console.log("member.js")

//1.회원가입
function signup(){
    //1.html 입력값 호출
    let id = document.querySelector("#id").value
    let pw = document.querySelector("#pw").value
    let name = document.querySelector("#name").value
    let phone = document.querySelector("#phone").value
    let email = document.querySelector("#email").value
    let img = document.querySelector("#img").value
    //2.객체화
    let info = {
        id : id, pw:pw,name:name,phone:phone,email:email,img:img
    }
    console.log(info)
    //3.객체를 배열에 저장 ---> 스프링 컨트롤러 서버와 연동
    //4.결과
    $.ajax({
            url : '/member/signup',
            method : 'POST',
            data :info,
            success : function result( result){
            if(result){
                alert("회원가입 성공")
                location.href = '/member/login'
            }
            else{
                alert("회원가입실패")
            }
         }
    })
}

function login(){
    //1.html 입력값 호출
    let id = document.querySelector("#id").value
    let pw = document.querySelector("#pw").value
    console.log([id,pw])
    //2.객체화
    let info = {
        id : id, pw:pw

    }
    console.log(info)
    //3.객체를 배열에 저장 ---> 스프링 컨트롤러 서버와 연동
    //4.결과
    $.ajax({
            url : '/member/login',
            method : 'POST',
            data :info,
            success : function result( result){
            if(result){
                alert("로그인 성공")
            }
            else{
                alert("로그인실패")
            }
         }
    })
}