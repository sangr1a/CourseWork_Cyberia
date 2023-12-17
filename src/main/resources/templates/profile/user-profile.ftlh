<#import "../blocks/template.ftlh" as t>
<@t.template user>
    <#if user.isAdmin()><h1 style="text-align: center">Личный кабинет администратора</h1>
    <#elseif user.isSeller()> <h1 style="text-align: center">Личный кабинет организатора</h1>
    <#else> <h1 style="text-align: center">Личный кабинет пользователя</h1>
    </#if>
    <hr>
    <br>
    <div style="text-align: center">
        <img src="/static/images/<#list user.roles as role>${role}</#list>.png" height="120">
        <br>
        <br>
        <h2>${user.name}</h2>
        <br>
        Электронная почта: <b>${user.email}</b>
        <br>
        Номер телефона: <b>${user.phoneNumber}</b>
        <br>
        <br>

        <a class="btn btn-dark mt-3 mb-3" style="text-align: center; width: 100%"
           data-toggle="collapse" href="#collapseExample" role="button"
           aria-expanded="true" aria-controls="collapseExample">
            Изменить профиль
        </a>
        <div class="collapse mb-3" id="collapseExample" style="">
            <form action="/user/edit-profile" method="post">
                <div class="form-group">
                    <label for="name">Имя пользователя</label>
                    <input type="text" class="form-control" value="${user.name}" required id="name" name="name">
                </div>
                <div class="form-group">
                    <label for="email">Электронная почта</label>
                    <input type="email" class="form-control" value="${user.email}" id="email" required name="email">
                </div>
                <div class="form-group">
                    <label for="phoneNumber">Номер телефона</label>
                    <input type="text" class="form-control" value="${user.phoneNumber}" id="phoneNumber" required name="phoneNumber">
                </div>
                <div class="form-group">
                    <label for="password">Пароль</label>
                    <input type="password" class="form-control" id="password" required name="password">
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button type="submit" class="btn btn-primary">Сохранить изменения</button>
            </form>
        </div>

        <#if user.isAdmin()>
            <a class="btn btn-dark mb-3" style="text-align: center; width: 100%"
               href="/admin/panel">
                Панель администратора
            </a><br>
        <#elseif user.isSeller()>
        <a class="btn btn-dark mb-3" style="text-align: center; width: 100%"
           href="/my/tours">
            Турниры организации
        </a>
        <#else>
            <a class="btn btn-dark mb-3" style="text-align: center; width: 100%" href="/user/my/favorites">
                Избранное
            </a>
            <a class="btn btn-dark mb-3" style="text-align: center; width: 100%" href="/user/my/attended-tours">
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