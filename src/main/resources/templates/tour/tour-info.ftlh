<#import "../blocks/template.ftlh" as t>
<@t.template user>
    <hr>
    <div class="tournament-card card border-primary mb-3">
        <h1 class="card-header tournament-title">${tour.title} | ${tourGame.name}</h1>
        <div class="card-body">
            <p class="card-text tournament-description">${tour.description}</p>
            <p class="card-text tournament-type"><#if tour.isLan>LAN<#else>Online</#if></p>
            <p class="card-text tournament-format">Формат проведения: <b>${tour.format}</b></p>
            <p class="card-text tournament-format-type">Форматы этапов: <b>${tour.formatType}</b></p>
            <p class="card-text tournament-prize">Призовой фонд: <b>${tour.prize}</b></p>
            <p class="card-text tournament-attendees">Количество участников: ${tour.attendees?size}</p>
            <p class="card-text tournament-limit">Лимит участников: <#if tour.getNumberOfPlayers() != 0>${tour.getNumberOfPlayers()}<#else> не ограничен!</#if></p>

            <p class="card-text tournament-organization-number">Номер организации: <b>${authorTour.phoneNumber}</b></p>
            <hr>
            <p class="card-text author-info">
                <a href="/user/${authorTour.id}" style="text-decoration: none; color: black">
                    <img src="/static/images/userImages/<#list authorTour.roles as role>${role}</#list>.png" alt=" " width="32" height="32" class="rounded-circle"> ${authorTour.name}
                </a>
            </p>
            <#if !user.isOrg() && !user.isAdmin() && user.attendedTours?seq_contains(tour)>
            <div class="alert alert-success alert-dismissable">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">
                    ×
                </button>
                <h4>
                    Вы зарегистрированы!
                </h4> <strong>${user.name}</strong>, для дальнейшей коммунации с организаторами перейдите в <a href="#" class="alert-link">Discord сообщества</a>
            </div>
             </#if>

            <div class="tournament-actions" style="align-items: center">
                <#if !user.isOrg() && !user.isAdmin()>
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
                        <form action="/tour/remove-from-favorites/${tour.id}" method="post">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                            <button type="submit" class="btn btn-warning">Убрать из избранного</button>
                        </form>
                    </#if>
                <#else>
                    <form action="/tour/participants/${tour.id}" method="get">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <button type="submit" class="btn btn-success">Участники турнира</button>
                    </form>
                </#if>
            </div>
        </div>
    </div>
</@t.template>