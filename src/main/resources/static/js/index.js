// 1. 로그인 여부 확인 요청
$.ajax({
    url : "/member/login/check",
    method : "get",
    success: (r) => {
        console.log(r);
        let login_menu = document.querySelector("#login_menu");
        let html = '';
        if(r){ // 로그인 했을때
            html += `                    <li class="nav-item">
                                             <a class="nav-link" onclick="logout()">로그아웃</a>
                                         </li>
                                         <li class="nav-item">
                                             <a class="nav-link" href="/member/signup">마이페이지</a>
                                         </li>
                                         <li class="nav-item">
                                             <img src="#"> ${r}님! 안녕하세요
                                         </li>`
        }
        else{
            html += `                    <li class="nav-item">
                                             <a class="nav-link" href="/member/login">로그인</a>
                                         </li>
                                         <li class="nav-item">
                                             <a class="nav-link" href="/member/signup">회원가입</a>
                                         </li>
                                         `
        }

        login_menu.innerHTML= html;

    }
});

function logout(){
    $.ajax({
        url : "/member/logout",
        method:"get",
        success: (r) => {
            alert('로그아웃성공')
            location.href = "/"
        }
    })
}