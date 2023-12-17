<#import "../../blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Избранное</h1>

    <div class="card-columns">
        <#if favoriteTours?size != 0>
            <#list favoriteTours as tour>
                <a href="/tour/${tour.id}" class="tour-link">
                    <div class="card text-white bg-dark my-3 tour">
                        <div class="card-body">
                            <h5 class="card-title">${tour.title}</h5>
                            <p class="card-text">${tour.game}</p>
                        </div>
                        <div class="card-footer">
                            ${tour.city}
                        </div>
                    </div>
                </a>
            </#list>
        <#else>
            <h4 style="text-align: center">Нет добавленных в избранное турниров</h4>
        </#if>
    </div>
</@t.template>