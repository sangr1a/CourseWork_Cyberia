<#import "blocks/template.ftlh" as t>
<@t.template userByPrincipal>
    <h1 style="text-align: center">Информация о пользователе</h1>
    <hr>
    <div style="text-align: center">
            <img src="/static/images/avatar.png"
                 style="border:2px solid black; border-radius: 20px" height="120" alt=" ">
        <br>
        <br>
        <h2>${user.name}</h2>
        <br>
        Discord: <b>${user.phoneNumber}</b>
    </div>
    <br>
    <hr>
    <h1 style="text-align: center">Турниры организатора ${user.name}</h1>
    <br>
    <div class="card-columns">
        <#list tours as tour>
            <a href="/tour/${tour.id}" class="tour-link">
                <div class="card text-white bg-dark my-3 tour">

                    <div class="card-body">
                        <h5 class="card-title">${tour.title}</h5>
                        <p class="card-text">${tour.game}</p>
                    </div>
                    <div class="card-footer">
                        ${tour.user.name}
                    </div>
                </div>
            </a>
        <#else>
            <h1 style="text-align: center">Не найдено</h1>
        </#list>
    </div>
</@t.template>