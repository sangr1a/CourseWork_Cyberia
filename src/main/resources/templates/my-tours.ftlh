<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Турниры организации</h1>
    <hr>
    <a class="btn btn-dark mt-3 mb-3" style="text-align: center; width: 100%"
       data-toggle="collapse" href="#collapseExample" role="button"
       aria-expanded="true" aria-controls="collapseExample">
        Добавить новый турнир
    </a>
    <div class="collapse mb-3" id="collapseExample" style="">
        <tour action="/tour/create" method="post" enctype="multipart/tour-data">
            <div class="tour-group">
                <label for="productName">Название турнира</label>
                <input type="text" required class="tour-control"
                       id="productName" name="title">
            </div>
            <div class="tour-group">
                <label for="searchGame">Игра</label>
                <select id="searchGame" required name="game" class="tour-control">
                    <option value="">Игра не выбрана</option>
                    <option value="Valorant">Valorant</option>
                    <option value="League Of Legends">League Of Legends</option>
                    <option value="CS2">CS2</option>
                    <option value="DOTA 2">DOTA 2</option>
                </select>
            </div>
            <div class="tour-group">
                <label for="city">Город</label>
                <input type="text" required class="tour-control"
                       id="city" name="city">
            </div>
            <div class="tour-group">
                <label for="description">Описание турнира</label>
                <textarea required class="tour-control" id="description" name="description"></textarea>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>

            <button type="submit" style="width: 100%" class="btn btn-dark">Добавить</button>
        </tour>
    </div>
    <div class="card-columns">
        <#list tours as tour>
            <a href="/tour/${tour.id}" class="tour-link">
                <div class="card text-white bg-dark my-3 tour">
                    <div class="card-top">
                        <tour action="/tour/delete/${tour.id}" method="post">
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                            <button type="submit" class="btn btn-danger m-3">
                                Удалить
                            </button>
                        </tour>
                    </div>
                    <div class="card-body">
                        <h5 class="card-title">${tour.title}</h5>
                        <p class="card-text">${tour.game}</p>
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