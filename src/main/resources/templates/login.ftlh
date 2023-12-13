<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Авторизация</h1>
    <hr>
    <form action="/login " method="post">
        <div class="form-group">
            <label for="email">Электронная почта</label>
            <input type="email" class="form-control <#if RequestParameters.error??>is-invalid</#if>" id="email" name="username">
            <#if !user.active>
                <div id="email" class="invalid-feedback">
                    Пользователь с таким email ограничен в доступе к сервису
                </div>
            <#elseif !user.active && user.seller>
                <div id="email" class="invalid-feedback">
                    Ожидайте подтверждения заявки
                </div>
            <#else>
            <div id="email" class="invalid-feedback">
                Неверная электронная почта
            </div>
            </#if>
        </div>
        <div class="form-group">
            <label for="password">Пароль</label>
            <input type="password" class="form-control <#if RequestParameters.error??>is-invalid</#if>" id="password" name="password">
            <#if !user.active>
            <#else>
            <div id="password" class="invalid-feedback">
                Неверный пароль
            </div>
            </#if>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" style="width: 100%" class="btn btn-dark">Войти</button>
    </form>
    <hr>
    <h2>Еще нет аккаунта? <a href="/registration">Зарегистрируйтесь!</a></h2>
</@t.template>