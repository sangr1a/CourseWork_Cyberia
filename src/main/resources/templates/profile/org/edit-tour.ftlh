<#import "../../blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Редактирование турнира</h1>
    <hr>
    <form action="/tour/edit-tour/${tour.id}" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="title">Название турнира</label>
            <input type="text" required class="form-control" id="title" name="title" value="${tour.title}">
        </div>
        <div class="form-group">
            <label for="searchGame">Игра</label>
            <select id="searchGame" required name="game" class="form-control">
                <option value="">Игра не выбрана</option>
                <option value="Valorant">Valorant</option>
                <option value="League Of Legends">League Of Legends</option>
                <option value="CS2">CS2</option>
                <option value="DOTA 2">DOTA 2</option>
            </select>
        </div>
        <div class="form-group">
            <label for="city">Город</label>
            <input type="text" required class="form-control" id="city" name="city" value="${tour.city}">
        </div>
        <div class="form-group">
            <label for="address">Адрес турнира</label>
            <textarea required class="form-control" id="address" name="address">${tour.address}</textarea>
        </div>
        <div class="form-group">
            <label for="description">Описание турнира</label>
            <textarea required class="form-control" id="description" name="description">${tour.description}</textarea>
        </div>
        <div class="form-group">
            <label for="availablePasses">Лимит посещения (0 для неограниченного посещения)</label>
            <input type="number" class="form-control" id="availablePasses" name="availablePasses" min="0">
        </div>

        <input type="hidden" name="id" value="${tour.id}"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>

        <button type="submit" style="width: 100%" class="btn btn-dark">Сохранить изменения</button>
    </form>
</@t.template>