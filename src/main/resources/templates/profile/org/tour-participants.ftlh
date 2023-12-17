<#import "../../blocks/template.ftlh" as t>
<@t.template user>
    <h1>Участники ${tour.title}</h1>
    <table class="table table-hover table-dark">
        <tr>
            <th scope="col">Никнейм</th>
            <th scope="col">ФИО</th>
            <th scope="col">Шифр студенческого</th>
            <th scope="col">Email</th>
            <th scope="col">Номер телефона</th>
            <th scope="col">Discord</th>
            <th scope="col">VK</th>
            <th scope="col">Telegram</th>
            <th scope="col">Удаление</th>
            <th scope="col">Подробная информация</th>
        </tr>
        <#list participants as participant>
            <tr>
                <th>${participant.name}</th>
                <th></th>
                <th></th>
                <th>${participant.email}</th>
                <th>${participant.phoneNumber}</th>
                <th></th>
                <th></th>
                <th></th>
                <th>
                    <form action="/tour/remove-participant/${tour.id}/${participant.id}" method="post">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <button type="submit">Удалить</button>
                    </form>
                </th>
                <th><a href="/user/${user.id}">Подробная информация</a></th>
            </tr>
        <#else>
            <h3>Пользователей нет</h3>
        </#list>
    </table>
</@t.template>