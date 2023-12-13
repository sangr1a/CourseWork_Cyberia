<!DOCTYPE html>
<html lang="ru" xmlns:th="http://www.thymeleaf.org">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" th:href="@{/static/css/style.css}" />

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" th:href="@{https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css}"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn"
          crossorigin="anonymous">

    <!-- Favicon -->
    <link rel="icon" th:href="@{/static/images/favicon.ico}" type="image/x-icon">
    <link rel="shortcut icon" th:href="@{/static/images/favicon.ico}" type="image/x-icon">

    <title>Cyberia</title>
</head>
<body>
<nav class="navbar navbar-dark bg-dark">
    <div class="container-fluid">
        <a th:href="@{/}">
            <span class="navbar-brand mb-0 h1">Cyberia</span>
        </a>
        <div th:if="${user?.email}">
            <div class="dropdown text-end">
                <a th:href="@{/profile}">
                    <img src="/static/images/avatar.png" alt="mdo"
                         width="32" height="32" class="rounded-circle">
                </a>
            </div>
        </div>
        <div th:unless="${user?.email}">
            <button class="btn btn-light my-2 my-sm-0" type="button" onclick="window.location.href = '/login'">
                Войти
            </button>
        </div>
    </div>
</nav>
<div class="container mt-4" th:fragment="content">
    <div th:replace="${content :: content}"></div>
</div>

<script th:src="@{https://code.jquery.com/jquery-3.3.1.slim.min.js}"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script th:src="@{https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js}"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script th:src="@{https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js}"
        integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
        crossorigin="anonymous"></script>
</body>
</html>