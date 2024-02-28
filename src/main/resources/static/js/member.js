console.log("member.js")

//1.회원가입
function signup(){
    //1.html 입력값 호출
//    let id = document.querySelector("#id").value
//    let pw = document.querySelector("#pw").value
//    let name = document.querySelector("#name").value
//    let phone = document.querySelector("#phone").value
//    let email = document.querySelector("#email").value
//    let img = document.querySelector("#img").value

    let signUpform = document.querySelector(".signUpform");
    console.log(signUpform)
    let signUpformData = new FormData(signUpform)
    console.log(signUpformData)

    //2.객체화
//    let info = {
//        id : id, pw:pw,name:name,phone:phone,email:email,img:img
//    }
//    console.log(info)
    //3.객체를 배열에 저장 ---> 스프링 컨트롤러 서버와 연동
    //4.결과
    $.ajax({
            url : '/member/signup',
            method : 'POST',
//            data :info,
            data: signUpformData,
            contentType: false,
            processData : false,
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
            success : (result) => {
            if(result){
                alert("로그인 성공")
                location.href = "/" // 로그인 성공시 메인페이지로
            }
            else{
                alert("로그인실패")
            }
         }
    })
}

function preimg(event){
    console.log(event.files); //현재 input의 첨부파일들
    console.log(event.files[0]);
    // 1. 파일 읽기 객체 생성
    let fileReader = new FileReader();
    fileReader.readAsDataURL(event.files[0])
    console.log(fileReader)
    console.log(fileReader.result)
    fileReader.onload = e =>{
        console.log(e);
        console.log(e.target);
        console.log(e.target.result);
        document.querySelector("#preimg2").src = e.target.result
    }

}