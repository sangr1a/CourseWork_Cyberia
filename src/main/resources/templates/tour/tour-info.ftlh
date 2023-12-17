<#import "../blocks/template.ftlh" as t>
<@t.template user>
    <hr>
    <p><h1 style="text-align: center">${tour.title} | ${tourGame.name}</h1></p>

    <p style="text-align: center">${tour.description}</p>

    <p style="text-align: center">Номер организации: <b>${authorTour.phoneNumber}</b></p>


    <p style="text-align: center">Количество участников: ${tour.attendees?size}</p>
    <p style="text-align: center">Лимит участников: <#if tour.getNumberOfPlayers() != 0>${tour.getNumberOfPlayers()}<#else> не ограничен!</#if></p>

    <#if !user.attendedTours?seq_contains(tour)>
        <form action="/tour/attend/${tour.id}" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit" class="btn btn-primary">Участвовать</button>
        </form>
    <#else>
        <form action="/tour/cancel-attendance/${tour.id}" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit" class="btn btn-danger">Отменить участие</button>
        </form>
    </#if>

    <#if !user.favoriteTours?seq_contains(tour)>
        <form action="/tour/add-to-favorites/${tour.id}" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit" class="btn btn-success">Добавить в избранное</button>
        </form>
    <#else>
        <p style="text-align: center">
        <form action="/tour/remove-from-favorites/${tour.id}" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit" class="btn btn-warning">Убрать из избранного</button>
        </form>
        </p>
    </#if>

    <hr>
    <p style="text-align: center">
        <a href="/user/${authorTour.id}" style="text-decoration: none; color: black">
            <img src="/static/images/<#list authorTour.roles as role>${role}</#list>.png" alt=" " width="32" height="32" class="rounded-circle"> ${authorTour.name}
        </a>
    </p>

</@t.template>