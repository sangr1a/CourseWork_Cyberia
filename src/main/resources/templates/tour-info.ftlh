<#import "blocks/template.ftlh" as t>
<@t.template user>
    <hr>
    <p><h1 style="text-align: center">${tour.title} | ${tour.game}</h1></p>
    <p style="text-align: center">${tour.description}</p>
    <p style="text-align: center">Номер организации: <b>${authorTour.phoneNumber}</b></p>

    <hr>
    <p style="text-align: center">
        <a href="/user/${authorTour.id}">
            <img src="/static/images/avatar.png" alt="mdo" width="32" height="32" class="rounded-circle"> ${authorTour.name}
        </a>
    </p>

</@t.template>