<#import "../../blocks/template.ftlh" as t>
<@t.template user>
    <h1>Tournament Participants</h1>
    <h2>${tour.title}</h2>
    <ul>
        <#list participants as participant>
            <li>${participant.name} - ${participant.email}
                <form action="/tour/remove-participant/${tour.id}/${participant.id}" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <button type="submit">Remove</button>
                </form>
            </li>
        </#list>
    </ul>
</@t.template>