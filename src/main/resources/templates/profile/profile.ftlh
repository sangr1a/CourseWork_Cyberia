<#import "../blocks/template.ftlh" as t>
<@t.template user>
    <#if user.isAdmin()><h1 style="text-align: center">Личный кабинет администратора</h1>
    <#elseif user.isSeller()> <h1 style="text-align: center">Личный кабинет организатора</h1>
    <#else> <h1 style="text-align: center">Личный кабинет пользователя</h1>
    </#if>
    <hr>
    <br>
    <div style="text-align: center">
        <img src="/static/images/${user.avatarName}.png" height="120">
        <br>
        <br>
        <h2>${user.name}</h2>
        <br>
        Электронная почта: <b>${user.email}</b>
        <br>
        Номер телефона: <b>${user.phoneNumber}</b>
        <br>
        <br>
        <#if user.isAdmin()>
            <a class="btn btn-dark mb-3" style="text-align: center; width: 100%"
               href="/admin">
                Панель администратора
            </a><br>
        <#elseif user.isSeller()>
        <a class="btn btn-dark mb-3" style="text-align: center; width: 100%"
           href="/my/tours">
            Мои турниры
        </a>
        <#else>
            <a class="btn btn-dark mb-3" style="text-align: center; width: 100%" href="/my/favorites">
                Избранное
            </a>
            <a class="btn btn-dark mb-3" style="text-align: center; width: 100%" href="/my/attended-tours">
                Посещенные турниры
            </a>
        </#if>
        <form action="/logout" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" style="text-align: center; width: 100%" class="btn btn-danger mb-2">
                Выйти из аккаунта
            </button>
        </form>
    </div>
</@t.template>