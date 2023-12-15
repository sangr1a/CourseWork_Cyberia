<#import "../blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Поиск турниров</h1><hr>
    <form method="get" action="/">
        <div class="form-row">
            <div class="form-group col-md-2">
                <label for="searchGame">Игра</label>
                <select id="searchGame" name="game" class="form-control">
                    <option value="">Игра не выбрана</option>
                    <option value="Valorant">Valorant</option>
                    <option value="League Of Legends">LoL</option>
                    <option value="CS2">CS2</option>
                    <option value="DOTA 2">DOTA 2</option>
                </select>
            </div>
            <div class="form-group col-md-4">
                <label for="searchCity">Город</label>
                <input type="text" name="searchCity" class="form-control" id="searchCity" <#if searchCity??>value="${searchCity}"</#if>>
            </div>
            <div class="form-group col-md-6">
                <label for="searchWord">Поиск по названию</label>
                <input type="text" name="searchWord" <#if searchWord??>value="${searchWord}"</#if> class="form-control"
                       id="searchWord">
            </div>
            <button style="width: 100%" type="submit" class="btn btn-dark">Поиск</button>
        </div>
    </form>
    <hr>
    <div class="card-columns">
        <#list tours as tour>
            <a href="/tour/${tour.id}" class="tour-link">
                <div class="card text-white bg-dark my-4 tour">

                    <div class="card-body">
                        <h5 class="card-title">${tour.title}</h5>
                        <p class="card-text">${tour.game}</p>
                        <p class="card-text">> ${tour.registeredUsers?size}</p>
                    </div>
                    <div class="card-footer">
                        ${tour.city}
                    </div>
                </div>
            </a>
        <#else>
            <h1 style="text-align: center">Не найдено</h1>
        </#list>
    </div>
</@t.template>