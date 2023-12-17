<#import "../../blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Ваши турниры</h1>
    <hr>
    <a class="btn btn-dark mt-3 mb-3" style="text-align: center; width: 100%"
       data-toggle="collapse" href="#collapseExample" role="button"
       aria-expanded="true" aria-controls="collapseExample">
        Добавить новый турнир
    </a>
    <div class="collapse mb-3" id="collapseExample" style="">
        <form action="/tour/create" method="post" enctype="multipart/form-data">
<#--            title-->
            <div class="form-group">
                <label for="tourName">Название турнира</label>
                <input type="text" required class="form-control"
                       id="tourName" name="title">
            </div>
<#--            game-->
            <div class="form-group">
                <label for="gameId">Дисциплина</label>
                <select id="gameId" name="gameId" class="form-control">
                    <option value="">Игра не выбрана</option>
                    <#list games as game>
                        <option value="${game.id}">${game.name}</option>
                    </#list>
                </select>
            </div>
<#--            numberOfPlayers-->
            <div class="form-group">
                <label for="numberOfPlayers">Лимит участников (0 для неограниченного посещения)</label>
                <input type="number" required class="form-control" id="numberOfPlayers" name="numberOfPlayers" min="0">
            </div>
<#--            format-->
            <div class="form-group">
                <label for="format">Формат участия</label>
                <input type="text" required class="form-control"
                       id="format" name="format">
            </div>
<#--            formatType-->
            <div class="form-group">
                <label for="formatType">Формат проведения</label>
                <input type="text" required class="form-control"
                       id="formatType" name="formatType">
            </div>
<#--            isLan-->
            <div class="form-group">
                <label for="isLan">Формат проведения</label>
                <select id="isLan" name="isLan" required class="form-control">
                    <option value="true">Online</option>
                    <option value="false">Offline (LAN)</option>
                </select>
            </div>
            <div class="form-group">
                <label for="prize">Приз</label>
                <input type="text" required class="form-control"
                       id="prize" name="prize">
            </div>
            <div class="form-group">
                <label for="city">Город</label>
                <input type="text" required class="form-control"
                       id="city" name="city">
            </div>
            <div class="form-group">
                <label for="address">Адрес турнира</label>
                <textarea required class="form-control" id="address" name="address"></textarea>
            </div>
            <div class="form-group">
                <label for="description">Описание турнира</label>
                <textarea required class="form-control" id="description" name="description"></textarea>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>

            <button type="submit" style="width: 100%" class="btn btn-dark">Добавить</button>
        </form>
    </div>
    <div class="card-columns">
        <#list tours as tour>
            <a class="tour-link">
                <div class="card text-white bg-dark my-3 tour">
                    <div class="card-top text-center">
                    </div>
                    <div class="card-body">
                        <a ></a>
                        <h5 class="card-title"><a href="/tour/${tour.id}" style="color: white; text-decoration: none">${tour.title}</a></h5>
                        <p class="card-text">${tour.game.name}</p>
                    </div>
                    <div class="card-footer text-center">
                        <form action="/tour/delete/${tour.id}" method="post" class="d-inline">
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                            <button type="submit" class="btn btn-danger m-2">
                                Удалить
                            </button>
                        </form>
                        <a href="/tour/edit-tour/${tour.id}" class="btn btn-warning m-2">
                            Изменить
                        </a>
                    </div>
                </div>
            </a>
        <#else>
            <h1 style="text-align: center">Не найдено</h1>
        </#list>
    </div>
</@t.template>