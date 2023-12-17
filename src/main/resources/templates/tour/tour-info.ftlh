<#import "../blocks/template.ftlh" as t>
<@t.template user>
    <hr>
    <p><h1 style="text-align: center">${tour.title} | ${tour.game}</h1></p>
    <p style="text-align: center">${tour.description}</p>

    <p style="text-align: center">Номер организации: <b>${authorTour.phoneNumber}</b></p>


    <p style="text-align: center">Количество участников: ${tour.attendees?size}</p>
    <p style="text-align: center">Лимит участников: <#if tour.getNumberOfPlayers != 0>${tour.getNumberOfPlayers}<#else> не ограничен!</#if></p>

    <#if !user.attendedTours?seq_contains(tour)>
        <p style="text-align: center">
        <form action="/tour/attend/${tour.id}" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit" class="btn btn-primary">Пойти на тур</button>
        </form>
        </p>
    <#else>
        <p style="text-align: center">
        <form action="/tour/cancel-attendance/${tour.id}" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit" class="btn btn-danger">Отменить посещение</button>
        </form>
        </p>
    </#if>

    <#if !user.attendedTours?seq_contains(tour)>
        <p style="text-align: center">
            <form action="/tour/register/${tour.id}" method="post">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button type="submit" class="btn btn-primary">Register for Tournament</button>
            </form>
        </p>
    </#if>

    <hr>
    <p style="text-align: center">
        <a href="/user/${authorTour.id}">
            <img src="/static/images/${authorTour.avatarName}.png" alt="mdo" width="32" height="32" class="rounded-circle"> ${authorTour.name}
        </a>
    </p>

    <#if !user.favoriteTours?seq_contains(tour)>
        <p style="text-align: center">
        <form action="/tour/add-to-favorites/${tour.id}" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit" class="btn btn-success">Добавить в избранное</button>
        </form>
        </p>
    <#else>
        <p style="text-align: center">
        <form action="/tour/remove-from-favorites/${tour.id}" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit" class="btn btn-warning">Убрать из избранного</button>
        </form>
        </p>
    </#if>

</@t.template>