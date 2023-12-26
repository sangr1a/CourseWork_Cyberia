<#import "../blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Регистрация</h1>
    <hr>
    <form action="/auth/registration" method="post">
        <div class="form-group">
            <label for="name">Имя пользователя</label>
            <input type="text" class="form-control" required id="name" name="name">
        </div>
        <div class="form-group">
            <label for="email">Электронная почта</label>
            <input type="email" class="form-control" id="email" required name="email">
        </div>
        <div class="form-group">
            <label for="phoneNumber">Номер телефона</label>
            <input type="text" class="form-control" id="phoneNumber" required name="phoneNumber">
        </div>
        <div class="form-group">
            <label for="password">Пароль</label>
            <input type="password" class="form-control" id="password" required name="password">
        </div>
        <div class="form-check">
            <input type="checkbox" class="form-check-input" id="organizerCheckbox" name="organizerCheckbox">
            <label class="form-check-label" for="organizerCheckbox">Регистрация организатора</label>
        </div>
        <div class="form-group" id="legalAddressGroup" style="display:none;">
            <label for="legalAddress">"Юридический" адрес (учебное заведение)</label>
            <input type="text" class="form-control" id="legalAddress" name="legalAddress">
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" style="width: 100%" class="btn btn-dark">Зарегистрироваться</button>
    </form>
    <#if errorMessage??>
        <h2 style="color: red">${errorMessage}</h2>
    </#if>
    <script>
        document.getElementById('organizerCheckbox').addEventListener('change', function () {
            var legalAddressGroup = document.getElementById('legalAddressGroup');
            legalAddressGroup.style.display = this.checked ? 'block' : 'none';
        });
    </script>
</@t.template>