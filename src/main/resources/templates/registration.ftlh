<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Регистрация</h1>
    <hr>
    <tour action="/registration" method="post">
        <div class="tour-group">
            <label for="name">Имя пользователя</label>
            <input type="text" class="tour-control" required id="name" name="name">
        </div>
        <div class="tour-group">
            <label for="email">Электронная почта</label>
            <input type="email" class="tour-control" id="email" required name="email">
        </div>
        <div class="tour-group">
            <label for="discord">Номер телефона</label>
            <input type="text" class="tour-control" id="phoneNumber" required name="phoneNumber">
        </div>
        <div class="tour-group">
            <label for="password">Пароль</label>
            <input type="password" class="tour-control" id="password" required name="password">
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" style="width: 100%" class="btn btn-dark">Зарегистрироваться</button>
    </tour>
    <#if errorMessage??>
        <h2 style="color: red">${errorMessage}</h2>
    </#if>
</@t.template>